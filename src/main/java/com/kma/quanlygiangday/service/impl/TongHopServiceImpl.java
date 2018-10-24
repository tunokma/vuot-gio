/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.TongHop;
import com.kma.quanlygiangday.repository.TongHopRepository;
import com.kma.quanlygiangday.service.TongHopService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tuno
 */
@Service
@Transactional
public class TongHopServiceImpl implements TongHopService {

    @Autowired
    TongHopRepository tongHopRepository;

    @Override
    public TongHop save(TongHop tongHop) {
        return tongHopRepository.save(tongHop);
    }

    @Override
    public Boolean delete(Long id) {
        if (tongHopRepository.existsById(id)) {
            tongHopRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TongHop update(TongHop tongHop) {
        return tongHopRepository.save(tongHop);
    }

    @Override
    public TongHop findById(Long id) {
        return tongHopRepository.findById(id).get();
    }

    @Override
    public TongHop findByObjectId(Long objectId, String namHoc) {
        return tongHopRepository.findByObjectId(objectId, namHoc);
    }

    @Override
    public List<TongHop> findAll() {
        return (List<TongHop>) tongHopRepository.findAll();
    }

    @Override
    public List<TongHop> search(TongHop tongHop) {
        return tongHopRepository.search(tongHop);
    }

}
