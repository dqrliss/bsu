package com.example.gifts_spring;

import java.util.ArrayList;
public class factory {
    ArrayList<gift> giftsList = new ArrayList<>();
    String name;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    int giftsAmount;

    public ArrayList<gift> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(ArrayList<gift> giftsList) {
        this.giftsList = giftsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGiftsAmount() {
        return giftsAmount;
    }

    public void setGiftsAmount(int giftsAmount) {
        this.giftsAmount = giftsAmount;
    }
}
