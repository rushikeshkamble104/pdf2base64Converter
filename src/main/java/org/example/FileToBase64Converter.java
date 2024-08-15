package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class FileToBase64Converter  {

    public static String convertFileToBase64(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileBytes = new byte[(int) file.length()];
            fileInputStream.read(fileBytes);
            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }

    public static void saveBase64ToFile(String base64String, String outputPath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputPath)) {
            fileWriter.write(base64String);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileToBase64Converter <file_path>");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File not found: " + args[0]);
            return;
        }

        try {
            String base64String = convertFileToBase64(file);
            System.out.println("Base64 Representation:");
            System.out.println(base64String);

            String outputFileName = file.getName() + "_base64.txt";
            saveBase64ToFile(base64String, outputFileName);
            System.out.println("Base64 string saved to: " + outputFileName);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
