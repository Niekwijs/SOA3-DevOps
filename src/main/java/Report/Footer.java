package report;

public class Footer {

    private final String address;
    private final String companyName;
    private final String phoneNumber;

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
