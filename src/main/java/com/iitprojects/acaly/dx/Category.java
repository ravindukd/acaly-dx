package com.iitprojects.acaly.dx;

abstract class AbstractCategory {
    protected int id;
    protected String name;
    protected String description;

    public AbstractCategory(int id, String name, String desc) {
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

    public abstract String getCategoryInfo();
}

class Category extends AbstractCategory {
    public Category(int id, String name, String desc) {
        super(id, name, desc);
    }

    @Override
    public String getCategoryInfo() {
        return "Category ID: " + id + ", Category Name: " + name;
    }
}