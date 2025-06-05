/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.principal.log;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 *
 * @author amoralesr16
 */
public interface LogRepositorio extends MongoRepository<LogEvento, String> {
}
