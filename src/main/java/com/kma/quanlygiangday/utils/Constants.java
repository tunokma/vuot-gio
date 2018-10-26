/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.utils;

/**
 *
 * @author Tuno
 */
public final class Constants {

    public interface SetDelete {

        public final long NOT_DELETE = 0L;
        public final long IS_DELETED = 1L;
    }

    public interface HocHam {

        public final long CU_NHAN = 0;
        public final long KY_SU = 1;
        public final long THAC_SY = 2;
        public final long TIEN_SI = 3;
        public final long PGS_TIEN_SI = 4;
        public final long GS_TIEN_SI = 5;

        public final String CU_NHAN_TEXT = "Cử nhân";
        public final String KY_SU_TEXT = "Kỹ sư";
        public final String THAC_SY_TEXT = "Thạc sỹ";
        public final String TIEN_SI_TEXT = "Tiến sĩ";
        public final String PGS_TIEN_SI_TEXT = "PGS-Tiến sĩ";
        public final String GS_TIEN_SI_TEXT = "GS-Tiến sĩ";

    }

    public interface HocKy_ChuyenNganh {

        public final String HOC_KY_1_MM = "00";
        public final String HOC_KY_1_AT = "01";
        public final String HOC_KY_2_MM = "10";
        public final String HOC_KY_2_AT = "11";

        public final String HOC_KY_1_MM_TEXT = "Học kỳ I - Đào tạo chuyên ngành Mật mã";
        public final String HOC_KY_1_AT_TEXT = "Học kỳ I - Đào tạo các chuyên ngành khác";
        public final String HOC_KY_2_MM_TEXT = "Học kỳ II - Đào tạo chuyên ngành Mật mã";
        public final String HOC_KY_2_AT_TEXT = "Học kỳ II - Đào tạo các chuyên ngành khác";
    }

    public interface DINH_MUC_GIANG_DAY {

        public final String CO_BAN = "00";
        public final String CHUYEN_MON = "01";
        public final String CO_BAN_THAI_SAN = "10";
        public final String CHUYEN_MON_THAI_SAN = "11";

        public final String CO_BAN_TEXT = "Giảng viên cơ bản";
        public final String CHUYEN_MON_TEXT = "Giảng viên chuyên môn";
        public final String CO_BAN_THAI_SAN_TEXT = "Giảng viên cơ bản, thai sản";
        public final String CHUYEN_MON_THAI_SAN_TEXT = "Giảng viên chuyên môn, thai sản";
    }

    public interface SO_TIET {

        public final long SO_TIET_NCKH = 120l;
        public final long CO_BAN = 270;
        public final long CHUYEN_MON = 260;
        public final long CO_BAN_THAI_SAN = 135;
        public final long CHUYEN_MON_THAI_SAN = 130;
    }

    public interface CHU_NHIEM {

        public final long KHONG = 0l;
        public final long KHOA = 1l;
        public final long BO_MON = 2l;

        public final String KHONG_TEXT = "Không";
        public final String KHOA_TEXT = "Khoa";
        public final String BO_MON_TEXT = "Bộ môn";
    }
}
