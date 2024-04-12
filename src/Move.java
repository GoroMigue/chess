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

        if (!CheckMate.checking && p.getMove()){
            r = piece.getRank();
            f = 'A';
            Square a = Square.getSquare(r,f);
            f++;
            Square b = Square.getSquare(r,f);
            f++;
            Square c = Square.getSquare(r,f);
            f++;
            Square d = Square.getSquare(r,f);
            f++;
            Square e = Square.getSquare(r,f);
            f++;
            Square t = Square.getSquare(r,f);
            f++;
            Square g = Square.getSquare(r,f);
            f++;
            Square h = Square.getSquare(r,f);

            if (a.getPiece().getMove() && p.getMove() && b.getPiece().equals(Square.neutralPiece)
                    && c.getPiece().equals(Square.neutralPiece) && d.getPiece().equals(Square.neutralPiece)) {
                Board.castlingButton("Right", a, b, c, d, e);
            }
            if (p.getMove() && h.getPiece().getMove() && t.getPiece().equals(Square.neutralPiece)
                    && g.getPiece().equals(Square.neutralPiece)) {
                Board.castlingButton("Left", e, t, g, h, null);
            }
        }
    }
    private static void pawnMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();
        int r = pieceRank; char f = pieceFile;
        if(p.getTeam().equals("White")){
            if(p.getMove()){
                r++; pieceCheckPawn(r,f,p);
                if(!stop){r++; pieceCheckPawn(r,f,p);}
            }
            else {
                r++; pieceCheckPawn(r,f,p);
            }
            r = pieceRank;
            r++; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }
        else{
            if(p.getMove()){
                r--; pieceCheckPawn(r,f,p);
                if(!stop){r--; pieceCheckPawn(r,f,p);}
            }
            else {
                r--; pieceCheckPawn(r,f,p);
            }
            r = pieceRank;
            r--; f++; pieceCheck(r,f,p);
            f--;f--; pieceCheck(r,f,p);
        }

        if (p.getEnPassant()) {
            r = pieceRank;
            f = pieceFile; f++;
            piece = Square.getSquare(r,f);
            if (piece.getPiece().getName().equals("Pawn")){
                r++;
                Square.selected = piece;
                Square.getSquare(r,f).activateRed(p);
            }
            r--; f--; f--;
            piece = Square.getSquare(r,f);
            if (piece.getPiece().getName().equals("Pawn")){
                r++;
                Square.selected = piece;
                Square.getSquare(r,f).activateRed(p);
            }
        }
        stop = false;
    }
//
    private static void pieceCheck(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);

        if (s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            if(!p.getName().equals("Pawn")){s.activateYellow(p);}
        } else if (!(s.getPiece().getTeam().equals(p.getTeam()))){
            // activate red
            s.activateRed(p);
            stop = true;
        }
        else if (p.getTeam().equals(s.getPiece().getTeam())){
            stop = true;
        }
    }
    private static void pieceCheckPawn(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);
        if (s.getPiece().equals(Square.neutralPiece)){
            //activate yellow
            s.activateYellow(p);
        }
        else {
            stop = true;
        }
    }
    public static void enPassant(Piece p, Square square){
        if (p.getMove() && p.getName().equals("Pawn")
                && (square.getRank() == 4 || square.getRank() == 5)){
            int r = square.getRank();
            char f = square.getFile();
            f++;
            Piece piece = Square.getSquare(r,f).getPiece();
            if (!piece.getTeam().equals(p.getTeam())){
                piece.setEnPassant(true);
            }
            f--;f--;
            piece = Square.getSquare(r,f).getPiece();
            if (!piece.getTeam().equals(p.getTeam())){
                piece.setEnPassant(true);
            }
        }
    }
    public static void castling(Square a, Square b , Square c, Square d, Square e){
        Piece rook = a.getPiece();
        rook.setMove();
        Piece king = e.getPiece();
        king.setMove();
        Board.setBounds(c.boardPosition,king.getButton());
        Board.setBounds(d.boardPosition,rook.getButton());

        a.setPiece(Square.neutralPiece);
        c.setPiece(king);
        d.setPiece(rook);
        e.setPiece(Square.neutralPiece);
    }
    public static void castling(Square e, Square f , Square g, Square h){
        Piece rook = h.getPiece();
        rook.setMove();
        Piece king = e.getPiece();
        king.setMove();
        Board.setBounds(f.boardPosition,rook.getButton());
        Board.setBounds(g.boardPosition,king.getButton());

        e.setPiece(Square.neutralPiece);
        f.setPiece(rook);
        g.setPiece(king);
        h.setPiece(Square.neutralPiece);
    }
}
