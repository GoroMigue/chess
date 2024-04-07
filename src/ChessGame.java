public class ChessGame {
    public static boolean start = true;
    public static void main(String[] args) {
        if(start) {
            Board.boardBuilder();
            Player.white = new Player("White");
            Player.black = new Player("Black");
            start = false;
        }

    }
}