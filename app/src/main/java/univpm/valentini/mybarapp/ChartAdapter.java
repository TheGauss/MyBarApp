package univpm.valentini.mybarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import app.util.Chart;
import app.util.Item;
import app.util.ItemNotFoundException;

public class ChartAdapter extends ArrayAdapter<Item> {
    Context context;
    public ChartAdapter(Context context, int resource, Item[] dummy) {
        super(context, resource, dummy);
        this.context = context;
    }
    @Override
    public View getView(final int position, View v, ViewGroup parent){
        View listItem = v;
        if(listItem == null) listItem = LayoutInflater.from(context).inflate(R.layout.layout_chart_list,parent,false);
        Item current = Chart.getItem(position);
        TextView name = (TextView) listItem.findViewById(R.id.ItemNameView_Chart);
        TextView price = (TextView) listItem.findViewById(R.id.ItemPriceView_Chart);
        TextView quantity = (TextView) listItem.findViewById(R.id.ItemQuantityView);
        name.setText(current.getName());
        price.setText(current.priceToString(Chart.getItemQuantity(position)));
        quantity.setText(Chart.getItemQuantity(position)+"");
        Button increase = (Button) listItem.findViewById(R.id.IncreaseButtonChart);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chart.add_item(Chart.getItem(position), 1);
                notifyDataSetChanged();
            }
        });
        Button decrease = (Button) listItem.findViewById(R.id.DecreaseButtonChart);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Chart.remove_item(Chart.getItem(position));
                    notifyDataSetChanged();
                }catch(ItemNotFoundException e){
                        e.printStackTrace();
                    }
            }
        });
        return listItem;
    }
}
