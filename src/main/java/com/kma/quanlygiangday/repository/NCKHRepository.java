/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.repository;

import com.kma.quanlygiangday.repository.custom.NCKHRepositoryCustom;
import com.kma.quanlygiangday.model.TbdNckh;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Tuno
 */
public interface NCKHRepository extends CrudRepository<TbdNckh,Long>, NCKHRepositoryCustom{
    
}
