package com.example.wlogger;

public class LogGet {

    int image;
    String name;
    String weight;
    String reps;

    String id;

    public LogGet(int image, String name, String weight, String reps, String id) {
        this.image = image;
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getId(){ return id;}

    public void setId(String id){ this.id = id;}
}
