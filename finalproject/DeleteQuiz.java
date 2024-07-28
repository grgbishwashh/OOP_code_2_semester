package twentyfour.spring.oop.group2.finalproject.m23w7314;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteQuiz {

    private static final String QUIZ_NAMES_FILE = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\m23w7314\\files\\quiz_names.txt";
    private static final String QUIZ_DIRECTORY = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\m23w7314\\files\\";

    public static void deleteQuiz(String username) {
        Scanner scanner = new Scanner(System.in);
        List<String> userQuizzes = getUserQuizzes(username);

        if (userQuizzes.isEmpty()) {
            System.out.println("You have no quizzes to delete.");
            return;
        }

        System.out.println("Your quizzes:");
        for (int i = 0; i < userQuizzes.size(); i++) {
            System.out.println((i + 1) + ". " + userQuizzes.get(i));
        }

        System.out.print("Choose a quiz to delete (enter the number): ");
        int quizChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (quizChoice < 1 || quizChoice > userQuizzes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        String selectedQuiz = userQuizzes.get(quizChoice - 1);
        String quizFileName = QUIZ_DIRECTORY + selectedQuiz.replaceAll("\\s+", "_") + ".txt";

        System.out.print("Are you sure you want to delete the quiz \"" + selectedQuiz + "\"? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            File quizFile = new File(quizFileName);
            if (quizFile.delete()) {
                removeQuizFromList(selectedQuiz);
                System.out.println("Quiz " + selectedQuiz + " deleted successfully.");
            } else {
                System.out.println("Failed to delete the quiz.");
            }
        } else {
            System.out.println("Quiz deletion aborted.");
        }
    }

    private static List<String> getUserQuizzes(String username) {
        List<String> userQuizzes = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(QUIZ_NAMES_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[1].equals(username)) {
                    userQuizzes.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving your quizzes.");
            e.printStackTrace();
        }
        return userQuizzes;
    }

    private static void removeQuizFromList(String quizTitle) {
        List<String> quizList = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(QUIZ_NAMES_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.startsWith(quizTitle + ",")) {
                    quizList.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the quiz names file.");
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(QUIZ_NAMES_FILE)) {
            for (String quiz : quizList) {
                writer.write(quiz + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the quiz names file.");
            e.printStackTrace();
        }
    }
}
