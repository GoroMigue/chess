import java.util.ArrayList;

public class Player {
    // Static reference of each player
    public static Player white;
    public static Player black;
    // Static reference of player turn
    public static String turn = "White";
//
    private String team;
    private ArrayList<Piece> teamPieces;
//
    public Player(String team){
        this.team = team;
    }
//
    public void kill(Piece p) {
        p.setMove();
        p.getButton().setEnabled(false);
        p.getButton().setVisible(false);
        Piece.getPieces().remove(p);
        if (p.getTeam().equals("White")) {
            white.teamPieces.remove(p);
        }
        else{
            black.teamPieces.remove(p);
        }
    }
    public ArrayList<Piece> getPieces(){
        return teamPieces;
    }
//
    // Set each tam pieces
    public static void setPieces() {
        ArrayList<Piece> pieces = Piece.getPieces();
        black.teamPieces = new ArrayList<>(pieces.subList(0, pieces.size() / 2));
        white.teamPieces = new ArrayList<>(pieces.subList(pieces.size() / 2, pieces.size()));
    }
    // Getter for the current player turn
    public static Player getPlayer() {
        if (turn.equals("White")) {
            return white;
        }
        else {
            return black;
        }
    }
    // Method that changes turn, it calls to checkmate method to verify the situation
    public static void nextTurn() {
        Board.castlingR.setVisible(false);
        Board.castlingL.setVisible(false);
        if (turn.equals("White")) {
            turn = "Black";
        }
        else {
            turn = "White";
        }
        CheckMate.checkMate();

        if (ChessGame.cpuMode && turn.equals("Black")) {
            CPU.checkMove();
        }
    }
}