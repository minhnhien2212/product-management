public class Purchaser {
    private String id;
    private String name;
    private String workField;
    private String phone;
    private String email;
    private String address;

    public Purchaser(String id, String name, String workField, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.workField = workField;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkField() {
        return workField;
    }

    public void setWorkField(String workField) {
        this.workField = workField;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Purchaser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", workField='" + workField + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String printToFile() {
        return id + "," + name + "," + workField + "," + phone + "," + email + "," + address;
    }
}
