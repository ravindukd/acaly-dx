package com.iitprojects.acaly.dx;

public class Task  extends BaseItem {
    
    private String bgColor;
    
    public String getBgColor(){
        return this.bgColor;
    }
    
    public void setBgColor(String color){
        this.bgColor = color;
    }

    public Task(int id, String title, String desc, String bgColor) {
        super(id, title, desc);
        this.setBgColor(bgColor);
    }

    
    
}
