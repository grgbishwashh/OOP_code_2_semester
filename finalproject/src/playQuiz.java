package twentyfour.spring.oop.group2.finalproject.src;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class playQuiz {

    private static final String QUIZ_NAMES_FILE = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\resources\\quiz_names.txt";
    private static final String QUIZ_DIRECTORY = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\resources\\";

    public static void selectAndPlayQuiz() {
        Scanner scanner = new Scanner(System.in);
        List<String> availableQuizzes = getAvailableQuizzes();

        if (availableQuizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Available quizzes:");
        for (int i = 0; i < availableQuizzes.size(); i++) {
            System.out.println((i + 1) + ". " + availableQuizzes.get(i));
        }

        System.out.print("Choose a quiz to play (enter the number): ");
        int quizChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (quizChoice < 1 || quizChoice > availableQuizzes.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        String selectedQuiz = availableQuizzes.get(quizChoice - 1);
        System.out.print("Do you want to start the quiz? (yes to start, any other key to abort): ");
        String startQuiz = scanner.nextLine().trim();

        if (startQuiz.equalsIgnoreCase("yes")) {
            String quizFileName = QUIZ_DIRECTORY + selectedQuiz.replaceAll("\\s+", "_") + ".txt";
            playQuizNow(quizFileName);
        } else {
            System.out.println("Quiz aborted. Returning to previous menu.");
        }
    }

    private static List<String> getAvailableQuizzes() {
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

    public static void playQuizNow(String fileName) {
        List<Question> questions = loadQuestions(fileName);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available in the quiz.");
            return;
        }

        Collections.shuffle(questions);
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.getQuestion());
            for (int j = 0; j < question.getOptions().length; j++) {
                System.out.println((j + 1) + ". " + question.getOptions()[j]);
            }
            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userAnswer == question.getCorrectOption()) {
                score++;
            }
        }

        System.out.println("Quiz finished! Your total score is: " + score + "/" + questions.size());
    }

    private static List<Question> loadQuestions(String fileName) {
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

    private static class Question {
        private final String question;
        private final String[] options;
        private final int correctOption;

        public Question(String question, String[] options, int correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectOption() {
            return correctOption;
        }
    }
}
