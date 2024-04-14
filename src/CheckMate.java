public class CheckMate {
    // When the team king is in check
    public static boolean teamCheck;
    // Variable to activate checking condition
    public static boolean checking;
    // Variable to teleport piece while checking
    public static boolean checkingMate;
    // Variable to avoid infinite recursion while checkingMate
    public static boolean checkingMateChecking;
    // Variable to check if there are no possible movements left
    public static boolean checkMove;

    public static void check() {
        checking = true;
        Piece[] pieces;
        if (Player.getPlayer().equals(Player.white)){
            pieces = Player.black.getPieces();
        }
        else {
            pieces = Player.white.getPieces();
        }
        for (Piece p : pieces) {
            Move.move(p);
            if (teamCheck){;break;}
        }
        Square.deactivateAll();
        checking = false;
    }

    public static void checkMate() {
        checkingMate = true;
        teamCheck = false;
        checkMove = false;
        Piece[] pieces = Player.getPlayer().getPieces();
        for (Piece p : pieces) {
            Move.move(p);
            if (checkMove) {
                break;
            }
        }
        Square.deactivateAll();
        if (!checkMove) {
            deadEnd();
        }
        teamCheck = false;
        checkingMate = false;
    }

    public static void deadEnd() {
        System.out.println("GAME OVER");
    }
}