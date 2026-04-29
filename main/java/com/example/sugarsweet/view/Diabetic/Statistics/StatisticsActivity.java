package com.example.sugarsweet.view.Diabetic.Statistics;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.domain.User;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);

        Button monthlyButton = findViewById(R.id.Monthly);
        Button weeklyButton = findViewById(R.id.Weekly);
        Button deviationButton = findViewById(R.id.Deviation);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        monthlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose("month",user);
            }
        });
        
        weeklyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose("week",user);
            }
        });

        deviationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose("deviation",user);
            }
        });
        
    }

    public void choose(String choice,User user) {
        Intent intent;
        switch (choice) {
            case "month":
                intent = new Intent(this, MonthlyStatsActivity.class);
                intent.putExtra("User", user);
                break;
            case "week":
                intent = new Intent(this, WeeklyStatsActivity.class);
                intent.putExtra("User", user);
                break;
            case "deviation":
                intent = new Intent(this, DeviationStatsActivity.class);
                intent.putExtra("User", user);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}