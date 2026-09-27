package Service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdfwriter.compress.CompressParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.*;

public class Service{
    public  byte[] file_bites;
    public File Service(String pdf_path) throws IOException {
         File pdf_file = new File(pdf_path);
         double file_size_start = pdf_file.length();
         if(pdf_file.exists()){
             System.out.println("File exists");
             return null;
         }
        System.out.println("Start compressing...");
         try(PDDocument document = Loader.loadPDF(pdf_file)){
             document.save(pdf_file, CompressParameters.DEFAULT_COMPRESSION);
             System.out.println("File had " + file_size_start + " bytes");
             System.out.println("File have " + pdf_file.length()  + " bytes");


         }
         catch (IOException e){
             System.out.println(e.getMessage());
         }
        return null;
    }
}