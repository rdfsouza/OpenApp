package com.example.openapp3.Forum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.openapp3.R;

import java.util.ArrayList;

public class AllForumAdapter extends RecyclerView.Adapter<AllForumAdapter.AllForumHolder>{ private ArrayList<AllForumStyle> AFS;

    private AllForumAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(AllForumAdapter.OnItemClickListener listener){
        mListener = listener;
    }


    public class AllForumHolder extends RecyclerView.ViewHolder{

        public TextView textView3;

        public AllForumHolder(@NonNull View itemView) {
            super(itemView);

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

    public AllForumAdapter(ArrayList<AllForumStyle> afs) {
        AFS = afs ;
    }



    @NonNull
    @Override
    public AllForumAdapter.AllForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_messages_style, parent, false);
        AllForumAdapter.AllForumHolder AMH = new AllForumAdapter.AllForumHolder(v);
        return AMH;
    }

    @Override
    public void onBindViewHolder(@NonNull AllForumAdapter.AllForumHolder holder, int position) {
        AllForumStyle AFSP = AFS.get(position);
        holder.textView3.setText(AFSP.getText3());
    }

    @Override
    public int getItemCount() {
        return AFS.size();
    }
}
