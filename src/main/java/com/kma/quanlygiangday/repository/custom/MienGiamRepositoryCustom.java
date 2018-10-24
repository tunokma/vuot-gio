/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.custom;

import com.kma.quanlygiangday.model.TbdMienGiam;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface MienGiamRepositoryCustom {
    
    List<TbdMienGiam> findByfindByNameContaining(String string);
}
