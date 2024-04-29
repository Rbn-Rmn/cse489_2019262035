package com.example.cse489_2019262035;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {
    TextView tvName, tvID;
    EditText etDate, etLecture, etTopic, etSummary;
    RadioGroup radioGrp1, radioGrp2;
    RadioButton radioBtn1, radioBtn2, radioBtn3, radioBtn4, radioBtnTheory, radioBtnLab;
    Button btnCancel, btnSave;

    String keyId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvID = findViewById(R.id.tvID);
        etDate = findViewById(R.id.etDate);
        etLecture = findViewById(R.id.etLecture);
        etTopic = findViewById(R.id.etTopic);
        etSummary = findViewById(R.id.etSummary);
        radioGrp1 = findViewById(R.id.radioGrp1);
        radioGrp2 = findViewById(R.id.radioGrp2);
        radioGrp3 = findViewById(R.id.radioGrp3);
        radioBtn1 = findViewById(R.id.radioBtn1);
        radioBtn2 = findViewById(R.id.radioBtn2);
        radioBtn3 = findViewById(R.id.radioBtn3);
        radioBtn4 = findViewById(R.id.radioBtn4);
        radioBtnTheory = findViewById(R.id.radioBtnTheory);
        radioBtnLab = findViewById(R.id.radioBtnLab);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        ClassSummaryDB db = new ClassSummaryDB(this);
        String errormssg = "";
        String name = tvName.getText().toString();
        String id = tvID.getText().toString();
        String date = etDate.getText().toString();
        String course = "";
        String type = "";
        String lecture = etLecture.getText().toString();
        String topic = etTopic.getText().toString();
        String summary = etSummary.getText().toString();
        if(radioBtn1.isChecked()){
            course = radioBtn1.getText().toString();
        }
        if(radioBtn2.isChecked()){
            course = radioBtn2.getText().toString();
        }
        if(radioBtn3.isChecked()){
            course = radioBtn3.getText().toString();
        }
        if(radioBtn4.isChecked()){
            course = radioBtn4.getText().toString();
        }
        if(radioBtnTheory.isChecked()){
            type = radioBtnTheory.getText().toString();
        }
        if(radioBtnLab.isChecked()){
            type = radioBtnLab.getText().toString();
        }

//        int selectedRadio1Id = radioGrp1.getCheckedRadioButtonId();
//        int selectedRadio2Id = radioGrp2.getCheckedRadioButtonId();

        // Validate input fields
        if(name.isEmpty()){
            errormssg += "Please Insert Name";
        }
        if(id.isEmpty()){
            errormssg += "\nPlease Insert ID";
        }
        if(date.isEmpty()){
            errormssg += "\nPlease Insert Date ";
        }
        if(course.isEmpty()){
            errormssg += "\nPlease Select course ";
        }
        if(type.isEmpty()){
            errormssg += "\nPlease Select type ";
        }
        if(lecture.isEmpty()){
            errormssg += "\nPlease Select lecture ";
        }
        if(topic.isEmpty()){
            errormssg += "\nPlease Select topic ";
        }
        if(summary.isEmpty()){
            errormssg += "\nPlease Select summary ";
        }

        if (!errormssg.isEmpty()) {
            showErrorDialog(errormssg);
            return;
        }
        else{
            if(keyId.isEmpty()){
                keyId = topic + System.currentTimeMillis();
                db.insertLecture(keyId, course, type, date, lecture,topic, summary);
                showSuccess("Inserted");
            }
            else{
                db.updateLecture(keyId, course, type, date, lecture,topic, summary);
                showSuccess("Updated");
            }
        }

    }

    private void showErrorDialog(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setTitle("Error");
        builder.setCancelable(true);
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showSuccess(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setTitle("Successfull");
        builder.setCancelable(true);
        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
