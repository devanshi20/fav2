package com.example.atul_.eatit.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atul_.eatit.FavoritesActivity;
import com.example.atul_.eatit.R;
import com.example.atul_.eatit.model.Favorites;
import com.example.atul_.eatit.model.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 09-Mar-18.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private Context context;
    private List<Favorites> favoritesList;

    public FavoritesAdapter(FavoritesActivity favoritesActivity, List<Favorites> allFavorites) {
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context)
                .inflate(R.layout.favorites_item,parent,false);
        return new FavoritesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder viewHolder, int position) {

        viewHolder.food_name.setText(favoritesList.get(position).getFoodName());
        viewHolder.food_price.setText(String.format("$ %s", favoritesList.get(position).getFoodPrice()));
        Picasso.with(context).load(favoritesList.get(position).getFoodImage());

    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }


    public void removeItem(int position)
    {
         favoritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Favorites item, int position)
    {
          favoritesList.add(position,item);
          notifyItemInserted(position);
    }
}
