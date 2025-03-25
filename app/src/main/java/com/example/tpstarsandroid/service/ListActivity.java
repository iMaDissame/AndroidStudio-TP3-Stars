package com.example.tpstarsandroid.service;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpstarsandroid.R;
import com.example.tpstarsandroid.adapter.StarAdapter;
import com.example.tpstarsandroid.beans.Star;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        // Configurer la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        service = StarService.getInstance();
        init();

        recyclerView = findViewById(R.id.recycle_view);

        // Ajout de logs pour débogage
        List<Star> allStars = service.findAll();
        Log.d(TAG, "Nombre de stars avant création de l'adaptateur: " + allStars.size());
        for (Star s : allStars) {
            Log.d(TAG, "Star trouvée: " + s.getName() + " avec ID: " + s.getId());
        }

        // Configuration de l'adaptateur et du RecyclerView
        starAdapter = new StarAdapter(this, allStars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Notification de changement de données
        starAdapter.notifyDataSetChanged();
    }


    public void init() {

        service.create(new Star("Leo Messi", R.mipmap.messi, 5));
        service.create(new Star("Cristiano Ronaldo", R.mipmap.cristiano, 3));
        service.create(new Star("Achraf Hakimi",R.mipmap.hakimi, 4.5f));
        service.create(new Star("Paulo Dybala", R.mipmap.dybala, 3.0f));
        service.create(new Star("Lawla Dorouf",R.mipmap.lawladorouf, 2.5f));
        service.create(new Star("Chawcha3",R.mipmap.chawcha3, 1.0f));
        service.create(new Star("Kylian Mbappe", R.mipmap.mbappe, 3.5f));
        service.create(new Star("emma watson", R.mipmap.emmawatson, 1));
        service.create(new Star("dwayne johnson", R.mipmap.dwaynejohnson, 5));
        service.create(new Star("Adele Laurie", R.mipmap.adelle, 1));
        service.create(new Star("tom cruise", R.mipmap.tomcruise, 4.5f));


        List<Star> stars = service.findAll();
        Log.d(TAG, "Nombre d'éléments après init: " + stars.size());
        for (Star s : stars) {
            Log.d(TAG, "Star: " + s.getName());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}