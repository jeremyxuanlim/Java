public class Product {
    private String prodId;
    private double prodPrice;
    private String prodName;
    private int quantitysold;

 
    public Product(String prodId, double prodPrice, String prodName, int quantitysold) {
        this.prodId = prodId;
        this.prodPrice = prodPrice;
        this.prodName = prodName;
        this.quantitysold = quantitysold;
    }






    public String setprodId() {
        return prodId;
    }

    public void setprodId(String prodId) {
        this.prodId = prodId;
    }

    public double getProPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getQuantitysold() {
        return quantitysold;
    }

    public void setQuantitysold(int quantitysold) {
        this.quantitysold = quantitysold;
    }

    @Override
    public String toString() {
        return "Product ID: " + prodId + ", Name: " + prodName + ", Price: " + prodPrice + ", Quantity Sold: " + quantitysold;
    }
}
