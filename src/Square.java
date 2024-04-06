import java.util.ArrayList;
import java.util.HashMap;

public class Square {
    private char file;
    private int rank;
    private int x;
    private int y;
    private Piece piece;

    public static ArrayList<ArrayList<Square>> squares = new ArrayList<>();
    public static Piece neutralPiece = new Piece("Neutral","Neutral");
    public static Square neutralSquare = new Square('N',0,0,0,neutralPiece);
//
    public Square(char file, int rank, int x, int y, Piece piece) {
        this.file = file;
        this.rank = rank;
        this.x = x;
        this.y = y;
        this.piece = piece;
        squares.get(rank).add(this);
    }

    //NO USED
    public int getX(){
        return this.x;
    }
    //NO USED
    public int getY(){
        return this.y;
    }
    public int getRank(){
        return this.rank;
    }
    public char getFile(){
        return this.file;
    }
    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void activateRed(){

    }
    public void activateYellow(){

    }
    public static void deactivateAll(){

    }
    public void teleportPiece(Piece piece){
            getSquare(piece).setPiece(neutralPiece);
            this.setPiece(piece);
            piece.setXY(this.x, this.y);
            piece.update();

    }
//
    public static ArrayList<ArrayList<Square>> getSquares(){
        return squares;
    }
    public static Square getSquare(Piece piece) {
        for (ArrayList<Square> r : squares ) {
            for (Square s : r ) {
                if (s.getPiece().equals(piece)) {
                    return s;
                }
            }
        }
        return neutralSquare;
    }

    public static Square getSquare(int rank,char file){
        for (ArrayList<Square> r : squares ) {
            for (Square s : r ) {
                if (s.getRank() == rank && s.getFile() == file) {
                    return s;
                }
            }
        }
        return neutralSquare;
    }
    public static void squareBuilder(){
        char file = 'A';
        int rank = 1;
        int x = 0;
        int y = 0;
        int totalSquares = 0;
        int totalPieces = 0;



        for (;rank <= 8; rank++){
            squares.add(new ArrayList<>());
            for (;file <= 'H'; file++){
                if (totalSquares >= 16 && totalPieces <= 47){
                    squares.get(rank).add(new Square(file, rank, x, y,neutralPiece)) ;
                }
                else{
                    squares.get(rank).add(new Square(file, rank, x, y, Piece.getPieces()[totalPieces]));
                    totalPieces++;
                }
                totalSquares++;
                x += 100; //
            }
            file = 'A';
            y += 100; //
            x = 0; //
        }
    }


}
