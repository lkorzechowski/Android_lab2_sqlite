package com.orzechowski.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementListAdapter extends RecyclerView.Adapter<ElementListAdapter.ElementViewHolder> {
    private final LayoutInflater mInflater;
    private List<Phones> mElementList;

    public ElementListAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
        this.mElementList = null;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = mInflater.inflate(R.layout.element_row, null);
        return new ElementViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementListAdapter.ElementViewHolder holder, int position) {
        TextView text1 = holder.itemView.findViewById(R.id.textView);
        TextView text2 = holder.itemView.findViewById(R.id.textView2);

        text1.setText(mElementList.get(position).getProducent());
        text2.setText(mElementList.get(position).getModel());
    }

    @Override
    public int getItemCount(){
        if(mElementList != null) return mElementList.size();
        else return 0;
    }

    public void setElementList(List<Phones> elementList){
        this.mElementList = elementList;
        notifyDataSetChanged();
    }

    public static class ElementViewHolder extends RecyclerView.ViewHolder{
        public ElementViewHolder(@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
        }
    }
}
