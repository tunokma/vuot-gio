/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.MonHoc;
import com.kma.quanlygiangday.viewmodel.MonHocVM;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tuno
 */
public interface MonHocService {
    
    MonHoc save(MonHoc monHoc);
    
    Boolean delete(Long id);
    
    MonHoc update(MonHoc monHoc);
    
    MonHoc findById(Long id);
    
    List<MonHocVM> findAll();
    
    List<MonHocVM> search(String string);
    
    Map<Long,String> getTenBoMonBy();
}
