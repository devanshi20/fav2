package com.example.atul_.eatit;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.atul_.eatit.Database.Database;
import com.example.atul_.eatit.ViewHolder.CartAdapter;
import com.example.atul_.eatit.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {
    
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    
    FirebaseDatabase database;
    DatabaseReference request;
    TextView txtTotalPrice;
    FButton btnPlace;


    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_cart);
        
        database=FirebaseDatabase.getInstance();
        request=database.getReference("Requests");
        
        recyclerView=(RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlace = (FButton)findViewById(R.id.btnPlaceOrder);
        
        loadListFood();

        
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void loadListFood() {
   cart= new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for(Order order1:cart)
            total+=(Integer.parseInt(order1.getPrice()))*(Integer.parseInt(order1.getQuantity()));
        Locale locale = new Locale("en","INDIA");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));

    }

}
