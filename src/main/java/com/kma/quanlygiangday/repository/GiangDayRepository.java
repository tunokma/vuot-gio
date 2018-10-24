/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.repository.custom.GiangDayRepositoryCustom;
import com.kma.quanlygiangday.model.TbdGiangDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tuno
 */
@SuppressWarnings("unused")
@Repository
public interface GiangDayRepository extends CrudRepository<TbdGiangDay, Long>, GiangDayRepositoryCustom {

}
