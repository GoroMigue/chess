import java.util.Arrays;
import java.util.Iterator;

public class Player {
    public static Player white;
    public static Player black;
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
    public static void setPieces(){
            white.teamPieces = Arrays.copyOfRange(Piece.getPieces(), 0, 15);
            black.teamPieces = Arrays.copyOfRange(Piece.getPieces(), 16, 31);
    }
    public static Player getPlayer(){
        if (turn.equals("White")){
            return white;
        }
        else {
            return black;
        }
    }
    public static void nextTurn(){
        Board.castlingR.setVisible(false);
        Board.castlingL.setVisible(false);
        if (CheckMate.enemyCheck){
            CheckMate.enemyCheck = false;
            CheckMate.teamCheck = false;
            CheckMate.checkMate();
        }
        if (turn.equals("White")){
            turn = "Black";
        }
        else {
            turn = "White";
        }
    }
}