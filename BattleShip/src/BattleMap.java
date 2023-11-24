public class BattleMap {

    private String player;
    private int ships;
    private char[][] Map = new char[10][10];

    private final char[] coordinates = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};

    public BattleMap(String player) {
        this.player = player;
        this.ships = 20;
        for (int i=0; i<this.Map.length; i++) {
            for (int j=0; j<this.Map.length; j++) {
                this.Map[i][j] = ' ';
            }
        }
    }

    public int getShips() {
        return ships;
    }

    public void setShips(int ships) {
        this.ships = ships;
    }

    public void shoot() {
        return;
    }

    public void printBattleMap() {

        System.out.print(" ");
        for (int i=0; i<this.Map.length; i++) {
            System.out.print("  " +  coordinates[i]);
        }
        System.out.print('\n');
        for (int i=0; i<this.Map.length; i++) {
            System.out.format("%-2d", i + 1);
            for (int j=0; j<this.Map.length; j++) {
                System.out.print(" " + this.Map[i][j] + " ");
            }
            System.out.print('\n');
        }
    }

    public boolean testCoordinates (String coord) {

        if(coord.length() < 2) {
            return false;
        }

        if (coord.charAt(0) < 'A' || coord.charAt(0) > coordinates[this.Map.length]) {
            return false;
        }
        int number;
        try{
            number = Integer.parseInt(coord.substring(1));
        }
        catch (NumberFormatException ex) {
            return false;
        }

        if (number < 1 || number > this.Map.length ) {
            return false;
        }

        return true;

    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public char[][] getMap() {
        return Map;
    }

    public void setMap(int x, int y, char value) {
        this.Map[x][y] = value;
    }
}


