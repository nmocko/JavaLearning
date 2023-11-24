import java.util.Scanner;

public class OpponentMap extends BattleMap {

    public OpponentMap(String player) {
        super(player);
    }

    @Override
    public void shoot () {
        while (true) {
            Scanner scan = new Scanner(System.in);
            this.printBattleMap();

            if (this.getShips() == 0) {
                return;
            }

            System.out.println("Write coordination to shoot a ship:");
            String coord = scan.nextLine();

            if (! this.testCoordinates(coord)) {
                System.out.println("Incorrect coordinations");
                continue;
            }

            int y = coord.charAt(0) - 'A';
            int x = Integer.parseInt(coord.substring(1)) - 1;
            int w = 0;


            while (true) {

                try {
                    if (this.getMap()[x][y] != ' ') {
                        w = 1;
                        System.out.println("You have shot here already");
                        break;
                    }

                    System.out.println("You shot (1) or missed (2): ");
                    int answer = Integer.parseInt(scan.nextLine());
                    if (answer == 1) {
                        System.out.println("One more shoot for you :)");
                        this.setShips(this.getShips() - 1);
                        w = 1;
                        this.setMap(x, y, 'X');
                        break;
                    } else if (answer == 2) {
                        this.setMap(x, y, '0');
                        break;
                    } else {
                        System.out.println("Incorrect input");
                    }
                }
                catch (Exception e) {
                    System.out.println("You should enter integer");
                }
            }

            if (w == 0) {
                break;
            }

        }
    }

}
