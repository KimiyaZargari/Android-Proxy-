package com.example.myproxy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroupListAdaptor extends RecyclerView.Adapter<GroupListAdaptor.MyViewHolder> {
    ArrayList<Group> groups;

    public GroupListAdaptor(ArrayList<Group> groups){
        this.groups = groups;
    }
    @NonNull
    @Override
    public GroupListAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.group, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GroupListAdaptor.MyViewHolder holder, int position) {
        holder.name.setText( groups.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group){
        groups.add(group);
        notifyDataSetChanged();
    }

    public void deleteGroup(int i){
        groups.remove(i);
        notifyDataSetChanged();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.groupName);
        }

    }
}
