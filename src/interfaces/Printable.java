package interfaces;

public interface Printable {

    default void print() {
        System.out.println(this);
    }

    static void header(String title) {
        System.out.println(title);
    }
}
