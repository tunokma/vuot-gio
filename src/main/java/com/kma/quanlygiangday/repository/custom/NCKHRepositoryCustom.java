/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.custom;

import com.kma.quanlygiangday.model.TbdNckh;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface NCKHRepositoryCustom {

    public int deleteNCKH(Long id);

    public List<TbdNckh> findByObjectId(Long objectId, String namHoc);
}
