/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository.custom;

import com.kma.quanlygiangday.model.TongHop;
import java.util.List;

/**
 *
 * @author Tuno
 */
public interface TongHopRepositoryCustom {

    public TongHop findByObjectId(Long objectId,String namHoc);

    List<TongHop> search(TongHop tongHop);

}
