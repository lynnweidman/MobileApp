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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196lynnweidman.DATABASE.Repository;
import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseList extends AppCompatActivity {
    EditText editTerm;
    TextView editStart;
    TextView editEnd;
    EditText editTermID;
    EditText editNote;
    int termID;
    String name;
    String start;
    String end;
    Repository repo;
    TermsEntity term;
    TermsEntity currentTerm;
    int numCourses;
    DatePickerDialog.OnDateSetListener setUpDatePicker;
    DatePickerDialog.OnDateSetListener setUpDatePickerEnd;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    String myFormat = "MM-dd-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        repo = new Repository(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courselist);

        editTerm = findViewById(R.id.editTermName);
        editStart = findViewById(R.id.editTermStart);
        editEnd = findViewById(R.id.editTermEnd);
        //editTermID = findViewById(R.id.edittermID);


        name = getIntent().getStringExtra("termName");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        termID = getIntent().getIntExtra("termID", -1);
        editTerm.setText(name);
        editStart.setText(start);
        editEnd.setText(end);

        // editNote = findViewById(R.id.note);
       // editTermID.setText(Integer.toString(termID));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.associatedCourseRecyclerView);
        repo = new Repository(getApplication());
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);

        List<CoursesEntity> filteredCourses = new ArrayList<>();
        for (CoursesEntity c : repo.getAllCourses()) {
            if (c.getTermID() == termID) {
                filteredCourses.add(c);
            }
        }
        courseAdapter.setCourse(filteredCourses);



        setUpDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto genterated stub
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        setUpDatePickerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto genterated stub
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
                new DatePickerDialog(CourseList.this, setUpDatePicker, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }


            public void onDateSetEnd(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //fixme auto genterated stub

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
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseList.this, setUpDatePickerEnd, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
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


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_termsdetails, menu);
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
                String termName = editTerm.getText().toString();
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
                    Intent intent = new Intent(CourseList.this, MyReceiver.class);
                    intent.putExtra("key", "Your term " + termName + " starts on " + startDateFromScreen);
                    PendingIntent sender = PendingIntent.getBroadcast(CourseList.this, ++MainActivity.numAlert, intent,  0);//PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
            case R.id.notifyEnd:

                String endDateFromScreen = editEnd.getText().toString();
                String termName2 = editTerm.getText().toString();
                String myEndFormat = "MM-dd-yyyy";
                SimpleDateFormat sdfEnd = new SimpleDateFormat(myEndFormat, Locale.US);
                Date myEndDate = null;
                try {
                    myEndDate = sdfEnd.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerEnd = myEndDate.getTime();
                Intent intentEnd = new Intent(CourseList.this, MyReceiver.class);
                intentEnd.putExtra("key", "Your term " + termName2 + " starts on " + endDateFromScreen);
                PendingIntent senderEnd = PendingIntent.getBroadcast(CourseList.this, MainActivity.numAlert++, intentEnd, 0);//PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveTerm(View view) {
        TermsEntity term;

        if(editTerm.getText().toString().matches("") || editStart.getText().toString().matches("") || editEnd.getText().toString().matches("")) {
            Toast.makeText( CourseList.this, "Fill in all text fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (termID == -1) {
            if (repo.getAllTerms().size() == 0)
                termID = 1;
             termID = repo.getAllTerms().get(repo.getAllTerms().size() - 1).getTermID() + 1;
            term = new TermsEntity(termID, editTerm.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
            repo.insert(term);
        } else {
            term = new TermsEntity(termID, editTerm.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
            repo.update(term);
        }
    }


   /* @Override
    protected void onResume() {

        super.onResume();
        List<CoursesEntity> allCourses=repo.getAllCourses();
        RecyclerView courseRecycler=findViewById(R.id.associatedCourseRecyclerView);
        final CourseAdapter courseAdapter=new CourseAdapter(this);
        courseRecycler.setAdapter(courseAdapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourse(allCourses);
    }*/
    public void toCourseDetails(View v) {
        Intent i = new Intent(CourseList.this, CourseDetails.class);
        i.putExtra("termID", termID);
        startActivity(i);
    }

    public void onDeleteTerm(View view) {
        //repo.delete((TermsEntity) repo.getAllTerms());
        for (TermsEntity term : repo.getAllTerms()) {

            if (term.getTermID() == termID) currentTerm = term;
        }

        numCourses = 0;
        for (CoursesEntity course : repo.getAllCourses()) {
            if (course.getTermID() == termID) ++numCourses;
        }

        if (numCourses == 0) {
            repo.delete(currentTerm);
            Toast.makeText(CourseList.this, currentTerm.getTermName() + " was deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CourseList.this, "Can't delete a product with parts", Toast.LENGTH_LONG).show();
        }


    }
}