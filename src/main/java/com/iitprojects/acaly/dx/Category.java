package com.iitprojects.acaly.dx;

public class Category {

    public Category(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }
    public int id;
    public String name;
    public String description;

}
