/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.repository.custom.MienGiamRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Tuno
 */
public interface MienGiamRepository extends CrudRepository<TbdMienGiam, Long>, MienGiamRepositoryCustom {

}
