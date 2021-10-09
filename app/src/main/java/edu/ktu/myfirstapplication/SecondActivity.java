package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private ListView myListView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivitydesign);
        myListView = (ListView) findViewById(R.id.listView);

        List<ListItem> items = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)) {
            items.add(new ListItem("Jack", R.drawable.baseline_3d_rotation_black_48dp, "Mathematics, Chemistry"));
            items.add(new ListItem("Jane", R.drawable.baseline_announcement_black_48dp, "Physics, Informatics"));
            items.add(new ListItem("Bob", R.drawable.baseline_alarm_black_48dp, "Geography, Chemistry"));
            items.add(new ListItem("Clara", R.drawable.baseline_account_box_black_48dp, "Geography, Chemistry"));
            items.add(new ListItem("Sam", R.drawable.baseline_accessibility_black_48dp, "Mathematics, Physics"));
        } else {
            items.add(new ListItem(
                    "Mathematics",
                    R.drawable.baseline_3d_rotation_black_48dp,
                    "Mathematics is the study of topics such as quantity, " +
                            "structure, space and change."
            ));
            items.add(new ListItem(
                    "Physics",
                    R.drawable.baseline_announcement_black_48dp,
                    "Physics is the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force."
            ));
            items.add(new ListItem(
                    "Chemistry",
                    R.drawable.baseline_alarm_black_48dp,
                    "Chemistry is a branch of physical science that studies the composition, structure, properties, and change of matter."
            ));
            items.add(new ListItem(
                    "Informatics",
                    R.drawable.baseline_account_box_black_48dp,
                    "Informatics is the science of information and computer information systems."
            ));
            items.add(new ListItem(
                    "Geography",
                    R.drawable.baseline_accessibility_black_48dp,
                    "Geography is a field of science devoted to the study of the lands, the features, the inhabitants, and the phenomena of Earth."
            ));
        }
        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);
    }
}
