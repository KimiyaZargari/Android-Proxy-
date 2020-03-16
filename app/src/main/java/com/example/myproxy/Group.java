package com.example.myproxy;

import java.util.Dictionary;
import java.util.List;

public class Group {
    private String name;
    private Dictionary<String, String> websites;

    public Group(String name) {
    this.name = name;
    }

    public Dictionary<String, String> getWebsites() {
        return websites;
    }

    public void addWebsite(String key, String val) {
        websites.put(key, val);

    }

    public void deleteWebsite(int i) {
        websites.remove(i);
    }

    public void deleteWebsite(String key) {
        websites.remove(key);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
