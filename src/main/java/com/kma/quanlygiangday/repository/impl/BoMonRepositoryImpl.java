/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.BoMonRepositoryCustom;
import com.kma.quanlygiangday.model.BoMon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tuno
 */
public class BoMonRepositoryImpl implements BoMonRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public List<BoMon> findByfindByNameContaining(String string) {
        return session.createQuery("SELECT k FROM BoMon k WHERE k.tenBoMon LIKE '%" + string + "%'")
                .getResultList();
    }

}
