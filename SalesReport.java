public class SalesReport extends  Product {
    private double totalAmount;
    private int quantitysell;

    
    public SalesReport(String prodId, double prodPrice, String prodName, int quantitysold, double totalAmount, int quantitysell) {
        super(prodId, prodPrice, prodName, quantitysold);  
        this.totalAmount = totalAmount;
        this.quantitysell = quantitysell;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getquantitysold
() {
        return quantitysell;
    }

    public void setQuantitysell(int quantitysell) {
        this.quantitysell = quantitysell;
    }
}
