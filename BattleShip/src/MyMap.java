import java.util.Scanner;

public class MyMap extends BattleMap {

    public MyMap(String player) {
        super(player);
    }

    public void PutShips() {
        Scanner scan = new Scanner(System.in);
        int largeship = 4;
        for (int i = largeship; i > 0; i --) {
            for (int j = i; j <= largeship; j++) {

                int[][] shipcoord = new int[largeship][2];
                int[][] avilablecord = new int [largeship*4][2];
                int lenac = 0;
                this.printBattleMap();
                for (int k = 0; k < i; k++) {

                    while (true) {
                        System.out.println("You put your " + i + "-master\nEnter your " + (k + 1) + " coordinates e.g. 'A1'");
                        String coord = scan.nextLine();

                        if (!this.testCoordinates(coord)) {
                            System.out.println("Incorrect coordinates\nTry again\n" +
                                    "You put your " + i + "master\nEnter coordinates e.g. 'A1'");
                            coord = scan.nextLine();
                            continue;
                        }

                        int y = coord.charAt(0) - 'A';
                        int x = Integer.parseInt(coord.substring(1)) - 1;

                        if (this.getMap()[x][y]  != ' ') {
                            System.out.println("You cannot put here a ship ;(");
                            continue;
                        }

                        if (k > 0) {
                            int f = 0;
                            for (int z=0; z<lenac; z++) {
                                if (avilablecord[z][0] == x && avilablecord[z][1] == y) {
                                    f = 1;
                                    break;
                                }
                            }
                            if(f == 0) {
                                System.out.println("Your next master must be close to your previous masters");
                                continue;
                            }
                        }

                        shipcoord[k][0] = x;
                        shipcoord[k][1] = y;

                        if (x > 0) {
                            avilablecord[lenac][0] = x -1;
                            avilablecord[lenac][1] = y;
                            lenac ++;
                        }
                        if (x  < 9) {
                            avilablecord[lenac][0] = x + 1;
                            avilablecord[lenac][1] = y;
                            lenac ++;
                        }
                        if (y > 0) {
                            avilablecord[lenac][0] = x;
                            avilablecord[lenac][1] = y - 1;
                            lenac ++;
                        }
                        if (y  < 9) {
                            avilablecord[lenac][0] = x;
                            avilablecord[lenac][1] = y + 1;
                            lenac ++;
                        }

                        this.setMap(x, y, 'M');

                        this.printBattleMap();
                        break;
                    }
                }

                for (int k=0; k<i; k++) {
                    int x = shipcoord[k][0] - 1;
                    int y = shipcoord[k][1] - 1;
                    for (int p=0; p < 3; p++) {
                        for (int o=0; o<3; o++) {
                            int xc = x + p;
                            int yc = y + o;
                            if (10 > xc  && xc > -1 && yc < 10 && yc > -1 ) {
                                if (this.getMap()[xc][yc] != 'M') {
                                    this.setMap(xc, yc, '^');
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println();
        for (int k=0; k<this.getMap().length; k++) {
            for (int p=0; p<this.getMap().length; p++) {
                if (this.getMap()[k][p] == '^') {
                    this.setMap(k, p, ' ');
                }
            }
        }
        System.out.println("Your map:");
        this.printBattleMap();
    }

    @Override
    public void shoot () {
        while (true) {
            Scanner scan = new Scanner(System.in);

            if (this.getShips() == 0) {
                return;
            }

            System.out.println("Write coordination which gave you a opponent:");
            String coord = scan.nextLine();

            if (! this.testCoordinates(coord)) {
                System.out.println("Uncorrect coordinations");
                continue;
            }

            int y = coord.charAt(0) - 'A';
            int x = Integer.parseInt(coord.substring(1)) - 1;

            if (this.getMap()[x][y] == '0' || this.getMap()[x][y] == 'X') {
                System.out.println("You have already shot there");
                continue;
            }

            if (this.getMap()[x][y] == ' ') {
                System.out.println("You missed");
                this.setMap(x, y, '0');
                break;
            }

            if (this.getMap()[x][y] == 'M') {
                System.out.println("You shoot accurately");
                this.setMap(x, y, 'X');
                this.setShips(this.getShips() - 1);
            }
            this.printBattleMap();
        }
    }

}
