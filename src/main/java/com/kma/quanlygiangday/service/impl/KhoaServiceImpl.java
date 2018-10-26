/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.repository.KhoaRepository;
import com.kma.quanlygiangday.service.KhoaService;
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
public class KhoaServiceImpl implements KhoaService {

    @Autowired
    KhoaRepository khoaRepository;
    
    @Override
    public Khoa save(Khoa khoa) {
        return khoaRepository.save(khoa);
    }

    @Override
    public Boolean delete(Long id) {
        if (khoaRepository.existsById(id)) {
            khoaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Khoa update(Khoa khoa) {
        return khoaRepository.save(khoa);
    }

    @Override
    public Khoa findById(Long id) {
        return khoaRepository.findById(id).get();
    }

    @Override
    public List<Khoa> findAll() {
        return (List<Khoa>) khoaRepository.findAll();
    }

    @Override
    public List<Khoa> search(String q) {
        return khoaRepository.findByfindByNameContaining(q);
    }

}
