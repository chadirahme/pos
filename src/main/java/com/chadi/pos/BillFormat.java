package com.chadi.pos;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;

public class BillFormat {

    public String test()
    {
        System.out.println("hiiii");

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }

        //GEN-LAST:event_jButton1ActionPerformed
       // Graphics2D g2d = (Graphics2D) graphics;
       // double width = pageFormat.getImageableWidth();

        return "test";
    }

    public String testDialog()
    {
        System.out.println("hiiii");
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
           // pj.setPrintable(new PFDPageable(document));
            //pj.printDialog();
            printToPDF("Test.pdf", new BillPrintable());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "test";
    }


    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public static boolean printToPDF(String file, Printable printable) {
        try {
            PageFormat pageFormat = new PageFormat();
            PDDocument doc = new PDDocument();
            int width = (int)(pageFormat.getWidth());
            int height = (int)(pageFormat.getHeight());

            int currentPage = 0;
            while(true) {
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, width, height);
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

                if(printable.print(g2d, pageFormat, currentPage) == Printable.NO_SUCH_PAGE) {
                    break;
                }
                ImageIO.write(image, "png", new File("test.png"));

                PDPage page = new PDPage();
                doc.addPage(page);

                PDJpeg img = new PDJpeg(doc, image); //Blurry
                //PDPixelMap img = new PDPixelMap(doc, image); //Black
                PDPageContentStream content = new PDPageContentStream(doc, page);
                content.drawImage(img, 0, 0);
                content.close();

                ++currentPage;
            }

            doc.save(file);
            doc.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }





}
