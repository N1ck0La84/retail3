package ru.retail;

public class Storage {

    private String name;
    private boolean taskExists;

    public Storage(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public boolean isTaskExists() {
        return taskExists;
    }

    public void setTaskExists(boolean taskExists) {
        this.taskExists = taskExists;
    }
}