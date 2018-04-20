package com.example.int_systems.parkingslot;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ServiceAdapter  extends RecyclerView.Adapter <ServiceAdapter.ViewHolder> {

    public ServiceAdapter(Context mCtx, List<Response> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Response> productList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ServiceAdapter.ViewHolder holder, int position) {
        Response product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getParkingSlot());
        holder.textViewShortDesc.setText(product.getAddress());
        holder.textDistance.setText(product.getDistance());
        holder.textStatus.setText(product.getStatus());
        holder.textID.setText(product.getParkingID());
        holder.driver_id.setText(product.getDriverID());
       // holder.textID.setText(product.getParkingID());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewShortDesc, textStatus, textDistance;
        TextView textID, driver_id;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = (TextView)itemView.findViewById(R.id.textAddress);
            textStatus =(TextView)itemView.findViewById(R.id.textStatus);
            textDistance =(TextView)itemView.findViewById(R.id.textDistance);
            textID=(TextView)itemView.findViewById(R.id.textIDS);
            driver_id=(TextView)itemView.findViewById(R.id.id_driver);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String parkingId= textID.getText().toString();
                    String distance =textDistance.getText().toString();
                    String driverID =driver_id.getText().toString();
                    Intent newIntent= new Intent(mCtx,SloteDetails.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    newIntent.putExtra("parkingId",parkingId);
                    newIntent.putExtra("driverID",driverID);
                    mCtx.startActivity(newIntent);
                    Toast.makeText(mCtx,driverID ,Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
