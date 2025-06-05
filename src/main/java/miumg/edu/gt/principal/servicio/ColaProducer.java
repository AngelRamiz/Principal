/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.servicio;
import miumg.edu.gt.principal.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
/**
 *
 * @author amoralesr16
 */
@Service
public class ColaProducer {

    private final RabbitTemplate rabbitTemplate;

    public ColaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(String mensaje) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.COLA_TAREAS, mensaje);
        System.out.println("Enviado a RabbitMQ: " + mensaje);
    }
}