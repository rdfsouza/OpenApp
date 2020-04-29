package com.example.openapp3.Chats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openapp3.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
private ArrayList<ChatsStyle> CSE;



    public class ChatHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }

    public ChatAdapter(ArrayList<ChatsStyle> cs) {
        CSE = cs ;
    }


    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chats_style, parent, false);
        ChatHolder CH = new ChatHolder(v);
        return CH;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

        ChatsStyle CSP = CSE.get(position);
        holder.image.setImageResource(CSP.getImage());
        holder.textView1.setText(CSP.getText1());
        holder.textView2.setText(CSP.getText2());
        holder.textView3.setText(CSP.getText3());
    }

    @Override
    public int getItemCount() {
        return CSE.size();
    }


}
