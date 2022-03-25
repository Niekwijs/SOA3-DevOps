package Report;

import java.util.Date;
import java.util.List;

public class ReportBuilder {
    public Report report;

    public ReportBuilder() {
        this.report = new Report();
    }

    public void addHeader(String companyName, String sprintName, int version, Date date){
        this.report.header = new Header(companyName, sprintName, version, date);
    }
    public void addFooter(String address, String companyName, String phoneNumber){
        this.report.footer = new Footer(address, companyName, phoneNumber);
    }
    public void addContent(List<String> content){
        this.report.contents = content;
    }
    public void setExportType(ExportType exportType){
        if(exportType == ExportType.PDF){
            this.report.exportBehavior = new ExportPDF(this.report);
        }else{
            this.report.exportBehavior = new ExportPNG(this.report);
        }
    }
}
