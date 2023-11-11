package com.shop.fashionaccessory.gui_components.panel;

import com.shop.fashionaccessory.password.PlainPassHashGenerator;

public class main {
    public static void main(String[] args) {
        PlainPassHashGenerator plainPassHashGenerator=new PlainPassHashGenerator();
        System.out.println(plainPassHashGenerator.generateHashedPassword("sladja1234"));
    }
}
