package com.example.railroads.railroads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mStartBtn;
    TextView checkText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartBtn = (Button) findViewById(R.id.start_button);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AdventurePlayActivity.class));
            }
        });

        Button checkHTTPBtn = new Button(getBaseContext());
        checkText = new TextView(getBaseContext());

        checkHTTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NetRequest(1, new PostAsync() {
                    @Override
                    public void PostAsyncTask(String json) {
                        checkText.setText(json);
                    }
                }).execute();
            }
        });

    }
}
