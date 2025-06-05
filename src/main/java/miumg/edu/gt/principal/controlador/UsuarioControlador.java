/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.controlador;
import miumg.edu.gt.persistencia.entidad.Usuario;
import miumg.edu.gt.principal.servicio.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import miumg.edu.gt.estructuras.lista.Lista;

/**
 *
 * @author amoralesr16
 */

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioServicio.crearUsuario(usuario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Lista<Usuario>> listarUsuarios() {
        Lista<Usuario> usuarios = usuarioServicio.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
