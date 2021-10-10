package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        final List<ListItem> items = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.baseline_3d_rotation_black_48dp);
            items.add(new ListItem("Jack",bitmap, "Mathematics, Chemistry"));
            items.add(new ListItem("Jane", bitmap, "Physics, Informatics"));
            items.add(new ListItem("Bob", bitmap, "Geography, Chemistry"));
            items.add(new ListItem("Clara", bitmap, "Geography, Chemistry"));
            items.add(new ListItem("Sam", bitmap, "Mathematics, Physics"));
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.baseline_3d_rotation_black_48dp);
            items.add(new ListItem(
                    "Mathematics",
                    bitmap,
                    "Mathematics is the study of topics such as quantity, " +
                            "structure, space and change."
            ));
            items.add(new ListItem(
                    "Physics",
                    bitmap,
                    "Physics is the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force."
            ));
            items.add(new ListItem(
                    "Chemistry",
                    bitmap,
                    "Chemistry is a branch of physical science that studies the composition, structure, properties, and change of matter."
            ));
            items.add(new ListItem(
                    "Informatics",
                    bitmap,
                    "Informatics is the science of information and computer information systems."
            ));
            items.add(new ListItem(
                    "Geography",
                    bitmap,
                    "Geography is a field of science devoted to the study of the lands, the features, the inhabitants, and the phenomena of Earth."
            ));
        }
        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int index = position;
                Toast.makeText(getApplicationContext(),"Selected content " + items.get(index), Toast.LENGTH_LONG).show();
            }
        });
        /*myListView.OnClickListener sendButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ListItem> things = new ArrayList<>();

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baseline_3d_rotation_black_48dp);
                things.add(new ListItem("First title", bitmap, "First description"));
                things.add(new ListItem("Second title", bitmap, "Second description"));
                Intent intent = new Intent(getBaseContext(), FourthActivity.class);
                intent.putExtra("things", (Parcelable) things);
                startActivity(intent);
            }
        };*/
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<ListItem> things = new ArrayList<>();
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                intent.putExtra("name", items.get(i).getTitle());
                startActivity(intent);

            }
        });
    }
}
