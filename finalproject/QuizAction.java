package twentyfour.spring.oop.group2.finalproject.m23w7314;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class QuizAction {
    protected static final String QUIZ_NAMES_FILE = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\m23w7314\\files\\quiz_names.txt";
    protected static final String QUIZ_DIRECTORY = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\m23w7314\\files\\";

    protected static List<String> getAvailableQuizzes() {
        List<String> quizzes = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(QUIZ_NAMES_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    quizzes.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the quizzes.");
            e.printStackTrace();
        }
        return quizzes;
    }

    protected static List<Question> loadQuestions(String fileName) {
        List<Question> questions = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String questionLine = fileScanner.nextLine();
                if (questionLine.startsWith("Q")) {
                    String questionText = questionLine.substring(questionLine.indexOf(":") + 2);
                    String[] options = new String[4];
                    for (int i = 0; i < 4; i++) {
                        options[i] = fileScanner.nextLine().substring(8);
                    }
                    int correctOption = Integer.parseInt(fileScanner.nextLine().split(": ")[1]);
                    questions.add(new Question(questionText, options, correctOption));
                    if (fileScanner.hasNextLine()) {
                        fileScanner.nextLine(); // Consume the empty line after each question block
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the quiz.");
            e.printStackTrace();
        }
        return questions;
    }

    protected static void saveQuestions(String fileName, List<Question> questions) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                writer.write("Q" + (i + 1) + ": " + question.getQuestion() + "\n");
                for (int j = 0; j < question.getOptions().length; j++) {
                    writer.write("Option " + (j + 1) + ": " + question.getOptions()[j] + "\n");
                }
                writer.write("Correct Option: " + question.getCorrectOption() + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the quiz.");
            e.printStackTrace();
        }
    }
}
