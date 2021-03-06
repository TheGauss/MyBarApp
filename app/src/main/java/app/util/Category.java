package app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Category {
    private String Name;
    private String Image;
    private ArrayList<Item> Items;
    public Category(String Name, String Image){
        this.Name = Name;
        this.Image = Image;
        Items = new ArrayList<Item>();
    }
    public String getName(){
        return Name;
    }
    public String Image(){
        return Image;
    }
    public void addItem(Item item){
        if (Items.contains(item)) return;
        Items.add(item);
    }
    public Bitmap getImageBitmap(){
        try{
            URL url = new URL(Image);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public Item[] getItems(){

        Item[] out = new Item[Items.size()];
        for (int i = 0; i < out.length; i++){
            out[i] = Items.get(i);
        }
        return out;
    }
    public Item getItemByID(int ID) throws ItemNotFoundException{
        if((ID>= 0) && (ID< Items.size()))return Items.get(ID);
        throw new ItemNotFoundException();
    }
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Category)) return false;
        Category cat = (Category) obj;
        if (!(this.Name.equals(cat.Name))) return false;
        return true;
    }
    @Override
    public String toString(){
        return "Category(Name: " + Name + " Image: " + Image +")";
    }
}
