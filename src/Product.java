public class Product {
    private String productID;
    private String productName;
    private Double unitPrice;
    private int stock;

    public Product(String productID, String productName, Double unitPrice, int stock) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                '}';
    }

    public String printToFile() {
        return productID + ", " + productName + ", " + unitPrice;
    }
}
