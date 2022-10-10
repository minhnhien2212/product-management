import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ContributorManagement contributorManagement = new ContributorManagement();
        PurchaserManagement purchaserManagement = new PurchaserManagement();
        QuotationManagement quotationManagement = new QuotationManagement();
        ProductManagement productManagement = new ProductManagement();

        System.out.println("Nhấn 1 để vào menu nhân viên.");
        System.out.println("Nhấn 2 để vào menu khách hàng.");
        System.out.println("Nhấn 3 để vào menu sản phẩm.");
        System.out.println("Nhấn 4 để vào menu báo giá.");
        
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                contributorManagement.contributorMenu();
                break;
            case 2:
                purchaserManagement.purchaserMenu();
                break;
            case 3:
                productManagement.productMenu();
                break;
            case 4:
                quotationManagement.quotationMenu();
                break;
        }
    }
}
