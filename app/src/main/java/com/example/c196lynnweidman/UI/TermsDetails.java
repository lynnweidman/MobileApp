package com.example.c196lynnweidman.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TermsDetails extends AppCompatActivity {
    int termID;
    String name;
    String start;
    String end;
    EditText editName;
    TextView editStart;
    TextView editEnd;
    EditText editNote;
    Repository repository;
    TermsEntity currentTerm;
    int id;
    TermsEntity term;
    CoursesEntity courses;
    DatePickerDialog.OnDateSetListener setUpDatePicker;
    DatePickerDialog.OnDateSetListener setUpDatePickerEnd;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        String myFormat = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        name = getIntent().getStringExtra("termName");
        editName = findViewById(R.id.termnameEdit);
        editName.setText(name);

        start = getIntent().getStringExtra("start");
        editStart = findViewById(R.id.termStartEdit);
        editStart.setText(start);

        end = getIntent().getStringExtra("end");
        editEnd = findViewById(R.id.termEndEdit);
        editEnd.setText(end);

        termID = getIntent().getIntExtra("termID", -1);

        /*RecyclerView recyclerView = findViewById(R.id.courselistrecyclerview);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/



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
                new DatePickerDialog(TermsDetails.this, setUpDatePicker, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
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
                new DatePickerDialog(TermsDetails.this, setUpDatePickerEnd, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
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





    public void saveNewTerm(View view) {
        TermsEntity term;

        if(editName.getText().toString().matches("") || editStart.getText().toString().matches("") || editEnd.getText().toString().matches("")) {
            Toast.makeText( TermsDetails.this, "Fill in all text fields", Toast.LENGTH_LONG).show();
           return;
        }


        if (termID == -1) {
            if (repository.getAllTerms().size() == 0)
                termID = 1;
            else
                termID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermID() + 1;
            term = new TermsEntity(termID, editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
            repository.insert(term);
        }else {
            term = new TermsEntity(termID, editName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString());
            repository.update(term);
        }
    }

    public void addCourse(View view) {
        Intent i = new Intent(this, CourseDetails.class);
        i.putExtra("termID", termID);
        startActivity(i);
    }
}













