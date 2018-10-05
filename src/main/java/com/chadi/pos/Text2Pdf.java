package com.chadi.pos;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class Text2Pdf {
    public static final String TEXT
            = "resources/text/jekyll_hyde.txt";
    public static final String DEST
            = "D:\\chadi\\pos\\text2pdf.pdf";

    public static void main(String[] args)
            throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Text2Pdf().createPdf(DEST);
    }

    public void createPdf(String dest)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        String aString =formatText();  //"I am Alex";
        Reader inputString = new StringReader(aString);
        BufferedReader br = new BufferedReader(inputString);

        //BufferedReader br = new BufferedReader(new FileReader(TEXT));
        String line;
        Paragraph p;
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        boolean title = true;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line, title ? bold : normal);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            title = line.isEmpty();
            document.add(p);
        }
        document.close();


    }
    
    private String formatText()
    {
        String result="";

        int y=20;
        int yShift = 10;
        int headerRectHeight=15;
        int headerRectHeighta=40;

        ///////////////// Product names Get ///////////
        String  pn1a="Item1";
        String pn2a="Item2";
        String pn3a="Item3";
        String pn4a="Item4";
        ///////////////// Product names Get ///////////


        ///////////////// Product price Get ///////////
        int pp1a=Integer.valueOf(1);
        int pp2a=Integer.valueOf(2);
        int pp3a=Integer.valueOf(3);
        int pp4a=Integer.valueOf(4);
        int sum=pp1a+pp2a+pp3a+pp4a;
        ///////////////// Product price Get ///////////


        StringBuilder sb=new StringBuilder();
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append("      Restaurant Bill Receipt        ");
        sb.append('\n');
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append(" Food Name                 T.Price   ");
        sb.append('\n');
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append(" "+pn1a+"                  "+pp1a + " " );
        sb.append('\n');
        sb.append(" "+pn2a+"                  "+pp2a+"  ");
        sb.append('\n');
        sb.append(" "+pn3a+"                  "+pp3a+"  ");
        sb.append('\n');
        sb.append(" "+pn4a+"                  "+pp4a+"  ");
        sb.append('\n');
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append(" Total amount: "+sum+"               ");
        sb.append('\n');
        sb.append("-------------------------------------");
        sb.append('\n');
        sb.append("          Free Home Delivery         ");
        sb.append('\n');
        sb.append("             03111111111             ");
        sb.append('\n');
        sb.append("*************************************");
        sb.append('\n');
        sb.append("    THANKS TO VISIT OUR RESTUARANT   ");
        sb.append('\n');
        sb.append("*************************************");
        sb.append('\n');

        result =sb.toString();

        return result;
    }
}
