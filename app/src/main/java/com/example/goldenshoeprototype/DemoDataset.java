package com.example.goldenshoeprototype;

import java.util.ArrayList;
import java.util.List;

public class DemoDataset {
    List<Item> items;

    DemoDataset() {
        items = new ArrayList<>();
        Item item = new Item("High Heels", 10000, "* Extremely comfortable");
        item.addImage("../../assets/high heels.jpg");
        items.add(item);
        items.add(new Item("Leather Shoes", 2999, "Super Stylish"));
        items.add(new Item("Trainers", 1200));

    }

    public List<Item> getDataset() {
        return items;
    }
}
