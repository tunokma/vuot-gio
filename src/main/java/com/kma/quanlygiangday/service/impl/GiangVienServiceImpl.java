/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.BoMon;
import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.repository.BoMonRepository;
import com.kma.quanlygiangday.repository.GiangVienRepository;
import com.kma.quanlygiangday.repository.TongHopRepository;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.viewmodel.GiangVienVM;
import java.util.ArrayList;
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
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    GiangVienRepository giangVienRepository;

    @Autowired
    BoMonRepository boMonRepository;

    @Autowired
    TongHopRepository tongHopRepository;

    @Override
    public GiangVien save(GiangVien giangVien) {
        return giangVienRepository.save(giangVien);
    }

    @Override
    public Boolean delete(Long id) {
        if (giangVienRepository.existsById(id)) {
            tongHopRepository.deleteFromObjectId(id);
            giangVienRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public GiangVien update(GiangVien giangVien) {
        return giangVienRepository.save(giangVien);
    }

    @Override
    public GiangVien findById(Long id) {
        return giangVienRepository.findById(id).get();
    }

    @Override
    public List<GiangVienVM> findAll() {
        List<GiangVien> lst = (List<GiangVien>) giangVienRepository.findAllGV();
        List<GiangVienVM> lstReturn = new ArrayList<>();
        List<BoMon> lstBoMon = (List<BoMon>) boMonRepository.findAll();
        if (lst != null) {
            for (GiangVien giangVien : lst) {
                if (giangVien != null) {
                    GiangVienVM temp = new GiangVienVM(giangVien);
                    for (BoMon boMon : lstBoMon) {
                        if (Objects.equals(giangVien.getIdBoMon(), boMon.getId())) {
                            temp.setTenBoMon(boMon.getTenBoMon());
                            break;
                        }
                    }
                    lstReturn.add(temp);
                }
            }
        }
        return lstReturn;
    }

    @Override
    public List<GiangVienVM> search(String string) {
        List<GiangVien> lst = (List<GiangVien>) giangVienRepository.findByfindByNameContaining(string);
        List<GiangVienVM> lstReturn = new ArrayList<>();
        List<BoMon> lstBoMon = (List<BoMon>) boMonRepository.findAll();
        if (lst != null) {
            for (GiangVien giangVien : lst) {
                if (giangVien != null) {
                    GiangVienVM temp = new GiangVienVM(giangVien);
                    for (BoMon boMon : lstBoMon) {
                        if (Objects.equals(giangVien.getIdBoMon(), boMon.getId())) {
                            temp.setTenBoMon(boMon.getTenBoMon());
                            break;
                        }
                    }
                    lstReturn.add(temp);
                    break;
                }
            }
        }
        return lstReturn;
    }

    @Override
    public Map<Long, String> getBoMon() {
        List<BoMon> lst = (List<BoMon>) boMonRepository.findAll();
        Map<Long, String> map = new HashMap<>();
        lst.forEach((boMon) -> {
            map.put(boMon.getId(), boMon.getTenBoMon());
        });
        return map;
    }

    @Override
    public GiangVien findByUsername(String username) {
        return giangVienRepository.findByUsername(username);
    }

    @Override
    public Boolean isChuNhiemKhoa(Long idGiangVien) {
        return giangVienRepository.findChuNhiemKhoa(idGiangVien);
    }

    @Override
    public Boolean isChuNhiemBoMon(Long idGiangVien) {
        return giangVienRepository.findChuNhiemBoMon(idGiangVien);
    }

    @Override
    public List<GiangVien> findByIdBoMon(Long idBoMon) {
        return giangVienRepository.findByIdBoMon(idBoMon);
    }

}
