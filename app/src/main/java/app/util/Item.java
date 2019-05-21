package app.util;

public class Item {
        private final int price;
        private final String  Name;
        private final String Image;
        private final String Descritpion;
        public Item(int price, String Name, String Image, String Description){
            this.price = price;
            this.Name = Name;
            this.Image = Image;
            this.Descritpion = Description;
        }
        public String getDescritpion(){return Descritpion;}
        public int getPrice(){
            return price;
        }
        public String getImage(){ return Image;}
        public String getName(){
            return Name;
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
