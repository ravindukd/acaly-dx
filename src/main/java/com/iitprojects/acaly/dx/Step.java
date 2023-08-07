package com.iitprojects.acaly.dx;

public class Step {

    public int id;
    public String title;
    public String description;
    public String type;
    public String prompt;
    public String instruciton;
    public int taskId;
    
    public String getPrompt(){
        return this.prompt;
    }

    Step(int id, String title, String description, String type, String prompt, String instruction, int taskId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.prompt = prompt;
        this.instruciton = instruction;
        this.taskId = taskId;
    }
}
