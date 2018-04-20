package com.example.int_systems.parkingslot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SloteDetails extends AppCompatActivity implements View.OnClickListener {
    TextView etName, etAddress, etPrice, etAgentnumber, etstatus;
    Button btnBook;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slote_details);

        //getting values from an intent

        Bundle extras = getIntent().getExtras();
        String parkingId = extras.getString("parkingId");
        String driverID =extras.getString("driverID");
        System.out.println("+++++++++++not clicked++++++++");
        System.out.println(driverID);


        // initialising the views from the id
        etName = (TextView) findViewById(R.id.Slotname);
        etAddress = (TextView) findViewById(R.id.parkingSlotAddress);
        etPrice = (TextView) findViewById(R.id.parkingslotprice);
        etAgentnumber = (TextView) findViewById(R.id.parkingagentNumber);
        etstatus = (TextView) findViewById(R.id.parkingStatus);
        btnBook = (Button) findViewById(R.id.etbookSlot);
        btnBook.setOnClickListener(this);

// initialising sever connection
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("parkingId", parkingId);
        client.post("http://10.0.2.2/parkingSlot/android/getParkingSlot.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {


                    JSONObject jObject = new JSONObject(responseString);

                    String Slotname = jObject.getString("Slotname");
                    String Slotaddress = jObject.getString("Slotaddress");
                    String Slotprice = jObject.getString("Slotprice");
                    String SlotAgentnumber = jObject.getString("Slotagent");
                    String Slotstatus = jObject.getString("Slotstatus");
                    String AccountNumber = jObject.getString("AccountNumber");

                    // assigning value to views
                    etName.setText(Slotname);
                    etAddress.setText(Slotaddress);
                    etPrice.setText(Slotprice);
                    etAgentnumber.setText(SlotAgentnumber);
                    etstatus.setText(Slotstatus);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    private void runPayment() {
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0779755462"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view == btnBook) {

            BookSlot();

        }
    }

    private void BookSlot() {
        Bundle extras = getIntent().getExtras();
        String parkingId = extras.getString("parkingId");
        String driverID = extras.getString("driverID");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("parkingId", parkingId);
        params.put("driverID",driverID);
        System.out.println("+++++++++++++Requested driverid++++++++++");
        System.out.println(driverID);
        System.out.println("+++++++++++++Requested parking++++++++++");
        System.out.println(parkingId);


        client.post("http://10.0.2.2/parkingSlot/android/BookSlot.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                errormessage();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                message();



            }
        });
    }

    private void errormessage() {
        Toast.makeText(this,"Booking failed please try again",Toast.LENGTH_LONG).show();
    }

    private void message() {
        Toast.makeText(this,"Parking Booked you have 10 min ",Toast.LENGTH_LONG).show();
    }

}
