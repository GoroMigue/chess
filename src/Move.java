import java.util.ArrayList;
import java.util.HashMap;

public class Move {
    public static void move(Piece piece){
        switch (piece.getName()) {
            case "Rook": rookMove(piece);
            case "Knight": knightMove(piece);
            case "Bishop": bishopMove(piece);
            case "Queen": queenMove(piece);
            case "King": kingMove(piece);
            case "Pawn": pawnMove(piece);
        }
    }
    private static void rookMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = Square.getSquare(p).getRank();
        char pieceFile = Square.getSquare(p).getFile();

        for (int r = pieceRank; r <= 8; r++){
            pieceCheck(r,pieceFile,p);
        }

        for (int r = pieceRank; r >= 1; r--){
            pieceCheck(r,pieceFile,p);
        }

        for (char f = pieceFile; f <= 'H'; f++){
            pieceCheck(pieceRank,f,p);
        }

        for (char f = pieceFile; f >= 'A'; f--){
            pieceCheck(pieceRank,f,p);
        }
    }

    private static void knightMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = Square.getSquare(p).getRank();
        char pieceFile = Square.getSquare(p).getFile();
        int r = pieceRank; char f = pieceFile;

        r += 2; f++;
        pieceCheck(r,f,p);

        r = pieceRank + 2; f = pieceFile--;
        pieceCheck(r,f,p);

        r = pieceRank - 2; f = pieceFile--;
        pieceCheck(r,f,p);

        r = pieceRank - 2; f = pieceFile++;
        pieceCheck(r,f,p);


        r = pieceRank++; f = pieceFile++; f++;
        pieceCheck(r,f,p);

        r = pieceRank--; f = pieceFile++; f++;
        pieceCheck(r,f,p);

        r = pieceRank++; f = pieceFile--; f--;
        pieceCheck(r,f,p);

        r = pieceRank--; f = pieceFile--; f--;
        pieceCheck(r,f,p);
    }

    private static void bishopMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = Square.getSquare(p).getRank();
        char pieceFile = Square.getSquare(p).getFile();

        int r = pieceRank; char f = pieceFile;
        for (; r <= 8; r++){
            pieceCheck(r,f,p);
            f++;
        }

        r = pieceRank; f = pieceFile;
        for (; r <= 8; r++){
            pieceCheck(r,f,p);
            f--;
        }

        r = pieceRank; f = pieceFile;
        for (; r >= 1; r--){
            pieceCheck(r,f,p);
            f++;
        }

        r = pieceRank; f = pieceFile;
        for (; r >= 1; r--){
            pieceCheck(r,f,p);
            f--;
        }
    }

    private static void queenMove(Piece p) {
        bishopMove(p); rookMove(p);
    }
    private static void kingMove(Piece p) {
        Square piece = Square.getSquare(p);
        int r = Square.getSquare(p).getRank();
        char f = Square.getSquare(p).getFile();

        r++; pieceCheck(r,f,p);
        f--; pieceCheck(r,f,p);
        r--; pieceCheck(r,f,p);
        r--; pieceCheck(r,f,p);
        f++; pieceCheck(r,f,p);
        f++; pieceCheck(r,f,p);
        r++; pieceCheck(r,f,p);
        r++; pieceCheck(r,f,p);
    }

    private static void pawnMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = Square.getSquare(p).getRank();
        char pieceFile = Square.getSquare(p).getFile();
        int r = pieceRank; char f = pieceFile;
        if(p.getTeam().equals("White")){
            if(p.getPawn()){
                r++; pieceCheckPawn(r,f,p);
                r++; pieceCheckPawn(r,f,p);
            }
            else {
                r++; pieceCheck(r,f,p);
            }
            r = pieceRank;
            r++; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }
        else{
            if(p.getPawn()){
                r--; pieceCheckPawn(r,f,p);
                r--; pieceCheckPawn(r,f,p);
            }
            else {
                r--; pieceCheck(r,f,p);
            }
            r = pieceRank;
            r--; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }
    }
    
    private static void pieceCheck(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);
        if (!s.getPiece().getTeam().equals(p.getTeam()) && !s.getPiece().equals(Square.neutralPiece)){
            // activate red
            s.activateRed();
        }
        else if (!s.getPiece().getTeam().equals(p.getTeam()) && s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            s.activateYellow();
        }
    }
    private static void pieceCheckPawn(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);
        if (!s.getPiece().getTeam().equals(p.getTeam()) && s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            s.activateYellow();
        }
    }
}
