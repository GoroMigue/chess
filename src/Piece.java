import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piece {
    // Static current selected piece
    public static Piece selected;
    // Static neutralPiece, situated on every neutral square
    public static Piece neutralPiece = new Piece("Neutral","Neutral");
    // Array of pieces of the current game
    private static Piece[] pieces;
//
    private String name;
    private String team;
    // Boolean to verify if it's first move
    private boolean move;
    // Booolean to verify en passant situations
    private boolean enPassant;
    private ImageIcon image;
    private JButton button;
//
    public Piece(String name){
        this.name = name;
        this.team = name;
    }
    public Piece(String name, String team) {
        this.name = name;
        this.team = team;
        this.image = new ImageIcon("src/images/" + name + team + ".png");
        Image ImR = image.getImage().getScaledInstance(111, 111, Image.SCALE_SMOOTH);
        ImageIcon image_ = new ImageIcon(ImR);
        this.button = new JButton(image_);
        this.move = true;
        Board.setBounds(Board.position, this.button);
        this.button.setVisible(true);
        this.button.setOpaque(false);
        this.button.setBorderPainted(false);
        this.button.setBorder(null);
        this.button.setContentAreaFilled(false);
        this.button.setFocusPainted(false);
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Board.castlingR.setVisible(false);
                Board.castlingL.setVisible(false);
                if (Board.promoting){
                    Board.promotePawn(Piece.this);
                }
                else{
                    if (Piece.selected.equals(Piece.this)){
                        Square.deactivateAll();
                        selected = Piece.neutralPiece;
                    }
                    else if (Player.turn.equals(team)) {
                        Square.deactivateAll();
                        selected = Piece.this;
                        Move.move(Piece.this);
                        System.out.println(selected.getName() + " " + Piece.this.team);
                    }
                }
            }});
        Board.position++;
        if (Board.position == 16){
            Board.position = 48;
        }
    }
//
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public boolean getMove(){
        return this.move;
    }
    public JButton getButton(){
        return this.button;
    }
    public boolean getEnPassant(){
        return enPassant;
    }
    public void setEnPassant(Boolean b){
        this.enPassant = b;
    }
    public void setMove(){
        this.move = false;
    }
//
    // Return all pieces of the current game
    public static Piece[] getPieces(){
        return pieces;
    }
//
    public static void piecesBuilder(){
        pieces = new Piece[32];
        Board.position = 0;
        //BLACK TEAM
        pieces[0] = new Piece("Rook","Black");
        pieces[1] = new Piece("Knight","Black");
        pieces[2] = new Piece("Bishop","Black");
        pieces[3] = new Piece("Queen","Black");
        pieces[4] = new Piece("King","Black");
        pieces[5] = new Piece("Bishop","Black");
        pieces[6] = new Piece("Knight","Black");
        pieces[7] = new Piece("Rook","Black");
        for (int i = 8; i <= 15; i++){
            pieces [i] = new Piece("Pawn","Black");
        }
        //WHITE TEAM
        for (int i = 16; i <= 23; i++){
            pieces [i] = new Piece("Pawn","White");
        }
        pieces[24] = new Piece("Rook","White");
        pieces[25] = new Piece("Knight","White");
        pieces[26] = new Piece("Bishop","White");
        pieces[27] = new Piece("Queen","White");
        pieces[28] = new Piece("King","White");
        pieces[29] = new Piece("Bishop","White");
        pieces[30] = new Piece("Knight","White");
        pieces[31] = new Piece("Rook","White");


        selected = new Piece("Selected");
    }
}