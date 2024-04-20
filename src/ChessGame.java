import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.URL;

public class ChessGame {
    // In game menu window
    public static JFrame inGameMenu;
    // Game over menu window
    public static JFrame gameOverMenu;
    // Winner label on game over menu
    public static JLabel winner;
    public static boolean cpuMode;

    // Main method, it builds in game menu and shows start menu
    public static void main(String[] args) {
        startMenu();
        inGameMenu();
        gameOverMenu();
        CPU.buildFrame();
    }

    // Start menu with start and exit buttons
    public static void startMenu() {
        JFrame startMenu = new JFrame();
        URL image = ChessGame.class.getResource("/images/StartMenu.png");


        ImageIcon bg = new ImageIcon(new ImageIcon("src/images/StartMenu.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(bg);
        background.setBounds(0,0,500,500);

        JButton exit = ChessGame.getButton("<html><center>x</center></html>",960,350);
        exit.setBounds(470,5,25,25);
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.addActionListener(e -> {
            startMenu.dispose();
            System.exit(0);
        });

        JButton start = getButton("2 PLAYERS",75,190);
        start.addActionListener(e -> {
            cpuMode = false;
            Player.white = new Player("White");
            Player.black = new Player("Black");
            new Board();
            startMenu.dispose();
        });

        JButton versusCpu = getButton("CPU MODE",275,190);
        versusCpu.addActionListener(e -> {
            cpuMode = true;
            Player.white = new Player("White");
            Player.black = new Player("Black");
            new Board();
            startMenu.dispose();
        });

        startMenu.add(start);
        startMenu.add(exit);
        startMenu.add(versusCpu);
        startMenu.add(background);
        startMenu.setUndecorated(true);
        startMenu.setLayout(null);
        startMenu.setVisible(true);
        startMenu.setSize(500,500);
        startMenu.setLocationRelativeTo(null);
    }
    // In game menu with resume, main menu and exit buttons
    public static void inGameMenu() {
        inGameMenu = new JFrame();
        inGameMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton resume = getButton("RESUME",175,113);
        resume.addActionListener(e -> {
            inGameMenu.setVisible(false);
            Board.board.setEnabled(true);
            Board.board.setLocationRelativeTo(null);
            Board.board.setVisible(true);
        });

        JButton mainMenu = getButton("MAIN MENU",175,213);
        mainMenu.addActionListener(e -> {
            resetGame();
            inGameMenu.setVisible(false);
            startMenu();
        });

        JButton exit = getButton("EXIT",175,313);
        exit.addActionListener(e -> {
            inGameMenu.dispose();
            Board.board.dispose();
            System.exit(0);
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
    public static void pauseGame() {
        Board.board.setEnabled(false);
        inGameMenu.setVisible(true);
        inGameMenu.setEnabled(true);
        inGameMenu.setLocationRelativeTo(null);
    }
    // When main menu button or the play again button is pressed it resets the game variables
    public static void resetGame() {
        Player.turn = "White";
        Board.board.setEnabled(true);
        for (Piece p: Piece.getPieces()) {
            p.getButton().setVisible(false);
        }
        Square.deactivateAll();
        Board.b = null;
        Board.board.dispose();
    }
    // Game over menu with play gain and exit buttons
    public static void gameOverMenu() {
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
        playAgain.addActionListener(e -> {
            resetGame();
            gameOverMenu.setVisible(false);
            startMenu();
        });

        JButton exit = getButton("EXIT",50,380);
        exit.addActionListener(e -> {
            gameOverMenu.dispose();
            Board.board.dispose();
            System.exit(0);
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
    public static void gameOver(String team) {
        Board.board.setEnabled(false);
        winner.setText(STR."<html><center>GAME OVER<br>\{team}<br>WINS</center></html>");
        gameOverMenu.setVisible(true);
        gameOverMenu.setLocationRelativeTo(null);
    }
    // Method to build same style buttons
    public static JButton getButton(String name, int x, int y) {
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