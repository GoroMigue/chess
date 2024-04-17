public class Move {
    // Static boolean that verifies when the movement has to stop,
    // when a piece is on the way
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
        stop = false;
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
        stop = false;
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
        r = piece.getRank();
        f = piece.getFile();
        r++; pieceCheck(r,f,p);
        f--; pieceCheck(r,f,p);
        r--; pieceCheck(r,f,p);
        r--; pieceCheck(r,f,p);
        f++; pieceCheck(r,f,p);
        f++; pieceCheck(r,f,p);
        r++; pieceCheck(r,f,p);
        r++; pieceCheck(r,f,p);

        if (!CheckMate.checking && !CheckMate.checkingMate && p.getMove()){
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

            if (!CheckMate.teamCheck){
                if (a.getPiece().getMove() && p.getMove() && b.getPiece().equals(Piece.neutralPiece)
                        && c.getPiece().equals(Piece.neutralPiece) && d.getPiece().equals(Piece.neutralPiece)) {
                    Board.castlingButton("Right", a, b, c, d, e);
                }
                if (p.getMove() && h.getPiece().getMove() && t.getPiece().equals(Piece.neutralPiece)
                        && g.getPiece().equals(Piece.neutralPiece)) {
                    Board.castlingButton("Left", e, t, g, h, null);
                }
            }
        }
    }
    private static void pawnMove(Piece p) {
        Square piece = Square.getSquare(p);
        int pieceRank = piece.getRank();
        char pieceFile = piece.getFile();
        int r = pieceRank; char f = pieceFile;
        stop = false;
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
            f = pieceFile;
            f++;
            pieceCheckEnPassant(r,f,p);
            f--;f--;
            pieceCheckEnPassant(r,f,p);
        }
        stop = false;
    }
//
    // Checks if a square contains an enemy, team or neutral piece
    private static void pieceCheck(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);

        if (s.getPiece().equals(Piece.neutralPiece)){
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
    // Checking special situation for pawn straight move
    private static void pieceCheckPawn(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);
        if (s.getPiece().equals(Piece.neutralPiece)){
            //activate yellow
            s.activateYellow(p);
        }
        else {
            stop = true;
        }
    }
    // Checking special situation en passant
    private static void pieceCheckEnPassant(int r, char f, Piece p){
        Square  s = Square.getSquare(r,f);
        if (s.getPiece().equals(Piece.neutralPiece)){
        }
        else if(!(s.getPiece().getTeam().equals(p.getTeam())) && s.getPiece().getEnPassant()) {
            if (Player.getPlayer().equals(Player.white)){
                r++;
            }
            else {
                r--;
            }
            Square.selected = s;
            s = Square.getSquare(r,f);
            s.activateRed(p);
        }
    }
    // Sets the en passant boolean true on both involved pieces
    public static void enPassant(Piece p, Square square){
        for (Piece piece : Piece.getPieces()){
            piece.setEnPassant(false);
        }

        int r = square.getRank();
        char f = square.getFile();
        if (p.getMove() && p.getName().equals("Pawn")
                && (r == 4 || r == 5)){
            f++;
            Piece piece = Square.getSquare(r,f).getPiece();
            if (piece.getTeam() != p.getTeam()){
                piece.setEnPassant(true);
                p.setEnPassant(true);
            }
            f--;f--;
            piece = Square.getSquare(r,f).getPiece();
            if (piece.getTeam() != p.getTeam()){
                piece.setEnPassant(true);
                p.setEnPassant(true);
            }
        }
    }
    // Teleporting on special situation of castling.
    public static void castling(Square a, Square b , Square c, Square d, Square e){
        Piece rook = a.getPiece();
        Piece king = e.getPiece();
        CheckMate.check();

        a.setPiece(Piece.neutralPiece);
        c.setPiece(king);
        d.setPiece(rook);
        e.setPiece(Piece.neutralPiece);

        CheckMate.check();
        if (CheckMate.teamCheck){
            e.setPiece(king);
            d.setPiece(Piece.neutralPiece);
            c.setPiece(Piece.neutralPiece);
            a.setPiece(rook);
        }
        else {
            Board.setBounds(c.boardPosition,king.getButton());
            Board.setBounds(d.boardPosition,rook.getButton());
            king.setMove();
            rook.setMove();
            Player.nextTurn();
        }


    }
    public static void castling(Square e, Square f , Square g, Square h){
        Piece rook = h.getPiece();
        Piece king = e.getPiece();
        CheckMate.check();

        e.setPiece(Piece.neutralPiece);
        f.setPiece(rook);
        g.setPiece(king);
        h.setPiece(Piece.neutralPiece);

        CheckMate.check();
        if (CheckMate.teamCheck){
            e.setPiece(king);
            f.setPiece(Piece.neutralPiece);
            g.setPiece(Piece.neutralPiece);
            h.setPiece(rook);
        }
        else {
            Board.setBounds(f.boardPosition,rook.getButton());
            Board.setBounds(g.boardPosition,king.getButton());
            king.setMove();
            rook.setMove();
            Player.nextTurn();
        }
        CheckMate.teamCheck = false;
    }
}
