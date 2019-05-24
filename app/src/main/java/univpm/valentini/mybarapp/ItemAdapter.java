package univpm.valentini.mybarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.util.Item;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private Item[] items;
    public ItemAdapter(Context context, int resource, Item[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent){
        View listItem = v;
        if(listItem == null) listItem = LayoutInflater.from(context).inflate(R.layout.itemlistlayout,parent,false);
        Item current = items[position];
        ImageView image = (ImageView) listItem.findViewById(R.id.ItemPic_cat);
        TextView name = (TextView) listItem.findViewById(R.id.ItemName_cat);
        TextView price = (TextView) listItem.findViewById(R.id.ItemPrice_cat);
        image.setImageBitmap(current.getImageBitmap());
        name.setText(current.getName());
        price.setText(current.priceToString(1));
        return listItem;
    }
}
