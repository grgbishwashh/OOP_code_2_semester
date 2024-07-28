package twentyfour.spring.oop.group2.finalproject.m23w7314;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EditQuiz extends QuizAction {

    public static void editQuiz(String username) {
        Scanner scanner = new Scanner(System.in);
        List<String> userQuizzes = getUserQuizzes(username);

        if (userQuizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Your quizzes:");
        for (int i = 0; i < userQuizzes.size(); i++) {
            System.out.println((i + 1) + ". " + userQuizzes.get(i));
        }

        int quizChoice = -1;
        while (true) {
            System.out.print("Choose a quiz to edit (enter the number): ");
            try {
                quizChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (quizChoice >= 1 && quizChoice <= userQuizzes.size()) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + userQuizzes.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        String selectedQuiz = userQuizzes.get(quizChoice - 1);
        String quizFileName = QUIZ_DIRECTORY + selectedQuiz.replaceAll("\\s+", "_") + ".txt";

        List<Question> questions = loadQuestions(quizFileName);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions available in the quiz.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("Quiz: " + selectedQuiz);
            for (int i = 0; i < questions.size(); i++) {
                System.out.println("Q" + (i + 1) + ": " + questions.get(i).getQuestion());
                for (int j = 0; j < questions.get(i).getOptions().length; j++) {
                    System.out.println("Option " + (j + 1) + ": " + questions.get(i).getOptions()[j]);
                }
                System.out.println("Correct Option: " + questions.get(i).getCorrectOption());
            }

            System.out.println("Choose an option:");
            System.out.println("1. Edit a question");
            System.out.println("2. Delete a question");
            System.out.println("3. Add a question");
            System.out.println("4. Exit editing");

            int choice = -1;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            switch (choice) {
                case 1:
                    int questionNumber = -1;
                    while (true) {
                        System.out.print("Enter question number to edit: ");
                        try {
                            questionNumber = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (questionNumber >= 1 && questionNumber <= questions.size()) {
                                break;
                            } else {
                                System.out.println("Invalid question number. Please enter a number between 1 and " + questions.size());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }

                    System.out.print("Are you sure you want to edit this question? (yes/no): ");
                    String confirmEdit = scanner.nextLine().trim().toLowerCase();
                    if (confirmEdit.equals("yes")) {
                        editQuestion(questions, questionNumber - 1);
                        saveQuestions(quizFileName, questions);
                    }
                    break;
                case 2:
                    int deleteNumber = -1;
                    while (true) {
                        System.out.print("Enter question number to delete: ");
                        try {
                            deleteNumber = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (deleteNumber >= 1 && deleteNumber <= questions.size()) {
                                break;
                            } else {
                                System.out.println("Invalid question number. Please enter a number between 1 and " + questions.size());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    }

                    System.out.print("Are you sure you want to delete this question? (yes/no): ");
                    String confirmDelete = scanner.nextLine().trim().toLowerCase();
                    if (confirmDelete.equals("yes")) {
                        questions.remove(deleteNumber - 1);
                        saveQuestions(quizFileName, questions);
                    }
                    break;
                case 3:
                    if (questions.size() >= 5) {
                        System.out.println("The quiz already has the maximum number of questions (5).");
                    } else {
                        System.out.print("Are you sure you want to add a new question? (yes/no): ");
                        String confirmAdd = scanner.nextLine().trim().toLowerCase();
                        if (confirmAdd.equals("yes")) {
                            addQuestion(questions);
                            saveQuestions(quizFileName, questions);
                        }
                    }
                    break;
                case 4:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static List<String> getUserQuizzes(String username) {
        List<String> quizzes = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(QUIZ_NAMES_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[1].equals(username)) {
                    quizzes.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the quizzes.");
            e.printStackTrace();
        }
        return quizzes;
    }

    private static void editQuestion(List<Question> questions, int index) {
        Scanner scanner = new Scanner(System.in);
        Question question = questions.get(index);

        System.out.println("Editing Question " + (index + 1));
        System.out.println("Current question: " + question.getQuestion());
        System.out.print("Enter new question text (or press Enter to keep current): ");
        String newQuestionText = scanner.nextLine();
        if (!newQuestionText.isEmpty()) {
            question.setQuestion(newQuestionText);
        }

        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println("Current option " + (i + 1) + ": " + question.getOptions()[i]);
            System.out.print("Enter new option " + (i + 1) + " text (or press Enter to keep current): ");
            String newOptionText = scanner.nextLine();
            if (!newOptionText.isEmpty()) {
                question.setOption(i, newOptionText);
            }
        }

        System.out.println("Current correct option: " + question.getCorrectOption());
        while (true) {
            System.out.print("Enter new correct option number (1-4) (or press Enter to keep current): ");
            String newCorrectOption = scanner.nextLine();
            if (newCorrectOption.isEmpty()) {
                break;
            }
            try {
                int newCorrectOptionInt = Integer.parseInt(newCorrectOption);
                if (newCorrectOptionInt >= 1 && newCorrectOptionInt <= 4) {
                    question.setCorrectOption(newCorrectOptionInt);
                    break;
                } else {
                    System.out.println("Invalid option number. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static void addQuestion(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question text: ");
        String questionText = scanner.nextLine();

        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }

        int correctOption = -1;
        while (true) {
            System.out.print("Enter the number of the correct option (1-4): ");
            try {
                correctOption = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (correctOption >= 1 && correctOption <= 4) {
                    break;
                } else {
                    System.out.println("Invalid option number. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        questions.add(new Question(questionText, options, correctOption));
        System.out.println("Question added successfully.");
    }
}
