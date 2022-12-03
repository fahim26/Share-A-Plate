package com.example.foodorderapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type",0) == 1) {


            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");
//            final String houseNo = getIntent().getStringExtra("houseNo");
//            final String streetNo = getIntent().getStringExtra("streetNo");
//            final String city = getIntent().getStringExtra("city");


            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
//        binding.namebox.setText(name);
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);


            //int img = getIntent().getIntExtra("add");
            ImageView img = (ImageView) findViewById(R.id.add);
            img.setOnClickListener(new View.OnClickListener() {
                @java.lang.Override
                public void onClick(View view) {
                    int quan = Integer.parseInt(binding.quantity.getText().toString());
                    quan += 1;
                    binding.quantity.setText(Integer.toString(quan));
                    binding.priceLbl.setText(String.format("%d", price*quan));
                }
            });

            ImageView img2 = (ImageView) findViewById(R.id.sub);
            img2.setOnClickListener(new View.OnClickListener() {
                @java.lang.Override
                public void onClick(View view) {
                    int quan = Integer.parseInt(binding.quantity.getText().toString());
                    quan -= 1;
                    binding.quantity.setText(Integer.toString(quan));
                    binding.priceLbl.setText(String.format("%d", price*quan));
                }
            });




            binding.insertButton.setOnClickListener((view) -> {
                String namebox = binding.namebox.getText().toString().trim();
                String phone = binding.phonebox.getText().toString().trim();
                String houseNo = binding.houseNo.getText().toString().trim();
                String streetNo = binding.streetNo.getText().toString().trim();
                String city = binding.city.getText().toString().trim();

                Log.d("name",namebox);
                Log.d("phone",phone);
                Log.d("h",houseNo);
                Log.d("s",streetNo);
                Log.d("c",city);



                if(namebox.length() > 0 && phone.length() > 0 && houseNo.length() > 0 && streetNo.length() > 0 && city.length() > 0) {
                    boolean isInserted = helper.insertOrder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()) ,
                            image,
                           // quan,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            description,
                            name,
                            binding.houseNo.getText().toString(),
                            binding.streetNo.getText().toString(),
                            binding.city.getText().toString()
                    );
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Order Done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DetailActivity.this, "Fill Up all the fields", Toast.LENGTH_SHORT).show();
                }

            });
        }else{
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);
            int p = cursor.getInt(3);
            int q = cursor.getInt(5);
            final int unit_price = p/q;
            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
//        binding.namebox.setText(name);

            binding.foodName.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(6));

            binding.namebox.setText(cursor.getString(1));
            binding.phonebox.setText(cursor.getString(2));
            binding.houseNo.setText(cursor.getString(8));
            binding.streetNo.setText(cursor.getString(9));
            binding.city.setText(cursor.getString(10));
            binding.quantity.setText(String.format("%d", cursor.getInt(5)));

//            int q = Integer.parseInt(binding.quantity.getText().toString());
//            binding.quantity.setText(Integer.toString(q));


//            Toast.makeText(this,cursor.getShort(1),Toast.LENGTH_SHORT).show();

            ImageView img = (ImageView) findViewById(R.id.add);
            img.setOnClickListener(new View.OnClickListener() {
                @java.lang.Override
                public void onClick(View view) {
                    int quan = Integer.parseInt(binding.quantity.getText().toString());
                    quan += 1;
                    binding.quantity.setText(Integer.toString(quan));
                    binding.priceLbl.setText(String.format("%d", unit_price*quan));
                }
            });

            ImageView img2 = (ImageView) findViewById(R.id.sub);
            img2.setOnClickListener(new View.OnClickListener() {
                @java.lang.Override
                public void onClick(View view) {
                    int quan = Integer.parseInt(binding.quantity.getText().toString());
                    quan -= 1;
                    binding.quantity.setText(Integer.toString(quan));
                    binding.priceLbl.setText(String.format("%d", unit_price*quan));

                }
            });




            binding.insertButton.setText("Update Orders");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @java.lang.Override
                public void onClick(View view) {
                  boolean isUpdated =  helper.updateOrder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            binding.houseNo.getText().toString(),
                            binding.streetNo.getText().toString(),
                            binding.city.getText().toString(),
                            id
                    );
                  if(isUpdated)
                      Toast.makeText(DetailActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                  else
                      Toast.makeText(DetailActivity.this,"Failed",Toast.LENGTH_SHORT).show();


                }
            });
        }


    }
}