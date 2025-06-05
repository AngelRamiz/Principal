/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;

import miumg.edu.gt.principal.log.LogEvento;
import miumg.edu.gt.principal.log.LogRepositorio;
import org.springframework.stereotype.Service;

/**
 *
 * @author amoralesr16
 */

@Service
public class LogServicio {

    private final LogRepositorio logRepositorio;

    public LogServicio(LogRepositorio logRepositorio) {
        this.logRepositorio = logRepositorio;
    }

    public void registrar(String mensaje, String usuario, String modulo) {
        LogEvento log = new LogEvento(mensaje, usuario, modulo);
        logRepositorio.save(log);
    }
}
