package twentyfour.spring.oop.group2.finalproject.m23w7314;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class playQuiz extends QuizAction {

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
}
