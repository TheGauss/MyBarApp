package app.util;

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
    public Item[] getItems(){
        return (Item[]) Items.toArray();
    }
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Category)) return false;
        Category cat = (Category) obj;
        if (!(this.Name==cat.Name)) return false;
        return true;
    }
    @Override
    public String toString(){
        return "Category(Name: " + Name + " Image: " + Image +")";
    }
}
