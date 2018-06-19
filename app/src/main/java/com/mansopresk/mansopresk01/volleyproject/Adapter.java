package com.mansopresk.mansopresk01.volleyproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    MainActivity mainActivity=new MainActivity();
    List<Model> data= Collections.emptyList();
    Adapter.MyHolder myHolder;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        final Adapter.MyHolder holder=new Adapter.MyHolder(itemView);
        return holder;
        }

    public Adapter(Context context, List<Model> data1){
        this.context=context;
        this.data=data1;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        myHolder= (Adapter.MyHolder) holder;
        Model current = data.get(position);
        myHolder.userid.setText(current.getUserId());
        //myHolder.department.setText(current.getBranch());
        myHolder.id_label.setText(current.getId());
        myHolder.title.setText(current.getTitle());
        myHolder.body.setText(current.getBody());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    private class MyHolder extends RecyclerView.ViewHolder{

        TextView userid,id_label,title,body;

        public MyHolder(View itemView) {
            super(itemView);
            userid = (TextView) itemView.findViewById(R.id.userid);
            id_label = (TextView) itemView.findViewById(R.id.id_label);
            title = (TextView) itemView.findViewById(R.id.title_label);
            body = (TextView) itemView.findViewById(R.id.body_label);


        }
    }
}
