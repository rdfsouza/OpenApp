package com.example.openapp3.Messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openapp3.R;

import java.util.ArrayList;

public class AllMessagesAdapter extends RecyclerView.Adapter<AllMessagesAdapter.AllMessagesHolder> {
    private ArrayList<AllMessagesStyle> AMS;

    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);

    }
public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
}


    public class AllMessagesHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textView2;
        public TextView textView3;

        public AllMessagesHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(mListener!=null){
                    int position = getAdapterPosition();

                    if (position!= RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
                }
            });

    }
    }

    public AllMessagesAdapter(ArrayList<AllMessagesStyle> ams) {
        AMS = ams ;
    }



    @NonNull
    @Override
    public AllMessagesAdapter.AllMessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_messages_style, parent, false);
        AllMessagesAdapter.AllMessagesHolder AMH = new AllMessagesAdapter.AllMessagesHolder(v);
        return AMH;
    }

    @Override
    public void onBindViewHolder(@NonNull AllMessagesAdapter.AllMessagesHolder holder, int position) {
        AllMessagesStyle AMSP = AMS.get(position);
        holder.image.setImageResource(AMSP.getImage());
        holder.textView2.setText(AMSP.getText2());
        holder.textView3.setText(AMSP.getText3());
    }

    @Override
    public int getItemCount() {
        return AMS.size();
    }
}
