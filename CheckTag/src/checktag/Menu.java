/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checktag;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author CaoThu
 */
public class Menu {

    public static void menu() {
        int choose = 0;
        Scanner scanner = new Scanner(System.in);
        CheckTag check = new CheckTag();
        try {
            do {
                System.out.println("1.Closing Tag Checker for HTML5:");
                System.out.println("2.Quit");
                System.out.print("Choose: ");
                choose = scanner.nextInt();
                if (choose == 1) {
                    check.checkTag();
                    System.out.println("-----------------------------");
                }
            } while (choose != 2);
        } catch (InputMismatchException e) {
            System.out.println("This is not an integer, please try again");
        }
    }
}
