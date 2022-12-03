package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger,"Burger","100","Juicy Burger"));
        list.add(new MainModel(R.drawable.beef_stake,"Beef Stake","1200","Finest and Medium Render Beef Stake"));
        list.add(new MainModel(R.drawable.beef_stew,"Beef Stew","320","best beef stew"));
        list.add(new MainModel(R.drawable.chicken_jalapeno,"Chicken Jalapeno","350","Special Chicken Jalapeno"));
        list.add(new MainModel(R.drawable.chilli_beef,"Crispy Chilli Beef","480","Tasty and crispy chilli with beef"));

        list.add(new MainModel(R.drawable.garlic_lamb_chops,"Garlic Lamb Chops","600","Lamb Chops with garlic"));
        list.add(new MainModel(R.drawable.pasta,"Pasta","280","best pasta"));
        list.add(new MainModel(R.drawable.peri_chicken,"Peri Peri Chicken","420","Juicy Peri Peri Chicken"));
        list.add(new MainModel(R.drawable.sandwitch,"Sandwitch","150","best chicken sandwitch"));
        list.add(new MainModel(R.drawable.peppercorn_roasted_beef,"Peppercorn Roasted Beef","580","best Peppercorn Roasted Beef"));



        MainAdapter adapter = new MainAdapter(list,this);
        binding.recycleview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleview.setLayoutManager(layoutManager);

    }

    @java.lang.Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu,menu);
         return super.onCreateOptionsMenu(menu);

    }

    @java.lang.Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}