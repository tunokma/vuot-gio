/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.Khoa;
import java.util.List;

/**
 *
 * @author Tuno
 */

public interface KhoaService {
    
    Khoa save(Khoa khoa);
    
    Boolean delete(Long id);
    
    Khoa update(Khoa khoa);
    
    Khoa findById(Long id);
    
    List<Khoa> findAll();
    
    List<Khoa> search(String q);
}
