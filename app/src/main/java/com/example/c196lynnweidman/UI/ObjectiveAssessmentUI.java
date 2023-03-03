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

public class ObjectiveAssessmentUI extends AppCompatActivity {
    int objectiveID;
    int courseID;

    String objectiveAssessmentName;
    String objectiveAssessmentDate;
    EditText editObjectiveName;
    TextView editObjectiveDate;
    TextView editCourseID;
    EditText editNote;

    DatePickerDialog.OnDateSetListener setUpDatePicker;
    DatePickerDialog.OnDateSetListener setUpDatePickerEnd;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    ObjectiveAssessment objectiveAssessment;
    ObjectiveAssessment currentObjectiveAssessment;
    Repository repository;
    String myFormat = "MM-dd-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());


        objectiveID = getIntent().getIntExtra("objectiveID", -1);

        objectiveAssessmentName = getIntent().getStringExtra("objectiveAssessmentName");
        editObjectiveName = findViewById(R.id.objectiveNameEdit);
        editObjectiveName.setText(objectiveAssessmentName);

        objectiveAssessmentDate = getIntent().getStringExtra("objectiveAssessmentDate");
        editObjectiveDate = findViewById(R.id.objectiveDateEdit);
        editObjectiveDate.setText(objectiveAssessmentDate);
        //editObjectiveDate.setText(sdf.format(myCalendarStart.getTime()));




        courseID = getIntent().getIntExtra("courseID", -1);
        editCourseID = findViewById(R.id.courseIDEdit);
        editCourseID.setText(Integer.toString(courseID));

       // editNote = findViewById(R.id.note);

        List<ObjectiveAssessment> allObjectiveAssessments = repository.getAllObjectiveAssessments();


        RecyclerView recyclerView = findViewById(R.id.objectiveRecycler);
        repository = new Repository(getApplication());

        final ObjectiveAdapter objectiveAdapter = new ObjectiveAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(objectiveAdapter);

        List<ObjectiveAssessment> filteredObjective = new ArrayList<>();
        for (ObjectiveAssessment o : repository.getAllObjectiveAssessments()) {
            if (o.getCourseID() == courseID) {
                filteredObjective.add(o);
            }
        }
        objectiveAdapter.setObjectiveAssessment(filteredObjective);


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


        editObjectiveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fixme auto generated stub
                Date date;
                //fixme get value from other screen
                String info = editObjectiveDate.getText().toString();

                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(ObjectiveAssessmentUI.this, setUpDatePicker, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabelStart() {
        String myFormat = "MM-dd-yyyy"; //fixme in which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editObjectiveDate.setText(sdf.format(myCalendarStart.getTime()));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_objective, menu);
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

                String startDateFromScreen = editObjectiveDate.getText().toString();
                String testName = editObjectiveName.getText().toString();
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
                    Intent intent = new Intent(ObjectiveAssessmentUI.this, MyReceiver.class);
                    intent.putExtra("key", "Your " + testName + " test starts on " + startDateFromScreen);
                    PendingIntent sender = PendingIntent.getBroadcast(ObjectiveAssessmentUI.this, MainActivity.numAlert++, intent,  PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
        }

            return super.onOptionsItemSelected(item);
        }





    public void saveObjectiveAssessment(View view) {
        ObjectiveAssessment objectiveAssessment1;

        if(editObjectiveName.getText().toString().matches("") || editObjectiveDate.getText().toString().matches("")) {
            Toast.makeText( ObjectiveAssessmentUI.this, "Fill in all text fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (objectiveID== -1) {

            if (repository.getAllObjectiveAssessments().size() == 0)
                objectiveID= 1;
            else
                objectiveID = repository.getAllObjectiveAssessments().get(repository.getAllObjectiveAssessments().size() - 1).getObjectiveID() + 1;

            objectiveAssessment1 = new ObjectiveAssessment(objectiveID, editObjectiveName.getText().toString(), editObjectiveDate.getText().toString(), courseID);
            repository.insert(objectiveAssessment1);
        } else {
            objectiveAssessment1 = new ObjectiveAssessment(objectiveID, editObjectiveName.getText().toString(), editObjectiveDate.getText().toString(), courseID);
            repository.update(objectiveAssessment1);
        }
    }

    public void deleteAssessment(View view) {
        for (ObjectiveAssessment objective : repository.getAllObjectiveAssessments()) {

            if (objective.getObjectiveID() == objectiveID) currentObjectiveAssessment = objective;
        }

        repository.delete(currentObjectiveAssessment);
        Toast.makeText(ObjectiveAssessmentUI.this, "Assessment with ID- " + currentObjectiveAssessment.getObjectiveID() + " was deleted", Toast.LENGTH_LONG).show();
    }

   /* @Override
    protected void onResume() {

        super.onResume();
        List<ObjectiveAssessment> allObjective=repository.getAllObjectiveAssessments();
        RecyclerView objectiveRecycler=findViewById(R.id.objectiveRecycler);
        final ObjectiveAdapter objectiveAdapter=new ObjectiveAdapter(this);
        objectiveRecycler.setAdapter(objectiveAdapter);
        objectiveRecycler.setLayoutManager(new LinearLayoutManager(this));
        objectiveAdapter.setObjectiveAssessment(allObjective);
    }*/
}
