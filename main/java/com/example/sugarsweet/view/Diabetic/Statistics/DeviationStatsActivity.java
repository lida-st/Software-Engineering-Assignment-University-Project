package com.example.sugarsweet.view.Diabetic.Statistics;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.MeasurementsDAO;
import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.util.Tuple;

import java.util.ArrayList;

public class DeviationStatsActivity extends AppCompatActivity {

    private MeasurementsDAO measurementsDAO;

    private ArrayList<Tuple<String,BloodSugarLog>> measurements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deviation_stats);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        Button ExitButton = findViewById(R.id.exitButton);
        TextView tv = findViewById(R.id.lasttextview);
        TextView tv2 = findViewById(R.id.averagetextview);
        TextView tv3 = findViewById(R.id.deviationtextview);

        measurementsDAO = new MemoryInitializer().getMeasurementsDAO();
        measurements = measurementsDAO.findAll(user.getPassword());

        int count = 0;
        int sum = 0;
        int s;
        double average = 0.0d;
        for (Tuple<String,BloodSugarLog> m  : measurements){
            if (m.x.equals(user.getPassword())) {
                count += 1;
                BloodSugarLog n = (BloodSugarLog) m.y;
                User u = (User) n.getUser();
                s = u.getSugarLvl();
                sum += s;
            }
        }
        if (count != 0) {
            average = (double) sum / count;
        }
        tv.setText(String.valueOf(user.getSugarLvl()));
        tv2.setText(String.valueOf(average));
        tv3.setText(String.valueOf(user.getSugarLvl()-average));
        ExitButton.setOnClickListener(v -> {
            finish();
        });

    }
}