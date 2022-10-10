import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContributorManagement {

    List<Contributor> contributorList = new ArrayList<>(); // Tạo một array list chứa thông tin nhân viên

    public ContributorManagement() { // Tạo hàm constructor
        Contributor con01 = new Contributor("KD-0001", "Nguyen Van A", "kinh doanh", "0912146749", "nguyenvana.vp.sale@gmail.com");
        Contributor con02 = new Contributor("MKT-0002", "Tran Le B", "marketing", "0912146749", "tranleb.vp.mkt@gmail.com");
        Contributor con03 = new Contributor("TV-0004", "Pham Tran C", "tu van ky thuat", "0912146749", "phamtranc.vp.techad@gmail.com");
        Contributor con04 = new Contributor("TK-0005", "Nguyen Minh D", "thiet ke", "0912146749", "nguyenminhd.vp.design@gmail.com");
        Contributor con05 = new Contributor("HR-0002", "Hoang Thi E", "quan ly nhan su", "0912146749", "hoangthie.vp.hr@gmail.com");
        Contributor con06 = new Contributor("KD-0002", "Phan Van E", "kinh doanh", "0912146749", "hoangthie.vp.sale@gmail.com");

        contributorList.add(con01);
        contributorList.add(con02);
        contributorList.add(con03);
        contributorList.add(con04);
        contributorList.add(con05);
        contributorList.add(con06);
    }

    public void contributorMenu() { // Hiển thị menu
        Scanner sc = new Scanner(System.in);

        displayContributorMenu(); // Hiển thị các lựa chọn

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                addContributor();
                contributorMenu();
                break;
            case 2:
                searchContributor();
                contributorMenu();
                break;
            case 3:
                updateContributor();
                contributorMenu();
                break;
            case 4:
                displayAllContributors();
                contributorMenu();
                break;
            case 5:
                writeToFile();
                contributorMenu();
                break;
            case 6:
                readFromFile();
                contributorMenu();
                break;
            case 0:
            default:
                System.exit(0);
        }
    }

    private void displayContributorMenu() { // Hiển thị các lựa chọn
        System.out.println("Nhấn phím 1 để thêm nhân viên mới");
        System.out.println("Nhấn phím 2 để tìm kiếm nhân viên trước đây");
        System.out.println("Nhấn phím 3 để cập nhật thông tin nhân viên");
        System.out.println("Nhấn phím 4 để hiển thị tất cả nhân viên");
        System.out.println("Nhấn phím 5 để lưu tất cả nhân viên vào file text");
        System.out.println("Nhấn phím 6 để đọc từ file.");
        System.out.println("Nhấn phím 0 để thoát khỏi chương trình.");
    }

    public void addContributor() { // Thêm nhân viên mới
        Contributor contributor = input();
        contributorList.add(contributor);
        System.out.println("Nhân viên mới đã được thêm vào danh sách.");
        System.out.println(contributor);
    }

    private Contributor input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ID nhân viên mới: ");
        String id = sc.nextLine();
        boolean isValidateID = isValidateID(id); // Kiểm tra id nhập vào có hợp lệ hay không

        if (isValidateID) {
            System.out.print("Nhập tên nhân viên mới: ");
            String name = sc.nextLine();
            System.out.print("Nhập phòng ban: ");
            String department = sc.nextLine();
            System.out.print("Nhập số điện thoại: ");
            String phone = sc.nextLine();
            System.out.print("Nhập email: ");
            String email = sc.nextLine();

            return new Contributor(id, name, department, phone, email);
        }
        else {
            System.out.println("ID điền vào không hợp lệ. Vui lòng thử lại.");
            input();
        }
        return null;
    }

    private boolean isValidateID(String id) {
        final String id_REGEX = "^[A-Z]{1,4}-[0,9]{1,4}$";
        Pattern pattern = Pattern.compile(id_REGEX);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public Contributor searchContributor() {
        Scanner sc = new Scanner(System.in);
        displaySearchOptions();
        int input = sc.nextInt();
        Contributor contributor = null;

        switch (input) {
            case 0:
                contributor = searchByID();
                break;
            case 1:
                contributor = searchByName();
                break;
            case 2:
                contributor = searchByPhone();
                break;
            case 3:
                contributor = searchByEmail();
                break;
            case 4:
                contributor = searchByDepartment();
                break;
            default:
                contributorMenu();
                break;
        }
        return contributor;
    }

    private void displaySearchOptions() {
        System.out.println("Nhấn 1 để tìm kiếm theo tên.");
        System.out.println("Nhấn 2 để tìm kiếm theo số điện thoại.");
        System.out.println("Nhấn 3 để tìm kiếm theo email.");
        System.out.println("Nhấn 4 để tìm kiếm theo phòng ban.");
    }

    private Contributor searchByDepartment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập phòng ban để tìm kiếm nhân viên: ");
        String search = sc.nextLine();

        for (Contributor c : contributorList) {
            if (c.getPhone().equals(search)) {
                System.out.println(c);
                return c;
            }
        }
        return null;
    }

    private Contributor searchByEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập email để tìm kiếm nhân viên: ");
        String search = sc.nextLine();

        for (Contributor c : contributorList) {
            if (c.getPhone().equals(search)) {
                System.out.println(c);
                return c;
            }
        }
        return null;
    }

    private Contributor searchByPhone() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số điện thoại để tìm kiếm nhân viên: ");
        String search = sc.nextLine();

        for (Contributor c : contributorList) {
            if (c.getPhone().equals(search)) {
                System.out.println(c);
                return c;
            }
        }
        return null;
    }

    public Contributor searchByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên để tìm kiếm nhân viên: ");
        String search = sc.nextLine();

        for (Contributor c : contributorList) {
            if (c.getName().trim().equals(search.trim())) {
                System.out.println(c);
                return c;
            }
        }
        contributorNotFound();
        return null;
    }

    private Contributor searchByID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhân viên để tìm kiếm nhân viên: ");
        String search = sc.nextLine();

        for (Contributor c : contributorList) {
            if (c.getId().trim().equals(search.trim())) {
                System.out.println(c);
                return c;
            }
        }
        contributorNotFound();
        return null;
    }

    private void contributorNotFound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Không tìm thấy nhân viên phụ trách.");
        System.out.println("Nhấn phím 1 để trở về menu.");
        System.out.println("Nhấn phím bất kỳ để thoát khỏi chương trình.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                contributorMenu();
                break;
            default:
                System.exit(0);
        }
    }

    public void updateContributor() {
        Contributor contributor = searchByName();
        if (contributor != null) {
            while (true) {
                displayUpdateOptions();
                chooseUpdateOptions(contributor);
            }
        }
    }

    private void chooseUpdateOptions(Contributor contributor) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: // sửa sđt
                updatePhone(contributor);
                break;
            case 2: // sửa email
                updateEmail(contributor);
                break;
            case 3: // sửa phòng ban
                updateDepartment(contributor);
                break;
            case 0:
                contributorMenu(); // Quay về menu
                break;
            default:
                System.exit(0); // Thoát khỏi chương trình
        }
    }

    private void updateEmail(Contributor contributor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật email: ");
        String emailUpdated = sc.nextLine();
        contributor.setEmail(emailUpdated);
        updateSuccess(contributor);
    }

    private void updateDepartment(Contributor contributor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật phòng ban: ");
        String departmentUpdated = sc.nextLine();
        contributor.setDepartment(departmentUpdated);
        updateSuccess(contributor);
    }

    private void updatePhone(Contributor contributor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cập nhật số điện thoại: ");
        String phoneUpdated = sc.nextLine();
        contributor.setName(phoneUpdated);
        updateSuccess(contributor);
    }

    private void updateSuccess(Contributor contributor) {
        System.out.println(contributor.getName() + " đã được cập nhật.");
        System.out.println(contributor);
    }

    private void displayUpdateOptions() {
        System.out.println("Nhập số 1 để sửa tên.");
        System.out.println("Nhập số 2 để sửa số điện thoại.");
        System.out.println("Nhập số 3 để sửa địa chỉ.");
        System.out.println("Nhập số 4 để sửa email.");
        System.out.println("Nhập số 0 để quay trở về menu nhân viên.");
        System.out.println("Vui lòng chọn một trong các lựa chọn trên.");
    }

    public void displayAllContributors() {
        for (Contributor c : contributorList) {
            System.out.println(c);
        }
    }

    private void readFromFile() {
        try {
            FileReader fileReader = new FileReader("fileContributor.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while ((s = reader.readLine()) != null) {
                String[] ss = s.split(",", 5);
                Contributor contributor = new Contributor(ss[0], ss[1], ss[2], ss[3], ss[4]);
                contributorList.add(contributor);
                System.out.println(contributor);
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try {
            PrintWriter printWriter = new PrintWriter("fileContributor.txt");
            for (Contributor c : contributorList) {
                printWriter.println(c.printToFile());
            }  printWriter.flush();
            System.out.println("Ghi file thanh cong.");
            printWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("Loi ghi file.");
        }
    }
}
