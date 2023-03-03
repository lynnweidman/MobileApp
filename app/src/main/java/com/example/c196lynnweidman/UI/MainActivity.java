package com.example.c196lynnweidman.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196lynnweidman.DATABASE.Repository;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.R;

public class MainActivity extends AppCompatActivity {
    private Repository repository;
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //add a line of code.
    }

    public void toAddTerm(View v) {
        //Intent i = new Intent(this, CourseList.class);
        Intent i = new Intent(this, TermsDetails.class);
        startActivity(i);
    }
    public void toTermsList(View v) {
        Intent i = new Intent(this, TermsList.class);
        startActivity(i);
    }

    public void EnterHere(View view) {


        Intent i= new Intent(MainActivity.this, TermsList.class);
        startActivity(i);
        Repository repo= new Repository(getApplication());
       /* PerformanceAssessment p = new PerformanceAssessment(0, "P1", "02-28-2023", 1);
        repo.insert(p);*/
       /* TermsEntity term= new TermsEntity( 0,"Fall11", "09-01-2023", "12-31-2023"  );
        repo.insert(term);*/

       /* CoursesEntity course= new CoursesEntity( 0,"SS", "01-01-2024", "08-31-2024", "Current", "Lynn",
                "888-888-8888", "email", 1);
        repo.insert(course);
*/




    }
}