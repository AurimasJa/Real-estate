package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Skelbimai extends AppCompatActivity {
    private ListView myListView;
    private SkelbimaiListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelbimaidesign);
        myListView = (ListView) findViewById(R.id.skelbimulistas);

        List<SkelbimaiList> items = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)) {
            items.add(new SkelbimaiList("Dviaukstis", R.drawable.baseline_3d_rotation_black_48dp, "Dviaukstis namas su visais patogumais", 50000, 3));
            //items.add(new SkelbimaiList("Dviaukstis", R.drawable.house, "Dviaukstis namas su visais patogumais", 50000, 3));
            items.add(new SkelbimaiList("Butas", R.drawable.baseline_announcement_black_48dp, "Butas Kauno centre", 4312312, 6));
            items.add(new SkelbimaiList("Butas", R.drawable.baseline_alarm_black_48dp, "Butas Kauno centre",233123,143));
            items.add(new SkelbimaiList("Butassubalkonu", R.drawable.baseline_account_box_black_48dp, "Butas su balkonu tiesiog",1231231,123));
            items.add(new SkelbimaiList("Namasprieuros", R.drawable.baseline_accessibility_black_48dp, "Namas prie juros su visais patogumais",9999999, 2));
            items.add(new SkelbimaiList("Dviaukstisnuosavasnamas", R.drawable.baseline_3d_rotation_black_48dp, "Dviaukstis nuosavas namas uz Kauno",77726,99));
            items.add(new SkelbimaiList("Butasbebalkono", R.drawable.baseline_announcement_black_48dp, "Butas be balkono",21322,1));
            items.add(new SkelbimaiList("Butassunuosavugarazu", R.drawable.baseline_alarm_black_48dp, "Didelis butas su garazo",312113,32));
            items.add(new SkelbimaiList("Butassubalkonu", R.drawable.baseline_account_box_black_48dp, "Didelis butas prie juros be garazo", 1000000, 7));
            items.add(new SkelbimaiList("Namaspriejurosbegarazo", R.drawable.baseline_accessibility_black_48dp, "Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo ", 123123, 4));
        } else {
            items.add(new SkelbimaiList(
                    "Mathematics",
                    R.drawable.baseline_3d_rotation_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, space and change.",
                    123123,
                    123
            ));
            items.add(new SkelbimaiList(
                    "Physics",
                    R.drawable.baseline_announcement_black_48dp,
                    "Physics is the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force.",
                    123123,
                    123
            ));
            items.add(new SkelbimaiList(
                    "Chemistry",
                    R.drawable.baseline_alarm_black_48dp,
                    "Chemistry is a branch of physical science that studies the composition, structure, properties, and change of matter.",
                    123123,
                    123
            ));
            items.add(new SkelbimaiList(
                    "Informatics",
                    R.drawable.baseline_account_box_black_48dp,
                    "Informatics is the science of information and computer information systems.",
                    34,
                    123
            ));
            items.add(new SkelbimaiList(
                    "Geography",
                    R.drawable.baseline_accessibility_black_48dp,
                    "Geography is a field of science devoted to the study of the lands, the features, the inhabitants, and the phenomena of Earth.",
                    123123,
                    123
            ));
        }
        adapter = new SkelbimaiListAdapter(this, items);
        myListView.setAdapter(adapter);
    }




}
