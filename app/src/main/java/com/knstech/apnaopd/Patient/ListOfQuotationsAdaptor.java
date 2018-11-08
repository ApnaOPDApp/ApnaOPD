package com.knstech.apnaopd.Patient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ListOfQuotationsAdaptor extends RecyclerView.Adapter<ListOfQuotationsAdaptor.ListOfQuotationsViewHolder> {

    @NonNull
    @Override
    public ListOfQuotationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListOfQuotationsViewHolder listOfQuotationsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListOfQuotationsViewHolder extends RecyclerView.ViewHolder{

        public ListOfQuotationsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
