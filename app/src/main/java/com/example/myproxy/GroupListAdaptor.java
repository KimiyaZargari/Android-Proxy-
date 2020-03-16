package com.example.myproxy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupListAdaptor extends RecyclerView.Adapter<GroupListAdaptor.MyViewHolder> {
    List<Group> groups;

    public GroupListAdaptor(List<Group> groups){
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
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.groupName);
        }


    }
}
