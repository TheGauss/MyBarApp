public class Item{
        private final int price;
        private final String  Name;
        private final String[] category;
        Item(int price, String Name, String[] category){
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
            if (Name.equals(item)) return true;
            return false;
        }
}
