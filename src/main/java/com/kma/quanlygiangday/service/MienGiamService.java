/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.TbdMienGiam;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface MienGiamService {
    
     TbdMienGiam save(TbdMienGiam mienGiam);
    
    Boolean delete(Long id);
    
    TbdMienGiam update(TbdMienGiam mienGiam);
    
    TbdMienGiam findById(Long id);
    
    List<TbdMienGiam> findAll();
    
    List<TbdMienGiam> search(String string);
    
}
