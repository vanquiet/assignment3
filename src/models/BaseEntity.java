package models;

import exceptions.InvalidInputException;

public abstract class BaseEntity {
    protected int id;
    protected String name;

    protected BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void validate();
    public abstract String getType();

    public String shortInfo() {
        return getType() + "#" + id + " " + name;
    }

    public int getId() { return id; }


    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("name required");
        this.name = name;
    }
}
