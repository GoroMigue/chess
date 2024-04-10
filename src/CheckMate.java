public class CheckMate {
    // When the team king is in check
    public static boolean teamCheck = false;
    // When the enemy king is in check
    public static boolean enemyCheck = false;
    // Variable to activate checking condition
    public static boolean checking = false;
    // Variable to teleport piece while checking
    public static boolean checkingMate = false;
    // Variable to avoid infinite recursion while checkingMate
    public static boolean checkingMateChecking = false;
    // Variable to check if there are no possible movements left
    public static boolean checkMove = false;

    // Check after move or trying to move, activating teamCheck or enemyCheck when it's needed
    public static void check() {
        checking = true;
        Piece[] pieces = Piece.getPieces();
        for (Piece p : pieces) {
            Move.move(p);
        }
        Square.deactivateAll();
        checking = false;
    }

    // Check when turn swapping and enemyTeam is activated
    public static void checkMate() {
        checkingMate = true;
        teamCheck = false;
        enemyCheck = false;
        checkMove = false;
        Piece[] pieces = Player.getPlayer().getPieces().clone();
        for (Piece p : pieces) {
            Move.move(p);
            // If there's a possible move, it breaks
            if (checkMove) {
                break;
            }
        }
        Square.deactivateAll();
        // If there's no possible move, it ends
        if (!checkMove) {
            deadEnd();
        }
        enemyCheck = false;
        teamCheck = false;
        checkingMate = false;
    }

    // Activate when there is no possible movement left
    public static void deadEnd() {
        System.out.println("GAME OVER");
    }
}