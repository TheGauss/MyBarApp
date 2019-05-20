package app.util;

public class Item {
        private final int price;
        private final String  Name;
        public Item(int price, String Name){
            this.price = price;
            this.Name = Name;
        }
        public int getPrice(){
            return price;
        }
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

        @Override
        public String toString(){
            return "Item: " + Name + " price: " + price/100 + "." + String.format("%02d", price%100);
        }
}
