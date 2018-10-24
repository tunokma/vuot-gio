/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.NCKHRepositoryCustom;
import com.kma.quanlygiangday.model.TbdNckh;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class NCKHRepositoryImpl implements NCKHRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public int deleteNCKH(Long id) {
        Query query = session.createQuery("UPDATE TbdNckh t SET t.isDeleted = 1 WHERE t.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public List<TbdNckh> findByObjectId(Long objectId, String namHoc) {
        Query query = session.createQuery("SELECT t FROM TbdNckh t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        return query.getResultList();
    }

}
