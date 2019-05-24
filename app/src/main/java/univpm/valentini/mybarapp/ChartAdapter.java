package univpm.valentini.mybarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import app.util.Chart;
import app.util.Item;

public class ChartAdapter extends ArrayAdapter<Item> {
    Context context;
    public ChartAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent){
        View listItem = v;
        if(listItem == null) listItem = LayoutInflater.from(context).inflate(R.layout.itemlistlayout,parent,false);
        Item current = Chart.getItem(position);
        TextView name = (TextView) listItem.findViewById(R.id.ItemNameView_Chart);
        TextView price = (TextView) listItem.findViewById(R.id.ItemPriceView_Chart);
        TextView quantity = (TextView) listItem.findViewById(R.id.ItemQuantityView);
        name.setText(current.getName());
        price.setText(current.priceToString(Chart.getItemQuantity(position)));
        quantity.setText(Chart.getItemQuantity(position));
        return listItem;
    }
}
