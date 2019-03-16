package com.example.myapplication;


public class track {

    private String id;
    private String title;
    private String singer;

    public track(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }
    public String getId(){

        return this.id;
    }

    public String getTitle(){

        return this.title;
    }

    public String getSinger(){

        return this.singer;
    }


}

