package com.extremeplayer.quizme;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.extremeplayer.quizme.R.id.practiceNumber;
import static com.extremeplayer.quizme.R.id.practiceTest;

/**
 * Created by Abishaik on 14-07-2017.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.recyclerViewHolder>{

    ArrayList<Test> mTests;
    private final TestAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Test item,int position);
    }

    public TestAdapter(ArrayList<Test> arrayList1,TestAdapter.OnItemClickListener listener) {
        mTests = arrayList1;
        this.listener=listener;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_test, parent, false);

        TestAdapter.recyclerViewHolder rvh = new TestAdapter.recyclerViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, int position) {
        holder.bind(mTests.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }

    public class recyclerViewHolder extends RecyclerView.ViewHolder {
        TextView practiceText;
        TextView numberText;
        CardView cardView;

        public recyclerViewHolder(View v) {
            super(v);
            practiceText = (TextView) v.findViewById(practiceTest);
            numberText = (TextView) v.findViewById(practiceNumber);
            cardView = (CardView)v.findViewById(R.id.cardView1);
        }

        public void bind(final Test item, final TestAdapter.OnItemClickListener listener) {

            YoYo.with(Techniques.FlipInX)
                    .playOn(cardView);

            practiceText.setText(item.getmString());
            numberText.setText(String.valueOf(item.getmNumber()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(item,position);
                }
            });
        }
    }
}
