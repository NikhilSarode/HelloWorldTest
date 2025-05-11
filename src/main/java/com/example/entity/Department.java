package com.example.entity;

public class Department {
    private Integer id;

    private String name;

    private String descrption;

    public Department(Integer id, String name, String descrption) {
        this.id = id;
        this.name = name;
        this.descrption = descrption;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
}
