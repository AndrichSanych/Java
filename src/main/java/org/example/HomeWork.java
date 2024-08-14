package org.example;
import java.util.Random;
import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int correctAnswers = 0;
        int incorrectAnswers = 5;

        System.out.println("Відповідайте на запитання, вводячи результат множення.");

        for (int i = 0; i < incorrectAnswers; i++) {
            int number1 = random.nextInt(9) + 1;
            int number2 = random.nextInt(9) + 1;

            System.out.print("Скільки буде " + number1 + " * " + number2 + "?  -> ");
            int answer = scanner.nextInt();

            if (answer == number1 * number2) {
                System.out.println("Правильно!");
                correctAnswers++;
            } else {
                System.out.println("Неправильно. Правильна відповідь: " + (number1 * number2));
            }
        }

        System.out.println("Правильних відповідей: " + correctAnswers );
    }



}
