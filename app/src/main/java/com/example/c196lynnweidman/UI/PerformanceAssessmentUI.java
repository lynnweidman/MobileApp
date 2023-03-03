package com.example.c196lynnweidman.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196lynnweidman.DATABASE.Repository;
import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PerformanceAssessmentUI extends AppCompatActivity {
    int performanceID;
    int courseID;

    String performanceAssessmentName;
    String performanceAssessmentDate;
    EditText editPerformanceName;
    TextView editPerformanceDate;
    TextView editCourseID;
    EditText editNote;


    DatePickerDialog.OnDateSetListener setUpDatePicker;
    DatePickerDialog.OnDateSetListener setUpDatePickerEnd;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();

    PerformanceAssessment currentPerformanceAssessment;
    Repository repository;
    String myFormat = "MM-dd-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    @Override
  protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        repository = new Repository(getApplication());
        setContentView(R.layout.activity_performance_assessment);



        performanceID = getIntent().getIntExtra("performanceID", -1);

        performanceAssessmentName = getIntent().getStringExtra("performanceAssessmentName");
        editPerformanceName = findViewById(R.id.performanceNameEdit);
        editPerformanceName.setText(performanceAssessmentName);

        performanceAssessmentDate = getIntent().getStringExtra("performanceAssessmentDate");
        editPerformanceDate = findViewById(R.id.performanceDateEdit);
        editPerformanceDate.setText(performanceAssessmentDate);
        //editPerformanceDate.setText(sdf.format(myCalendarStart.getTime()));


        courseID = getIntent().getIntExtra("courseID", -1);
        editCourseID = findViewById(R.id.courseIDEdit);
        editCourseID.setText(Integer.toString(courseID));

        // editNote = findViewById(R.id.note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.performanceRecycler);
        repository = new Repository(getApplication());
        List<PerformanceAssessment> allPerformanceAssessments = repository.getAllPerformanceAssessments();
        final PerformanceAdapter performanceAdapter = new PerformanceAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(performanceAdapter);

        List<PerformanceAssessment> filteredPerformance = new ArrayList<>();
        for (PerformanceAssessment p : repository.getAllPerformanceAssessments()) {
            if (p.getCourseID() == courseID) {
                filteredPerformance.add(p);
            }
        }
        performanceAdapter.setPerformanceAssessment(filteredPerformance);


        setUpDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto generated stub
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };


        editPerformanceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fixme auto generated stub
                Date date;
                //fixme get value from other screen
                String info = editPerformanceDate.getText().toString();

                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(PerformanceAssessmentUI.this, setUpDatePicker, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "MM-dd-yyyy"; //fixme in which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editPerformanceDate.setText(sdf.format(myCalendarStart.getTime()));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_performance, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        //repository = new Repository(getApplication());
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;


            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notifyStart:
                String startDateFromScreen = editPerformanceDate.getText().toString();
                String testName = editPerformanceName.getText().toString();
                String myFormat = "MM-dd-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myStartDate = null;
                try {
                    myStartDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (myStartDate != null) {
                    Long trigger = myStartDate.getTime();
                    Intent intent = new Intent(PerformanceAssessmentUI.this, MyReceiver.class);
                    intent.putExtra("key", "Your " + testName + " test starts on "  + startDateFromScreen);
                    PendingIntent sender = PendingIntent.getBroadcast(PerformanceAssessmentUI.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }




    public void deleteAssessment(View view) {
        for (PerformanceAssessment performance : repository.getAllPerformanceAssessments()) {

            if (performance.getPerformanceID() == performanceID)
                currentPerformanceAssessment = performance;
        }

        repository.delete(currentPerformanceAssessment);
        Toast.makeText(PerformanceAssessmentUI.this, "Assessment with ID- " + currentPerformanceAssessment.getPerformanceID() + " was deleted", Toast.LENGTH_LONG).show();
    }

    public void saveAssessment(View view) {
        PerformanceAssessment p;


        if(editPerformanceName.getText().toString().matches("") || editPerformanceDate.getText().toString().matches("")) {
            Toast.makeText( PerformanceAssessmentUI.this, "Fill in all text fields", Toast.LENGTH_LONG).show();
            return;
        }
        if (performanceID == -1) {

            if (repository.getAllPerformanceAssessments().size() == 0)
                performanceID = 1;
            else
                performanceID = repository.getAllPerformanceAssessments().get(repository.getAllPerformanceAssessments().size() - 1).getPerformanceID() + 1;
            p = new PerformanceAssessment(performanceID, editPerformanceName.getText().toString(), editPerformanceDate.getText().toString(), courseID);
            repository.insert(p);
        } else {
            p = new PerformanceAssessment(performanceID, editPerformanceName.getText().toString(), editPerformanceDate.getText().toString(), courseID);
            repository.update(p);
        }
    }
/*
    @Override
    protected void onResume() {

        super.onResume();
        List<PerformanceAssessment> allPerformance=repository.getAllPerformanceAssessments();
        RecyclerView performanceRecycler=findViewById(R.id.performanceRecycler);
        final PerformanceAdapter performanceAdapter=new PerformanceAdapter(this);
        performanceRecycler.setAdapter(performanceAdapter);
        performanceRecycler.setLayoutManager(new LinearLayoutManager(this));
        performanceAdapter.setPerformanceAssessment(allPerformance);
    }*/

    }

