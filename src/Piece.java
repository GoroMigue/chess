import java.util.ArrayList;

public class Piece {
    private String name;
    private String team;
    private boolean pawn;
    private int x;
    private int y;

    public static Piece[] pieces;

//
    public Piece(String name, String team) {
        this.name = name;
        this.team = team;
        if (name.equals("Pawn")){
            this.pawn = true;
        }
    }
//
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }


    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getPawn(){
        return this.pawn;
    }
    public void setPawn(){
        this.pawn = false;
    }
    public void update(){

    }
//
    public void hide(){}



//
    public static Piece[] getPieces(){
        return pieces;
    }
    public static void piecesBuilder(){
        pieces = new Piece[32];
        //WHITE TEAM
        pieces[0] = new Piece("Rook","White");
        pieces[1] = new Piece("Knight","White");
        pieces[2] = new Piece("Bishop","White");
        pieces[3] = new Piece("Queen","White");
        pieces[4] = new Piece("King","White");
        pieces[5] = new Piece("Bishop","White");
        pieces[6] = new Piece("Knight","White");
        pieces[7] = new Piece("Rook","White");
        for (int i = 8; i <= 15; i++){
            pieces [i] = new Piece("Pawn","White");
        }

        //BLACK TEAM
        for (int i = 16; i <= 23; i++){
            pieces [i] = new Piece("Pawn","Black");
        }
        pieces[24] = new Piece("Rook","Black");
        pieces[25] = new Piece("Knight","Black");
        pieces[26] = new Piece("Bishop","Black");
        pieces[27] = new Piece("Queen","Black");
        pieces[28] = new Piece("King","Black");
        pieces[29] = new Piece("Bishop","Black");
        pieces[30] = new Piece("Knight","Black");
        pieces[31] = new Piece("Rook","Black");
    }

}

