/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;
import miumg.edu.gt.persistencia.entidad.Usuario;
import miumg.edu.gt.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import miumg.edu.gt.estructuras.lista.Lista;
/**
 *
 * @author amoralesr16
 */
@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Lista<Usuario> listarUsuarios() {
        Lista<Usuario> listaUsuarios = new Lista<>();

        for (Usuario usuario : usuarioRepositorio.findAll()) {
            listaUsuarios.agregar(usuario);
        }

        return listaUsuarios;
    }
}