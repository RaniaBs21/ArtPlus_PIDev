package Jeu;



import javax.swing.JOptionPane;

public class TriviaQuiz {



    private static final String[] QUESTIONS = {
        "Quelle est la capitale de la France ?",
        "Quelle est la plus grande planète du système solaire ?",
        "Quel est le plus grand océan du monde ?",
        "Qui a écrit le roman 'Les Misérables' ?",
        "En quelle année a eu lieu la Révolution française ?"
    };

    private static final String[] ANSWERS = {
        "Paris",
        "Jupiter",
        "L'océan Pacifique",
        "Victor Hugo",
        "1789"
    };

    private static final int NUM_QUESTIONS = QUESTIONS.length;

    public static void main(String[] args) {
        int score = 0;
        for (int i = 0; i < NUM_QUESTIONS; i++) {
            int questionIndex = i;
            String question = QUESTIONS[questionIndex];
            String answer = ANSWERS[questionIndex];

            String input = JOptionPane.showInputDialog(null, (i + 1) + ". " + question);
            if (input != null && input.equalsIgnoreCase(answer)) {
                JOptionPane.showMessageDialog(null, "Correct!");
                score++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is " + answer);
            }
        }

        JOptionPane.showMessageDialog(null, "Your final score is " + score + " out of " + NUM_QUESTIONS);
    }
}
