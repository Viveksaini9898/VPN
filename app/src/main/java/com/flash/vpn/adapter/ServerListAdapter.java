package com.flash.vpn.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flash.vpn.EncryptData;
import com.flash.vpn.R;
import com.flash.vpn.model.Server;

import java.util.ArrayList;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.MyViewHolder> {

    private String image;
    private ArrayList<Server> serverList;
    private Context mContext;
    private OnItemClickListener listener;
    private Server server;
    private SharedPreferences SharedAppDetails;

    public ServerListAdapter(ArrayList<Server> serverList, Server server, Context context) {
        this.serverList = serverList;
        this.mContext = context;
        this.listener = (OnItemClickListener) context;
        this.server = server;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.server_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       /* holder.serverCountry.setText(serverList.get(position).getCountry());
        Glide.with(mContext)
                .load(serverList.get(position).getFlagUrl())
                .into(holder.iv_flag);
*/
        server = serverList.get(position);
        image = server.getCountry();

        switch (image) {
            case "Japan":
                holder.iv_flag.setImageResource(R.drawable.ic_flag_japan);
                holder.serverCountry.setText("Japan");
                break;
            case "India":
                holder.iv_flag.setImageResource(R.drawable.india);
                holder.serverCountry.setText("India");
                break;
            case "Russia":
                holder.iv_flag.setImageResource(R.drawable.ic_flag_russia);
                holder.serverCountry.setText("Russia");
                break;
            case "Thailand":
                holder.iv_flag.setImageResource(R.drawable.ic_flag_thailand);
                holder.serverCountry.setText("Thailand");
                break;
            default:
                holder.iv_flag.setImageResource(R.drawable.ic_flag_unknown_mali);
                break;

        }

        holder.serverItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    listener.onItemClick(position);

                } catch (Exception e) {
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return serverList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout serverItemLayout;
        ImageView iv_flag;
        TextView serverCountry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serverItemLayout = itemView.findViewById(R.id.serverItemLayout);
            iv_flag = itemView.findViewById(R.id.iconImg);
            serverCountry = itemView.findViewById(R.id.countryTv);

        }

    }


    public interface OnItemClickListener {
        void onItemClick(int position);

    }
}

