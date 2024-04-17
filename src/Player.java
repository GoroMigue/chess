import java.util.Arrays;

public class Player {
    // Static reference of each player
    public static Player white;
    public static Player black;
    // Static reference of player turn
    public static String turn = "White";
//
    private String team;
    private int kills = 0;
    private Piece [] enemyPieces;
    private Piece [] teamPieces;
//
    public Player(String team){
        this.team = team;
        this.enemyPieces = new Piece[16];
        this.teamPieces = new Piece[16];
    }
//
    public void kill(Piece p){
        p.getButton().setEnabled(false);
        p.getButton().setVisible(false);
        enemyPieces[kills] = p;
        kills++;
    }
    public Piece[] getPieces(){
        return teamPieces;
    }
//
    // Set each tam pieces
    public static void setPieces(){
        black.teamPieces = Arrays.copyOfRange(Piece.getPieces(), 0, 16);
        white.teamPieces = Arrays.copyOfRange(Piece.getPieces(), 16, 32);
    }
    // Getter for the current player turn
    public static Player getPlayer(){
        if (turn.equals("White")){
            return white;
        }
        else {
            return black;
        }
    }
    // Method that changes turn, it calls to checkmate method to verify the situation
    public static void nextTurn(){
        Board.castlingR.setVisible(false);
        Board.castlingL.setVisible(false);
        if (turn.equals("White")){
            turn = "Black";
        }
        else {
            turn = "White";
        }
        CheckMate.checkMate();
    }
}