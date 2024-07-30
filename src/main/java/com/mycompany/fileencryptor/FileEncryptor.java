package com.mycompany.fileencryptor;
import java.io.*;
import java.util.*;

public class FileEncryptor {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter input file name (example, 'in.txt'): ");
        String inputFileName = in.nextLine();
        System.out.print("Enter output file name (example, 'out.txt'): ");
        String outputFileName = in.nextLine();
        System.out.print("Enter key: ");
        int key = 0;
        try {
            key = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Only numbers are accepted for the key.");
            return; 
        }
        
       
        boolean success = encryptFile(inputFileName, outputFileName, key);
        if (success) {
            System.out.println("File encryption completed successfully.");
        } else {
            System.out.println("File encryption failed.");
        }
    }
   
    public static boolean encryptFile(String inputFileName, String outputFileName, int key) {
        File inFile = new File(inputFileName);
        if (!inFile.exists()) {
            System.out.println("Input file does not exist.");
            return false;
        }
       
        try (Scanner fileScanner = new Scanner(inFile);
             PrintWriter fileWriter = new PrintWriter(outputFileName)) {
            while (fileScanner.hasNextLine()) {
                String text = fileScanner.nextLine();
                String encryptedText = encryptString(text, key);
                fileWriter.println(encryptedText);
            }
        } catch (IOException e) {
            System.out.println("Error during file processing: " + e.getMessage());
            return false;
        }
        return true;
    }
   
    public static String encryptString(String inputString, int aKey) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            char encryptedChar = (char) (inputString.charAt(i) + aKey);
            outputString.append(encryptedChar);
        }
        return outputString.toString();
    }
}
