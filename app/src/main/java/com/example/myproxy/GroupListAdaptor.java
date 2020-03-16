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

public class GroupListAdaptor extends RecyclerView.Adapter<GroupListAdaptor.MyViewHolder> {
    ArrayList<Group> groups;
    private Context context;

    public GroupListAdaptor(ArrayList<Group> groups, Context context){
        this.groups = groups;
        this.context = context;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(context, GroupActivity.class);
                   intent.putExtra("TITLE_KEY", name.getText().toString());
                    context.startActivity(intent);

                }
            });
        }

    }
}
