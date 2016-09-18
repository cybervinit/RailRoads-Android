package com.example.railroads.railroads;

import android.content.Context;
import android.graphics.Color;
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
public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int START_CARD = 0;
    private final int CHOICES_CARD = 1;
    private final int SLIDER_CARD = 2;

    private ArrayList<AdvQuestion> dataset;
    private int questionType;

    public CustomAdapter (ArrayList<AdvQuestion> dataset) {
        this.dataset = dataset;
    }

    public static class ViewHolder0 extends RecyclerView.ViewHolder {
        private TextView mStartTips;
        private Button httpTest1;

        public ViewHolder0(View itemView) {
            super(itemView);
            mStartTips = (TextView) itemView.findViewById(R.id.start_textview);
            httpTest1 = (Button) itemView.findViewById(R.id.begin_button);
        }
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView mQuestionTextView;
        private Button httpTest1;

        //Choices
        private ArrayList<Button> mChoiceBtns;

        public ViewHolder1(View itemView) {
            super(itemView);
            mQuestionTextView = (TextView) itemView.findViewById(R.id.choices_question_textview);
            httpTest1 = (Button) itemView.findViewById(R.id.next_choices_button);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView mQuestionTextView;
        private Button httpTest1;

        //Choices
        private SeekBar mSeekBar;

        public ViewHolder2(View itemView) {
            super(itemView);
            mQuestionTextView = (TextView) itemView.findViewById(R.id.slider_question_textview);
            httpTest1 = (Button) itemView.findViewById(R.id.next_slider_button);
            mSeekBar = (SeekBar) itemView.findViewById(R.id.seekBar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataset.get(position).getQuestionType() == 0) {
            return START_CARD;
        } else if (dataset.get(position).getQuestionType() == 1) {
            return CHOICES_CARD;
        } else if (dataset.get(position).getQuestionType() == 2) {
            return SLIDER_CARD;
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_choices, parent, false);
        if (viewType == START_CARD) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_start, parent, false);
            return new ViewHolder0(v);
        } else if (viewType == CHOICES_CARD) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_choices, parent, false);
            return new ViewHolder1(v);
        } else if (viewType == SLIDER_CARD) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_slider, parent, false);
            return new ViewHolder2(v);
        } else {
            return null;
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);
        if (itemType == START_CARD) {
            ((ViewHolder0)holder).mStartTips.setText(R.string.instructions);
            ((ViewHolder0)holder).httpTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get data from server.
                    new NetRequest("GET", 0, new PostAsync() {
                        @Override
                        public void PostAsyncTask(String json) {
                            dataset.add(dataset.size(), new AdvQuestion(json, 1));
                            //questionDataSet.add(questionDataSet.size(), json);
                        }
                    }).execute();
                    dataset.add(dataset.size(), new AdvQuestion("This is a test", 1));
                    notifyDataSetChanged();
                }
            });


        } else if (itemType == CHOICES_CARD) {
            ((ViewHolder1)holder).mQuestionTextView.setText(dataset.get(position).getQuestion());
            ((ViewHolder1)holder).httpTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataset.add(dataset.size(), new AdvQuestion("This is Question 2!", 2));

                    // get data from server.
                    new NetRequest("GET", 0, new PostAsync() {
                        @Override
                        public void PostAsyncTask(String json) {
                            //questionDataSet.add(questionDataSet.size(), json);
                        }
                    }).execute();
                    notifyDataSetChanged();
                }
            });
        } else if (itemType == SLIDER_CARD) {
            ((ViewHolder2)holder).mQuestionTextView.setText(dataset.get(position).getQuestion());
            ((ViewHolder2)holder).httpTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataset.add(dataset.size(), new AdvQuestion("This is Question 3!", 1));
                    new NetRequest("GET", 0, new PostAsync() {
                        @Override
                        public void PostAsyncTask(String json) {
                        }
                    }).execute();
                    notifyDataSetChanged();
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
