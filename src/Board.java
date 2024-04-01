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
        pieces[0] = new Piece("Rook",1,Move.find("Rook"));
        pieces[1] = new Piece("Knight",1,Move.find("Knight"));
        pieces[2] = new Piece("Bishop",1,Move.find("Bishop"));
        pieces[3] = new Piece("Queen",1,Move.find("Queen"));
        pieces[4] = new Piece("King",1,Move.find("King"));
        pieces[5] = new Piece("Bishop",1,Move.find("Bishop"));
        pieces[6] = new Piece("Knight",1,Move.find("Knight"));
        pieces[7] = new Piece("Rook",1,Move.find("Rook"));
        for (int i = 8; i <= 15; i++){
            pieces [i] = new Piece("Pawn",1,Move.find("wPawn"));
        }

        for (int i = 48; i <= 55; i++){
            pieces [i] = new Piece("Pawn",2,Move.find("bPawn"));
        }
        pieces[56] = new Piece("Rook",2,Move.find("Rook"));
        pieces[57] = new Piece("Knight",2,Move.find("Knight"));
        pieces[58] = new Piece("Bishop",2,Move.find("Bishop"));
        pieces[59] = new Piece("Queen",2,Move.find("Queen"));
        pieces[60] = new Piece("King",2,Move.find("King"));
        pieces[61] = new Piece("Bishop",2,Move.find("Bishop"));
        pieces[62] = new Piece("Knight",2,Move.find("Knight"));
        pieces[63] = new Piece("Rook",2,Move.find("Rook"));
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
                if (totalPieces >= 16 && totalPieces <= 47){
                    squares.put(new Square(file, rank, x, y), null) ;
                }
                else{
                    squares.put(new Square(file, rank, x, y), pieces[totalPieces]);
                }
                totalSquares++;
                totalPieces++;
                x += 100; //
            }
            file = 'A';
            y += 100; //
            x = 0; //
        }
    }

}
