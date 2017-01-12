package com.yoavi.materialtest1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class    Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    private LayoutInflater inflator;
    List<RowInfo> data = Collections.emptyList();

    public Adapter(Context context, List<RowInfo> data){
        inflator=LayoutInflater.from(context);
        this.data=data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflator.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RowInfo current = data.get(position);
        holder.text.setText(current.title);
        holder.image.setImageResource(current.resId);

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.row_textView);
            image=(ImageView)itemView.findViewById(R.id.row_img);
        }
    }
}
