public class ChessGame {
    public static boolean start = true;
    public static void main(String[] args) {
        if(start) {
            Player.white = new Player("White");
            Player.black = new Player("Black");
            new Board();
            start = false;
        }
    }
}