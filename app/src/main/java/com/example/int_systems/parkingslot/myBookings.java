package com.example.int_systems.parkingslot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class myBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_bookings);

        getBookings();

    }

    private void getBookings() {
        Bundle extras = getIntent().getExtras();
        String driverID = extras.getString("driverID");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("driverID",driverID);
        client.post("http://10.0.2.2/parkingSlot/android/MyBookings.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {


            }
        });
        }

    }


