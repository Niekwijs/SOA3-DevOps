package Report;

public class ExportPDF implements IExportBehavior{


    @Override
    public void export(Report report) {
        System.out.println("PDF");
        System.out.println("-----------------------------");
        System.out.println(report.header.toString());
        System.out.println(report.contents);
        System.out.println(report.footer.toString());
        System.out.println("-----------------------------");
    }
}
