package com.extremeplayer.quizme;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import static android.R.attr.numStars;
import static android.R.attr.rating;
import static android.content.Context.MODE_PRIVATE;
import static com.extremeplayer.quizme.R.id.cardView;
import static com.extremeplayer.quizme.R.id.challengeNumber;
import static com.extremeplayer.quizme.R.id.challengeText;
import static com.extremeplayer.quizme.R.id.practiceNumber;
import static com.extremeplayer.quizme.R.id.practiceTest;
import static com.extremeplayer.quizme.R.id.visible;

/**
 * Created by Abishaik on 14-07-2017.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.recyclerViewHolder> {

    ArrayList<Challenge> mChallenges;
    private final ChallengeAdapter.OnItemClickListener listener;
    private int mTracker;
    private float mSaved_list[] = new float[10];

    public interface OnItemClickListener {
        void onItemClick(Challenge item, int position, int tracker);
    }

    public ChallengeAdapter(float saved_list[],int tracker, ArrayList<Challenge> arrayList1,ChallengeAdapter.OnItemClickListener listener) {
        mChallenges = arrayList1;
        this.listener = listener;
        this.mTracker = tracker;
        this.mSaved_list=saved_list;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_challenge, parent, false);

        ChallengeAdapter.recyclerViewHolder rvh = new ChallengeAdapter.recyclerViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {
        holder.bind(mChallenges.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return mChallenges.size();
    }

    public class recyclerViewHolder extends RecyclerView.ViewHolder {
        TextView challengeText1;
        TextView challengeNumber1;
        CardView cardView;
        private RatingBar ratingBar;
        private ImageView imageView;

        public recyclerViewHolder(View v) {
            super(v);
            challengeText1 = (TextView) v.findViewById(challengeText);
            challengeNumber1 = (TextView) v.findViewById(challengeNumber);
            cardView = (CardView) v.findViewById(R.id.cardView2);
            ratingBar = (RatingBar) v.findViewById(R.id.myRatingBar);
            imageView = (ImageView) v.findViewById(R.id.myLock);
        }

        private void setRatingPosition(int position,float value)
        {
           switch(position)
           {
               case 0: setRating(value);
                        break;
               case 1: setRating(value);
                       break;
               case 2: setRating(value);
                   break;
               case 3: setRating(value);
                   break;
               case 4: setRating(value);
                   break;
               case 5: setRating(value);
                   break;
               case 6: setRating(value);
                   break;
               case 7: setRating(value);
                   break;
               case 8: setRating(value);
                   break;
               case 9: setRating(value);
                   break;
           }
        }

        private void setRating(float value){
            if (value >= 0 && value <= 25)
                ratingBar.setRating((float) 0);

            else if (value <= 50)
                ratingBar.setRating((float) 1.5);

            else if (value > 50 && value <= 75)
                ratingBar.setRating((float) 2);
            else
                ratingBar.setRating((float) 3);
        }

        public void bind(final Challenge item, final ChallengeAdapter.OnItemClickListener listener) {

            YoYo.with(Techniques.FlipInY)
                    .playOn(cardView);

            challengeText1.setText(item.getmString());
            challengeNumber1.setText(String.valueOf(item.getmNumber()));

            final int position = getAdapterPosition();
            /**int i,pos1 = 0;

            while ((pos1++) < mTracker) {
                switch (pos1) {
                    case 0:
                        ratingBar.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        if (score >= 0 && score <= 25)
                            ratingBar.setRating((float) 0);

                        else if (score <= 50)
                            ratingBar.setRating((float) 1.5);

                        else if (score > 50 && score <= 75)
                            ratingBar.setRating((float) 2);
                        else
                            ratingBar.setRating((float) 3);
                        break;

                    case 1:
                        ratingBar.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);

                        /**if (score >= 0 && score <= 25)
                         ratingBar.setRating((float) 0);

                         else if (score <= 50)
                         ratingBar.setRating((float) 1.5);

                         else if (score > 50 && score <= 75)
                         ratingBar.setRating((float) 2);
                         else
                         ratingBar.setRating((float) 3);
                         break;
                    case 2:
                        ratingBar.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        /**if (score >= 0 && score <= 25)
                         ratingBar.setRating((float) 0);

                         else if (score <= 50)
                         ratingBar.setRating((float) 1.5);

                         else if (score > 50 && score <= 75)
                         ratingBar.setRating((float) 2);
                         else
                         ratingBar.setRating((float) 3);
                         break;
                }*/


                /*for(i=(mTracker+1);i<10;i++){
                    imageView.setVisibility(View.VISIBLE);
                }*/
                if(position>=0 && position <=mTracker){
                    switch (position)
                    {
                        case 0: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 1: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 2: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 3: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 4: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 5: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 6: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 7: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 8: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                        case 9: ratingBar.setVisibility(View.VISIBLE);
                                setRatingPosition(position,mSaved_list[position]);
                                break;
                    }
                }
                else{
                    imageView.setVisibility(View.VISIBLE);
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(item, position,1);
                    }
                });
            }
        }
    }