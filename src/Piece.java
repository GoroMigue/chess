import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Piece {
    // Static current selected piece
    public static Piece selected;
    // Static neutralPiece, situated on every neutral square
    public static Piece neutralPiece = new Piece("Neutral","Neutral");
    // Array of pieces of the current game
    private static ArrayList<Piece> pieces;
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
    public Piece(String name) {
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
        this.button.addActionListener(e -> {
            if (!CPU.cpu){
                Board.castlingR.setVisible(false);
                Board.castlingL.setVisible(false);
                if (Board.promoting) {
                    Board.promotePawn(Piece.this);
                }
                else{
                    if (Piece.selected.equals(Piece.this)) {
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
            }
        });
        Board.position++;
        if (Board.position == 16) {
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
    public static ArrayList<Piece> getPieces() {
        return pieces;
    }
//
    public static void piecesBuilder() {
        pieces = new ArrayList<>();
        Board.position = 0;
        //BLACK TEAM
        pieces.add(new Piece("Rook","Black"));
        pieces.add(new Piece("Knight","Black"));
        pieces.add(new Piece("Bishop","Black"));
        pieces.add(new Piece("Queen","Black"));
        pieces.add(new Piece("King","Black"));
        pieces.add(new Piece("Bishop","Black"));
        pieces.add(new Piece("Knight","Black"));
        pieces.add(new Piece("Rook","Black"));
        for (int i = 8; i <= 15; i++) {
            pieces.add(new Piece("Pawn","Black"));
        }
        //WHITE TEAM
        for (int i = 16; i <= 23; i++) {
            pieces.add(new Piece("Pawn","White"));
        }
        pieces.add(new Piece("Rook","White"));
        pieces.add(new Piece("Knight","White"));
        pieces.add(new Piece("Bishop","White"));
        pieces.add(new Piece("Queen","White"));
        pieces.add(new Piece("King","White"));
        pieces.add(new Piece("Bishop","White"));
        pieces.add(new Piece("Knight","White"));
        pieces.add(new Piece("Rook","White"));

        selected = new Piece("Selected");
    }
}