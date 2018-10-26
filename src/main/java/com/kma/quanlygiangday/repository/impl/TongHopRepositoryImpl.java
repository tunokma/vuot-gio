/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.impl;

import com.kma.quanlygiangday.model.TongHop;
import com.kma.quanlygiangday.repository.custom.TongHopRepositoryCustom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tuno
 */
public class TongHopRepositoryImpl implements TongHopRepositoryCustom {

    @PersistenceContext
    private EntityManager session;

    protected boolean validParam(String obj) {
        return obj != null && !obj.trim().isEmpty();
    }

    protected boolean validParam(Long obj) {
        return obj != null && obj >= 0l;
    }

    @Override
    public TongHop findByObjectId(Long objectId, String namHoc) {
        List<TongHop> lst;
        Query query = session.createQuery("SELECT t FROM TongHop t WHERE t.objectId = :objectId"
                + " AND t.namHoc = :namHoc");
        query.setParameter("objectId", objectId);
        query.setParameter("namHoc", namHoc);
        lst = query.getResultList();
        if (lst == null || lst.isEmpty()) {
            return new TongHop();
        }
        return lst.get(0);
    }

    @Override
    public List<TongHop> search(TongHop tongHop) {
        List<TongHop> lst;
        StringBuilder hql = new StringBuilder(" from TongHop t where 1 =1 ");
        List lstParam = new ArrayList();
        if (tongHop != null) {
            if (validParam(tongHop.getNamHoc())) {
                hql.append(" and t.namHoc like ? ");
                lstParam.add(tongHop.getNamHoc());
            }

            if (validParam(tongHop.getIdKhoa())) {
                hql.append(" and t.idKhoa = ? ");
                lstParam.add(tongHop.getIdKhoa());
            }

            if (validParam(tongHop.getIdBoMon())) {
                hql.append(" and t.idBoMon = ? ");
                lstParam.add(tongHop.getIdBoMon());
            }

        }
        Query query = session.createQuery("SELECT t " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        lst = query.getResultList();
        if (lst == null || lst.isEmpty()) {
            lst = new ArrayList<>();
        }
        return lst;
    }

    @Override
    public int deleteFromObjectId(Long objectId) {
        Query query = session.createQuery("DELETE FROM TongHop t WHERE t.objectId = :objectId");
        query.setParameter("objectId", objectId);
        return query.executeUpdate();
    }

}
