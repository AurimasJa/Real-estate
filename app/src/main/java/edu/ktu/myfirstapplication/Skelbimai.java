package edu.ktu.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Skelbimai extends AppCompatActivity{
    private ListView myListView;
    private SkelbimaiListAdapter adapter;
    private Toolbar myToolbar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skelbimaidesign);
        myListView = (ListView) findViewById(R.id.skelbimulistas);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /*ImageView[] images = new ImageView[1];
        ImageView defaultimage;
        defaultimage.setImageResource(R.drawable.house);
        images[0] = defaultimage;*/

        final List<SkelbimaiList> items = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)) {
            items.add(new SkelbimaiList("Dviaukstis", R.drawable.baseline_3d_rotation_black_48dp, "Dviaukstis namas su visais patogumais", 50000, 3, "+37000000000",""));
            //items.add(new SkelbimaiList("Dviaukstis", R.drawable.house, "Dviaukstis namas su visais patogumais", 50000, 3));
            items.add(new SkelbimaiList("Butas", R.drawable.baseline_announcement_black_48dp, "Butas Kauno centre", 4312312, 6, "+37000000001",""));
            items.add(new SkelbimaiList("Butas", R.drawable.baseline_alarm_black_48dp, "Butas Kauno centre",233123,143, "+37000000002",""));
            items.add(new SkelbimaiList("Butassubalkonu", R.drawable.baseline_account_box_black_48dp, "Butas su balkonu tiesiog",1231231,123, "+37000000003",""));
            items.add(new SkelbimaiList("Namasprieuros", R.drawable.baseline_accessibility_black_48dp, "Namas prie juros su visais patogumais",9999999, 2, "+37000000004",""));
            items.add(new SkelbimaiList("Dviaukstisnuosavasnamas", R.drawable.baseline_3d_rotation_black_48dp, "Dviaukstis nuosavas namas uz Kauno",77726,99, "+37000000005",""));
            items.add(new SkelbimaiList("Butasbebalkono", R.drawable.baseline_announcement_black_48dp, "Butas be balkono",21322,1, "+37000000006",""));
            items.add(new SkelbimaiList("Butassunuosavugarazu", R.drawable.baseline_alarm_black_48dp, "Didelis butas su garazo",312113,32, "+37000000007",""));
            items.add(new SkelbimaiList("Butassubalkonu", R.drawable.baseline_account_box_black_48dp, "Didelis butas prie juros be garazo", 1000000, 7, "+37000000008",""));
            items.add(new SkelbimaiList("Namaspriejurosbegarazo", R.drawable.baseline_accessibility_black_48dp, "Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo Didelis namas prie juros be garazo ", 123123, 4, "+37000000000",""));
        } else {
            return;
            /*items.add(new SkelbimaiList(
                    "Mathematics",
                    R.drawable.baseline_3d_rotation_black_48dp,
                    "Mathematics is the study of topics such as quantity, structure, space and change.",
                    123123,
                    123, "123"
            ));
            items.add(new SkelbimaiList(
                    "Physics",
                    R.drawable.baseline_announcement_black_48dp,
                    "Physics is the natural science that involves the study of matter and its motion through space and time, along with related concepts such as energy and force.",
                    123123,
                    123, "123"
            ));
            items.add(new SkelbimaiList(
                    "Chemistry",
                    R.drawable.baseline_alarm_black_48dp,
                    "Chemistry is a branch of physical science that studies the composition, structure, properties, and change of matter.",
                    123123,
                    123, "123"
            ));
            items.add(new SkelbimaiList(
                    "Informatics",
                    R.drawable.baseline_account_box_black_48dp,
                    "Informatics is the science of information and computer information systems.",
                    34,
                    123, "123"
            ));
            items.add(new SkelbimaiList(
                    "Geography",
                    R.drawable.baseline_accessibility_black_48dp,
                    "Geography is a field of science devoted to the study of the lands, the features, the inhabitants, and the phenomena of Earth.",
                    123123,
                    123, "123"
            ));*/
        }
        adapter = new SkelbimaiListAdapter(this, items);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int index = position;
                Toast.makeText(getApplicationContext(),"Selected content " + items.get(index), Toast.LENGTH_LONG).show();
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<ListItem> things = new ArrayList<>();
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),FourthActivity.class);
                intent.putExtra("pavadinimas", items.get(i).getTitle());
                intent.putExtra("kaina", items.get(i).getPrice());
                intent.putExtra("descriptionas", items.get(i).getDescription());
                intent.putExtra("nuotrauka", items.get(i).getImageId());
                intent.putExtra("kambariai", items.get(i).getRoom_count());
                intent.putExtra("numeris", items.get(i).getPhoneNum());
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
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

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                //UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });*/
        /*SearchManager searchManager = (SearchManager) Skelbimai.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(Skelbimai.this.getComponentName()));
        }*/
        // Configure the search info and add any event listeners...

        return true;
        //return super.onCreateOptionsMenu(menu);
    }
}
