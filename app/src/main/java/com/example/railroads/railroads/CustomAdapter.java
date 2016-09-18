package com.example.railroads.railroads;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
        private Button httpTest1;
        private Context context;

        // Seeking
        private SeekBar mSeekbar;
        //Choices
        private ArrayList<Button> mChoiceBtns;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View itemView, int questionType) {
            super(itemView);

            context = itemView.getContext();
            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
            httpTest1 = (Button) itemView.findViewById(R.id.test_http_button);

            if (questionType == 0) { // Start Card
                mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
                mQuestionTextView.setText(questionNumber+". "+question);

            } else if (questionType == 1) { // Choices card
                mQuestionTextView = (TextView) itemView.findViewById(R.id.question_textview);
                mQuestionTextView.setText(questionNumber+". "+question);
                // Add Slider!
            } else { // Slider Card
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
        holder.httpTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataset.add(dataset.size(), "This is a new index!"+dataset.size()+1);
                // get data from server.
                new NetRequest(0, new PostAsync() {
                    @Override
                    public void PostAsyncTask(String json) {

                        //questionDataSet.add(questionDataSet.size(), json);
                    }
                }).execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
