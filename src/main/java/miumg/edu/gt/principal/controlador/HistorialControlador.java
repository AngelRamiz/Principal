/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package miumg.edu.gt.principal.controlador;

import miumg.edu.gt.persistencia.entidad.Historial;
import miumg.edu.gt.principal.servicio.HistorialServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import miumg.edu.gt.estructuras.lista.Lista;

/**
 *
 * @author amoralesr16
 */

@RestController
@RequestMapping("/api/historial")
public class HistorialControlador {

    private final HistorialServicio historialServicio;

    public HistorialControlador(HistorialServicio historialServicio) {
        this.historialServicio = historialServicio;
    }

    @PostMapping
    public ResponseEntity<Historial> registrar(@RequestParam Long idUsuario, @RequestParam String accion) {
        Historial registro = historialServicio.registrarAccion(accion, idUsuario);
        if (registro != null) {
            return new ResponseEntity<>(registro, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Historial[]> listar() {
        Lista<Historial> historial = historialServicio.listarHistorial();

        Historial[] arreglo = new Historial[historial.tamanio()];
        for (int i = 0; i < historial.tamanio(); i++) {
            arreglo[i] = historial.obtener(i);
        }

        return new ResponseEntity<>(arreglo, HttpStatus.OK);
    }
}
