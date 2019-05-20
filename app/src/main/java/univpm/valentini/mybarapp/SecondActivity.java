package univpm.valentini.mybarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.util.Chart;
import app.util.Item;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = findViewById(R.id.button3);
        button.setOnClickListener(this);
        Intent intent = getIntent();
    }
    @Override
    public void onClick(View v){
        String[] a = {"caffe", "colazione"};
        Log.d("Test", "Crated string array");
        Item b = new Item(130, "Cappuccino", "");
        Log.d("Test", "Created Item");
        Chart.add_item(b);
        Toast.makeText(this, Chart.getItem(0).toString(), Toast.LENGTH_LONG).show();
        Snackbar.make(v, "Quantity of this item= " + Chart.getItemQuantity(0)+" This is the item no. = " + Chart.getItemsNumber(), Snackbar.LENGTH_LONG).show();
        try{
            Chart.sendChart();
        }
        catch (Exception e){
            Log.d("PHP resolver", "Something went wrong");
        }
    }
}
