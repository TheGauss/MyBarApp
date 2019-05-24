package app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

public class Item {
        private final int price;
        private final String  Name;
        private final String Image;
        private final String Description;
        public Item(int price, String Name, String Image, String Description){
            this.price = price;
            this.Name = Name;
            this.Image = Image;
            this.Description = Description;
        }
        public String getDescritpion(){return Description;}
        public int getPrice(){
            return price;
        }
        public String getImage(){ return Image;}
        public String getName(){
            return Name;
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
        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof Item)) return false;
            Item item = (Item) obj;
            if (item == this) return true;
            if (price != item.price) return false;
            if (Name.equals(item.Name)) return true;
            return false;
        }
        public String priceToString(int number){
            int tot = price*number;
            if (tot%100 == 0) return "" + tot/100+".00";
            return "" + tot/100 + "." + tot%100;
        }
        @Override
        public String toString(){
            return "Item: " + Name + " price: " + price/100 + "." + String.format("%02d", price%100) + " Image: " + Image;
        }
}
