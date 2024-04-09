import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    private static JFrame board;
    private static JLabel b;
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
    public static int position = 0;
//
    public Board(){
        if (!(board == null)){
            board.dispose();
        }
        board = new JFrame();
        board.setSize(1000,1000);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
