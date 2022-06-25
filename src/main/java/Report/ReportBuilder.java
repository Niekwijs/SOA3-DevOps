package report;

import java.util.Date;
import java.util.List;

public class ReportBuilder {
    private final Report report;

    public ReportBuilder() {
        this.report = new Report();
    }

    public void addHeader(String companyName, String sprintName, int version, Date date){
        this.report.setHeader(new Header(companyName, sprintName, version, date));
    }
    public void addFooter(String address, String companyName, String phoneNumber){
        this.report.setFooter(new Footer(address, companyName, phoneNumber));
    }
    public void addContent(List<String> content){
        this.report.setContents(content);
    }
    public void setExportType(ExportType exportType){
        if(exportType == ExportType.PDF){
            this.report.setExportBehavior(new ExportPDF());
        }else{
            this.report.setExportBehavior(new ExportPNG());
        }
    }

    public void getReport() {
        report.getExportBehavior().export(this.report);
    }

}
