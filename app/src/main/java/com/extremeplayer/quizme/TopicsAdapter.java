package com.extremeplayer.quizme;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

/**
 * Created by Abishaik on 30-06-2017.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.recyclerViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(Topics item,int position);
    }

    ArrayList<Topics> mTopics;
    private final OnItemClickListener listener;
    private Context context;

    public TopicsAdapter(Context context,ArrayList<Topics> dataObjects,OnItemClickListener listener) {
        this.context = context;
        this.mTopics = dataObjects;
        this.listener=listener;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_topic, parent, false);

        recyclerViewHolder rvh = new recyclerViewHolder(view);
        return rvh;
    }



    @Override
    public void onBindViewHolder(recyclerViewHolder holder, final int position) {
        holder.bind(mTopics.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }


    public class recyclerViewHolder extends RecyclerView.ViewHolder {
        TextView topicsText;
        ImageView topicsImage;
        CardView cardView;

        public recyclerViewHolder(View v) {
            super(v);
            topicsText = (TextView) v.findViewById(R.id.topicsText);
            topicsImage = (ImageView) v.findViewById(R.id.topicsImage);
            cardView = (CardView)v.findViewById(R.id.cardView);
        }

        public void bind(final Topics item, final OnItemClickListener listener) {

            YoYo.with(Techniques.SlideInLeft)
                    .playOn(cardView);

            topicsText.setText(item.getTextTopics());
            PiccasoClient.downloadImage(context,item.getUrl(),topicsImage);
            //topicsImage.setImageResource(item.getImageid());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(item,position);
                }
            });
        }
    }
}



