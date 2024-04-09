import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Square {
    public static ArrayList<ArrayList<Square>> squares = new ArrayList<>();
    public static Piece neutralPiece = new Piece("Neutral","Neutral");
    public static Square neutralSquare;
//
    private char file;
    private int rank;
    private Piece piece;
    private ImageIcon imageYellow;
    private ImageIcon imageRed;
    private JButton button;
    public boolean kill;
    public int boardPosition;
//
    public Square(char file, int rank, Piece piece) {
        this.file = file;
        this.rank = rank;
        this.piece = piece;
        this.kill = false;
        ImageIcon yellow = new ImageIcon("src/images/yellow.png");
        Image ImY = yellow.getImage().getScaledInstance(111, 111, Image.SCALE_SMOOTH);
        this.imageYellow = new ImageIcon(ImY);
        this.button = new JButton(imageRed);

        ImageIcon red = new ImageIcon("src/images/red.png");
        Image ImR = red.getImage().getScaledInstance(111, 111, Image.SCALE_SMOOTH);
        this.imageRed = new ImageIcon(ImR);

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
                teleportPiece(Square.this);
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
            if (this.piece.getTeam().equals(Piece.selected.getTeam())){
                CheckMate.teamCheck = true;
            }
            else{
                CheckMate.enemyCheck = true;
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
        sq.setPiece(neutralPiece);
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
        }
        else {
            Board.setBounds(square.boardPosition, Piece.selected.getButton());
            if (Piece.selected.getName().equals("Pawn")) {
                Piece.selected.setPawn();
            }
            if (square.kill) {
                Player.getPlayer().kill(p);
            }
            Player.nextTurn();
        }
        Piece.selected = neutralPiece;
        CheckMate.teamCheck = false;
        square.kill = false;
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
//
    public static void squareBuilder(){
        int totalPieces = 0;
        Board.position = 0;
        for (int rank = 7;rank >= 0; rank--){
            int irank = 0;
            squares.add(new ArrayList<>());
            for (char file = 'A';file <= 'H'; file++){
                int crank = rank + 1;
                if (rank >= 2 && rank < 6){
                    squares.get(irank).add(new Square(file, crank,neutralPiece));
                }
                else{
                    squares.get(irank).add(new Square(file, crank, Piece.getPieces()[totalPieces]));
                    totalPieces++;
                }
            }
            irank++;
        }
        neutralSquare  = new Square('N',0,neutralPiece);
    }
}