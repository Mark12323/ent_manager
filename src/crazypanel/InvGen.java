
package crazypanel;

import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.colors.ColorConstants.GRAY;
import static com.itextpdf.kernel.colors.ColorConstants.WHITE;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class InvGen implements Job {
    private Connection con;
    String urlink = "jdbc:MySQL://localhost:3306/java_users_off";
    String u = "root";
    String p = "";
    String tot;
    String tot1;
    InventoryPage ip = new InventoryPage();
    String tot2 = ip.totstock.toString();
    String tot3 = ip.estock.toString();
    String tot4 = ip.nestock.toString();
    RecordsPage rp = new RecordsPage();
    String val = rp.totincome.toString();
    String val1 = rp.netincome.toString();
    String val2 = rp.expenses.toString();
    List<String> staffList;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your PDF generation logic goes here
        System.out.println("Begin");    
        String filePath = "invoice.pdf";
        //JOptionPane.showMessageDialog(null, "Invoice Generation successful");
        try {
            String query = "SELECT CONCAT(Surname, ' ', First_Name, ' ', Other_Names) AS full_name FROM staff";
            con = DriverManager.getConnection(urlink , u , p);
            //Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Retrieve the full name from the result set
                String fullName = rs.getString("full_name");

                // Add the full name to the fullNameList
                staffList.add(fullName);
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        try{
            String query = "SELECT SUM(quantity) as total_amount FROM sales_record " +
               "WHERE YEAR(date_column) = YEAR(CURDATE()) AND MONTH(date_column) = MONTH(CURDATE())";
            con = DriverManager.getConnection(urlink , u , p);
            //Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                double totalAmount = rs.getDouble("total_amount");
                tot = Double.toString(totalAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         try{
            String query = "SELECT SUM(quantity) as total_amount FROM product_data  " +
               "WHERE YEAR(date_column) = YEAR(CURDATE()) AND MONTH(date_column) = MONTH(CURDATE())";
            con = DriverManager.getConnection(urlink , u , p);
            //Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                double totalAmount = rs.getDouble("total_amount");
                tot1 = Double.toString(totalAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            //variable declaration
            float lim2 = 190f;
            float lim = 285f;
            float lim1 = lim + 150f;
            float [] dimension = {lim1, lim};
            float [] fullWidth = {lim2 * 3};
            float [] threeColWidth = {lim2, lim2, lim2};
            Paragraph onesp = new Paragraph("\n");
            
            Random rand = new Random();
            long randomNum = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
            String ra = String.valueOf(randomNum);
            LocalDate da = LocalDate.now();
                        
            Table table = new Table(dimension);
            table.addCell(new Cell().add(new Paragraph("Company Invoice")).setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
            Table innerTable = new Table(new float [] {lim/2,lim/2});
            innerTable.addCell(getHeaderTextCell("Invoice No."));
            innerTable.addCell(getHeaderTextCellValue(ra));
            innerTable.addCell(getHeaderTextCell("Invoice Date"));
            innerTable.addCell(getHeaderTextCellValue(da.toString()));
            
            table.addCell(new Cell().add(innerTable).setBorder(Border.NO_BORDER));
            
            Border gb = new SolidBorder(GRAY, 2F);
            Table div = new Table(fullWidth);
            div.setBorder(gb); 
            
            
            document.add(table);
            document.add(onesp);
            document.add(div);
            document.add(onesp);
            
            Table twotab = new Table(dimension);
            twotab.addCell(getBillingData("Company Details"));
            twotab.addCell(getBillingData("Invoice Details"));
            document.add(twotab.setMarginBottom(12f));
            
            Table twotab2 = new Table(dimension);
            twotab2.addCell(getLeftCell("Company" , true));
            twotab2.addCell(getLeftCell("Name" , true));
            twotab2.addCell(getLeftCell("Data" , false));
            twotab2.addCell(getLeftCell("More Data" , false));
            document.add(twotab2);
            
            Table twotab3 = new Table(dimension);
            twotab3.addCell(getLeftCell("Text" , true));
            twotab3.addCell(getLeftCell("More text" , true));
            twotab3.addCell(getLeftCell("Even more Text" , false));
            twotab3.addCell(getLeftCell("Extra Text" , false));
            document.add(twotab3);
            
            Table div2 = new Table(fullWidth);
            Border nbg = new DashedBorder(GRAY, 0.5f);
            document.add(onesp);
            document.add(div2.setBorder(nbg));
            
            Paragraph para = new Paragraph("Financial Breakdown");
            document.add(onesp);
            document.add(para.setBold());
            Table threeCol = new Table(threeColWidth);
            threeCol.setBackgroundColor(BLACK,0.7f);
            
            threeCol.addCell(new Cell().add(new Paragraph("Report")).setBold().setFontColor(WHITE).setBorder(Border.NO_BORDER));
            threeCol.addCell(new Cell().add(new Paragraph("Values")).setBold().setFontColor(WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeCol.addCell(new Cell().add(new Paragraph("Disparity")).setBold().setFontColor(WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
            document.add(threeCol);

            List<Report> Values = new ArrayList<>();
            Values.add(new Report("Total Income", val , "****"));
            Values.add(new Report("Expenses", val2 , "****"));
            Values.add(new Report("Net Income(Gain)", val1 , "****"));
            Values.add(new Report("Stock sold", tot , "****"));
            Values.add(new Report("Stock bought", tot1 , "****"));
            Values.add(new Report("Available stock", tot2 , "****"));
            Values.add(new Report("Empties available", tot3 , "****"));
            Values.add(new Report("Non-empties available", tot4 , "****"));
            
            Table threeCol2 = new Table(threeColWidth);
            for (Report report:Values) {
                threeCol2.addCell(new Cell().add(new Paragraph(report.getReport())).setBorder(Border.NO_BORDER)).setMarginLeft(10f);
                threeCol2.addCell(new Cell().add(new Paragraph(report.getValue())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
                threeCol2.addCell(new Cell().add(new Paragraph(report.getDis())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER)).setMarginRight(15f);
            }
            document.add(threeCol2);
            Paragraph para1 = new Paragraph("Staff List");
            document.add(onesp);
            document.add(div2.setBorder(nbg));
            document.add(onesp);
            document.add(para1.setBold());
            
            staffList = new ArrayList<>();
            staffList.add("Mark");
            staffList.add("Stephanie");
            Table staff = new Table(threeColWidth);
            for (String val:staffList) {
                staff.addCell(new Cell().add(new Paragraph(val)).setBorder(Border.NO_BORDER)).setMarginLeft(15f);
            }
            document.add(staff);
            document.close();
            JOptionPane.showMessageDialog(null, "Invoice Generation successful");


            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Cell getHeaderTextCell(String text) {
        return new Cell().add(new Paragraph(text)).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }  
    static Cell getHeaderTextCellValue(String text) {
        return new Cell().add(new Paragraph(text)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }  
    static Cell getBillingData(String text) {
        return new Cell().add(new Paragraph(text)).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static Cell getLeftCell(String text, boolean isBold) {
        Cell myCell = new Cell().add(new Paragraph(text)).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ?myCell.setBold():myCell;
    }
    
}

class Report {
    private String report;
    private String value;
    private String disparity;
    
    public Report(String report, String value, String disparity) {
        this.report = report;
        this.value = value;
        this.disparity = disparity;
    }  
    public String getReport() {
        return report;
    }
    public String getValue() {
        return value;
    }
    public String getDis() {
        return disparity;
    }
    public void setReport(String report) {
        this.report = report;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setDis(String disparity) {
        this.disparity = disparity;
    }
}