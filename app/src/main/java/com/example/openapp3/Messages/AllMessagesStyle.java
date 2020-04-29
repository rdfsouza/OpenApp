package com.example.openapp3.Messages;

import android.content.Intent;

public class AllMessagesStyle {


    private int Image;
    private String text2;
    private String text3;

    public AllMessagesStyle(int image,String Text2,String Text3){
        Image = image;
        text2 = Text2;
        text3 = Text3;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}

