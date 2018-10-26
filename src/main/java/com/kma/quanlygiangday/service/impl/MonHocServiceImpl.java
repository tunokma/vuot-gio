/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.MonHoc;
import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.repository.MonHocRepository;
import com.kma.quanlygiangday.repository.KhoaRepository;
import com.kma.quanlygiangday.service.MonHocService;
import com.kma.quanlygiangday.viewmodel.MonHocVM;
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
public class MonHocServiceImpl implements MonHocService {

    @Autowired
    MonHocRepository monHocRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Override
    public MonHoc save(MonHoc monHoc) {
        return monHocRepository.save(monHoc);
    }

    @Override
    public Boolean delete(Long id) {
        if (monHocRepository.existsById(id)) {
            monHocRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public MonHoc update(MonHoc monHoc) {
        return monHocRepository.save(monHoc);
    }

    @Override
    public MonHoc findById(Long id) {
        return monHocRepository.findById(id).get();
    }

    @Override
    public List<MonHocVM> findAll() {
        List<MonHocVM> lstReturn = new ArrayList<>();
        List<MonHoc> lst = (List<MonHoc>) monHocRepository.findAll();
        List<Khoa> lstKhoa = (List<Khoa>) khoaRepository.findAll();
        if (lst != null) {
            for (MonHoc monHoc : lst) {
                MonHocVM temp = new MonHocVM(monHoc);
                if (lstKhoa != null) {
                    for (Khoa khoa : lstKhoa) {
                        if (Objects.equals(monHoc.getIdKhoa(), khoa.getId())) {
                            temp.setTenKhoa(khoa.getTenKhoa());
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
    public List<MonHocVM> search(String string) {
        List<MonHocVM> lstReturn = new ArrayList<>();
        List<MonHoc> lst = (List<MonHoc>) monHocRepository.findByfindByNameContaining(string);
        List<Khoa> lstKhoa = (List<Khoa>) khoaRepository.findAll();
        if (lst != null) {
            for (MonHoc monHoc : lst) {
                MonHocVM temp = new MonHocVM(monHoc);
                if (lstKhoa != null) {
                    for (Khoa khoa : lstKhoa) {
                        if (Objects.equals(monHoc.getIdKhoa(), khoa.getId())) {
                            temp.setTenKhoa(khoa.getTenKhoa());
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
    public Map<Long, String> getTenKhoaBy() {
        List<Khoa> lst = (List<Khoa>) khoaRepository.findAll();
        Map<Long, String> map = new HashMap<>();
        lst.forEach((khoa) -> {
            map.put(khoa.getId(), khoa.getTenKhoa());
        });
        return map;
    }
    @Override
    public MonHoc findByTenMon(String tenMon) {
        return monHocRepository.findByTenMon(tenMon);
    }

    @Override
    public List<MonHoc> findByIdKhoa(Long idKhoa) {
        return monHocRepository.findByIdKhoa(idKhoa);
    }

}
