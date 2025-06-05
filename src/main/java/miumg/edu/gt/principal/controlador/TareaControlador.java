/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package miumg.edu.gt.principal.controlador;

import miumg.edu.gt.persistencia.entidad.Tarea;
import miumg.edu.gt.principal.servicio.TareaServicio;
import miumg.edu.gt.estructuras.lista.Lista;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author amoralesr16
 */

@RestController
@RequestMapping("/api/tareas")
public class TareaControlador {

    private final TareaServicio tareaServicio;

    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea, @RequestParam Long idUsuario) {
        Tarea nueva = tareaServicio.guardar(tarea, idUsuario);
        return (nueva != null) ?
            new ResponseEntity<>(nueva, HttpStatus.CREATED) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<Tarea[]> listarTareas() {
        Lista<Tarea> tareas = tareaServicio.listar();
        Tarea[] arreglo = new Tarea[tareas.tamanio()];
        for (int i = 0; i < tareas.tamanio(); i++) {
            arreglo[i] = tareas.obtener(i);
        }
        return new ResponseEntity<>(arreglo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long id) {
        boolean eliminada = tareaServicio.eliminar(id);
        if (eliminada) {
            return new ResponseEntity<>("Tarea eliminada correctamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("No se encontro la tarea con el ID proporcionado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deshacer")
    public ResponseEntity<String> deshacerTarea() {
        Tarea restaurada = tareaServicio.deshacer();
        return (restaurada != null) ?
            new ResponseEntity<>("Tarea restaurada correctamente", HttpStatus.OK) :
            new ResponseEntity<>("No hay tareas para restaurar", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/subtarea")
    public ResponseEntity<String> agregarSubtarea(
        @RequestBody Tarea tarea,
        @RequestParam Long idPadre,
        @RequestParam Long idUsuario) {
        
        Tarea tareaConId = tareaServicio.guardar(tarea, idUsuario); 
        tareaServicio.agregarSubtarea(tareaConId, idPadre);  
        return new ResponseEntity<>("Subtarea agregada correctamente al arbol de tareas", HttpStatus.CREATED);
    }

    @GetMapping("/arbol")
    public ResponseEntity<String> verArbolTareas() {
        String arbolTexto = tareaServicio.mostrarArbol();
        return new ResponseEntity<>(arbolTexto, HttpStatus.OK);
    }

    @PostMapping("/programar")
    public ResponseEntity<String> programarTarea(
        @RequestBody Tarea tarea,
        @RequestParam Long idUsuario
    ) {
        tareaServicio.programar(tarea, idUsuario);
        return new ResponseEntity<>("Tarea programada correctamente en la cola", HttpStatus.CREATED);
    }

    @GetMapping("/programada/siguiente")
    public ResponseEntity<?> obtenerSiguienteTarea() {
        Tarea siguiente = tareaServicio.siguienteTarea();
        return (siguiente != null) ?
            ResponseEntity.ok(siguiente) :
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay tareas programadas en la cola");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea actualizada = tareaServicio.actualizar(id, tarea);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro la tarea a actualizar", HttpStatus.NOT_FOUND);
        }
    }
}