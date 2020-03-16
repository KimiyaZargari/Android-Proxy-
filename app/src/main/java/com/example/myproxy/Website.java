package com.example.myproxy;

public class Website {
    String name, host;
    int in, out;
   public Website(String name, String host){
       this.name = name;
       this.host = host;
       in = 0;
       out = 0;
   }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public void setIn(String name) {
        this.name = name;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }
}
