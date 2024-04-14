import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board {
    private static JFrame board;
    private static JLabel b;
    public static int position = 0;
    public static int[][] boardXY = {
            {56, 56},   {167, 56},  {278, 56},  {389, 56},  {500, 56},  {611, 56},  {722, 56},  {833, 56},
            {56, 167},  {167, 167}, {278, 167}, {389, 167}, {500, 167}, {611, 167}, {722, 167}, {833, 167},
            {56, 278},  {167, 278}, {278, 278}, {389, 278}, {500, 278}, {611, 278}, {722, 278}, {833, 278},
            {56, 389},  {167, 389}, {278, 389}, {389, 389}, {500, 389}, {611, 389}, {722, 389}, {833, 389},
            {56, 500},  {167, 500}, {278, 500}, {389, 500}, {500, 500}, {611, 500}, {722, 500}, {833, 500},
            {56, 611},  {167, 611}, {278, 611}, {389, 611}, {500, 611}, {611, 611}, {722, 611}, {833, 611},
            {56, 722},  {167, 722}, {278, 722}, {389, 722}, {500, 722}, {611, 722}, {722, 722}, {833, 722},
            {56, 833},  {167, 833}, {278, 833}, {389, 833}, {500, 833}, {611, 833}, {722, 833}, {833, 833},
    };
//
    public Board(){
        if (!(board == null)){
            board.dispose();
        }
        board = new JFrame();
        board.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        board.setSize(1000,1000);
        board.setLayout(null);
        board.setUndecorated(true);
        board.setLocationRelativeTo(null);


        ImageIcon boardIm = new ImageIcon("src/images/board.png");
        Image boardImR = boardIm.getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        ImageIcon board_ = new ImageIcon(boardImR);
        b = new JLabel(board_);
        b.setBounds(0,0,1000,1000);


        board.add(b);
        board.setVisible(true);

        Piece.piecesBuilder();
        Square.squareBuilder();
        addPieces();
        Player.setPieces();
        board.revalidate();
        board.repaint();

    }
//
    public static void addButton(JButton b){
        Board.b.add(b);
    }
    public static void addPieces(){
        for (ArrayList<Square> r : Square.squares ) {
            for (Square s : r ) {
                Board.b.add(s.getPiece().getButton());
            }
        }
    }
    public static void setBounds(int index, JButton button){
        if (index < 64){
            button.setBounds(Board.boardXY[index][0],Board.boardXY[index][1],111,111);
        }}
//
    private static JFrame promotion;
    public static boolean promoting;
    public static void promotion(){
        promoting = true;
        board.setEnabled(false);
        board.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        promotion = new JFrame();
        promotion = new JFrame();
        promotion.setSize(525,300);
        promotion.setLayout(null);
        promotion.setUndecorated(true);
        promotion.setLocationRelativeTo(null);

        JLabel title = new JLabel("PROMOTION");
        title.setFont(new Font("Agency FB", Font.BOLD, 80));
        title.setBounds(100,25,325,60);

        title.setVisible(true);


        String team = "";
        if (Piece.selected.getTeam().equals("White")){
            team = "White";
        }
        else {
            team = "Black";
        }
        Piece rook = new Piece("Rook",team);
        rook.getButton().setBounds(25,100,100,110);
        Piece knight = new Piece("Knight",team);
        knight.getButton().setBounds(150,100,100,110);
        Piece bishop = new Piece("Bishop",team);
        bishop.getButton().setBounds(275,100,100,110);
        Piece queen = new Piece("Queen",team);
        queen.getButton().setBounds(400,100,100,110);

        JButton noPromotion = new JButton("<html><center>DON'T<br>PROMOTE</center></html");
        noPromotion.setBackground(new Color(97, 41, 27, 205));
        noPromotion.setForeground(Color.WHITE);
        noPromotion.setFont(new Font("Geneva", Font.BOLD, 20));
        noPromotion.setBounds(200,215,125,75);
        noPromotion.setVisible(true);
        noPromotion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                promotion.dispose();
                promoting = false;
                board.setEnabled(true);
                board.setLocationRelativeTo(null);
                board.setVisible(true);
                Player.nextTurn();
            }});

        promotion.add(title);
        promotion.add(rook.getButton());
        promotion.add(knight.getButton());
        promotion.add(bishop.getButton());
        promotion.add(queen.getButton());
        promotion.add(noPromotion);
        promotion.setVisible(true);
    }
    public static void promotePawn(Piece p){
        Square sq = Square.getSquare(Piece.selected);
        sq.setPiece(p);
        promotion.dispose();
        Piece[] pieces = Piece.getPieces();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals(Piece.selected)) {
                pieces[i] = p;
                break;
            }
        }
        pieces = Player.getPlayer().getPieces();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].equals(Piece.selected)) {
                pieces[i] = p;
                break;
            }
        }
        b.remove(Piece.selected.getButton());
        b.add(p.getButton());
        setBounds(sq.boardPosition,p.getButton());

        promoting = false;
        Piece.selected = Square.neutralPiece;

        board.setEnabled(true);
        board.setLocationRelativeTo(null);
        board.setVisible(true);
        Player.nextTurn();
    }
//
    public static JButton castlingR = new JButton();
    public static JButton castlingL = new JButton();
    public static void castlingButton(String side, Square a, Square b, Square c, Square d, Square e){
        JButton castling = new JButton("<html><center>CASTLING</center></html");
        int x, y;
        if (side.equals("Right")){
            x = 271;
            castlingR = castling;
        }
        else {
            x = 604;
            castlingL = castling;
        }
        if (Player.getPlayer().equals(Player.white)){
            y = 900;
        }
        else{
            y = 25;
        }

        castling.setBackground(new Color(110, 50, 36, 255));
        castling.setForeground(Color.WHITE);
        castling.setFont(new Font("Geneva", Font.BOLD, 20));
        castling.setBounds(x,y,125,75);
        castling.setVisible(true);
        castling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f){
                if (e == null){
                    Move.castling(a, b, c, d);}
                else {
                    Move.castling(a, b, c, d, e);
                }
                castlingR.setVisible(false);
                castlingL.setVisible(false);
                Square.deactivateAll();
            }});
        Board.b.add(castling,1);
        board.revalidate();board.repaint();
    }
}
