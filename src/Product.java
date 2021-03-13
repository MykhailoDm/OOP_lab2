public class Product {

    // Продукт матиме назву та ціну
    private String label;
    private int price;

    public Product(String label, int price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public int getPrice() {
        return price;
    }
}
