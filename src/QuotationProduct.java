import java.util.List;

public class QuotationProduct {
    private Product product;
    private Double width;
    private Double perimeter;

    public QuotationProduct(Product product, Double width, Double perimeter) {
        this.product = product;
        this.width = width;
        this.perimeter = perimeter;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuotationProductList(Product product) {
        this.product = product;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea(Double perimeter, Double width) {
        return getWidth() * getPerimeter();
    }

//    public void setArea(Double area) {
//        this.area = area;
//    }

    public Double getSubTotal(Double perimeter, Double width, Double unitPrice) {
        return perimeter * width * product.getUnitPrice();
    }


    @Override
    public String toString() {
        return "\n----"
                + "\nSản phẩm: \t" + product.getProductName()
                + "\nĐơn giá: \t" + product.getUnitPrice()
                + "\nQuy cách: \t" + width + " x " + perimeter + " = " + this.getArea(width, perimeter)
                + "\nGiá thành: \t" +  String.format("%,.2f",getSubTotal(width, perimeter, product.getUnitPrice()));
    }
}
