package app.util;

import java.util.ArrayList;

public class All_resources {
    private static ArrayList<Category> Categories = new ArrayList<>();
    public static void addCategory(Category cat){
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
        Category[] array = new Category[Categories.size()];
        for (int i = 0; i < array.length; i++) array[i] = Categories.get(i);
        return array;
    }
}
