package br.com.alessanderleite.retrofit2fetchjsondata.model;

import java.util.ArrayList;

public class ItemResponse {

    private ArrayList<Item> itemArrayList;

    public ItemResponse(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }
}
