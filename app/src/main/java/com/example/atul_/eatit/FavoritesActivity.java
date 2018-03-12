package com.example.atul_.eatit;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import com.example.atul_.eatit.Database.Database;

import com.example.atul_.eatit.Common.Common;
import com.example.atul_.eatit.ViewHolder.FavoritesAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FavoritesAdapter adapter;
    RelativeLayout rootLayout;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rootLayout=(RelativeLayout)findViewById(R.id.root_layout);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_fav);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(recyclerView.getContext(),
                R.anim.layout_from_left);
        recyclerView.setLayoutAnimation(controller);
        
        loadFavorites();

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void loadFavorites() {

        adapter=new FavoritesAdapter(this,new Database(this).getAllFavorites(Common.currentUser.getPhone()) );
        recyclerView.setAdapter(adapter);
    }
}
