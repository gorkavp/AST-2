package EX11_Lavabo_Unisex;

/**
 *
 * @author Gorka
 */
public class Test extends Thread {

    @Override
    public void run() {

        int persones = 100;
        GestorLavabo lb = new GestorLavabo();

        for (int i = 0; i < persones; i++) {
            new Noia(lb).start();
            new Noi(lb).start();
        }

        try {
            for (int i = 0; i < persones; i++) {
                new Noia(lb).join();
                new Noi(lb).join();
            }
        } catch (Exception e) {
        }
        System.out.println("fi simulació");
    }

    public static void main(String[] args) {
        new Test().start();
    }
}
