package report;

public class ExportPDF implements IExportBehavior{


    @Override
    public void export(Report report) {
        System.out.println("PDF");
        System.out.println("-----------------------------");
        System.out.println(report.getHeader().toString());
        System.out.println(report.getContents());
        System.out.println(report.getFooter().toString());
        System.out.println("-----------------------------");
    }
}
