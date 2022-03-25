package Report;

public class ExportPNG implements IExportBehavior{
    public Report report;

    public ExportPNG(Report report) {
        this.report = report;
    }

    @Override
    public void export() {
        System.out.println("PNG");
        System.out.println("-----------------------------");
        System.out.println(report.header.toString());
        System.out.println(report.contents);
        System.out.println(report.footer.toString());
        System.out.println("-----------------------------");
    }
}
