package Report;

import java.util.List;

public class Report {

    public Header header;
    public List<String> contents;
    public Footer footer;
    public IExportBehavior exportBehavior;

    @Override
    public String toString() {
        return "Report{" +
                "header=" + header +
                ", contents=" + contents +
                ", footer=" + footer +
                ", exportBehavior=" + exportBehavior +
                '}';
    }
}
