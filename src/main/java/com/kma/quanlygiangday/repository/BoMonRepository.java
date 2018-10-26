/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.repository.custom.BoMonRepositoryCustom;
import com.kma.quanlygiangday.model.BoMon;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Tuno
 */
public interface BoMonRepository extends CrudRepository<BoMon, Long>, BoMonRepositoryCustom{
    List<BoMon> findByIdKhoa(Long idKhoa);
}
