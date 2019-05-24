package univpm.valentini.mybarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.util.Category;

public class MainAdapter extends ArrayAdapter<Category> {
    private Context context;
    private Category[] categories;
    public MainAdapter(Context context, int resource, Category[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.categories = objects;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent){
        View listItem = v;
        if(listItem == null) listItem = LayoutInflater.from(context).inflate(R.layout.layout_category_list,parent,false);
        Category current = categories[position];
        ImageView image = (ImageView) listItem.findViewById(R.id.CategoryImage);
        TextView name = (TextView) listItem.findViewById(R.id.CategoryName);
        image.setImageBitmap(current.getImageBitmap());
        name.setText(current.getName());
        return listItem;
    }
}
