import java.util.Date;
import java.util.List;

public class Quotation {
    private String quotationNumber; // Mã báo giá

    private List<Contributor> contributorList; // Bên bán, bên cung cấp

    private Purchaser purchaser; // Bên mua

    private List<QuotationProduct> productList; // Sản phẩm

    private Date quotationDate = new Date(); // Ngày báo giá

    private int quotationValidity; // Hiệu lực báo giá (đơn vị: ngày)

    private int deliveryTime; // Thời gian giao hàng

    public Quotation(String quotationNumber, List<Contributor> contributorList, Purchaser purchaser, List<QuotationProduct> productList, Date quotationDate, int quotationValidity, int deliveryTime) {
        this.quotationNumber = quotationNumber;
        this.contributorList = contributorList;
        this.purchaser = purchaser;
        this.productList = productList;
        this.quotationDate = quotationDate;
        this.quotationValidity = quotationValidity;
        this.deliveryTime = deliveryTime;
    }

    public String getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(String quotationNumber) {
        this.quotationNumber = quotationNumber;
    }

    public List<Contributor> getContributorList() {
        return contributorList;
    }

    public void setContributorList(List<Contributor> contributorList) {
        this.contributorList = contributorList;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    public List<QuotationProduct> getQuotationProductList() {
        return productList;
    }

    public void setQuotationProductList(List<QuotationProduct> quotationProductList) {
        this.productList = quotationProductList;
    }

    public Date getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    public int getQuotationValidity() {
        return quotationValidity;
    }

    public void setQuotationValidity(int quotationValidity) {
        this.quotationValidity = quotationValidity;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        String sout = null;

        sout = "\nVP Industrial Belts And Chains JSC"
            + "\nBản báo giá số: " + quotationNumber
            + "\nNgày: \t" + quotationDate
            + "\n\n----------\n"

            + "\nKính gửi: \t" + purchaser.getName()
            + "\nĐịa chỉ: \t" + purchaser.getAddress()
            + "\nSố điện thoại: \t" + purchaser.getPhone()

            + "\nCông ty Cổ Phần VP chân thành cám ơn Quý Công Ty đã quan tâm tới sản phẩm và dịch vụ của chúng tôi!"
            + "\nTheo đề nghị của Quý Công Ty, VP xin được báo giá sản phẩm/ dịch vụ dưới đây: \n";


        for (QuotationProduct quotationProduct : productList) {
                sout += quotationProduct.toString();
            }

        sout += "\n----------\n"
            + "\nGhi chú: \tGiá trên chưa bao gồm chi phí vận chuyển, lắp ráp, và thuế VAT 8%"
            + "\nThời gian giao hàng: \t" + deliveryTime + " ngày làm việc kể từ ngày xác nhận đặt hàng và khoản thanh toán."

            + "\n\nPhương thức thanh toán: "
            + "\n\tChuyển khoản. Thanh toán lần đầu 50% giá trị đơn hàng sau khi xác nhận đơn hàng."
            + "\n\tThanh toán lần 2 sau 07 ngày làm việc kể từ ngày giao hàng."
            +  "\n\tTHÔNG TIN CHUYỂN KHOẢN:" +
                "\n\tTên tài khoản: CÔNG TY ..." +
                "\n\tSố tài khoản: 0123456678 - tại Ngân hàng B - Chi nhánh A\n"

            + "\nThời gian bảo hành: \t12 tháng kể từ ngày sản xuất"
            + "\nHiệu lực báo giá: \t" + quotationValidity + " ngày."

            + "\n\nNhân viên phụ trách đơn hàng: \t";
                for (Contributor c : contributorList) {
                    sout += "\n" + c.getName() + " - " + c.getPhone() + " - " + c.getEmail();
                }
            sout += "\n\nCông ty Cổ Phần Băng Tải VP rất mong nhận được hồi âm sớm từ Quý Công Ty!"
            + "\nTrân trọng!\n";

        return sout;
    }

    public String printToFile() {
        String sout = null;
        sout = quotationNumber + "," + quotationDate + "," + purchaser + ",";

        for (Contributor c : contributorList) {
            sout += c.getId() +"," + c.getName() +",";
        }

        for (QuotationProduct quotationProduct : productList) {
            sout += quotationProduct.getProduct().getProductName() + ","
                    + quotationProduct.getProduct().getUnitPrice() + ","
                    + quotationProduct.getPerimeter() + ","
                    + quotationProduct.getWidth() + ","
                    + quotationProduct.getSubTotal(quotationProduct.getPerimeter(), quotationProduct.getWidth(), quotationProduct.getProduct().getUnitPrice()) + ",";
        }

        sout += deliveryTime + "," + quotationValidity;
        return sout;
        }

}


