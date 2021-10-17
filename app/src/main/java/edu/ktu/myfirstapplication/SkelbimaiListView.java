package edu.ktu.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SkelbimaiListView extends AppCompatActivity {
    private ListView myListView;
    private Button sortASC,sortDES,sortPriceB, sortPriceS;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    ArrayList<SkelbimaiList> list;
    SkelbimaiListAdapter adapter;
    SkelbimaiList skelbimaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelbimailistviewxml);
        myListView = (ListView) findViewById(R.id.skelbimulistview);
        skelbimaiList = new SkelbimaiList();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = FirebaseDatabase.getInstance("https://real-estate-f6875-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Skelbimai");
        list = new ArrayList<>();

        adapter = new SkelbimaiListAdapter(this, list);
        sortASC = (Button) findViewById(R.id.button3);
        sortDES = (Button) findViewById(R.id.button4);
        sortPriceB = (Button) findViewById(R.id.button5);
        sortPriceS = (Button) findViewById(R.id.button6);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    skelbimaiList = dataSnapshot.getValue(SkelbimaiList.class);
                    list.add(skelbimaiList);
                }
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                intent.putExtra("pavadinimas", list.get(i).getTitle());
                intent.putExtra("kaina", list.get(i).getPrice());
                intent.putExtra("descriptionas", list.get(i).getDescription());
                //intent.putExtra("nuotrauka", list.get(i).getImageId());
                intent.putExtra("kambariai", list.get(i).getRoom_count());
                intent.putExtra("numeris", list.get(i).getPhoneNum());
                startActivity(intent);
            }
        });
        sortAscList();
        sortDesList();
        sortPriceSmall();
        sortPriceBig();

    }
    private void sortAscList(){
        sortASC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        return skelbimaiList.getTitle().compareTo(t1.getTitle());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void sortDesList(){
        sortDES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        return skelbimaiList.getTitle().compareTo(t1.getTitle());
                    }
                });
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void sortPriceSmall(){
        sortPriceS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        float price1 = skelbimaiList.getPrice();
                        float price2 = t1.getPrice();

                        return Float.compare(price1, price2);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void sortPriceBig(){
        sortPriceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<SkelbimaiList>() {
                    @Override
                    public int compare(SkelbimaiList skelbimaiList, SkelbimaiList t1) {
                        float price1 = skelbimaiList.getPrice();
                        float price2 = t1.getPrice();

                        return Float.compare(price1, price2);
                    }
                });
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
}

