package Report;

public class Footer {
    public String address;
    public String companyName;
    public String phoneNumber;

    public Footer(String address, String companyName, String phoneNumber) {
        this.address = address;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "address='" + address + '\'' +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' ;
    }
}
