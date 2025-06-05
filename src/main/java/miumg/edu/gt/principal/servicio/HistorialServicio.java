/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;
import miumg.edu.gt.persistencia.entidad.Historial;
import miumg.edu.gt.persistencia.entidad.Usuario;
import miumg.edu.gt.persistencia.repositorio.HistorialRepositorio;
import miumg.edu.gt.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import miumg.edu.gt.estructuras.lista.Lista;

import java.time.LocalDateTime;
/**
 *
 * @author amoralesr16
 */
@Service
public class HistorialServicio {

    private final HistorialRepositorio historialRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ColaProducer colaProducer;

    public HistorialServicio(
        HistorialRepositorio historialRepositorio,
        UsuarioRepositorio usuarioRepositorio,
        ColaProducer colaProducer,
        LogServicio logServicio
    ) {
        this.historialRepositorio = historialRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.colaProducer = colaProducer;
    }

    public Historial registrarAccion(String accion, Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
        if (usuario != null) {
            Historial historial = new Historial();
            historial.setAccion(accion);
            historial.setFecha(LocalDateTime.now());
            historial.setUsuario(usuario);
            Historial guardado = historialRepositorio.save(historial);

            colaProducer.enviarMensaje("La accion se registro: " + accion);

            return guardado;
        }
        return null;
    }

    public Lista<Historial> listarHistorial() {
        Lista<Historial> lista = new Lista<>();
        for (Historial h : historialRepositorio.findAll()) {
            lista.agregar(h);
        }
        return lista;
    }
}
