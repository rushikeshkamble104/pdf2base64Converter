package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64ToPdfConverter {

    public static void convertBase64ToPdf(String base64String, String outputPath) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(decodedBytes);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Base64ToPdfConverter <base64_string_file_path> <output_pdf_file_path>");
            return;
        }

        File base64File = new File(args[0]);
        if (!base64File.exists()) {
            System.out.println("Base64 file not found: " + args[0]);
            return;
        }

        try {
            String base64String = new String(java.nio.file.Files.readAllBytes(base64File.toPath()));
            convertBase64ToPdf(base64String, args[1]);
            System.out.println("PDF file created at: " + args[1]);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
