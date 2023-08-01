package com.iitprojects.acaly.dx;

public class Task {

    public Task(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.description = desc;
    }

    public String getTitle() {
        return this.title;
    }

    public int getID() {
        return this.id;
    }
    public int id;
    public String title;
    public String description;

}
