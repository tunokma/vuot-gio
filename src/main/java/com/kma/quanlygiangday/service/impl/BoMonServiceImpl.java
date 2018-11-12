/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.BoMon;
import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.repository.BoMonRepository;
import com.kma.quanlygiangday.repository.KhoaRepository;
import com.kma.quanlygiangday.service.BoMonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tuno
 */
@Service
@Transactional
public class BoMonServiceImpl implements BoMonService {

    @Autowired
    BoMonRepository boMonRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Override
    public BoMon save(BoMon boMon) {
        return boMonRepository.save(boMon);
    }

    @Override
    public Boolean delete(Long id) {
        if (boMonRepository.existsById(id)) {
            boMonRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public BoMon update(BoMon boMon) {
        return boMonRepository.save(boMon);
    }

    @Override
    public BoMon findById(Long id) {
        return boMonRepository.findById(id).get();
    }

    @Override
    public List<BoMon> findAll() {
        List<BoMon> lst = (List<BoMon>) boMonRepository.findAll();
        List<Khoa> lstKhoa = (List<Khoa>) khoaRepository.findAll();
        if (lst != null) {
            for (BoMon boMon : lst) {
                if (lstKhoa != null) {
                    for (Khoa khoa : lstKhoa) {
                        if (Objects.equals(boMon.getIdKhoa(), khoa.getId())) {
                            boMon.setTenKhoa(khoa.getTenKhoa());
                        }
                    }
                }
            }
        }

        return lst;
    }

    @Override
    public List<BoMon> search(String string) {
        return boMonRepository.findByfindByNameContaining(string);
    }

    @Override
    public Map<Long, String> getTenKhoaBy() {
        List<Khoa> lst = (List<Khoa>) khoaRepository.findAll();
        Map<Long, String> map = new HashMap<>();
        lst.forEach((khoa) -> {
            map.put(khoa.getId(), khoa.getTenKhoa());
        });
        return map;
    }

    @Override
    public Boolean findByIdKhoa(Long idKhoa) {
        List<BoMon> lst = boMonRepository.findByIdKhoa(idKhoa);
        return !(lst == null || lst.isEmpty());
    }

    @Override
    public List<BoMon> getListByIdKhoa(Long idKhoa) {
        return boMonRepository.findByIdKhoa(idKhoa);
    }

}
