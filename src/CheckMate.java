import java.util.ArrayList;

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

    // It checks every enemy piece movement to verify if team king is in check
    public static void check() {
        checking = true;
        ArrayList<Piece> pieces;
        if (CPU.cpu) {
            pieces = Piece.getPieces();
        }
        else if (Player.getPlayer().equals(Player.white)) {
            pieces = Player.black.getPieces();
        }
        else {
            pieces = Player.white.getPieces();
        }
        for (Piece p : pieces) {
            Move.move(p);
            if (teamCheck){break;}
        }
        Square.deactivateAll();
        checking = false;
    }

    // It verifies every possible subsequent situation after every movement of every piece
    // If there's no possible movement left, "checkMove" stays false and game over
    // If it's possible to move, "checkMove" turns to true and game continues
    public static void checkMate() {
        checkingMate = true;
        teamCheck = false;
        checkMove = false;
        ArrayList<Piece> pieces = Player.getPlayer().getPieces();
        for (Piece p : pieces) {
            Move.move(p);
            if (checkMove) {
                break;
            }
        }
        Square.deactivateAll();
        if (Player.getPlayer().getPieces().size() == 1) {
            checkMove = false;
        }
        if (!checkMove) {
            ChessGame.cpuMode = false;
            String team = "";
            if (Player.getPlayer().equals(Player.white)) {
                team = "BLACK TEAM";
            }
            else {
                team = "WHITE TEAM";
            }
            ChessGame.gameOver(team);
        }
        teamCheck = false;
        checkingMate = false;
    }
}