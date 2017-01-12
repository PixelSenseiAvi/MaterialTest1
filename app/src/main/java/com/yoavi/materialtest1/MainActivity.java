package com.yoavi.materialtest1;

import android.app.SearchManager;
import android.provider.SearchRecentSuggestions;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar my_toolbar =(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle(R.string.my_title);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);
        getSupportActionBar().setSubtitle(R.string.my_subtitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView_main = (ListView) findViewById(R.id.listview_main);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.simple_list_item_1,getResources().getStringArray(R.array.countries));
        listView_main.setAdapter(adapter);

        navigationDrawer navigationdrawer=(navigationDrawer) getSupportFragmentManager().findFragmentById(R.id.drawer);
        navigationdrawer.setUp(R.id.drawer,(DrawerLayout) findViewById(R.id.drawerlayout),my_toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager=(SearchManager)getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_1:

                SearchRecentSuggestions searchRecentSuggestion = new SearchRecentSuggestions(this,SearchableProvider.AUTHORITY,SearchableProvider.MODE);
                searchRecentSuggestion.clearHistory();
                Toast.makeText(MainActivity.this,"Search history cleared",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_2:
                Toast.makeText(MainActivity.this,"Option 2 selected",Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
