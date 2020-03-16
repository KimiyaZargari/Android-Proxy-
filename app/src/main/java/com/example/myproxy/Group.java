package com.example.myproxy;

import java.util.List;

public class Group {
    private String Name;
    private List<String> websites;

    public List<String> getWebsites() {
        return websites;
    }
    public void addWebsite(String s){
        websites.add(s);

    }
    public void deleteWebsite(int i){
        websites.remove(i);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
