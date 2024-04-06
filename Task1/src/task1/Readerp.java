/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Readerp {

    public static List<LiteraturePrize> readLiteraturePrizes(String filePath) {
        List<LiteraturePrize> literaturePrizes = new ArrayList<>();
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            LiteraturePrize currentPrize = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.startsWith("-----")) {
                    continue; // Skip delimiter lines
                }
                
                // If line is numeric, it's a year
                if (line.matches("\\d+")) {
                    int year = Integer.parseInt(line);
                    currentPrize = new LiteraturePrize(year);
                    literaturePrizes.add(currentPrize);
                } else if (currentPrize != null && !line.equals("Not awarded")) {
                    // Parse laureate details
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        String[] nameAndBirth = parts[0].split(" \\(");
                        String name = nameAndBirth[0].trim();
                        String birthDeath = nameAndBirth[1].replace(")", "");
                        String nations = parts[1].trim();
                        String languages = parts[2].trim();
                        
                        // next lines are citation and genres
                        String citation = scanner.hasNextLine() ? scanner.nextLine().trim() : "No citation";
                        String genres = scanner.hasNextLine() ? scanner.nextLine().trim() : "No genres";
                        
                        Laurette laurette = new Laurette(name, birthDeath, nations, languages, genres, citation);
                        currentPrize.addWinner(laurette);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return literaturePrizes;
    }
}