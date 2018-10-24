/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.repository.custom.MienGiamRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class MienGiamRepositoryImpl implements MienGiamRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public List<TbdMienGiam> findByfindByNameContaining(String string) {
        Query query = session.createQuery("SELECT k FROM TbdMienGiam k WHERE k.doiTuong LIKE '%" + string + "%'");
        return query.getResultList();
    }

}
