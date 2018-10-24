/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.TbdNckh;
import com.kma.quanlygiangday.repository.NCKHRepository;
import com.kma.quanlygiangday.service.NCKHService;
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
public class NCKHServiceImpl implements NCKHService {

    @Autowired
    NCKHRepository nCKHRepository;

    @Override
    public TbdNckh save(TbdNckh nckh) {
        return nCKHRepository.save(nckh);
    }

    @Override
    public Boolean delete(Long id) {
        int isDeleted = 0;
        if (nCKHRepository.existsById(id)) {
            isDeleted = nCKHRepository.deleteNCKH(id);
        }
        return isDeleted != 0;
    }

    @Override
    public TbdNckh update(TbdNckh nckh) {
        return nCKHRepository.save(nckh);
    }

    @Override
    public TbdNckh findById(Long id) {
        return nCKHRepository.findById(id).get();
    }

    @Override
    public List<TbdNckh> findByObjectId(Long id, String namHoc) {
        if (nCKHRepository.findByObjectId(id, namHoc).isEmpty()) {
            return null;
        }
        return nCKHRepository.findByObjectId(id, namHoc);
    }

    @Override
    public List<TbdNckh> findAll() {
        return (List<TbdNckh>) nCKHRepository.findAll();
    }

}
