package com.kma.quanlygiangday.utils;

public enum Roles {
    ROLE_ADMIN(1l), ROLE_USER(2l);
    private long value;

    Roles(long value) {
        this.value = value;
    }


    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }


}
