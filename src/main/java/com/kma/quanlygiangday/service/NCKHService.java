/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.service;

import com.kma.quanlygiangday.model.TbdNckh;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface NCKHService {

    TbdNckh save(TbdNckh nckh);

    Boolean delete(Long id);

    TbdNckh update(TbdNckh nckh);

    TbdNckh findById(Long id);

    List<TbdNckh> findByObjectId(Long id, String namHoc);

    List<TbdNckh> findAll();

}
