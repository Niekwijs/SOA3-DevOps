package report;

import java.util.List;

public class Report {

    private Header header;
    private List<String> contents;
    private Footer footer;
    private IExportBehavior exportBehavior;

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public void setExportBehavior(IExportBehavior exportBehavior) {
        this.exportBehavior = exportBehavior;
    }

    public Header getHeader() {
        return header;
    }

    public List<String> getContents() {
        return contents;
    }

    public Footer getFooter() {
        return footer;
    }

    public IExportBehavior getExportBehavior() {
        return exportBehavior;
    }

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
