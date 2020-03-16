package com.example.myproxy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class WebsiteListAdaptor extends RecyclerView.Adapter<WebsiteListAdaptor.MyViewHolder> {
    ArrayList<Website> websites;
    private Context context;

    public WebsiteListAdaptor(ArrayList<Website> websites, Context context){
        this.websites = websites;
        this.context = context;
    }
    @NonNull
    @Override
    public WebsiteListAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.website, parent, false);
        return new WebsiteListAdaptor.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteListAdaptor.MyViewHolder holder, int position) {
        Website website = websites.get(position);
        holder.name.setText(website.getName());
        holder.host.setText(website.getHost());

    }

    @Override
    public int getItemCount() {
        return websites.size();
    }

    public ArrayList<Website> getWebsites() {
        return websites;
    }

    public void addWebsite(Website website){
        websites.add(website);
        notifyDataSetChanged();
    }

    public void deleteWebsite(int i){
        websites.remove(i);
        notifyDataSetChanged();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView host;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.websiteName);
            host = itemView.findViewById(R.id.hostName);
        }

    }

}
