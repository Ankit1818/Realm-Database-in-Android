package com.example.realmdatabase;

import io.realm.RealmObject;

public class Model extends RealmObject
{

    String name,pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
