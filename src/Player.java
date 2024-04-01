public class Player {
    private int team;
    Piece [] pieces;


    public Player(int team){
        this.team = team;
        pieces = new Piece[16];
    }

    public void capture(Piece piece){
        switch (piece.getName()) {
            case "Queen": piece.hide(); pieces [5] = piece;
        }
    }
}
