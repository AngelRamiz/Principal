/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 *
 * @author amoralesr16
 */
@Component
public class ColaConsumer {

    @RabbitListener(queues = "colatareas")
    public void recibirMensaje(String mensaje) {
        System.out.println("RabbitMQ = Mensaje recibido: " + mensaje);
        
      
    }
}