package models;

public abstract class BaseEntity {

    protected int id;
    protected String name;

    public abstract String getType();

    public abstract void validate();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String shortInfo() {
        return getType() + "#" + id + " " + name;
    }
}

