/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 *
 * @author amoralesr16
 */
@SpringBootApplication
// componentes de los paquetes 
@ComponentScan(basePackages = {
    "miumg.edu.gt.principal",          
    "miumg.edu.gt.persistencia",       
    "umg.edu.gt.estructuras"      
})
//entidades JPA
@EntityScan(basePackages = "miumg.edu.gt.persistencia.entidad")
// repositorios JPA
@EnableJpaRepositories(basePackages = "miumg.edu.gt.persistencia.repositorio")
public class Principal {

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
        System.out.println(" Aplicacion de SISTEMA RESERVAS CON PRIORIDAD iniciada.");
    }
}