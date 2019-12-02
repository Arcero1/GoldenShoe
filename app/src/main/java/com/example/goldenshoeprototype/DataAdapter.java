package com.example.goldenshoeprototype;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private List<Item> rawDataSet;
    private List<Item> dataSet;
    private List<Boolean> isDisplayed;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
        }
    }

    public DataAdapter(List<Item> items) {
        rawDataSet = items;
        dataSet = rawDataSet;
        isDisplayed = new ArrayList<>(Collections.nCopies(items.size(), true));
    }

    public void refine(String query) {
        System.out.println("QUERY: " + query);

        List<Item> toRemove = new ArrayList<>();
        List<Item> toAdd = new ArrayList<>();
        for(Item item : rawDataSet) {
            if(!item.getName().toLowerCase().contains(query.toLowerCase())) {
                toRemove.add(item);
            } else {
                toAdd.add(item);
            }
        }


        for(Item item : toRemove) {
            dataSet.remove(item);
            notifyItemRemoved(rawDataSet.indexOf(item));
            notifyItemRangeChanged(0, dataSet.size());
        }

        for(Item item : toAdd) {
            if(!dataSet.contains(item)) {
                dataSet.add(item);
                notifyItemInserted(dataSet.indexOf(item));
                notifyItemRangeChanged(0, dataSet.size());
            }
        }
    }

    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listing  , parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Item item = dataSet.get(position);

//        ImageView image = holder.linearLayout.findViewById(R.id.image);
//        image.setImageDrawable(Drawable.createFromPath("drawable/trainers.jpg"));


        TextView text = holder.linearLayout.findViewById(R.id.title) ;
        text.setText(item.getName());

        text = holder.linearLayout.findViewById(R.id.description) ;
        text.setText(item.getDescription());

        text = holder.linearLayout.findViewById(R.id.price);
        text.setText(Util.getDisplayPrice(item.getPriceInPennies()));

        final Button button = holder.linearLayout.findViewById(R.id.add_to_basket_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item itemRaw = rawDataSet.get(position);
                if(itemRaw.getNumberInBasket() == 0) {
                    itemRaw.add(1);
                    button.setBackgroundColor(Color.GREEN);
                    button.setText("In Basket");
                } else {
                    itemRaw.remove(1);
                    button.setBackgroundColor(Color.GRAY);
                    button.setText("Add To Basket");
                }

            }
        });
    }

    public int getTotalPrice() {
        int total = 0;
        for(Item item: rawDataSet) {
            total += item.getTotal();
        }

        return total;
    }

    // Return the size of your rawDataSet (invoked by the layout manager)
    @Override
    public int getItemCount() {
        int size = 0;
        for(boolean b : isDisplayed) {
            size += b ? 1 : 0;
        }
        return rawDataSet.size();
    }
}