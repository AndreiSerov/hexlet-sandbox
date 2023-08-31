package heaxlet.teach;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author andreiserov
 */
public class Demo {

}

class Game {
    private static final String rule = "Game rule is";

    void play() {

        final String[][] questionsAndAnswers = new String[Engine.ROUNDS][2];

        for (int i = 0; i < questionsAndAnswers.length; i++) {
            questionsAndAnswers[i][0] = getQuestion();
            questionsAndAnswers[i][1] = getAnswer();
        }


        Engine.play(rule, questionsAndAnswers);
    }

    private String getAnswer() {
        return null;
    }

    private String getQuestion() {
        return null;
    }
}

class Engine {

    static int ROUNDS = 10;

    static void play(String rule, String[][] quesAndAnswers) {
        System.out.println(rule);

        final Scanner sc = new Scanner(System.in);


        for (String[] quesAndAnswer : quesAndAnswers) {
            System.out.printf("Your Question is %s", quesAndAnswer[0]);

            final String userAnswer = sc.next();

            if (!userAnswer.equals(quesAndAnswer[1])) {
                System.out.println("You lost");
                return;
            }
        }

        System.out.println("Congratulation");
    }
}



class Klaia {



    public static void main(String[] args) {
        int kalia = 0;
        for (int i = 0; i < 10; i++) {
            kalia += i;
        }

        System.out.println(kalia);
    }
}