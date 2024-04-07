import java.util.Iterator;

public class Player {
    public static Player white;
    public static Player black;
    public static String turn = "White";
//
    private String team;
    private int kills = 0;
    private Piece [] enemyPieces;
//
    public Player(String team){
        this.team = team;
        this.enemyPieces = new Piece[16];
    }
//
    public void kill(Piece p){
        p.getButton().setEnabled(false);
        p.getButton().setVisible(false);
        enemyPieces[kills] = p;
        kills++;
    }
//
    public static Player getPlayer(){
        if (turn.equals("White")){
            return white;
        }
        else {
            return black;
        }
    }
}