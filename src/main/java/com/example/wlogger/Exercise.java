package com.example.wlogger;

public class Exercise {

    int image;
    String name;
    String ppl;
    String part;

    public Exercise(String name, int image, String ppl, String part){
        this.name = name;
        this.image = image;
        this.ppl = ppl;
        this.part = part;
    }

    public Exercise(String name, int image, String ppl){
        this.name = name;
        this.image = image;
        this.ppl = ppl;
    }
    
    public String getName() {
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getPpl() {
        return ppl;
    }

    public void setPpl(){
        this.ppl = ppl;
    }

    public String getPart() {
        return part;
    }

    public void setPart(){
        this.part = part;
    }

    public int getImage() {
        return image;
    }

    public void setImage(){
        this.image = image;
    }

}
