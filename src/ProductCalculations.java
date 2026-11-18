public class ProductCalculations implements IProductCalculations {
    private double price;

    public ProductCalculations(double price){
        this.price = price;
    }

    @Override
    public double GetVat() {
        //Calculate Vat 15%
        return price * 0.15;
    }

    @Override
    public double GetCommission() {
        //Calculate Commission 5%
        return price * 0.05;
    }

    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
