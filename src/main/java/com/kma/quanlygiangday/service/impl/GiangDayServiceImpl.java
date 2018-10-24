/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.repository.GiangDayRepository;
import com.kma.quanlygiangday.service.GiangDayService;
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
public class GiangDayServiceImpl implements GiangDayService {

    @Autowired
    GiangDayRepository giangDayRepository;

    @Override
    public TbdGiangDay save(TbdGiangDay giangDay) {
        return giangDayRepository.save(giangDay);
    }

    @Override
    public Boolean delete(Long id) {
        int isDeleted = 0;
        if (giangDayRepository.existsById(id)) {
            isDeleted = giangDayRepository.deleteGiangDay(id);
        }
        return isDeleted != 0;
    }

    @Override
    public TbdGiangDay update(TbdGiangDay giangDay) {
        return giangDayRepository.save(giangDay);
    }

    @Override
    public TbdGiangDay findById(Long id) {
        return giangDayRepository.findById(id).get();
    }

    @Override
    public List<TbdGiangDay> findByHKCN(String hkcn, String namHoc) {
        return giangDayRepository.findByHkCn(hkcn,namHoc);
    }

    @Override
    public List<TbdGiangDay> findAll() {
        return (List<TbdGiangDay>) giangDayRepository.findAll();
    }

    @Override
    public List<TbdGiangDay> findByObjectId(Long id, String namHoc) {
        return giangDayRepository.findByObjectId(id, namHoc);
    }

    @Override
    public Long getSoTietDaDayTheoHKCN(Long objectId, String hkcn, String namHoc) {
        return giangDayRepository.getSoTietDaDayTheoHKCN(objectId, hkcn, namHoc);
    }

    @Override
    public Long getSoTietDaDay(Long objectId, String namHoc) {
        return giangDayRepository.getSoTietDaDay(objectId, namHoc);
    }

}
