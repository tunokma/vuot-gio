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
import javax.persistence.Query;

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

    @Override
    public Boolean findChuNhiemKhoa(Long idGiangVien) {
        Query query = session.createQuery("SELECT COUNT(k) FROM GiangVien k WHERE k.id=:idGiangVien AND k.chuNhiem =:chuNhiem ")
                .setParameter("idGiangVien", idGiangVien)
                .setParameter("chuNhiem", Constants.CHU_NHIEM.KHOA);
        Long soLuong = (Long) query.getSingleResult();
        if (soLuong == null || soLuong == 0l) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean findChuNhiemBoMon(Long idGiangVien) {
        Query query = session.createQuery("SELECT COUNT(k) FROM GiangVien k WHERE k.id=:idGiangVien AND k.chuNhiem =:chuNhiem ")
                .setParameter("idGiangVien", idGiangVien)
                .setParameter("chuNhiem", Constants.CHU_NHIEM.BO_MON);
        Long soLuong = (Long) query.getSingleResult();
        if (soLuong == null || soLuong == 0l) {
            return false;
        }
        return true;
    }

    @Override
    public List<GiangVien> findByIdBoMon(Long idBoMon) {
        return session.createQuery("SELECT k FROM GiangVien k WHERE k.role =:role AND k.idBoMon =:idBoMon")
                .setParameter("idBoMon", idBoMon)
                .setParameter("role", 2l)
                .getResultList();
    }

}
