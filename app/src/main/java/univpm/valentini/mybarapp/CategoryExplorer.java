package univpm.valentini.mybarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import app.util.All_resources;
import app.util.Item;
import app.util.ItemNotFoundException;

public class CategoryExplorer extends AppCompatActivity {
    ListView itemlist_view = null;
    int categoryID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_explorer);
        itemlist_view = findViewById(R.id.list);
        Intent intent = getIntent();
        categoryID = intent.getIntExtra("Category", -1);
        try{
            Item[] items =  All_resources.getCategoryByID(categoryID).getItems();
            ItemAdapter adapter = new ItemAdapter(this, R.layout.itemlistlayout, items);
            itemlist_view.setAdapter(adapter);
        }catch(ItemNotFoundException e){
            e.printStackTrace();
        }
        itemlist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Item_Review.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Category", categoryID);
                bundle.putInt("Item", position);
                intent.putExtra("Data", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.appmenu, menu);
    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
        startActivity(intent);
        return true;
    }
}
