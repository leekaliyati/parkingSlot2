package com.example.int_systems.parkingslot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.int_systems.parkingslot.WebServices.DatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ProcessReg extends AppCompatActivity implements View.OnClickListener{
    TextView etUsername,etPassword,etLogin;
    DatabaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_reg);


        etUsername = (TextView) findViewById(R.id.textusername);
        etPassword =(TextView) findViewById(R.id.textpassword) ;
        etLogin =(TextView) findViewById(R.id.btn_login);
        etLogin.setOnClickListener(this);

        myDb = new DatabaseHelper(this);





        Intent intent = getIntent();
        String owner = intent.getExtras().getString("owner");
        String address = intent.getExtras().getString("address");
        String phone = intent.getExtras().getString("phone");
        String make = intent.getExtras().getString("make");
        String registration = intent.getExtras().getString("registration");
        String color = intent.getExtras().getString("color");
        String model = intent.getExtras().getString("model");

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("owner", owner);
        params.put("address", address);
        params.put("phone", phone);
        params.put("make", make);
        params.put("registration", registration);
        params.put("color", color);
        params.put("model", model);
        client.post("http://10.0.2.2/parkingSlot/android/SaveVehicle.php", params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject jObject = new JSONObject(responseString);
                    String Username = jObject.getString("username");
                    String Password = jObject.getString("password");
                    String driverID =jObject.getString("driverID");
                    etUsername.setText(Username);
                    etPassword.setText(Password);
                    AddData(driverID);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view==etLogin){
            Intent intent = new Intent(ProcessReg.this,LoginActivity.class);
            startActivity(intent);
        }

    }
    public  void AddData(String driverID) {
                        boolean isInserted = myDb.insertData(
                               driverID

                                //   deviceID = tm.getDeviceId().toString()
  );
                        if(isInserted == true)
                            Toast.makeText(ProcessReg.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProcessReg.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }

