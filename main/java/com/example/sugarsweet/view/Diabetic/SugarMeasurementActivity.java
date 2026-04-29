package com.example.sugarsweet.view.Diabetic;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.MeasurementsDAO;
import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.domain.InsulinAdministration;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.util.Tuple;

public class SugarMeasurementActivity extends AppCompatActivity {

    private MeasurementsDAO measurementsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sugar_measurement);

        LocalDate currentDate = LocalDate.now();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        EditText sugarLevelInput = findViewById(R.id.sugarLevelIn);
        Button FinishButton = findViewById(R.id.finishButton);
        TextView tv = findViewById(R.id.advicetextview);
        TextView tv2 = findViewById(R.id.changeTextView);

        measurementsDAO = new MemoryInitializer().getMeasurementsDAO();

        FinishButton.setOnClickListener(v -> {
            String sugarLevel = sugarLevelInput.getText().toString();
            if (sugarLevel.isEmpty() || Integer.parseInt(sugarLevel) <= 39 || Integer.parseInt(sugarLevel) >= 501 ) {
                sugarLevelInput.setError("Sugar level should be between 40 and 500");
            }else {
                user.setSugarLvl(Integer.parseInt(sugarLevel));
                BloodSugarLog bsl = new BloodSugarLog(user,currentDate);
                Tuple<String,BloodSugarLog> t = new Tuple(user.getPassword(),bsl);
                measurementsDAO.save(t);
                InsulinAdministration ia = new InsulinAdministration(user);
                ia.advice(bsl,tv);
            }
            tv2.setText("Press the button to exit the sugar measurement registration");
            FinishButton.setOnClickListener(v1 -> {
                finish();
            });
        });

    }

}