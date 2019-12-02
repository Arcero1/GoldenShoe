package com.example.goldenshoeprototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Item {
    private String name;

    public List<String> getImages() {
        return images;
    }

    private List<String> images = new ArrayList<>();
    private String description = "";
    private int priceInPennies;
    private int numberInBasket;

    Item(String name, int priceInPennies) {
        this.name = name;
        this.priceInPennies = priceInPennies;
        this.images = images;
    }

    Item(String name, int priceInPennies, String description) {
        this.name = name;
        this.description = description;
        this.priceInPennies = priceInPennies;
    }

    void addImage(String url) {
        images.add(url);
    }

    void add(int quantity) {
        numberInBasket += quantity;
    }

    void remove(int quantity) {
        numberInBasket -= quantity;
        if (numberInBasket < 0) {
            numberInBasket = 0;
        }
    }

    int getNumberInBasket() {
        return numberInBasket;
    }

    int getTotal() {
        return priceInPennies * numberInBasket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceInPennies() {
        return priceInPennies;
    }

    public void setPriceInPennies(int priceInPennies) {
        this.priceInPennies = priceInPennies;
    }
}
