package com.example.demo.models;

public enum Type {
    OneOfSeveral,ManyOfSeveral,Text;

    private String str;

    Type(String str) {
        this.str = str;
    }

    Type() {
    }

    public String getStr() {
        return str;
    }
}
