// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String yourName = scan.nextLine();
        MyMap YourMap = new MyMap(yourName);
        System.out.print("Enter your opponent name: ");
        String oppName = scan.nextLine();
        OpponentMap OppMap = new OpponentMap(oppName);


        BattleMap[] priority = new BattleMap[2];
        while (true) {
            try {
                int who;
                System.out.println("Who is starting?\n(1) you\n(2) your opponent");
                who = Integer.parseInt(scan.nextLine());
                if (who == 1) {
                    priority[1] = YourMap;
                    priority[0] = OppMap;
                    break;
                } else if (who == 2) {
                    priority[1] = OppMap;
                    priority[0] = YourMap;
                    break;
                } else {
                    System.out.println("You should enter 1 or 2");
                }
            }
            catch (Exception e) {
                System.out.println("You should enter integer");
            }
        }

        System.out.println("First put your ships");
        YourMap.PutShips();


        int i = 0;

        while (priority[0].getShips() > 0 && priority[1].getShips() > 0) {

            priority[i].shoot();
            i = (i + 1) % 2;
        }

        System.out.println("Congratulations " + priority[i].getPlayer() + "!\nYou won!");
        System.out.println("\nThe end!");

    }
}