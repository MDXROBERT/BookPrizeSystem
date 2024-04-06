package task1;

import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class ApplicationRunner {

    public static void main(String[] args) {
        String fileLocation = System.getProperty("user.dir");
        String dataPath = fileLocation + File.separator + "literature-prizes.txt";

        List<LiteraturePrize> literaturePrizes = Readerp.readLiteraturePrizes(dataPath);

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            displayMenu();
            System.out.print("Enter choice > ");

            while (true) {
                try {
                    choice = scanner.nextInt();
                    break; // Exit the loop if input is valid
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Consume the invalid input
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.print("Enter choice > ");
                }
            }

            switch (choice) {
                case 1:
                    listLiteraturePrizes(literaturePrizes, scanner);
                    break;
                case 2:
                    selectLiteraturePrize(literaturePrizes, scanner);
                    break;
                case 3:
                    searchLaureates(literaturePrizes, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("-----------------------");
        System.out.println("Literature prize menu");
        System.out.println("-----------------------");
        System.out.println("List .................1");
        System.out.println("Select ...............2");
        System.out.println("Search ...............3");
        System.out.println("Exit .................0");
        System.out.println("-----------------------");
    }

    private static void listLiteraturePrizes(List<LiteraturePrize> literaturePrizes, Scanner scanner) {
        System.out.print("Enter start year > ");
        int startYear = scanner.nextInt();
        System.out.print("Enter end year > ");
        int endYear = scanner.nextInt();

        if (startYear < 1901 || endYear > 2022 || startYear > endYear) {
            System.out.println("Range exceeded maximum or is invalid. Please enter a range between 1901 and 2022.");
            return;
        }

        
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("| Year | Prize winners (and associated nations)                              |");
        System.out.println("------------------------------------------------------------------------------");

        literaturePrizes.stream()
    .filter(prize -> prize.getYear() >= startYear && prize.getYear() <= endYear)
    .sorted(Comparator.comparing(LiteraturePrize::getYear))
    .forEach(prize -> System.out.println(prize.toString()));

       
        System.out.println("------------------------------------------------------------------------------");
    }

   private static void selectLiteraturePrize(List<LiteraturePrize> literaturePrizes, Scanner scanner) {
    System.out.print("Enter year of prize > ");
    int year = scanner.nextInt();

    LiteraturePrize selectedPrize = literaturePrizes.stream()
            .filter(prize -> prize.getYear() == year)
            .findFirst()
            .orElse(null);

    if (selectedPrize == null) {
        System.out.println("No prize was found for the year " + year + ". Please try again.");
    } else {
        
        selectedPrize.getWinners().forEach(laurette -> System.out.println(laurette.toString()));
    }
}
private static void searchLaureates(List<LiteraturePrize> literaturePrizes, Scanner scanner) {
    System.out.print("Enter search term for writing genre > ");
    String searchTerm = scanner.next().toLowerCase();

    // Stores the results to sort before displaying
    List<String[]> formattedResults = new ArrayList<>();

    for (LiteraturePrize prize : literaturePrizes) {
        for (Laurette laurette : prize.getWinners()) {
            String[] genres = laurette.getGenres().split(", ");
            List<String> formattedGenres = new ArrayList<>();
            for (String genre : genres) {
                if (genre.toLowerCase().contains(searchTerm)) {
                    // Replace the search term with uppercase within the genre
                    genre = genre.replaceAll("(?i)" + searchTerm, searchTerm.toUpperCase());
                }
                formattedGenres.add(genre);
            }
            // Check if any genre was matched and highlighted
            if (formattedGenres.toString().toLowerCase().contains(searchTerm)) {
                formattedResults.add(new String[]{
                        laurette.getName(),
                        String.join(", ", formattedGenres),
                        Integer.toString(prize.getYear())
                });
            }
        }
    }

    // Sort results by laureate name
    formattedResults.sort((a, b) -> a[0].compareToIgnoreCase(b[0]));

    // Print the results
    System.out.println("-------------------------------------------------------------");
    System.out.println("| Name            | Genres                                   | Year |");
    System.out.println("-------------------------------------------------------------");
    for (String[] result : formattedResults) {
        System.out.printf("| %-15s | %-40s | %-4s |\n", result[0], result[1], result[2]);
    }
    System.out.println("-------------------------------------------------------------");
}

}

