import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Square {
    // Static ArrayList of ArrayList of every square of the current game
    // Main ArrayList contains 8 Arraylist representing ranks of the board
    // Each rank ArrayList contains squares representing files of the board
    public static ArrayList<ArrayList<Square>> squares;
    // Neutral square to avoid null references, it's actually never used
    public static Square neutralSquare;
    // Current selected square, it helps while teleporting or checking at special situations
    public static Square selected;
//
    private char file;
    private int rank;
    private Piece piece;
    private JButton button;
    public boolean kill;
    public int boardPosition;
    // Static images for squares and their states
    private static ImageIcon imageYellow;
    private static ImageIcon imageRed;
//
    public Square(char file, int rank, Piece piece) {
        this.file = file;
        this.rank = rank;
        this.piece = piece;
        this.kill = false;
        this.button = new JButton(imageRed);
        this.boardPosition = Board.position;

        Board.setBounds(boardPosition, this.button);
        this.button.setEnabled(false);
        this.button.setVisible(false);
        this.button.setOpaque(false);
        this.button.setBorderPainted(false);
        this.button.setBorder(null);
        this.button.setContentAreaFilled(false);
        this.button.setFocusPainted(false);
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Piece.selected.getEnPassant() && Piece.selected.getName().equals("Pawn") && Square.this.kill){
                    teleportEnPassant(Square.this);
                }
                else{
                    teleportPiece(Square.this);
                }
                System.out.println(Square.this.file + " " + Square.this.rank + " Board Position: " + Square.this.boardPosition);
                deactivateAll();
            }});
        Board.position++;
        Board.addButton(this.button);
    }
    public int getRank(){
        return this.rank;
    }
    public char getFile(){
        return this.file; }
    public Piece getPiece(){
        return this.piece; }
    public void setPiece(Piece piece) {
        this.piece = piece; }
    public void activateRed(Piece p){
        if (this.piece.getName().equals("King") && CheckMate.checking){
            if (this.piece.getTeam().equals(Player.turn)) {
                CheckMate.teamCheck = true;
            }
            else {
                CPU.enemyCheck = true;
            }
        }
        if (CheckMate.checkingMate){
            Piece.selected = p;
            CheckMate.checkingMateChecking = true;
            teleportPiece(this);
        }
        else{
            this.button.setEnabled(true);
            this.button.setIcon(imageRed);
            this.button.setVisible(true);
            this.kill = true;
        }
    }
    public void activateYellow(Piece p){
        this.button.setEnabled(true);
        this.button.setIcon(imageYellow);
        this.button.setVisible(true);
        this.kill = false;
        if (CheckMate.checkingMate){
            Piece.selected = p;
            CheckMate.checkingMateChecking = true;
            teleportPiece(this);
        }
    }
//
    public static Square getSquare(Piece piece) {
        for (ArrayList<Square> r : squares ) {
            for (Square s : r ) {
                if (s.getPiece().equals(piece)) {
                    return s;
                }
            }
        }
        return neutralSquare;
    }
    public static Square getSquare(int rank,char file){
        for (ArrayList<Square> r : squares ) {
            for (Square s : r ) {
                if (s.getRank() == rank && s.getFile() == file) {
                    return s;
                }
            }
        }
        return neutralSquare;
    }
    public static void deactivateAll(){
        for (ArrayList<Square> r : squares){
            for (Square s : r){
                s.button.setEnabled(false);
                s.button.setVisible(false);
            }
        }
    }
    public static void teleportPiece(Square square){
        Square sq = getSquare(Piece.selected);
        Piece p = square.getPiece();
        sq.setPiece(Piece.neutralPiece);
        square.setPiece(Piece.selected);

        if (CheckMate.checkingMateChecking) {CheckMate.checkingMate = false;}
        CheckMate.check();
        if (CheckMate.checkingMateChecking) {
            CheckMate.checkingMate = true;
            CheckMate.checkingMateChecking = false;
        }

        if (CheckMate.teamCheck){
            sq.setPiece(Piece.selected);
            square.setPiece(p);
        }
        else if (CheckMate.checkingMate) {
            CheckMate.checkMove = true;
            sq.setPiece(Piece.selected);
            square.setPiece(p);
            if (CPU.cpu) { CPU.score(square);}
        }
        else {
            Board.setBounds(square.boardPosition, Piece.selected.getButton());
            Move.enPassant(Piece.selected, square);
            if (!CheckMate.checking){
                Piece.selected.setMove();
            }
            if (square.kill) {
                Player.getPlayer().kill(p);
            }
            if (Piece.selected.getName().equals("Pawn") && (square.getRank() == 1 || square.getRank() == 8)) {
                if (!(ChessGame.cpuMode && Player.turn.equals("Black"))) {Board.promotion();}
                else {Player.nextTurn();}
            }
            else {Player.nextTurn();}
        }
        if (!Board.promoting){Piece.selected = Piece.neutralPiece;}
        CheckMate.teamCheck = false;
        square.kill = false;
    }
    public static void teleportEnPassant(Square square){
        Square sq = getSquare(Piece.selected);
        Piece p = Square.selected.getPiece();
        Square.selected.setPiece(Piece.neutralPiece);
        sq.setPiece(Piece.neutralPiece);
        square.setPiece(Piece.selected);

        if (CheckMate.checkingMateChecking) {CheckMate.checkingMate = false;}
        CheckMate.check();
        if (CheckMate.checkingMateChecking) {
            CheckMate.checkingMate = true;
            CheckMate.checkingMateChecking = false;
        }

        if (CheckMate.teamCheck){
            sq.setPiece(Piece.selected);
            square.setPiece(Piece.neutralPiece);
            Square.selected.setPiece(p);
        }
        else if (CheckMate.checkingMate) {
            CheckMate.checkMove = true;
            sq.setPiece(Piece.selected);
            square.setPiece(Piece.neutralPiece);
            Square.selected.setPiece(p);
        }
        else {
            Board.setBounds(square.boardPosition, Piece.selected.getButton());
            Move.enPassant(Piece.selected, square);
            if (square.kill) {
                Player.getPlayer().kill(p);
            }
            Player.nextTurn();
        }
        Piece.selected = Piece.neutralPiece;
        CheckMate.teamCheck = false;
        square.kill = false;
    }
//
    public static void squareBuilder(){
        ImageIcon yellow = new ImageIcon("src/images/yellow.png");
        Image ImY = yellow.getImage().getScaledInstance(111, 111, Image.SCALE_SMOOTH);
        imageYellow = new ImageIcon(ImY);

        ImageIcon red = new ImageIcon("src/images/red.png");
        Image ImR = red.getImage().getScaledInstance(111, 111, Image.SCALE_SMOOTH);
        imageRed = new ImageIcon(ImR);

        squares = new ArrayList<>();
        int totalPieces = 0;
        Board.position = 0;
        for (int rank = 7;rank >= 0; rank--){
            int irank = 0;
            squares.add(new ArrayList<>());
            for (char file = 'A';file <= 'H'; file++){
                int crank = rank + 1;
                if (rank >= 2 && rank < 6){
                    squares.get(irank).add(new Square(file, crank,Piece.neutralPiece));
                }
                else{
                    squares.get(irank).add(new Square(file, crank, Piece.getPieces()[totalPieces]));
                    totalPieces++;
                }
            }
            irank++;
        }
        neutralSquare  = new Square('N',0,Piece.neutralPiece);
    }
}