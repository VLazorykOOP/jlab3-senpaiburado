public class Main {

    public static void main(String[] args) {
        Document[] documents = new Document[3];
        documents[0] = new Receipt("asdfgwfgg", "21-01-2023", 4);
        documents[1] = new Invoice("fnwjnbfeqj", "23-03-2023", "Vladyslav", 12350);
        documents[2] = new Waybill("2kosfbwidwb", "12-02-2023", "bank", 344);

        for (Document document : documents) {
            document.Show();
        }

        Series[] series = new Series[2];
        series[0] = new Exponential(5, 3);
        series[1] = new Linear(55, 42);

        for (Series item : series) {
            System.out.println(item.getNthTerm(5));
            System.out.println(item.getSum(5));
        }
    }
}

abstract class Document {
    String id;
    String date;
    
    Document(String id, String date){
        this.id = id;
        this.date = date;
    }
    
    abstract void Show();
}

class Receipt extends Document{
    double amount;
    
    Receipt(String id, String date, double amount){
        super(id, date);
        this.amount = amount;
    }
    
    @Override
    void Show() {
        System.out.println("Receipt ID: " + id + ", Date: " + date + ", Amount: " + amount);
    }
}

class Invoice extends Document{
    String customerName;
    double total;
    
    Invoice(String id, String date, String customerName, double total){
        super(id, date);
        this.customerName = customerName;
        this.total = total;
    }
    
    @Override
    void Show() {
        System.out.println("Invoice ID: " + id + ", Date: " + date + ", Customer Name: " + customerName + ", Total: " + total);
    }
}

class Waybill extends Document{
    String supplier;
    int quantity;
    
    Waybill(String id, String date, String supplier, int quantity){
        super(id, date);
        this.supplier = supplier;
        this.quantity = quantity;
    }
    
    @Override
    void Show() {
        System.out.println("Waybill ID: " + id + ", Date: " + date + ", Supplier: " + supplier + ", Quantity: " + quantity);
    }
}

interface Series {
    abstract double getNthTerm(int n);

    abstract double getSum(int n);
}

class Linear implements Series {
    protected double a1;  // Перший член прогресії
    protected double d;   // Різниця або знаменник прогресії

    Linear(double a1, double d) {
        this.a1 = a1;
        this.d = d;
    }

    @Override
    public double getNthTerm(int n) {
        return a1 + (n - 1) * d;
    }

    @Override
    public double getSum(int n) {
        return (n / 2.0) * (2.0 * a1 + (n - 1) * d);
    }
}

class Exponential implements Series {
    protected double a1;  // Перший член прогресії
    protected double d;   // Різниця або знаменник прогресії

    Exponential(double a1, double d) {
        this.a1 = a1;
        this.d = d;
    }

    @Override
    public double getNthTerm(int n) {
        return a1 * Math.pow(d, n - 1);
    }

    @Override
    public double getSum(int n) {
        if (d == 1) {
            return n * a1;
        } else {
            return a1 * (1 - Math.pow(d, n)) / (1 - d);
        }
    }
}
