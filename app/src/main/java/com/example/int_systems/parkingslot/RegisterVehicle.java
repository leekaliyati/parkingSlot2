package com.example.int_systems.parkingslot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegisterVehicle extends AppCompatActivity implements View.OnClickListener {
    EditText etowner, etaddress,etphone,etmake,etregistration,etcolor,etmodel;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);
        etowner = (EditText) findViewById(R.id.editOwner);
        etaddress = (EditText) findViewById(R.id.editTextAddress);
        etphone = (EditText) findViewById(R.id.editTextPhone);
        etmake = (EditText) findViewById(R.id.editTextMake);
        etregistration = (EditText) findViewById(R.id.editTextRegistrationNum);
        etcolor = (EditText) findViewById(R.id.editTextColor);
        etmodel = (EditText) findViewById(R.id.editTextModel);

        loginbtn = (Button) findViewById(R.id.btnLogin);
        loginbtn.setOnClickListener(this);

    }

    private void registerVehicle() {
        String owner = etowner.getText().toString();
        String address = etaddress.getText().toString();
        String phone = etphone.getText().toString();
        String make = etmake.getText().toString();
        String registration = etregistration.getText().toString();
        String color = etcolor.getText().toString();
        String model = etmodel.getText().toString();


        if (owner.contentEquals("") || address.contentEquals("")|| phone.contentEquals("")
                || make.contentEquals("")|| registration.contentEquals("")|| color.contentEquals("")|| model.contentEquals("")) {
            //progressDialog.dismiss();
            Toast.makeText(RegisterVehicle.this, "Fill in all the Feilds", Toast.LENGTH_LONG).show();


        } else {
            Intent i = new Intent(RegisterVehicle.this,ProcessReg.class);
            i.putExtra("owner", etowner.getText().toString());
            i.putExtra("address", etaddress.getText().toString());
            i.putExtra("phone", etphone.getText().toString());
            i.putExtra("make", etmake.getText().toString());
            i.putExtra("registration", etregistration.getText().toString());
            i.putExtra("color", etcolor.getText().toString());
            i.putExtra("model", etmodel.getText().toString());
            startActivity(i);

            }

        }


    @Override
    public void onClick(View view) {
        registerVehicle();
    }
}

