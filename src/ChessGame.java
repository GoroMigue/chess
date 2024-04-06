public class ChessGame {
    public static Player white;
    public static Player black;
    public static boolean start = true;

    public static void main(String[] args) {

        if(start) {
            Board.boardBuilder();
            white = new Player("White");
            black = new Player("Black");
            Piece.piecesBuilder();
            Square.squareBuilder();
            start = false;
        }

    }



}
