/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;

import java.util.Optional;
import miumg.edu.gt.persistencia.entidad.Tarea;
import miumg.edu.gt.persistencia.entidad.Usuario;
import miumg.edu.gt.persistencia.repositorio.TareaRepositorio;
import miumg.edu.gt.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import miumg.edu.gt.estructuras.lista.Lista;
import miumg.edu.gt.estructuras.pila.Pila;
import miumg.edu.gt.estructuras.cola.Cola;
import miumg.edu.gt.estructuras.arbol.Arbol;
import miumg.edu.gt.estructuras.arbol.NodoArbol;

/**
 *
 * @author amoralesr16
 */
@Service
public class TareaServicio {

    private final TareaRepositorio tareaRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final ColaProducer colaProducer;
    private final HistorialServicio historial;
    private final LogServicio log;

    private final Pila<Tarea> pila = new Pila<>();
    private final Arbol<Tarea> arbol = new Arbol<>();
    private final Cola<Tarea> cola = new Cola<>();

    public TareaServicio(
        TareaRepositorio tareaRepo,
        UsuarioRepositorio usuarioRepo,
        ColaProducer colaProducer,
        HistorialServicio historial,
        LogServicio log
    ) {
        this.tareaRepo = tareaRepo;
        this.usuarioRepo = usuarioRepo;
        this.colaProducer = colaProducer;
        this.historial = historial;
        this.log = log;
    }

    public Tarea guardar(Tarea tarea, Long idUsuario) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        if (usuario != null) {
            tarea.setUsuario(usuario);
            Tarea nueva = tareaRepo.save(tarea);

            if (arbol.getRaiz() == null) {
                arbol.setRaiz(new NodoArbol<>(nueva));
            }

            colaProducer.enviarMensaje("Nueva tarea creada: " + nueva.getTitulo());
            historial.registrarAccion("Tarea creada: " + nueva.getTitulo(), idUsuario);
            log.registrar("Se creo la tarea: " + nueva.getTitulo(), usuario.getNombre(), "TareaServicio");
            return nueva;
        }
        return null;
    }

    public void agregarSubtarea(Tarea tarea, Long idPadre) {
        if (arbol.getRaiz() == null) return;

        NodoArbol<Tarea> padre = buscar(arbol.getRaiz(), idPadre);
        if (padre != null) {
            padre.agregarHijo(new NodoArbol<>(tarea));
        }
    }

    private NodoArbol<Tarea> buscar(NodoArbol<Tarea> nodo, Long id) {
        if (nodo == null || nodo.getDato() == null) return null;
        if (nodo.getDato().getId().equals(id)) return nodo;

        NodoArbol<Tarea> hijo = nodo.getPrimerHijo();
        while (hijo != null) {
            NodoArbol<Tarea> encontrado = buscar(hijo, id);
            if (encontrado != null) return encontrado;
            hijo = hijo.getSiguienteHermano();
        }
        return null;
    }

    private Tarea copiar(Tarea t) {
        Tarea copia = new Tarea();
        copia.setId(t.getId());
        copia.setTitulo(t.getTitulo());
        copia.setDescripcion(t.getDescripcion());
        copia.setEstado(t.getEstado());
        copia.setPrioridad(t.getPrioridad());
        copia.setTipo(t.getTipo());
        copia.setUsuario(t.getUsuario());
        return copia;
    }

    public String mostrarArbol() {
        StringBuilder sb = new StringBuilder();
        NodoArbol<Tarea> raiz = arbol.getRaiz();
        if (raiz == null || raiz.getDato() == null) {
            return "Arbol vacio no hay tareas registradas.";
        }
        imprimir(raiz, "", sb);
        return sb.toString();
    }

    private void imprimir(NodoArbol<Tarea> nodo, String prefijo, StringBuilder sb) {
        if (nodo == null || nodo.getDato() == null) return;

        sb.append(prefijo)
          .append("* ")
          .append(nodo.getDato().getTitulo())
          .append("\n");

        NodoArbol<Tarea> hijo = nodo.getPrimerHijo();
        while (hijo != null) {
            imprimir(hijo, prefijo + "   ", sb);
            hijo = hijo.getSiguienteHermano();
        }
    }

    public boolean eliminar(Long id) {
        Optional<Tarea> opt = tareaRepo.findById(id);
        if (opt.isPresent()) {
            Tarea tarea = opt.get();
            pila.apilar(tarea);
            tareaRepo.deleteById(id);

            if (arbol.getRaiz() != null && arbol.getRaiz().getDato().getId().equals(id)) {
                arbol.setRaiz(null);
            } else {
                arbol.eliminarNodo(tarea);
            }

            historial.registrarAccion("Tarea eliminada: " + tarea.getTitulo(), tarea.getUsuario().getId());
            log.registrar("Se elimino la tarea: " + tarea.getTitulo(), tarea.getUsuario().getNombre(), "TareaServicio");
            return true;
        }
        return false;
    }

    public Tarea deshacer() {
        if (!pila.estaVacia()) {
            Tarea tarea = pila.desapilar();
            Tarea restaurada = tareaRepo.save(tarea);

            if (arbol.getRaiz() == null) {
                arbol.setRaiz(new NodoArbol<>(restaurada));
            }

            historial.registrarAccion("Tarea restaurada: " + tarea.getTitulo(), tarea.getUsuario().getId());
            log.registrar("Se restauro la tarea: " + tarea.getTitulo(), tarea.getUsuario().getNombre(), "TareaServicio");
            return restaurada;
        }
        return null;
    }

    public void programar(Tarea tarea, Long idUsuario) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        if (usuario != null) {
            tarea.setUsuario(usuario);
            tareaRepo.save(tarea);
            cola.encolar(copiar(tarea));
            historial.registrarAccion("Tarea programada: " + tarea.getTitulo(), idUsuario);
            log.registrar("Se programo la tarea: " + tarea.getTitulo(), usuario.getNombre(), "TareaServicio");
        }
    }

    public Tarea siguienteTarea() {
        return cola.desencolar();
    }

    public Tarea actualizar(Long id, Tarea nueva) {
        Optional<Tarea> opt = tareaRepo.findById(id);
        if (opt.isPresent()) {
            Tarea t = opt.get();
            t.setTitulo(nueva.getTitulo());
            t.setDescripcion(nueva.getDescripcion());
            t.setEstado(nueva.getEstado());
            t.setPrioridad(nueva.getPrioridad());
            t.setTipo(nueva.getTipo());

            Tarea actualizada = tareaRepo.save(t);
            historial.registrarAccion("Tarea actualizada: " + actualizada.getTitulo(), actualizada.getUsuario().getId());
            log.registrar("Se actualizo la tarea: " + actualizada.getTitulo(), actualizada.getUsuario().getNombre(), "TareaServicio");
            return actualizada;
        }
        return null;
    }

    public Lista<Tarea> listar() {
        Lista<Tarea> lista = new Lista<>();
        for (Tarea t : tareaRepo.findAll()) {
            lista.agregar(t);
        }
        return lista;
    }
}