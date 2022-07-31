package com.example.dagger2test.data.model;

public class HotGirl {
    String name;
    String image;

    public HotGirl(String name, String image) {
        this.name = name;
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
