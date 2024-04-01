import java.util.HashMap;

public class Board {
    public static Player white;
    public static Player black;
    public static Piece[] pieces = new Piece[32];
    public static HashMap <Square, Piece> squares = new HashMap<Square, Piece>();
    public static boolean start = true;

    public static void main(String[] args) {
        if(start) {
            white = new Player(1);
            black = new Player(2);
            piecesBuilder();
            squareBuilder();
            start = false;
        }
    }


    public static void piecesBuilder(){
        //WHITE TEAM
        pieces[0] = new Piece("Rook",1);
        pieces[1] = new Piece("Knight",1);
        pieces[2] = new Piece("Bishop",1);
        pieces[3] = new Piece("Queen",1);
        pieces[4] = new Piece("King",1);
        pieces[5] = new Piece("Bishop",1);
        pieces[6] = new Piece("Knight",1);
        pieces[7] = new Piece("Rook",1);
        for (int i = 8; i <= 15; i++){
            pieces [i] = new Piece("Pawn",1);
        }

        //BLACK TEAM
        for (int i = 16; i <= 23; i++){
            pieces [i] = new Piece("Pawn",2);
        }
        pieces[24] = new Piece("Rook",2);
        pieces[25] = new Piece("Knight",2);
        pieces[26] = new Piece("Bishop",2);
        pieces[27] = new Piece("Queen",2);
        pieces[28] = new Piece("King",2);
        pieces[29] = new Piece("Bishop",2);
        pieces[30] = new Piece("Knight",2);
        pieces[31] = new Piece("Rook",2);
    }

    public static void squareBuilder(){
        char file = 'A';
        int rank = 1;
        int x = 0;
        int y = 0;
        int totalSquares = 0;
        int totalPieces = 0;
        for (;rank <= 8; rank++){
            for (;file <= 'H'; file++){
                if (totalSquares >= 16 && totalPieces <= 47){
                    squares.put(new Square(file, rank, x, y), null) ;
                }
                else{
                    squares.put(new Square(file, rank, x, y), pieces[totalPieces]);
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
