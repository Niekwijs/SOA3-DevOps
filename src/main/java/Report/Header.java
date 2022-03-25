package Report;

import java.util.Date;

public class Header {
    public String companyName;
    public String sprintName;
    public int version;
    public Date date;

    public Header(String companyName, String sprintName, int version, Date date) {
        this.companyName = companyName;
        this.sprintName = sprintName;
        this.version = version;
        this.date = date;
    }
}
