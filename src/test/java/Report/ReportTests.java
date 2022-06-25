package report;

import nl.altindag.console.ConsoleCaptor;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import static org.testng.AssertJUnit.assertTrue;

public class ReportTests {

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void T_39_1_report_is_generated_successfully(){
        // Arrange
        ReportBuilder reportBuilder = new ReportBuilder();
        ArrayList<String> contents = new ArrayList<>();
        contents.add("This is a report!");

        Date date = new Date();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        //Act
        reportBuilder.addHeader("Company name", "Sprint name", 1, date);
        reportBuilder.addContent(contents);
        reportBuilder.addFooter("Address", "Company name", "06");
        reportBuilder.setExportType(ExportType.PDF);
        reportBuilder.getReport();

        //Assert
        assertTrue(os.toString().contains("PDF"));
        assertTrue(os.toString().contains("-----------------------------"));
        assertTrue(os.toString().contains("companyName='Company name', sprintName='Sprint name', version=1, date=" + date));
        assertTrue(os.toString().contains("[This is a report!]"));
        assertTrue(os.toString().contains("address='Address', companyName='Company name', phoneNumber='06'"));
        assertTrue(os.toString().contains("-----------------------------"));

    }

    @Test
    public void T_40_1_header_is_added_successfully(){
        // Arrange
        ReportBuilder reportBuilder = new ReportBuilder();
        ArrayList<String> contents = new ArrayList<>();
        contents.add("This is a report!");

        Date date = new Date();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        //Act
        reportBuilder.addHeader("Company name", "Sprint name", 1, date);
        reportBuilder.addContent(contents);
        reportBuilder.addFooter("", "", "");
        reportBuilder.setExportType(ExportType.PDF);
        reportBuilder.getReport();

        //Assert
        assertTrue(os.toString().contains("companyName='Company name', sprintName='Sprint name', version=1, date=" + date));

    }

    @Test
    public void T_40_2_footer_is_added_successfully(){
        // Arrange
        ReportBuilder reportBuilder = new ReportBuilder();
        ArrayList<String> contents = new ArrayList<>();
        contents.add("This is a report!");

        Date date = new Date();

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        //Act
        reportBuilder.addHeader("", "", 0, date);
        reportBuilder.addContent(contents);
        reportBuilder.addFooter("Address", "Company name", "06");
        reportBuilder.setExportType(ExportType.PDF);
        reportBuilder.getReport();

        //Assert
        assertTrue(os.toString().contains("address='Address', companyName='Company name', phoneNumber='06'"));

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void T_41_1_export_as_PDF(){
        // Arrange
        ReportBuilder reportBuilder = new ReportBuilder();

        //Act
        reportBuilder.setExportType(ExportType.PDF);
        reportBuilder.getReport();

        //Assert
        assert(consoleCaptor.getStandardOutput()).contains("PDF");
        consoleCaptor.clearOutput();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void T_41_2_export_as_PNG(){
        // Arrange
        ReportBuilder reportBuilder = new ReportBuilder();

        //Act
        reportBuilder.setExportType(ExportType.PNG);
        reportBuilder.getReport();

        //Assert
        assert(consoleCaptor.getStandardOutput()).contains("PNG");
        consoleCaptor.clearOutput();
    }

}
