<div align="center""><img src="https://github.com/GoroMigue/chess/assets/162487939/faa585d5-6a3c-4aba-a3ff-3ae076f637ea.png" alt="Descripción de la imagen" width="500"/></div>

# ABOUT THE PROJECT

<div align="justify">
  This is a personal project. After one month, in October 2023, of beginning to learn programming in java, I decided to challenge myself by developing a chess game. At that time, I built a complete chess, but with a flawed checkmate detector because I didn't even know about the existence of object-oriented programming.
  <br><br>
  In April 2024, I started developing the same chess game from scratch, including all special mechanics (castling, en passant, promoting), and a better checkmate detector by verifying if there's any possible movement left.  
  <br><br>
  It may be a simple chess game, but it helped me a lot to understand object-oriented programming more deeply in java.
  <br><br>
</div>

# ABOUT THE GAME

<div align="justify">
  <ul>
    <li><b>Board:</b> GUI logic using Java Swing with an array as map.</li><br>
    <li><b>Pieces:</b> with attributes to check their team, if it's their first move and en passant situations.</li><br>
    <li><b>Squares:</b> with rank and file attributes, and board position linked to the board map.</li><br>
    <li><b>Movement logic:</b> every piece has its own, no illegal movements allowed.</li><br>
    <li><b>Special situations:</b>
      <ul>
        <li><b>En passant:</b> with a boolean that gets activated on both involved pieces when the situation occurs.</li>
        <li><b>Castling:</b> if king and rook haven't moved and there are no pieces between them, a button can appear to cast.</li>
        <li><b>Promoting:</b> when a pawn reaches the last rank, a window for promoting appears.</li><br>
      </ul>
    </li>
    <li><strong>Checkmate:</strong> logic to verify if a movement is legal or not, and checkmate logic that verifies every possible subsequent situation after every movement of every piece, determining if there any possible movement left.</li><br>
    <li><strong>Menu windows:</strong>
      <ul>
        <li><b>Main menu:</b> with buttons to start the game or exit the application.</li>
        <li><b>In-game menu:</b> with buttons to resume the game, go to main menu or exit the application.</li>
        <li><b>Game over menu:</b> it declares the winner and has buttons to start another game or exit the application.</li>
      </ul>
    </li>
  </ul>
</div>
