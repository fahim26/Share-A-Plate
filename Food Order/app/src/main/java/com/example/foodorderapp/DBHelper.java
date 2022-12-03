package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "fooddatabase.db";
    final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @java.lang.Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "name text NOT NULL," +
                        "phone text NOT NULL," +
                        "price int NOT NULL," +
                        "image int NOT NULL," +
                        "quantity int NOT NULL," +
                        "description text NOT NULL," +
                        "foodname text NOT NULL," +
                        "houseNo text NOT NULL," +
                        "streetNo text NOT NULL," +
                        "city text NOT NULL)"


        );


    }

    @java.lang.Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name, String phone, int price, int image, int quantity, String desc, String foodname, String houseNo,String streetNo,String city ) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        values.put("houseNo",houseNo);
        values.put("streetNo",streetNo);
        values.put("city",city);

        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT id,foodname,image,price from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderName(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);

            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * from orders WHERE id = " +id, null);
        Log.d("CURSOR", String.valueOf(cursor));
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, int quantity, String desc, String foodname, String houseNo,String streetNo,String city ,int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        values.put("houseNo",houseNo);
        values.put("streetNo",streetNo);
        values.put("city",city);
        long row = database.update("orders", values,"id="+id,null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }


}
