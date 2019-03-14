package com.example.srinidhianantharamu.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Guest {

    @SerializedName("guestNames")
    @Expose
    private List<String> guestNames = null;


    public List<String> getGuestNames() {
        return guestNames;
    }

    public void setGuestNames(List<String> guestNames) {
        this.guestNames = guestNames;
    }





}