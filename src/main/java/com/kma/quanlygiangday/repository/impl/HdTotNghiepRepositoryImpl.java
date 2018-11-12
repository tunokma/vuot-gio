/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.HdTotNghiepRepositoryCustom;
import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class HdTotNghiepRepositoryImpl implements HdTotNghiepRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public int deleteHdTN(Long id) {
        Query query = session.createQuery("UPDATE TbdHdTotNghiep t SET t.isDeleted = 1 WHERE t.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public List<TbdHdTotNghiep> findByObjectId(Long objectId, String namHoc) {
        Query query = session.createQuery("SELECT t FROM TbdHdTotNghiep t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        return query.getResultList();
    }

    @Override
    public Long getSoTietByObjectId(Long objectId, String namHoc) {
        Long soTiet = 0l;
        Query query = session.createQuery("SELECT SUM(t.soTietQd) FROM TbdHdTotNghiep t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        soTiet = (Long) query.getSingleResult();
        if(null==soTiet){
            soTiet = 0l;
        }
        return soTiet;
    }

}
