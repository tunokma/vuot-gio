/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.TongHop;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface TongHopService {

    TongHop save(TongHop tongHop);

    Boolean delete(Long id);

    TongHop update(TongHop tongHop);

    TongHop findById(Long id);

    TongHop findByObjectId(Long objectId, String namHoc);

    List<TongHop> search(TongHop tongHop);

    List<TongHop> findAll();

    int deleteFromObjectId(Long objectId);
    
}
