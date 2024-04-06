/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task1;

/**
 *
 * @author iarin
 */
public class Laurette {
private String name;
private String birthDeath;
private String nations;
private String languages;
private String genres;
private String citation;

public Laurette(String name, String birthDeath, String nations, String languages, String genres, String citation){
this.name = name;
this.birthDeath = birthDeath;
this.nations = nations;
this.languages = languages;
this.genres = genres;
this.citation = citation;


}

 public String getName() {
        return this.name;
    }

    public String getBirthDeath() {
        return this.birthDeath;
    }

    public String getNations() {
        return this.nations;
    }

    public String getLanguages() {
        return this.languages;
    }

    public String getGenres() {
        return this.genres;
    }

    public String getCitation() {
        return this.citation;
    }


 public String toString() {
        String[] birthDeathSplit = birthDeath.split("-");
        String birthYear = birthDeathSplit[0].trim();
        String deathYear = birthDeathSplit.length > 1 ? birthDeathSplit[1].trim() : "----";
        String languagesFormatted = String.join(", ", this.languages.split(", "));
        String genresFormatted = String.join(", ", this.genres.split(", "));
        
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------------\n");
        sb.append("| Winner(s)       | Born | Died | Language(s) | Genre(s)         |\n");
        sb.append("-----------------------------------------------------------------\n");
        sb.append(String.format("| %-15s | %4s | %4s | %-11s | %-16s |\n", this.name, birthYear, deathYear, languagesFormatted, genresFormatted));
        
        // Handle citation length
        sb.append("-----------------------------------------------------------------\n");
        sb.append("| Citation:\n");
        String[] citationWords = this.citation.split("\\s+");
        StringBuilder citationLine = new StringBuilder("| ");
        for (String word : citationWords) {
            if (citationLine.length() + word.length() > 60) {
                sb.append(String.format("%-63s|\n", citationLine.toString()));
                citationLine = new StringBuilder("| ");
            }
            citationLine.append(word).append(" ");
        }
        if (citationLine.length() > 2) { // If there is any remaining text, add it.
            sb.append(String.format("%-63s|\n", citationLine.toString()));
        }
        sb.append("-----------------------------------------------------------------\n");
        
        return sb.toString();
    }
}



