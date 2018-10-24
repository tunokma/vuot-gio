/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.custom;

import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface HdTotNghiepRepositoryCustom {

    public int deleteHdTN(Long id);

    public List<TbdHdTotNghiep> findByObjectId(Long objectId, String namHoc);

    public Long getSoTietByObjectId(Long objectId, String namHoc);
}
