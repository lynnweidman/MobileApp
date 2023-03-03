package com.example.c196lynnweidman.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.c196lynnweidman.DATABASE.Repository;
import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TermsList extends AppCompatActivity {
    private Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.termListRecyclerView);
        repository = new Repository(getApplication());
        List<TermsEntity> terms = repository.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        adapter.setTerms(terms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_termsdetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
//                Intent intent=new Intent(ProductDetails.this,MainActivity.class);
//                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

        @Override
        protected void onResume() {

            super.onResume();
            List<TermsEntity> allTerms=repository.getAllTerms();
            RecyclerView termRecyclerView=findViewById(R.id.termListRecyclerView);
            final TermAdapter termAdapter=new TermAdapter(this);
            termRecyclerView.setAdapter(termAdapter);
            termRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            termAdapter.setTerms(allTerms);

        }

    public void addTerm(View view) {
        Intent intent = new Intent(TermsList.this, TermsDetails.class);

        startActivity(intent);
    }


}
