/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.viewmodel.GiangVienVM;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tuno
 */
public interface GiangVienService {
    
    GiangVien save(GiangVien giangVien);
    
    Boolean delete(Long id);
    
    GiangVien update(GiangVien giangVien);
    
    GiangVien findById(Long id);
    
    GiangVien findByUsername(String username);
    
    List<GiangVienVM> findAll();
    
    List<GiangVienVM> search(String string);
    
    Map<Long,String> getBoMon();
    
}
