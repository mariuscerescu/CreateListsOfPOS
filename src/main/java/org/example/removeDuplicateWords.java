package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class removeDuplicateWords {
    public static void main(String[] args) {
        File inputFile = new File("POS/VERB.txt");
        File outputFile = new File("POS/VERB.txt  ");

        Set<String> words = new HashSet<>();

        try {
            // Create reader
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Read file and add to set
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }

            // Close reader
            reader.close();

            // Create writer
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Write words to file
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }

            // Close writer
            writer.close();

            System.out.println("Processing complete. Check output.txt for unique words.");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + inputFile);
        } catch (IOException e) {
            System.out.println("An error occurred while processing the files.");
            e.printStackTrace();
        }
    }
}
