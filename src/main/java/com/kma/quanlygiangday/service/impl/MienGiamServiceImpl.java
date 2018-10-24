/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service.impl;

import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.repository.MienGiamRepository;
import com.kma.quanlygiangday.service.MienGiamService;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tuno
 */
@Service
@Transactional
public class MienGiamServiceImpl implements MienGiamService {

    @Autowired
    MienGiamRepository mienGiamRepository;

    @Override
    public TbdMienGiam save(TbdMienGiam mienGiam) {
        mienGiam.setIsDeleted(0L);
        return mienGiamRepository.save(mienGiam);
    }

    @Override
    public Boolean delete(Long id) {
        if (mienGiamRepository.existsById(id)) {
            mienGiamRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TbdMienGiam update(TbdMienGiam mienGiam) {
        mienGiam.setIsDeleted(0L);
        return mienGiamRepository.save(mienGiam);
    }

    @Override
    public TbdMienGiam findById(Long id) {
        return mienGiamRepository.findById(id).get();
    }

    @Override
    public List<TbdMienGiam> findAll() {
        return (List<TbdMienGiam>) mienGiamRepository.findAll();
    }

    @Override
    public List<TbdMienGiam> search(String string) {
        return mienGiamRepository.findByfindByNameContaining(string);
    }

}
