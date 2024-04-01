public class Move {
    String type;

    public Move(String type) {
        this.type = type;
    }

    public static Move find(String piece){
        return new Move(" ");
    }
}
