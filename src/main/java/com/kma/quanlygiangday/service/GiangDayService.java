/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.TbdGiangDay;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface GiangDayService {

    TbdGiangDay save(TbdGiangDay giangDay);

    Boolean delete(Long id);

    TbdGiangDay update(TbdGiangDay giangDay);

    TbdGiangDay findById(Long id);

    List<TbdGiangDay> findByHKCN(String hkcn, String namHoc);

    List<TbdGiangDay> findByObjectId(Long id, String namHoc);

    Long getSoTietDaDayTheoHKCN(Long objectId, String hkcn, String namHoc);

    Long getSoTietDaDay(Long objectId, String namHoc);

    List<TbdGiangDay> findAll();

}
