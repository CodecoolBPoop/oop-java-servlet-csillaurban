package com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public class WebShopServlet {
    public static final List<Item> init() {
        List<Item> webshop = new ArrayList<>();
        webshop.add(new Item(1,"Asus Laptop", 1600.0));
        webshop.add(new Item(2,"Harry Potter Ebook", 50.0));
        webshop.add(new Item(3,"Lego", 80));
        webshop.add(new Item(4,"Book", 70));
        webshop.add(new Item(5,"Table", 180));
        webshop.add(new Item(6,"Phone", 400));

        return webshop;
    }
}
