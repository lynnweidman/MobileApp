package com.example.c196lynnweidman.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.c196lynnweidman.DATABASE.Repository;
import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {
    int courseID;
    String courseName;
    String start;
    String end;
    String status;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String note;
    int termID;
    int performanceID;
    int objectiveID;

    EditText editName;
    TextView editStart;
    TextView editEnd;
    EditText editStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editEmail;
    EditText editTermID;
    EditText editNote;
    EditText editDate;
    DatePickerDialog.OnDateSetListener setUpDatePicker;
    DatePickerDialog.OnDateSetListener setUpDatePickerEnd;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    CoursesEntity course;
    CoursesEntity currentCourse;
    Repository repository;
    String myFormat = "MM-dd-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        List<PerformanceAssessment> allPerformanceAssessments = repository.getAllPerformanceAssessments();
        List<ObjectiveAssessment> allObjectiveAssessments = repository.getAllObjectiveAssessments();
        final PerformanceAdapter performanceAdapter = new PerformanceAdapter(this);
        final ObjectiveAdapter objectiveAdapter = new ObjectiveAdapter(this);


        performanceAdapter.setPerformanceAssessment(allPerformanceAssessments);
        objectiveAdapter.setObjectiveAssessment(allObjectiveAssessments);


        courseName = getIntent().getStringExtra("courseName");
        editName = findViewById(R.id.coursenameEdit);
        editName.setText(courseName);

        start = getIntent().getStringExtra("start");
        editStart = findViewById(R.id.courseStartEdit);

        editStart.setText(start);
       // editStart.setText(sdf.format(myCalendarStart.getTime()));



        end = getIntent().getStringExtra("end");
        editEnd = findViewById(R.id.courseEndEdit);
        editEnd.setText(end);
       // editEnd.setText(sdf.format(myCalendarEnd.getTime()));

       /* status = getIntent().getStringExtra("status");
        editStatus = findViewById(R.id.statusEdit);
        editStatus.setText(status);*/

        instructorName = getIntent().getStringExtra("instructorName");
        editInstructorName = findViewById(R.id.instructorNameEdit);
        editInstructorName.setText(instructorName);

        instructorPhone = getIntent().getStringExtra("instructorPhone");
        editInstructorPhone = findViewById(R.id.instructorPhoneEdit);
        editInstructorPhone.setText(instructorPhone);

        instructorEmail = getIntent().getStringExtra("instructorEmail");
        editEmail = findViewById(R.id.emailEdit);
        editEmail.setText(instructorEmail);

        courseID = getIntent().getIntExtra("courseID", -1);

        termID = getIntent().getIntExtra("termID", -1);
       /* editTermID = findViewById(R.id.termIDEdit);
        editTermID.setText(Integer.toString(termID));*/

        note = getIntent().getStringExtra("note");
        editNote = findViewById(R.id.note);
        editNote.setText(note);



//set up spinner

        String[] statusList= {"In progress", "Completed", "Dropped", "Plan to take"};
        Spinner statusSpinner=(Spinner) findViewById(R.id.statusEdit);

        ArrayAdapter statusAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,statusList);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        //statusSpinner.setSelection(1);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = statusList[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // another interface callback
            }
        });



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

        setUpDatePickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto generated stub
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };


        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fixme auto generated stub
                Date date;
                //fixme get value from other screen
                String info = editStart.getText().toString();

                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, setUpDatePicker, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }


            public void onDateSetEnd(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto generated stub

                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        });

        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fixme auto generated stub
                Date date;
                //fixme get value from other screen
                String info = editEnd.getText().toString();
                if (info.equals("")) info = "02/10/23"; //fixme cut out
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, setUpDatePickerEnd, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }



            private void updateLabelStart() {
        String myFormat = "MM-dd-yyyy"; //fixme in which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStart.setText(sdf.format(myCalendarStart.getTime()));

    }

    private void updateLabelEnd() {
        String myFormat = "MM-dd-yyyy"; //fixme in which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnd.setText(sdf.format(myCalendarEnd.getTime()));

    }


    ArrayList<CoursesEntity> courseList = new ArrayList<>(); //fixme


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursedetails, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
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

                String startDateFromScreen = editStart.getText().toString();
                String courseName = editName.getText().toString();
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
                    Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
                    intent.putExtra("key", "Your course " + courseName + " is starting on " + startDateFromScreen);
                    PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
            case R.id.notifyEnd:

                String endDateFromScreen = editEnd.getText().toString();
                String courseName2 = editName.getText().toString();
                String myEndFormat = "MM-dd-yyyy";
                SimpleDateFormat sdfEnd = new SimpleDateFormat(myEndFormat, Locale.US);
                Date myEndDate = null;
                try {
                    myEndDate = sdfEnd.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerEnd = myEndDate.getTime();
                Intent intentEnd = new Intent(CourseDetails.this, MyReceiver.class);
                intentEnd.putExtra("key", "Your course " + courseName2 + " is starting on " + endDateFromScreen);
                PendingIntent senderEnd = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++, intentEnd, 0);//PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveCourseClicked(View view) {
        CoursesEntity course;


        if(editName.getText().toString().matches("") || editStart.getText().toString().matches("") || editEnd.getText().toString().matches("") || status.matches("")
        || editInstructorName.getText().toString().matches("") || editInstructorPhone.getText().toString().matches("") || editEmail.getText().toString().matches("")) {
            Toast.makeText( CourseDetails.this, "Fill in all text fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (courseID == -1) {

            if (repository.getAllCourses().size() == 0)
                courseID = 1;
            else
                courseID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            course = new CoursesEntity(courseID, editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), status,
                    editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editEmail.getText().toString(), termID, editNote.getText().toString());//Integer.parseInt(String.valueOf(editTermID.getText())));
            repository.insert(course);
        } else {
            course = new CoursesEntity(courseID, editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), status,
                    editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editEmail.getText().toString(),termID, editNote.getText().toString()); //Integer.parseInt(String.valueOf(editTermID.getText())));
            repository.update(course);
        }
    }


        public void deleteCourse (View view){
            for (CoursesEntity c : repository.getAllCourses()) {

                if (c.getCourseID() == courseID) currentCourse = c;
            }

                repository.delete(currentCourse);
                Toast.makeText(CourseDetails.this, currentCourse.getCourseName() + " was deleted", Toast.LENGTH_LONG).show();
            }




    public void toNewPerformance(View view) {
        Intent i = new Intent(CourseDetails.this, PerformanceAssessmentUI.class);
        i.putExtra("courseID", courseID);
        startActivity(i);
    }

    public void toNewObjective(View view) {
        Intent i = new Intent(this, ObjectiveAssessmentUI.class);
       i.putExtra("courseID", courseID);
        startActivity(i);
    }



}




