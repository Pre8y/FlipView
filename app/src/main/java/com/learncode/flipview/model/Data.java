package com.learncode.flipview.model;

/**
 * Created by Preeti on 21/07/16.
 */
public class Data {
    public Data(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Data() {
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

    private String name, image;
}
