/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface HdTotNghiepService {

    TbdHdTotNghiep save(TbdHdTotNghiep hdTotNghiep);

    Boolean delete(Long id);

    TbdHdTotNghiep update(TbdHdTotNghiep hdTotNghiep);

    TbdHdTotNghiep findById(Long id);

    List<TbdHdTotNghiep> findByObjectId(Long id, String namHoc);
    
    Long getSoTietByObjectId(Long objectId, String namHoc);

    List<TbdHdTotNghiep> findAll();

}
