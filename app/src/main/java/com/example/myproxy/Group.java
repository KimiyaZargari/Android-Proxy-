package com.example.myproxy;

import java.util.List;

public class Group {
    private String name;
    private List<String> websites;

    public Group(String name) {
    this.name = name;
    }

    public List<String> getWebsites() {
        return websites;
    }

    public void addWebsite(String s) {
        websites.add(s);

    }

    public void deleteWebsite(int i) {
        websites.remove(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
