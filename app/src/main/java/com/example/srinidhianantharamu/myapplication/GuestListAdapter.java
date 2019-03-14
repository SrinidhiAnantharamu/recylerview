package com.example.srinidhianantharamu.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    private Context context;
    private List<Guest> list;

    public GuestListAdapter(Context context, List<Guest> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Guest guest = list.get(position);
        System.out.println("-------------"+guest.getGuestNames());
       // StringBuilder gstring = new StringBuilder();
        for(String s : guest.getGuestNames()) {
            holder.guestname.setText(s);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView guestname;

        public ViewHolder(View itemView) {
            super(itemView);

            guestname = itemView.findViewById(R.id.guest_names);
        }
    }

}