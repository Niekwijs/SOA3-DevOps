package report;

public class ExportPNG implements IExportBehavior{
    @Override
    public void export(Report report) {
        System.out.println("PNG");
        System.out.println("-----------------------------");
        System.out.println(report.getHeader().toString());
        System.out.println(report.getContents());
        System.out.println(report.getFooter().toString());
        System.out.println("-----------------------------");
    }
}
