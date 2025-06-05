/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
/**
 *
 * @author amoralesr16
 */
@Document(collection = "logs")
public class LogEvento {
    @Id
    private String id;
    private String mensaje;
    private LocalDateTime fecha;
    private String usuario;
    private String modulo;

    // constructor
    public LogEvento() {}

    public LogEvento(String mensaje, String usuario, String modulo) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
        this.modulo = modulo;
    }

    public String getId() { return id; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFecha() { return fecha; }
    public String getUsuario() { return usuario; }
    public String getModulo() { return modulo; }

    public void setId(String id) { this.id = id; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setModulo(String modulo) { this.modulo = modulo; }
}
