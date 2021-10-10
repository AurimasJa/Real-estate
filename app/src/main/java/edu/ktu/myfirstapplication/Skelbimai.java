package edu.ktu.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class Skelbimai extends AppCompatActivity {

    ActivitySkelbimaiBinding binding;

    private TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelbimaidesign);
   //     myTextView = (TextView) findViewById(R.id.textView2);

        int[] imageId = {R.drawable.house1};
        final String[] title = {"Pirmas namas"};
        final String[] description = {"Pirmasis namas sarase"};
        final String[] price = {"100.000$"};
        final String[] place = {"Kaunas"};
        final String[] room_count = {"3"};

        ArrayList<Skelbimas> skelbimasArrayList = new ArrayList<>();

        for (int i=0;i< imageId.length;i++)
        {
            Skelbimas skelbimas = new Skelbimas(title[i],description[i],price[i],place[i],room_count[i]);
            skelbimasArrayList.add(skelbimas);
        }

        listAdapterhouselist listAdapterhouselist = new listAdapterhouselist(Skelbimai.this,skelbimasArrayList);
        binding.listview.setAdapter(listAdapterhouselist);
        binding.listview.setClickable(true);
        binding.listview.SetOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void OnItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent i = new Intent(Skelbimai.this,Skelbimas.class);
                i.putExtra("title", title[position]);
                i.putExtra("description", description[position]);
                i.putExtra("price", price[position]);
                i.putExtra("place", place[position]);
                i.putExtra("room_count", room_count[position]);
                startActivity(i);

            }

        })


    }




}
