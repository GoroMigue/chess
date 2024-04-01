public class Piece {
    private String name;
    private int team;
    private Move move;

    public Piece(String name, int team) {
        this.name = name;
        this.team = team;
        this.move = Move.find(name);
    }

    public String getName() {
        return name;
    }

    public int getTeam() {
        return team;
    }

    public void Move() {
        //
    }

    public void hide(){}

}

