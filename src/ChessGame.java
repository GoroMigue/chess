import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGame {
    // In game menu window
    public static JFrame inGameMenu;
    // Game over menu window
    public static JFrame gameOverMenu;
    // Winner label on game over menu
    public static JLabel winner;

    // Main method, it builds in game menu and shows start menu
    public static void main(String[] args) {
        startMenu();
        inGameMenu();
        gameOverMenu();
    }

    // Start menu with start and exit buttons
    public static void startMenu(){
        JFrame startMenu = new JFrame();
        ImageIcon bg = new ImageIcon(new ImageIcon("src/images/StartMenu.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(bg);
        background.setBounds(0,0,500,500);

        JButton start = getButton("START",75,190);
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Player.white = new Player("White");
                Player.black = new Player("Black");
                new Board();
                startMenu.dispose();
            }
        });

        JButton exit = getButton("EXIT",275,190);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                startMenu.dispose();
                System.exit(0);
            }
        });

        startMenu.add(start);
        startMenu.add(exit);
        startMenu.add(background);
        startMenu.setUndecorated(true);
        startMenu.setLayout(null);
        startMenu.setVisible(true);
        startMenu.setSize(500,500);
        startMenu.setLocationRelativeTo(null);
    }
    // In game menu with resume, main menu and exit buttons
    public static void inGameMenu(){
        inGameMenu = new JFrame();
        inGameMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton resume = getButton("RESUME",175,113);
        resume.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                inGameMenu.setVisible(false);
                Board.board.setEnabled(true);
                Board.board.setLocationRelativeTo(null);
                Board.board.setVisible(true);
            }
        });

        JButton mainMenu = getButton("MAIN MENU",175,213);
        mainMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                resetGame();
                startMenu();
            }
        });

        JButton exit = getButton("EXIT",175,313);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                inGameMenu.dispose();
                System.exit(0);
            }
        });

        ImageIcon bg = new ImageIcon(new ImageIcon("src/images/InGameMenu.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(bg);
        background.setBounds(0,0,500,500);

        inGameMenu.add(background);
        background.add(resume);
        background.add(mainMenu);
        background.add(exit);
        inGameMenu.setUndecorated(true);
        inGameMenu.setLayout(null);
        inGameMenu.setVisible(true);
        inGameMenu.setSize(500,500);
        inGameMenu.setLocationRelativeTo(null);
        inGameMenu.setVisible(false);
    }
    // Pause game method, it disables the board and shows the in game menu
    public static void pauseGame(){
        Board.board.setEnabled(false);
        inGameMenu.setVisible(true);
        inGameMenu.setEnabled(true);
        inGameMenu.setLocationRelativeTo(null);
    }
    // When main menu button or the play again button is pressed it resets the game variables
    public static void resetGame(){
        Player.turn = "White";
        Board.board.setEnabled(true);
        for (Piece p: Piece.getPieces()){
            p.getButton().setVisible(false);
        }
        Square.deactivateAll();
        Board.b = null;
        Board.board.dispose();
    }
    // Game over menu with play gain and exit buttons
    public static void gameOverMenu(){
        gameOverMenu = new JFrame();
        ImageIcon bg = new ImageIcon(new ImageIcon("src/images/GameOver.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(bg);
        background.setBounds(0,0,500,500);
        winner = new JLabel();
        winner.setFont(new Font("Agency FB", Font.BOLD, 50));
        winner.setBounds(242,50,350,200);
        winner.setHorizontalTextPosition(SwingConstants.CENTER);
        winner.setForeground(new Color(255, 255, 255));
        winner.setVisible(true);

        JButton playAgain = getButton("PLAY AGAIN",50,280);
        playAgain.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                resetGame();
                Player.white = new Player("White");
                Player.black = new Player("Black");
                new Board();
                gameOverMenu.setVisible(false);
            }
        });

        JButton exit = getButton("EXIT",50,380);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameOverMenu.dispose();
                Board.board.dispose();
                System.exit(0);
            }
        });
        gameOverMenu.add(winner);
        gameOverMenu.add(playAgain);
        gameOverMenu.add(exit);
        gameOverMenu.add(background);
        gameOverMenu.setUndecorated(true);
        gameOverMenu.setLayout(null);
        gameOverMenu.setVisible(false);
        gameOverMenu.setSize(500,500);
        gameOverMenu.setLocationRelativeTo(null);
    }
    // Game over method, it disables the board and shows the in game menu
    public static void gameOver(String team){
        Board.board.setEnabled(false);
        winner.setText("<html><center>GAME OVER<br>" + team + "<br>WINS</center></html>");
        gameOverMenu.setVisible(true);
        gameOverMenu.setLocationRelativeTo(null);
    }
    // Method to build same style buttons
    public static JButton getButton(String name, int x, int y){
        JButton button = new JButton(name);
        button.setBackground(new Color(35, 33, 32, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Geneva", Font.BOLD, 20));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        button.setBounds(x,y,150,75);
        return button;
    }
}