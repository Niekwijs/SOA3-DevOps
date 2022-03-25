package Report;

public class ExportPDF implements IExportBehavior{

    public Report report;


    @Override
    public void export() {
        System.out.println("PDF");
        System.out.println("-----------------------------");
        System.out.println(report.header);
        System.out.println(report.contents);
        System.out.println(report.footer);
        System.out.println("-----------------------------");
    }
}
