package app.util;

import java.util.ArrayList;

public class All_resources {
    private static ArrayList<Category> Categories;
    All_resources(){
        Categories = new ArrayList<Category>();
    }
    public static void addCategory(Category cat){
        if (Categories.contains(cat)) return;
        Categories.add(cat);
    }
    public static void addItem(Item itm, Category[] cats){ ;
        for (int i = 0; i < cats.length; i++) {
            for (int u = 0; i < Categories.size(); u++) {
                if (Categories.get(u).equals(cats[i])) {
                    Categories.get(u).addItem(itm);
                }
            }
        }
    }
    public static Category[] getResources(){
        return (Category[]) Categories.toArray();
    }
}
