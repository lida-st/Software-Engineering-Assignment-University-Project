package com.example.sugarsweet.view.Diabetic.Statistics;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.MeasurementsDAO;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.util.Tuple;
import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.domain.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyStatsActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView avgBloodSugarTextView;
    private TextView weeklyAverageTextView;
    private Map<String, List<Float>> bloodSugarData = new HashMap<>();
    private MeasurementsDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weekly_stats);

        calendarView = findViewById(R.id.calendarView);
        avgBloodSugarTextView = findViewById(R.id.avgBldSgrTextView);
        weeklyAverageTextView = findViewById(R.id.weeklyAverageTextView);

        dao = new MemoryInitializer().getMeasurementsDAO();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        List<Tuple<String, BloodSugarLog>> measurements = dao.findAll(user.getPassword());

        for (Tuple<String, BloodSugarLog> tuple : measurements) {
            if (tuple != null && tuple.y != null) {
                String date = tuple.y.getLogDate() != null ? tuple.y.getLogDate().toString() : "No Date";
                float value = tuple.y.getUser().getSugarLvl();

                if (!bloodSugarData.containsKey(date)) {
                    bloodSugarData.put(date, new ArrayList<>());
                }
                bloodSugarData.get(date).add(value);
            }
        }


        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();

        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        long oneWeekAgo = calendar.getTimeInMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = sdf.format(oneWeekAgo);
        String formattedCurrentDate = sdf.format(currentDate);


        calendarView.setMinDate(oneWeekAgo);
        calendarView.setMaxDate(currentDate);
        calendarView.setDate(currentDate, false, true);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            calculateAndDisplayAverage(selectedDate);
        });

        calculateAndDisplayWeeklyAverage(formattedStartDate,formattedCurrentDate);

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finish());

    }

    private void calculateAndDisplayWeeklyAverage(String startDate, String endDate) {
        float sum = 0;
        int count = 0;

        for (Map.Entry<String, List<Float>> entry : bloodSugarData.entrySet()) {
            String date = entry.getKey();
            if (date != null && !date.equals("No Date")) {
                if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
                    List<Float> measurements = entry.getValue();
                    for (float measurement : measurements) {
                        sum += measurement;
                        count++;
                    }
                }
            }
        }

        if (count > 0) {
            float weeklyAverage = sum / count;
            weeklyAverageTextView.setText(String.format("Weekly Average: %.2f mg/dL", weeklyAverage));
        } else {
            weeklyAverageTextView.setText("No data available for this week.");
        }
    }

    private void calculateAndDisplayAverage(String date) {
        if (bloodSugarData.containsKey(date)) {
            List<Float> measurements = bloodSugarData.get(date);
            float sum = 0;
            for (float measurement : measurements) {
                sum += measurement;
            }
            float average = sum / measurements.size();
            avgBloodSugarTextView.setText(String.format("Average: %.2f mg/dL", average));
        } else {
            avgBloodSugarTextView.setText("No data available for this date.");
        }

    }

}
