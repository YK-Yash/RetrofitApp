package com.example.dell.retrofitapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 03-02-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HeroHolder> {

    List<RecyclerModel> list;
    Context context;

    public RecyclerAdapter(List<RecyclerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public HeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        HeroHolder heroHolder = new HeroHolder(view);

        return heroHolder;
    }

    @Override
    public void onBindViewHolder(HeroHolder holder, int position) {

        RecyclerModel HeroList = list.get(position);
        holder.heroName.setText(HeroList.getHeroName());

    }

    @Override
    public int getItemCount() {

        int arr = 0;
        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }
        }catch (Exception e){

        }
        return arr;

    }

    class HeroHolder extends RecyclerView.ViewHolder{

        TextView heroName;

        public HeroHolder(View itemView) {
            super(itemView);
            heroName = (TextView) itemView.findViewById(R.id.heroo);

        }
    }

}


