import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CPU{
    // Basic CPU works by evaluating every possible move and assigning them a score,
    // then it will move to the highest score.
    // Additionally, it displays a frame while CPU is making its move, ensuring it
    // doesn't move instantly, making its behavior more human-like and facilitating
    // observation of its actions.
    public static boolean cpu;
    public static int score;
    public static boolean enemyCheck;
    public static Square squareCpu;
    public static Piece pieceCpu;
    public static JFrame cpuTurn;

    public static void checkMove() {
        cpu = true;
        SwingUtilities.invokeLater(() -> {
            Square.deactivateAll();
            cpuTurn.setVisible(true);
        });
        Timer timer = new Timer(1000, e -> {
            Square.deactivateAll();
            runCPU();
            cpuTurn.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();
    }
    public static void runCPU() {
        score = -1;
        CheckMate.checkingMate = true;
        ArrayList<Piece> pieces = Player.black.getPieces();
        for (Piece p : pieces) {
            Piece.selected = p;
            Move.move(p);
        }
        CheckMate.checkingMate = false;
        cpu = false;
        enemyCheck =false;
        squareCpu.kill = true;
        Piece.selected = pieceCpu;
        Square.teleportPiece(squareCpu);
        Square.deactivateAll();
    }
    public static void score(Square square) {
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
        if (enemyCheck) {
            squareScore += 100;
        }

        if (squareScore > score && !square.equals(Square.neutralSquare)) {
            score = squareScore;
            pieceCpu = Piece.selected;
            squareCpu = square;
        }
        enemyCheck = false;
    }
    public static void buildFrame() {
        cpuTurn = new JFrame();
        cpuTurn.setSize(300,170);
        cpuTurn.setLayout(null);
        cpuTurn.setUndecorated(true);
        cpuTurn.setLocationRelativeTo(null);

        JLabel text = new JLabel("<html><center>BLACK TEAM<br>TURN</center></html>");
        text.setFont(new Font("Agency FB", Font.BOLD, 60));
        text.setBounds(25,25,250,120);
        text.setHorizontalTextPosition(SwingConstants.CENTER);
        text.setForeground(new Color(0, 0, 0));
        text.setVisible(true);

        cpuTurn.add(text);
        cpuTurn.setVisible(false);
    }
}
