package com.example.int_systems.parkingslot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.int_systems.parkingslot.WebServices.DatabaseHelper;

import java.nio.Buffer;

public class FindSlot extends AppCompatActivity  {
    Button findSlot;
    Double latitude,longitude;
    DatabaseHelper myDb;
    String driverID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_slot);
       // findSlot =(Button) findViewById(R.id.btn_find);
        myDb = new DatabaseHelper(this);


        Intent serviceIntent = new Intent(this, ParkingService.class);
        startService(serviceIntent);
        getService();


        CardView first = (CardView) findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLoc();

            }
        });


        CardView second = (CardView) findViewById(R.id.second);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FindSlot.this, myBookings.class);
                driverID=theDriver();
                intent.putExtra("driverID",driverID);
                System.out.println("+++++++++++++sending driverid++++++++++");
                System.out.println(driverID);
                startActivity(intent);


            }
        });
    }
    public String theDriver(){
        Cursor res = myDb.getDriverID();
        if (res.moveToFirst()) {
            driverID = res.getString(1);
        }
        return driverID;

    }

    private void getLoc() {

        Intent i = new Intent(FindSlot.this, SearchResult.class);
        driverID=theDriver();
        i.putExtra("driverID",driverID);

        i.putExtra("latitude",latitude);
        i.putExtra("longitude",longitude);
        i.putExtra("driverID",driverID);


        startActivity(i);

    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));



        }


    };

    private void getService() {
        this.registerReceiver(broadcastReceiver, new IntentFilter(ParkingService.str_receiver));
    }
}
