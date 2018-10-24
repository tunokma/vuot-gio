/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.KhoaRepositoryCustom;
import com.kma.quanlygiangday.model.Khoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class KhoaRepositoryImpl implements KhoaRepositoryCustom{

    @PersistenceContext
    private EntityManager session;
    
    @Override
    public List<Khoa> findByfindByNameContaining(String q) {
        Query query =session.createQuery("SELECT k FROM Khoa k WHERE k.tenKhoa LIKE '%" +q+ "%'");
        return query.getResultList();
    }
    
}
