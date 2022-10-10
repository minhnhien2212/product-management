import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuotationManagement {

    List<Quotation> quotationList = new ArrayList<>();

    public void quotationMenu() {
        Scanner sc = new Scanner(System.in);

        displayQuotationMenu();

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                addNewQuotation(); // Tạo báo giá mới
                quotationMenu();
                break;
            case 2:
                searchQuotation(); // Tìm kiếm báo giá
                quotationMenu();
                break;
            case 3:
                updateQuotation(); // Cập nhật báo giá
                quotationMenu();
                break;
            case 4:
                displayAll(); // Hiển thị tất cả báo giá
                quotationMenu();
                break;
            case 5:
                writeDataToFile(); // In dữ liệu ra file
                quotationMenu();
                break;
            case 6:
                writeQuotationToFile();
                quotationMenu();
                break;
            case 7:
                quotationMenu();
                break;
            default:
                System.exit(0);
        }
    }

    private void displayQuotationMenu() {
        System.out.println("Nhập 1 để tạo báo giá mới");
        System.out.println("Nhập 2 để tìm báo giá trước đây.");
        System.out.println("Nhập 3 để cập nhật báo giá.");
        System.out.println("Nhập 4 để hiển thị tất cả báo giá.");
        System.out.println("Nhập 5 để viết dữ liệu vào file text.");
        System.out.println("Nhập 6 để viết báo giá ra file text.");
        System.out.println("Nhập 7 để trở về menu báo giá.");
        System.out.println("Nhập 0 để thoát khỏi chương trình.");
    }

    private void writeQuotationToFile() {
        try {
            PrintWriter printWriter = new PrintWriter("Quotation.txt");
            for (Quotation q : quotationList) {
                printWriter.println(q);
            }  printWriter.flush();
            System.out.println("Ghi file thanh cong.");
            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void displayAll() {
        for (Quotation quotation : quotationList) {
            System.out.println(quotation);
        }
    }

    public void updateQuotation() {
        Quotation quotation = searchQuotation();
        if (quotation != null) {
            displayUpdateOptions();
            chooseUpdateOptions(quotation);
        } else {
            System.out.println("Không tìm thấy báo giá nào trước đây.");
            quotationMenu();
        }

    }

    private void chooseUpdateOptions(Quotation quotation) {
        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                Date date = new Date();
                quotation.setQuotationDate(date);
                System.out.println("Báo giá đã được cập nhật ngày mới.");
                System.out.println(quotation.printToFile());
                break;
            case 2:
                // CHưa hoàn thành
        }
    }

    private void displayUpdateOptions() {
        System.out.println("Nhấn 1 để cập nhật ngày báo giá.");
        System.out.println("Nhấn 2 để cập nhật giá.");
    }

    public Quotation searchQuotation() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã số báo giá để tìm kiếm: ");
        String inputQuotNumber = sc.nextLine();

        for (Quotation quotation : quotationList) {
            if (quotation.getQuotationNumber().equals(inputQuotNumber)) {
                System.out.println(quotation.getQuotationNumber() + " đã được tìm thấy.");
                System.out.println(quotation.toString());
                return quotation;
            }
        }
        return null;
    }

    private void writeDataToFile() {
        try {
            PrintWriter printWriter = new PrintWriter("fileQuotation.txt");
            for (Quotation q : quotationList) {
                printWriter.println(q.printToFile());
            }  printWriter.flush();
            System.out.println("Ghi file thanh cong.");
            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private Quotation addNewQuotation() {
        Scanner sc = new Scanner(System.in);

        String quotationNumber = inputQuotationNumber();

        boolean checkValidateNumber = isValidateNumber(quotationNumber);

        if (checkValidateNumber) {
            List<Contributor> contributorList =  quotationContributor();

            Purchaser purchaser = quotationPurchaser();

            System.out.print("Nhập số lượng sản phẩm khách hàng muốn lấy: ");
            int productAmount = Integer.parseInt(sc.nextLine());

            List<QuotationProduct> quotationProducts = new ArrayList<>(productAmount);

            for (int i = 1; i <= productAmount; i++) {
                QuotationProduct product = addProductToQuotation();
                quotationProducts.add(product);
            }

            Date date = new Date();

            System.out.print("Hiệu lực báo giá: ");
            int validity = Integer.parseInt(sc.nextLine());

            System.out.print("Thời gian giao hàng: ");
            int deliveryTime = Integer.parseInt(sc.nextLine());

            Quotation quotation = new Quotation(quotationNumber, contributorList, purchaser, quotationProducts, date, validity, deliveryTime);

            System.out.println(quotation.toString());

            quotationList.add(quotation);
            return quotation;
        } else {
            System.out.println("Mã báo giá không hợp lệ. Vui lòng thử lại.");
            addNewQuotation();
        }
        return null;
    }

    private String inputQuotationNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã báo giá: ");
        return sc.nextLine();
    }

    private boolean isValidateNumber(String quotationNumber) {
        final String number_regex = "^[A-Z]{1,3}-[A-Z0-9]{3,7}$";
        Pattern pattern = Pattern.compile(number_regex);
        Matcher matcher = pattern.matcher(quotationNumber);
        return matcher.matches();
    }

    private QuotationProduct addProductToQuotation() {
        Scanner sc = new Scanner(System.in);

        ProductManagement productManagement = new ProductManagement();

        Product product = productManagement.searchProduct();

        System.out.print("Nhập chu vi nối tròn của sản phẩm: ");
        Double perimeter = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập bề ngang của sản phẩm: ");
        Double width = Double.parseDouble(sc.nextLine());

        return new QuotationProduct(product, width, perimeter);
    }

    private Purchaser quotationPurchaser() {
        Scanner sc = new Scanner(System.in);

        PurchaserManagement purchaserManagement = new PurchaserManagement();

        Purchaser purchaser = purchaserManagement.searchByName();

        if (purchaser != null) {
            return purchaser;
        }
        else {
            System.out.println("Khách hàng không được tìm thấy trong danh sách. Tạo một khách hàng mới:");
            Purchaser purchaser1 = purchaserManagement.addNewPurchaser();
            return purchaser1;
        }
    }

    private List<Contributor> quotationContributor() {
        Scanner sc = new Scanner(System.in);

        ContributorManagement contributorManagement = new ContributorManagement();
        List<Contributor> contributorList = new ArrayList<>();

        System.out.print("Nhập số lượng nhân viên tham gia phụ trách đơn hàng: ");
        int numberOfCon = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= numberOfCon; i++) {
            Contributor contributor = contributorManagement.searchByName();
            if (contributor != null) {
                contributorList.add(contributor);
                System.out.println(contributor.getName() + " đã được thêm vào danh sách phụ trách đơn hàng.");
            }
        } return contributorList;
    }

    private void readFromFile(Date date) {
        /*
        * FileInputStream inputStream = null;
        String path="anhviet109K.txt";
        Scanner sc = null;
        //String l;
        String meaning;
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains(word)){
                    System.out.println(line);
                    while(!(meaning=sc.nextLine()).equals("")){
                        System.out.println(meaning);
                    }

                    return;
                }
            }
            System.err.println("Word not found");
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }*/
    }
}
