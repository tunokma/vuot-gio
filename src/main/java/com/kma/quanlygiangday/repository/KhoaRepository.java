/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.repository.custom.KhoaRepositoryCustom;
import com.kma.quanlygiangday.model.Khoa;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Tuno
 */
public interface KhoaRepository extends CrudRepository<Khoa, Long>, KhoaRepositoryCustom{

}
