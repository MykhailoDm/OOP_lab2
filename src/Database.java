import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Database {

    private TreeMap<Integer, Product> catalogue;

    // Додаємо продукт до бази даних, оскільки кожне id повинне бути унікальне,
    // то цим методом можна і змінити вже існуючий entry
    public void addToDatabase(int id, Product product){
        if(id >= 0) {
            catalogue.put(id, product);
            System.out.println(product.getLabel() + " was added to catalogue. Id: " +
                    id + " Price: " + product.getPrice());
        } else {
            System.out.println("Invalid id.");
        }
    }

    // Знаходиме певний продукт
    public void findProduct(int id){
        if (catalogue.containsKey(id)){
            Product product = catalogue.get(id);
            System.out.println("Id: " + id + " Label: " + product.getLabel() + " Price: " + product.getPrice());
        }
    }

    // Видаляємо певний продукт з бази даних
    public void remove(int id){
        if (catalogue.containsKey(id)){
            catalogue.remove(id);
            System.out.println("Product removed successfully.");
            return;
        }

        System.out.println("No such product.");
    }

    // Виводимо весь каталог того, що є в базі даних
    public void printDatabase(){
        catalogue.forEach((id, product) -> System.out.println("Id: " + id +
                " Label: " + product.getLabel() + " Price: " + product.getPrice()));
    }

    // Дістажмо певні продукти і виводимо їх залежно від предиката-умови
    private void printConditional(PriceCondition priceCondition){
        catalogue.entrySet().stream()
                .filter(priceCondition)
                .forEach(entry -> System.out.println("Id: " + entry.getKey() + " Label: "
                        + entry.getValue().getLabel() + " Price: " + entry.getValue().getPrice()));
    }

    // Виводимо продукти ціни вищої за ту, яку ввели
    public void printPriceHigher(int price){

        PriceCondition priceCondition = (entry -> entry.getValue().getPrice() > price);
        printConditional(priceCondition);
    }

    // Виводимо продукти ціни нижчої за ту, яку ввели
    public void printPriceLower(int price){

        PriceCondition priceCondition = (entry -> entry.getValue().getPrice() < price);
        printConditional(priceCondition);
    }

    // Виводимо продукти ціни тої самої, яку ввели
    public void printPriceEqual(int price){

        PriceCondition priceCondition = (entry -> entry.getValue().getPrice() == price);
        printConditional(priceCondition);
    }

    // Конструктор
    public Database() {
        catalogue = new TreeMap<>();
    }

    // Інтерфейс предикат для створення лямбд, що приймають entry каталогу
    // і повертають boolean
    interface PriceCondition extends Predicate<Map.Entry<Integer, Product>> {

        @Override
        boolean test(Map.Entry<Integer, Product> entry);
    }
}
