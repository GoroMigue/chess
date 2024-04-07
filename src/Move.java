public class Move {
    public static boolean stop = false;
    public static void move(Piece piece){
        switch (piece.getName()) {
            case "Rook": rookMove(piece); break;
            case "Knight": knightMove(piece); break;
            case "Bishop": bishopMove(piece); break;
            case "Queen": queenMove(piece); break;
            case "King": kingMove(piece); break;
            case "Pawn": pawnMove(piece); break;
        }
    }
    private static void rookMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();

        for (int r = pieceRank + 1; r <= 8; r++){
            pieceCheck(r,pieceFile,p);
            if(stop){break;}
        }
        stop = false;
        for (int r = pieceRank - 1; r >= 1; r--){
            pieceCheck(r,pieceFile,p);
            if(stop){break;}
        }
        stop = false;

        pieceFile++;
        for (char f = pieceFile; f <= 'H'; f++){
            pieceCheck(pieceRank,f,p);
            if(stop){break;}
        }
        stop = false;
        pieceFile--; pieceFile--;
        for (char f = pieceFile; f >= 'A'; f--){
            pieceCheck(pieceRank,f,p);
            if(stop){break;}
        }
        stop = false;
    }
    private static void knightMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();
        int r = pieceRank; char f = pieceFile;

        r++; r++; f++; pieceCheck(r,f,p);
        f--; f--; pieceCheck(r,f,p);
        f--; r--; pieceCheck(r,f,p);
        r--; r--; pieceCheck(r,f,p);
        f++; r--; pieceCheck(r,f,p);
        f++; f++; pieceCheck(r,f,p);
        r++;f++; pieceCheck(r,f,p);
        r++;r++; pieceCheck(r,f,p);
    }
    private static void bishopMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();

        int r = pieceRank; char f = pieceFile;
        for (r++; r <= 8; r++){
            f++;
            pieceCheck(r,f,p);
            if(stop){break;}

        }
        stop = false;
        r = pieceRank; f = pieceFile;
        for (r++; r <= 8; r++){
            f--;
            pieceCheck(r,f,p);
            if(stop){break;}

        }
        stop = false;
        r = pieceRank; f = pieceFile;
        for (r--; r >= 1; r--){
            f++;
            pieceCheck(r,f,p);
            if(stop){break;}

        }
        stop = false;
        r = pieceRank; f = pieceFile;
        for (r--; r >= 1; r--){
            f--;
            pieceCheck(r,f,p);
            if(stop){break;}

        }
        stop = false;
    }
    private static void queenMove(Piece p) {
        bishopMove(p); rookMove(p);
    }
    private static void kingMove(Piece p) {
        Square piece = Square.getSquare(p);
        int r = piece.getRank();
        char f = piece.getFile();
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
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();
        int r = pieceRank; char f = pieceFile;
        if(p.getTeam().equals("White")){
            if(p.getPawn()){
                r++; pieceCheckPawn(r,f);
                if(!stop){r++; pieceCheckPawn(r,f);}
            }
            else {
                r++; pieceCheckPawn(r,f);
            }
            r = pieceRank;
            r++; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }
        else{
            if(p.getPawn()){
                r--; pieceCheckPawn(r,f);
                if(!stop){r--; pieceCheckPawn(r,f);}
            }
            else {
                r--; pieceCheckPawn(r,f);
            }
            r = pieceRank;
            r--; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }
        stop = false;
    }
    private static void pieceCheck(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);

        if (s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            if(!p.getName().equals("Pawn")){s.activateYellow();}
        } else if (!(s.getPiece().getTeam().equals(p.getTeam()))){
            // activate red
            s.activateRed();
            stop = true;
        }
        else if (p.getTeam().equals(s.getPiece().getTeam())){
            stop = true;
        }
    }
    private static void pieceCheckPawn(int r, char f){
        Square  s = Square.getSquare(r,f);
        if (s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            s.activateYellow();
        }
    }
}
