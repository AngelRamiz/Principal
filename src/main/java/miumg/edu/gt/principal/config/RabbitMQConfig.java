/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author amoralesr16
 */
@Configuration
public class RabbitMQConfig {

    public static final String COLA_TAREAS = "colatareas";

    @Bean
    public Queue colaTareas() {
        return new Queue(COLA_TAREAS, false);
    }
}
