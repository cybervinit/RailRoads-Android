package com.example.railroads.railroads;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Vinit Soni on 2016-09-17.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<String> dataset;
    private int questionType;

    public CustomAdapter (ArrayList<String> dataset) {
        this.dataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private int questionNumber;
        private String question;

        private TextView mQuestionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
            //Need to declare the data here.
        }

        public ViewHolder(View itemView, int questionType) {
            super(itemView);

            if (questionType == 0) {
                mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
                mQuestionTextView.setText(questionNumber+". "+question);
                // Add choices!
            } else if (questionType == 1) {
                mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
                mQuestionTextView.setText(questionNumber+". "+question);
                // Add Slider!
            } else {
                mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
                mQuestionTextView.setText(questionNumber+". "+question);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_choices, parent, false);
        if (questionType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_choices, parent, false);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_choices, parent, false);
        }



        ViewHolder viewHolder = new ViewHolder(v, questionType);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mQuestionTextView.setText(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
