import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaserManagement {

    List<Purchaser> purchaserList = new ArrayList<>(); // Tạo danh sách người mua

    public PurchaserManagement() { // Thêm người mua vào danh sách (Cần tìm nơi lưu trữ dữ liệu)
        Purchaser purA = new Purchaser("TT-0001", "A company", "textile", "012345679", "companyA@gmail.com", "TPHCM");
        Purchaser purB = new Purchaser("FNB-0002", "B company", "fnb", "1223475901", "companyB@gmail.com", "HANOI");
        Purchaser purC = new Purchaser("AUTOM-0003", "C company", "automation", "0103344688", "companyC@gmail.com", "DANANG");
        Purchaser purD = new Purchaser("SEAF-0004", "D company", "seafood", "121367493", "companyD@gmail.com", "HUE");
        Purchaser purE = new Purchaser("MECH-0005", "E company", "mechanics", "012354679", "companyE@gmail.com", "NHATRANG");

        purchaserList.add(purA);
        purchaserList.add(purB);
        purchaserList.add(purC);
        purchaserList.add(purD);
        purchaserList.add(purE);
    }

    public void purchaserMenu() { // Menu thao tác vào danh sách người mua
        Scanner sc = new Scanner(System.in);
        showPurchaserMenu();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                addNewPurchaser();
                purchaserMenu();
                break;
            case 2:
                searchPurchaser();
                purchaserMenu();
                break;
            case 3:
                updatePurchaser();
                purchaserMenu();
                break;
            case 4:
                displayAll();
                purchaserMenu();
                break;
            case 5:
                writeAllToFile();
                purchaserMenu();
                break;
            case 6:
                readFromFile();
                purchaserMenu();
                break;
            case 0:
            default:
                System.exit(0);
        }
    }

    private void showPurchaserMenu() {
        System.out.println("Nhấn phím 1 để thêm người mua mới");
        System.out.println("Nhấn phím 2 để tìm kiếm người mua trước đây");
        System.out.println("Nhấn phím 3 để cập nhật thông tin người mua");
        System.out.println("Nhấn phím 4 để hiển thị tất cả người mua");
        System.out.println("Nhấn phím 5 để lưu tất cả người mua vào file text");
        System.out.println("Nhấn phím 6 để đọc dữ liệu từ file text.");
        System.out.println("Nhấn phím 0 để thoát khỏi chương trình.");
    }

    private void readFromFile() {
        try {
            FileReader fileReader = new FileReader("filePurchaser.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while ((s = reader.readLine()) != null) {
                String[] ss = s.split(",", 5);
                Purchaser purchaser = new Purchaser(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5]);
                purchaserList.add(purchaser);
                System.out.println(purchaser);
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAllToFile() {
        try {
            PrintWriter printWriter = new PrintWriter("filePurchaser.txt");
            for (Purchaser p : purchaserList) {
                printWriter.println(p.printToFile());
            }  printWriter.flush();
            System.out.println("Ghi file thanh cong.");
            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            // System.err.println("Loi ghi file.");
        }
        purchaserMenu();
    }

    private void displayAll() {
        for (Purchaser purchaser : purchaserList) {
            System.out.println(purchaser);
        }
        purchaserMenu();
    }

    private void updatePurchaser() {
        Purchaser purchaser = searchByName();
        if (purchaser != null) {
            while (true) {
                displayUpdateOptions();
                chooseUpdateOptions(purchaser);
            }
        }
        purchaserMenu();
    }

    private void chooseUpdateOptions(Purchaser purchaser) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                updateName(purchaser);
                purchaserMenu();
                break;
            case 2:
                updatePhone(purchaser);
                purchaserMenu();
                break;
            case 3:
                updateAddress(purchaser);
                purchaserMenu();
                break;
            case 4:
                updateEmail(purchaser);
                purchaserMenu();
                break;
            default:
                purchaserMenu();
                break;
        }
    }

    private void updateName(Purchaser purchaser) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật tên: ");
        String nameUpdated = sc.nextLine();
        purchaser.setName(nameUpdated);
        updateSuccess(purchaser);
    }

    private void updatePhone(Purchaser purchaser) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật số điện thoại: ");
        String phoneUpdated = sc.nextLine();
        purchaser.setPhone(phoneUpdated);
        updateSuccess(purchaser);
    }

    private void updateAddress(Purchaser purchaser) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật địa chỉ: ");
        String addressUpdated = sc.nextLine();
        purchaser.setAddress(addressUpdated);
        updateSuccess(purchaser);
    }

    private void updateEmail(Purchaser purchaser) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật email: ");
        String emailUpdated = sc.nextLine();
        purchaser.setEmail(emailUpdated);
        updateSuccess(purchaser);
    }

    private void updateSuccess(Purchaser purchaser) {
        System.out.println(purchaser.getName() + " đã được cập nhật.");
        System.out.println(purchaser);
    }

    private void displayUpdateOptions() {
        System.out.println("Nhập số 1 để sửa tên.");
        System.out.println("Nhập số 2 để sửa số điện thoại.");
        System.out.println("Nhập số 3 để sửa địa chỉ.");
        System.out.println("Nhập số 4 để sửa email.");
        System.out.println("Nhập số 0 để quay trở về menu khách hàng.");
    }

    private void searchPurchaser() {
        Scanner sc = new Scanner(System.in);

        displaySearchOptions();
        int input = sc.nextInt();

        switch (input) {
            case 1:
                searchByID();
                purchaserMenu();
                break;
            case 2:
                searchByName();
                purchaserMenu();
                break;
            case 3:
                searchByWorkField();
                purchaserMenu();
                break;
            default:
                purchaserMenu();
                break;
        }
    }

    public Purchaser searchByWorkField() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập lĩnh vực kinh doanh để tìm kiếm: ");
        String search = sc.nextLine();

        for (Purchaser purchaser : purchaserList) {
            if (purchaser.getId().equals(search)) {
                System.out.println(purchaser);
                return purchaser;
            }
        }
        return null;
    }

    public Purchaser searchByName() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên khách hàng để tìm kiếm: ");
        String search = sc.nextLine();

        for (Purchaser purchaser : purchaserList) {
            if (purchaser.getName().equals(search)) {
                System.out.println(purchaser);
                return purchaser;
            }
        }
        return null;
    }

    public Purchaser searchByID() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã khách hàng để tìm kiếm: ");
        String search = sc.nextLine();

        for (Purchaser purchaser : purchaserList) {
            if (purchaser.getId().equals(search)) {
                System.out.println(purchaser);
                return purchaser;
            }
        }
        return null;
    }

    private void displaySearchOptions() {
        System.out.println("Nhấn 1 để tìm kiếm theo mã khách hàng.");
        System.out.println("Nhấn 2 để tìm kiếm theo tên khách hàng.");
        System.out.println("Nhấn 3 để tìm kiếm theo lĩnh vực kinh doanh.");
        System.out.println("Nhấn 0 để trở về menu khách hàng.");
    }

    public Purchaser addNewPurchaser() {
        Purchaser newPurchaser = input();
        purchaserList.add(newPurchaser);
        System.out.println("Tạo khách hàng mới thành công.");
        System.out.println(newPurchaser);
        return newPurchaser;
    }

    private Purchaser input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ID người mua mới: ");
        String id = sc.nextLine();
        boolean checkID = isIDValidate(id);

        if (checkID) {
            System.out.print("Nhập tên người mua mới: ");
            String name = sc.nextLine();
            System.out.print("Chuyên ngành: ");
            String workField = sc.nextLine();
            System.out.print("Số điện thoại:");
            String phone = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Nhập địa chỉ nhận hàng: ");
            String address = sc.nextLine();

            return new Purchaser(id, name, workField, phone, email, address);
        } else {
            System.out.println("ID nhập vào không phù hợp. Vui lòng nhập lại.");
            input();
        }
        return null;
    }

    private boolean isIDValidate(String id) {
        final String id_regex = "^[A-Z]{2,5}-[0-9]{3,7}$";
        Pattern pattern = Pattern.compile(id_regex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }



}
