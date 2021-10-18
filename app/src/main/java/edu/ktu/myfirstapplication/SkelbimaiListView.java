package edu.ktu.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

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
    private Button sortASC,sortDES,sortPriceB, sortPriceS, filterbutton;
    private Button add_item, sortButton;
    private EditText filterprice1, filterprice2;
    private Toolbar myToolbar;
    private Context context = this;

    //asdasd
    private LinearLayout sortLayout;
    boolean sortHidden = true;

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

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        add_item = (Button) findViewById(R.id.button8);
        adapter = new SkelbimaiListAdapter(this, list);
        sortASC = (Button) findViewById(R.id.button3);
        sortDES = (Button) findViewById(R.id.button4);
        sortPriceB = (Button) findViewById(R.id.button5);
        sortPriceS = (Button) findViewById(R.id.button6);
        filterbutton = (Button) findViewById(R.id.button7);
        filterprice1 = (EditText) findViewById(R.id.editFilterPrice1);
        filterprice2 = (EditText) findViewById(R.id.editFilterPrice2);

        //aaa
        sortButton = (Button) findViewById(R.id.sortButton);
        hideSort();
        showSort();

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
        Filter();
        add_item.setOnClickListener(start_add_item_activity);
    }
    //asdasdasd
    private void hideSort(){
        sortLayout.setVisibility(View.GONE);
    }

    private void showSort(){
        sortLayout.setVisibility(View.VISIBLE);
    }

    /*private void showSortLayout(View view){
        if (sortHidden == true){
            sortHidden = false;
            showSort();
        }
        else{
            sortHidden = true;
            hideSort();
        }
    }*/

    public void showSortLayout(View view) {
        if (sortHidden == true){
            sortHidden = false;
            showSort();
        }
        else{
            sortHidden = true;
            hideSort();
        }
    }


    View.OnClickListener start_add_item_activity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Skelbimo_pridejimas.class);
            intent.putExtra("flag", true);
            startActivity(intent);
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarmenu, menu);

       // MenuItem searchItem = menu.findItem(R.id.action_search);
       // SearchView searchView = (SearchView) searchItem.getActionView();

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
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

    private void Filter(){

        filterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SkelbimaiList> filteredList = new ArrayList<>();
                if(filterprice1.getText().toString().isEmpty() || filterprice2.getText().toString().isEmpty()){
                    filterprice1.setError("Privalo būti įvestas kažkoks skaičius.");
                    filterprice2.setError("Privalo būti įvestas kažkoks skaičius.");
                    return;
                }else{
                    float price1 = Float.valueOf(filterprice1.getText().toString());
                    float price2 = Float.valueOf(filterprice2.getText().toString());

                    for (SkelbimaiList listas : list){

                        //Toast.makeText(SkelbimaiListView.this, listas.getPrice() + "   -   " + price1, Toast.LENGTH_LONG ).show();
                        if(listas.getPrice() > price1 && listas.getPrice() < price2){
                            filteredList.add(listas);
                            //Toast.makeText(SkelbimaiListView.this, listas.getPrice() + "   -   " + price1, Toast.LENGTH_LONG ).show();
                        }
                    }
                    adapter = new SkelbimaiListAdapter(SkelbimaiListView.this, filteredList);
                    myListView.setAdapter(adapter);
                }
            }
        });
    }

}

