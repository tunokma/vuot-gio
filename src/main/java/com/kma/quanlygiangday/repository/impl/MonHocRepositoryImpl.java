/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.MonHocRepositoryCustom;
import com.kma.quanlygiangday.model.MonHoc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tuno
 */
public class MonHocRepositoryImpl implements MonHocRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public List<MonHoc> findByfindByNameContaining(String string) {
        return session.createQuery("SELECT k FROM MonHoc k WHERE k.tenMon LIKE '%" + string + "%'")
                .getResultList();
    }

}
