package com.example.int_systems.parkingslot;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SearchResult extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ListView listViewNames;
    //Response response =new Response();
    ArrayList<String> listItems = new ArrayList<String>();
    //a list to store all the products
    List<Response> productList;

    //the recyclerview
    RecyclerView recyclerView;

    ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        // initialise Progress dialog and display it

        //recycler view configurations
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        postData();


        }

    private void postData() {
        final ProgressDialog progressDialog= new ProgressDialog(SearchResult.this);

        progressDialog.setTitle("Searching for a parking slot");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Bundle extras = getIntent().getExtras();
        double latitude = extras.getDouble("latitude");
        double longitude = extras.getDouble("longitude");
        String driverID  = extras.getString("driverID");

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("latitude",latitude);
        params.put("longitude",longitude);
        params.put("driverID",driverID);
        System.out.println("+++++++++++++sending driverid++++++++++");
        System.out.println(driverID);
        client.post("http://10.0.2.2/parkingSlot/android/index.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressDialog.dismiss();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {



                progressDialog.dismiss();


                try {
                    JSONArray array = new JSONArray(responseString);
                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject product = array.getJSONObject(i);

                        //adding the product to product list
                        productList.add(new Response(
                                product.getString("address"),
                                product.getString("parkingSlot"),
                                product.getString("numberOfslots"),
                                product.getString("status"),
                                product.getString("parkingID"),
                                product.getString("distance"),
                                product.getString("driverID")

                        ));
                    }

                    //creating adapter object and setting it to recyclerview
                    ServiceAdapter adapter = new ServiceAdapter(SearchResult.this, productList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // progressDialog.dismiss();


            }
        });
    }


}


