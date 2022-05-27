package edu.skku.cs.pa3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.StepListAdapter;

public class DetailActivity extends AppCompatActivity {
    private StepListAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        listView = findViewById(R.id.listView);
        listAdapter = new StepListAdapter();
        listView.setAdapter(listAdapter);

    }
}