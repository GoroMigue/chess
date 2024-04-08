import java.util.ArrayList;

public class CheckMate {
    public static boolean teamCheck = false;
    public static boolean enemyCheck = false;
    public static boolean checking = false;
    public static boolean checkingMate = false;
    public static boolean checkMove = false;

    public static ArrayList<ArrayList<Square>> squaresCheck;
    public static void check(){
        checking = true;
        Piece [] pieces;
        if (Player.getPlayer().equals(Player.white)){
            pieces = Player.black.getPieces().clone();
        }
        else {
            pieces = Player.white.getPieces().clone();
        }
        for(Piece p : pieces){
            Move.move(p);
            Square.deactivateAll();
        }
        checking = false;
    }


    public static void checkMate() {
        checking = true;
        checkingMate = true;
        teamCheck = false;
        checkMove = false;
        Piece [] pieces = Player.getPlayer().getPieces().clone();
        for(Piece p : pieces){
            Move.move(p);
            Square.deactivateAll();
            checking = true;
            if (!checkMove){
                deadEnd();
                break;
            }
        }

        teamCheck = true;
        checking = false;
        checkingMate = false;
    }

    public static void deadEnd(){
        System.out.println("GAME OVER");
    }


    /*
    public static void checkMateReCheck(){
        checking = true;
        checkingMove = true;
        Piece [] pieces;
        if (Player.getPlayer().equals(Player.white)){
            pieces = Player.black.getPieces();
        }
        else {
            pieces = Player.white.getPieces();
        }
        for(Piece p : pieces){
            Move.move(p);
            Square.deactivateAll();
        }
        checkingMove = true;
        checking = false;
    }
    public static void checkMate() {
        checking = true;
        checkingMate = true;
        teamCheck = false;
        checkMove = false;
        Piece [] pieces = Player.getPlayer().getPieces();
        for(Piece p : pieces){

        }
        System.out.println(checkMove);
        if (!checkMove){
            deadEnd();
        }
        teamCheck = true;
        checking = false;
        checkingMate = false;
    }

    public static void deadEnd(){
        System.out.println("GAME OVER");
    }*/

}
