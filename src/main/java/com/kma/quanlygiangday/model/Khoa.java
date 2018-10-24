/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Tuno
 */
@Entity
@Table(name ="tbd_khoa", schema = "gio_day_them")
public class Khoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ten_khoa")
    private String tenKhoa;
    
    @Column(name = "mo_ta")
    private String moTa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
            
    

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,tenKhoa,moTa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Khoa other = (Khoa) obj;
        if (!Objects.equals(this.tenKhoa, other.tenKhoa)) {
            return false;
        }
        if (!Objects.equals(this.moTa, other.moTa)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
