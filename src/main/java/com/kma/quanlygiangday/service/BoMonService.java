/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.BoMon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tuno
 */
public interface BoMonService {
    
    BoMon save(BoMon boMon);
    
    Boolean delete(Long id);
    
    BoMon update(BoMon boMon);
    
    BoMon findById(Long id);
    
    List<BoMon> findAll();
    
    List<BoMon> search(String string);
    
    Map<Long,String> getTenKhoaBy();
    
    Boolean findByIdKhoa(Long idKhoa);
    
    List<BoMon> getListByIdKhoa(Long idKhoa);
}
