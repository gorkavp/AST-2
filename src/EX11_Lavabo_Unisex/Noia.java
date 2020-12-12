package EX11_Lavabo_Unisex;

/**
 *
 * @author Gorka
 */
public class Noia extends Thread {

    protected GestorLavabo lavabo;

    public Noia(GestorLavabo lb) {

        this.lavabo = lb;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                this.lavabo.entraNoia();
                sleep(10);
                this.lavabo.surtNoia();
            } catch (InterruptedException ex) {

            }
        }
    }
}
