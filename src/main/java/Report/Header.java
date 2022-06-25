package report;

import java.util.Date;

public class Header {

    private final String companyName;
    private final String sprintName;
    private final int version;
    private final Date date;

    public Header(String companyName, String sprintName, int version, Date date) {
        this.companyName = companyName;
        this.sprintName = sprintName;
        this.version = version;
        this.date = date;
    }

    @Override
    public String toString() {
        return "companyName='" + companyName + '\'' +
                ", sprintName='" + sprintName + '\'' +
                ", version=" + version +
                ", date=" + date ;
    }
}
