/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.repository.custom.GiangDayRepositoryCustom;
import com.kma.quanlygiangday.model.TbdGiangDay;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class GiangDayRepositoryImpl implements GiangDayRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    @Override
    public int deleteGiangDay(Long id) {
        Query query = session.createQuery("UPDATE TbdGiangDay t SET t.isDeleted = 1 WHERE t.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public List<TbdGiangDay> findByHkCn(String hkCn, String namHoc) {
        Query query = session.createQuery("SELECT t FROM TbdGiangDay t WHERE t.isDeleted = 0"
                + " AND t.hkCn = :hkCn"
                + " AND t.namHoc = :namHoc");
        query.setParameter("hkCn", hkCn);
        query.setParameter("namHoc", namHoc);
        return query.getResultList();
    }

    @Override
    public List<TbdGiangDay> findByObjectId(Long objectId, String namHoc) {
        Query query = session.createQuery("SELECT t FROM TbdGiangDay t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        return query.getResultList();
    }

    @Override
    public Long getSoTietDaDayTheoHKCN(Long objectId, String hkcn, String namHoc) {
        Long soTiet;
        Query query = session.createQuery("SELECT SUM(t.tietTg) FROM TbdGiangDay t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc"
                + " AND t.hkcn = :hkcn");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        query.setParameter("hkcn", hkcn);
        soTiet = (Long) query.getSingleResult();
        soTiet = (soTiet == null ? 0l : soTiet);
        return soTiet;
    }

    @Override
    public Long getSoTietDaDay(Long objectId, String namHoc) {
        Long soTiet;
        Query query = session.createQuery("SELECT SUM(t.tietTg) FROM TbdGiangDay t WHERE t.isDeleted = 0"
                + " AND t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        soTiet = (Long) query.getSingleResult();
        soTiet = (soTiet == null ? 0l : soTiet);
        return soTiet;
    }

}
