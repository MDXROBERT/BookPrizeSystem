/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task1;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author iarin
 */
public class LiteraturePrize {
    private int year;
    private List<Laurette> winners;

    public LiteraturePrize(int year) {
        this.year = year;
        this.winners = new ArrayList<>();
    }

    public void addWinner(Laurette winner) {
        winners.add(winner);
    }

   
    public int getYear() {
        return year;
    }

    
    public List<Laurette> getWinners() {
        return winners;
    }

    
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("| %4d | ", this.year));
    if (winners.isEmpty()) {
        sb.append("NOT AWARDED\n");
    } else {
        
        winners.forEach(winner -> sb.append(winner.getName())
                                     .append(" (")
                                     .append(winner.getNations())
                                     .append("), "));
      
        sb.setLength(sb.length() - 2);
        sb.append("\n");
    }
    return sb.toString();
}
}