import java.util.Iterator;

public class Player {
    private String team;
    private Piece [] enemyPieces;
    private Piece [] teamPieces;

    public Player(String team){
        this.team = team;
        this.enemyPieces = new Piece[16];
        this.teamPieces = new Piece[16];
    }
    public void getPieces (Piece [] pieces){
        int i = 0;
        for (Piece p : pieces){
            if (p.getTeam().equals(this.team)){
                this.teamPieces[i] = p;
                i++;
            }
        }
    }
    public void capture(Piece piece){
        switch (piece.getName()) {
            case "Queen": piece.hide(); enemyPieces [5] = piece;
        }
    }
}
