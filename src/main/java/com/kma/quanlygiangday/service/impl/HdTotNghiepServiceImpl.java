/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import com.kma.quanlygiangday.repository.HdTotNghiepRepository;
import com.kma.quanlygiangday.service.HdTotNghiepService;
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
public class HdTotNghiepServiceImpl implements HdTotNghiepService {

    @Autowired
    HdTotNghiepRepository hdTotNghiepRepository;

    @Override
    public TbdHdTotNghiep save(TbdHdTotNghiep hdTotNghiep) {
        return hdTotNghiepRepository.save(hdTotNghiep);
    }

    @Override
    public Boolean delete(Long id) {
        int isDeleted = 0;
        if (hdTotNghiepRepository.existsById(id)) {
            isDeleted = hdTotNghiepRepository.deleteHdTN(id);
        }
        return isDeleted != 0;
    }

    @Override
    public TbdHdTotNghiep update(TbdHdTotNghiep hdTotNghiep) {
        return hdTotNghiepRepository.save(hdTotNghiep);
    }

    @Override
    public TbdHdTotNghiep findById(Long id) {
        return hdTotNghiepRepository.findById(id).get();
    }

    @Override
    public List<TbdHdTotNghiep> findByObjectId(Long objectId, String namHoc) {
        return hdTotNghiepRepository.findByObjectId(objectId, namHoc);
    }

    @Override
    public List<TbdHdTotNghiep> findAll() {
        return (List<TbdHdTotNghiep>) hdTotNghiepRepository.findAll();
    }

    @Override
    public Long getSoTietByObjectId(Long objectId, String namHoc) {
        return hdTotNghiepRepository.getSoTietByObjectId(objectId, namHoc);
    }

}
