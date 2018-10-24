/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.repository.custom.MonHocRepositoryCustom;
import com.kma.quanlygiangday.model.MonHoc;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Tuno
 */
public interface MonHocRepository extends CrudRepository<MonHoc, Long>, MonHocRepositoryCustom{
    
}
