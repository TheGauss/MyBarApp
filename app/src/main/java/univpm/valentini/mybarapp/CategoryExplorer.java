package univpm.valentini.mybarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CategoryExplorer extends AppCompatActivity {
    ListView itemlist_view = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_explorer);
        itemlist_view = findViewById(R.id.Itemlist);
        Intent intent = getIntent();
    }
}
