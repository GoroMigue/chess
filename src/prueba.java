public class prueba {
    public static void main(String[] args) {
        Piece p1 = new Piece("hola","hola");
        Piece p3 = null;
        Piece p2 = null;

        p2 = p1;

        p1 = null;

        p3 = p2;

    }
}
