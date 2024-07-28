package twentyfour.spring.oop.group2.finalproject.m23w7314;
import java.util.Scanner;

public class displayMenu {

    public static void displayMenu(String username, Scanner scanner) {
        boolean ans = false;
        while (!ans) {
            try {
                System.out.println("Choose the options:- ");
                System.out.println("1. Play Quiz");
                System.out.println("2. Create Quiz");
                System.out.println("3. Edit Quiz");
                System.out.println("4. Delete Quiz");
                System.out.println("5. Quit Quiz");

                int option1 = scanner.nextInt(); // Read user input for option

                switch (option1) {
                    case 1:
                        playQuiz.selectAndPlayQuiz();
                        break;
                    case 2:
                        createQuiz.quizCreations(username);
                        break;
                    case 3:
                        EditQuiz.editQuiz(username);
                        break;
                    case 4:
                        DeleteQuiz.deleteQuiz(username);
                        break;
                    case 5:
                        ans = true;
                        System.out.println("Exiting quiz playing section");
                        break;
                    default:
                        System.out.println("Enter correct option");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
