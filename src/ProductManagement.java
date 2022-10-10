import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    List<Product> productList = new ArrayList<>(); // Tạo list sản phẩm
    public ProductManagement() { // Tạo Constructor
        Product bang_tai_1_ly_xanh = new Product("BT-1X", "Bang tai 1 ly xanh", 180000.0, 100);
        Product bang_tai_2_ly_xanh = new Product("BT-2X", "Bang tai 2 ly xanh",  220000.0, 111);
        Product bang_tai_3_ly_xanh = new Product("BT-3X", "Bang tai 3 ly xanh", 320000.0, 133);
        Product bang_tai_3_ly_xanh_caro_bo = new Product("BT-3XCB", "Bang tai 3ly xanh caro bo (mat caro, mat bo)", 380000.0, 200);
        Product bang_tai_3_ly_xanh_caro = new Product("BT-3XC", "Bang tai 3 ly xanh caro (mat caro, mat lang)", 380000.0, 120);
        Product bang_tai_3_ly_xanh_soc = new Product("BT-3XS", "Bang tai 3 ly xanh soc (mat tren soc, mat duoi bo)", 530000.0, 130);
        Product bang_tai_3_5_ly_xanh_2_lop_bo = new Product("BT-3.5X/2B", "Bang tai 3.5 ly xanh 2 mat bo", 700000.0, 180);
        Product bang_tai_4_ly_xanh_3_lop_bo = new Product("BT-4X", "Bang tai 4 ly xanh, 3 lop bo", 450000.0, 178);
        Product bang_tai_5_ly_xanh_3_lop_bo = new Product("BT-5X", "Bang tai 5 ly xanh, 3 lop bo", 520000.0, 520);
        Product bang_tai_5_ly_xanh_bac_thang_2_bo = new Product("BT-5BT", "Bang tai 5 ly xanh hinh bac thang, 2 bo", 750000.0, 300);
        Product bang_tai_5_ly_xanh_gai_2_lop_bo = new Product("BT-5XG", "Bang tai 5 ly xanh gai, 2 lop bo", 460000.0, 50);
        Product bang_tai_5_ly_xam_gai_2_lop_bo = new Product("BT-5XAG", "Bang tai 5 ly xam gai, 2 lop bo", 480000.0, 10);

        productList.add(bang_tai_1_ly_xanh);
        productList.add(bang_tai_2_ly_xanh);
        productList.add(bang_tai_3_ly_xanh);
        productList.add(bang_tai_3_ly_xanh_caro_bo);
        productList.add(bang_tai_3_ly_xanh_caro);
        productList.add(bang_tai_3_ly_xanh_soc);
        productList.add(bang_tai_3_5_ly_xanh_2_lop_bo);
        productList.add(bang_tai_4_ly_xanh_3_lop_bo);
        productList.add(bang_tai_5_ly_xanh_3_lop_bo);
        productList.add(bang_tai_5_ly_xanh_bac_thang_2_bo);
        productList.add(bang_tai_5_ly_xanh_gai_2_lop_bo);
        productList.add(bang_tai_5_ly_xam_gai_2_lop_bo);
    }

    public void productMenu() { // Menu sản phẩm (còn thiếu)
        Scanner sc = new Scanner(System.in);
        showProductMenu();
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                addProduct();
                productMenu();
                break;
            case 2:
                searchProduct();
                productMenu();
                break;
            case 3:
                updateProduct();
                productMenu();
                break;
            case 4:
                removeProduct();
                productMenu();
                break;
            case 5:
                displayAll();
                productMenu();
                break;
            case 6:
                writeAllToFile();
                productMenu();
                break;
            case 7:
                readFromFile();
                productMenu();
                break;
            case 8:
                checkStock();
                break;
            case 0:
                System.exit(0);
        }
    }

    private void showProductMenu() {
        System.out.println("Chọn một trong các phương thức sau:");
        System.out.println("1- Thêm sản phẩm mới");
        System.out.println("2- Tìm kiếm sản phẩm");
        System.out.println("3- Sửa sản phẩm");
        System.out.println("4- Xóa sản phẩm");
        System.out.println("5- Xem tất cả sản phẩm");
        System.out.println("6- Lưu sản phẩm vào file text.");
        System.out.println("7- Đọc sản phẩm từ file text.");
        System.out.println("8- Về trang chủ");
        System.out.println("0- Thoát khỏi chương trình");
    }

    private void readFromFile() {
        try {
            FileReader fileReader = new FileReader("fileProducts.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while ((s = reader.readLine()) != null) {
                String[] ss = s.split(",", 5);
                Product product = new Product(ss[0], ss[1], Double.parseDouble(ss[2]), Integer.parseInt(ss[3]));
                productList.add(product);
                System.out.println(product);
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List getProductList() {
        return productList;
    }

    public boolean checkStock() {
        Product searchProduct = searchProduct();
        if (searchProduct.getStock() >= 3 && searchProduct().getStock() < 50) {
            System.out.println("Hàng tồn của sản phẩm " + searchProduct() + " sắp hết.");
            productMenu();
            return true;
        } else if (searchProduct().getStock() >= 100) {
            System.out.println("Hàng tồn của sản phẩm " + searchProduct() + " vẫn còn.");
            productMenu();
            return true;
        } else {
            System.out.println("Hàng tồn của sản phẩm " + searchProduct() + " đã hết. Vui lòng nhập thêm hàng tồn.");
            productMenu();
            return false;
        }
    }

    public void writeAllToFile() {
        try {
            PrintWriter printWriter = new PrintWriter("fileProducts.txt");
            for (Product product : productList) {
                printWriter.println(product.printToFile());
            }  printWriter.flush();
            System.out.println("Ghi file thanh cong.");
            printWriter.close();
        } catch (IOException exception) {
             System.err.println("Loi ghi file.");
        } finally {
            productMenu();
        }

    }

    public void displayAll() {
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void removeProduct() {
        Scanner sc = new Scanner(System.in);

        Product searchProduct = searchProduct();

        if (searchProduct != null) {
            System.out.println(searchProduct + " đã được xóa.");
            productList.remove(searchProduct);
            productMenu();
        } else {
            productNotFound();
        }
    }

    private void updateProduct() {
        Scanner sc = new Scanner(System.in);

        Product searchProduct = searchProduct();

        if (searchProduct != null) {
            System.out.println("Nhập 1 để cập nhật giá.");
            System.out.println("Nhập 2 để cập nhật lượng hàng tồn.");
            System.out.println("Nhập 0 để trở về menu sản phẩm.");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Cập nhật giá mới: ");
                    Double priceUpdated = sc.nextDouble();
                    searchProduct.setUnitPrice(priceUpdated);
                    System.out.println(searchProduct.toString());
                    break;
                case 2:
                    System.out.println("Cập nhật hàng tồn: ");
                    int stockUpdated = Integer.parseInt(sc.nextLine());
                    searchProduct.setStock(stockUpdated);
                    System.out.println(searchProduct.toString());;
                    break;
                default:
                    productMenu();
                    break;
            }
        } else {
            productNotFound();
        }
    }

    public Product searchProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm để tìm kiếm: ");
        String searchID = sc.nextLine();

        Product searchProduct = searchByID(searchID);

        if (searchProduct != null) {
            System.out.println("Sản phẩm" + searchProduct.getProductName() + " đã được tìm thấy.");
            return searchProduct;
        }
        else {
            productNotFound();
            return null;
        }
    }

    private Product searchByID(String searchID) {
        for (Product p : productList) {
            if (p.getProductID().equals(searchID))
                return p;
        }
        return null;
    }

    private void productNotFound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Product not found.");
        System.out.println("Press 1 to go back to product menu.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                productMenu();
                break;
            default:
                System.exit(0);
        }
    }



    public void addProduct() {
        Product newProduct = input();
        productList.add(newProduct);
    }

    private Product input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm mới: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String desc = sc.nextLine();
        System.out.println("Nhập đơn giá: ");
        Double price = sc.nextDouble();
        System.out.println("Nhập tồn kho: ");
        int stock = sc.nextInt();

        return new Product(id, desc, price, stock);
    }



    public static String getProductInsurance06() {
        return "06 tháng kể từ khi nhận hàng.";
    }
}
