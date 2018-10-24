/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.GiangVienRepositoryCustom;
import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.utils.Constants;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tuno
 */
public class GiangVienRepositoryImpl implements GiangVienRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public List<GiangVien> findByfindByNameContaining(String string) {
        return session.createQuery("SELECT k FROM GiangVien k WHERE k.hoTen LIKE '%" + string + "%'")
                .getResultList();
    }

    @Override
    public List<GiangVien> findAllGV() {
        return session.createQuery("SELECT k FROM GiangVien k WHERE k.role =:role ")
                .setParameter("role", 2l)
                .getResultList();
    }

}
