package Service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdfwriter.compress.CompressParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.*;

public class Service{
    public  byte[] file_bites;
    public String file_end_path;
    public double file_start_size, file_end_size;
    public File Service(String pdf_path ) throws IOException {
        File pdf_file = new File(pdf_path + ".pdf");
        file_end_path = pdf_path + "_compresses.pdf";

        if(!pdf_file.exists()){ System.out.println("File not found"); return null; }

         try(PDDocument document = Loader.loadPDF(pdf_file)){
             for (PDPage page : document.getPages()) {
                 PDResources resources = page.getResources();
                    for (COSName name : resources.getXObjectNames() ){
                        PDXObject xObject = resources.getXObject(name);
                            if(xObject instanceof PDImageXObject){
                                PDImageXObject image = (PDImageXObject) xObject;

                                BufferedImage bufferedImage = image.getImage();

                                PDImageXObject  commpressed_image = (PDImageXObject) image;

                                resources.put(name, commpressed_image);
                            }
                        }
                    }
             file_start_size = pdf_file.length();
             file_end_size = file_end_path.length();
             document.save(file_end_path, CompressParameters.DEFAULT_COMPRESSION);
             Print_Info();
             }
         catch (RuntimeException ex) {
             throw new RuntimeException(ex);
         }

         catch (IOException e){
             System.out.println(e.getMessage());
         }
        return null;
    }
    public void  Print_Info (){
        System.out.println("File had " +file_start_size  + " bytes");
        System.out.println("File have " + file_end_size + " bytes");
    }
}