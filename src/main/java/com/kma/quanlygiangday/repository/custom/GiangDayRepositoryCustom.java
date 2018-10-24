/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.custom;

import com.kma.quanlygiangday.model.TbdGiangDay;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface GiangDayRepositoryCustom {

    public int deleteGiangDay(Long id);

    public List<TbdGiangDay> findByHkCn(String hkCn, String namHoc);

    public List<TbdGiangDay> findByObjectId(Long id, String namHoc);

    public Long getSoTietDaDayTheoHKCN(Long objectId, String hkcn, String namHoc);
    
    public Long getSoTietDaDay(Long objectId, String namHoc);

}
