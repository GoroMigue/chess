import java.util.Random;

public class CPU {
    public static boolean cpu;
    public static int score;
    public static Square squareCpu;
    public static Piece pieceCpu;

    public static void cpuMove(){
        score = -1;
        cpu = true;
        CheckMate.checkingMate = true;
        Piece [] pieces = Player.black.getPieces();
        for (Piece p : pieces) {
            Piece.selected = p; p.getName();
            Move.move(p);
        }
        CheckMate.checkingMate = false;
        cpu = false;
        squareCpu.kill = true;
        Piece.selected = pieceCpu;
        Square.teleportPiece(squareCpu);
        Square.deactivateAll();
    }

    public static void score(Square square){
        int squareScore = 0;
        Random rd = new Random();
        switch (square.getPiece().getName()) {
            case "Rook": squareScore += 120 + rd.nextInt(50); break;
            case "Knight": squareScore += 100 + rd.nextInt(50); break;
            case "Bishop": squareScore += 110 + rd.nextInt(50); break;
            case "Queen": squareScore += 250 + rd.nextInt(50); break;
            case "King": squareScore += 150 + rd.nextInt(50); break;
            case "Pawn": squareScore += 90 + rd.nextInt(50); break;
            default:
        }

        switch (Piece.selected.getName()) {
            case "Rook": squareScore += rd.nextInt(50); break;
            case "Knight": squareScore += rd.nextInt(50); break;
            case "Bishop": squareScore += rd.nextInt(50); break;
            case "Queen": squareScore += rd.nextInt(60); break;
            case "King": squareScore += rd.nextInt(30); break;
            case "Pawn": squareScore += rd.nextInt(50); break;
            default:
        }

        if (squareScore > score && !square.equals(Square.neutralSquare)) {
            score = squareScore;
            pieceCpu = Piece.selected;
            squareCpu = square;
        }
    }
}
