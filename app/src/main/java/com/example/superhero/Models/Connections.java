package com.example.superhero.Models;

import com.google.gson.annotations.SerializedName;

public class Connections {

    @SerializedName("group-affiliation")
    String groupAffiliation;
    String relatives;

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation) {
        this.groupAffiliation = groupAffiliation;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }
}
