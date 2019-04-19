package app.util;

public class Item<Snackbar> {
        private final int price;
        private final String  Name;
        private final String[] category;
        public Item(int price, String Name, String[] category){
            this.price = price;
            this.Name = Name;
            this.category = category;
        }
        public int getPrice(){
            return price;
        }
        public String getName(){
            return Name;
        }
        public String[] getCategories(){
            return category;
        }
        public boolean equals(Item item){
            if (item == this) return true;
            if (price != item.price) return false;
            if (Name.equals(item.Name)) return true;
            return false;
        }

        @Override
        public String toString(){
            String part_1 = "Item: " + Name + " price: " + price/100 + "." + String.format("%02d", price%100) + " Categories:";
            String part_2 = new String();
            for (int i = 0; i<category.length; i++) part_2 = part_2 + " " + (i+1) + ") " + category[i];
            return  part_1 + part_2;
        }
}
