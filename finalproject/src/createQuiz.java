package twentyfour.spring.oop.group2.finalproject.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class createQuiz {

    private static final String QUIZ_NAMES_FILE = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\resources\\quiz_names.txt";
    private static final int MAX_QUESTIONS = 5;

    public static void quizCreations(String username) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter quiz title:");
        String title = scanner.nextLine();

        if (!title.isEmpty() && !username.isEmpty()) {
            String fileName = title.replaceAll("\\s+", "_") + ".txt";
            fileName = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\resources\\" + fileName;

            if (isQuizNameValid(title)) {
                createQuizFile(fileName, title);
                storeQuizName(title, username);
                enterQuestions(fileName);
            } else {
                System.out.println("Quiz with the given title already exists.");
            }
        } else {
            System.out.println("Invalid quiz title or name.");
        }
    }

    private static boolean isQuizNameValid(String quizTitle) {
        try (Scanner fileScanner = new Scanner(new File(QUIZ_NAMES_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(quizTitle)) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the quiz.");
            e.printStackTrace();
        }
        return true;
    }

    private static void createQuizFile(String fileName, String title) {
        File quizFile = new File(fileName);
        try {
            if (quizFile.createNewFile()) {
                System.out.println("Quiz created: " + title);
            } else {
                System.out.println("Quiz already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the quiz.");
            e.printStackTrace();
        }
    }

    private static void storeQuizName(String quizTitle, String authorName) {
        try (FileWriter writer = new FileWriter(QUIZ_NAMES_FILE, true)) {
            writer.write(quizTitle + "," + authorName + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the quiz names file.");
            e.printStackTrace();
        }
    }

    private static void enterQuestions(String fileName) {
        Scanner scanner = new Scanner(System.in);
        int questionCount = 0;

        while (questionCount < MAX_QUESTIONS) {
            System.out.println("Enter question (or type 'exit' to finish):");
            String question = scanner.nextLine();
            if (question.equalsIgnoreCase("exit")) {
                break;
            }

            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("Enter option " + (i + 1) + ":");
                options[i] = scanner.nextLine();
            }

            int correctOption = 0;
            while (true) {
                System.out.println("Enter the number of the correct option (1-4):");
                try {
                    correctOption = Integer.parseInt(scanner.nextLine());
                    if (correctOption >= 1 && correctOption <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid option number. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 4.");
                }
            }

            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write("Q" + (questionCount + 1) + ": " + question + "\n");
                for (int i = 0; i < 4; i++) {
                    writer.write("Option " + (i + 1) + ": " + options[i] + "\n");
                }
                writer.write("Correct Option: " + correctOption + "\n");
                writer.write("\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the quiz file.");
                e.printStackTrace();
            }

            questionCount++;
            if (questionCount == MAX_QUESTIONS) {
                System.out.println("Maximum number of questions reached.");
            }
        }
    }
}
