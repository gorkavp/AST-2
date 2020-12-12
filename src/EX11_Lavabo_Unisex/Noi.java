package EX11_Lavabo_Unisex;

/**
 *
 * @author Gorka
 */
public class Noi extends Thread {
    
    protected GestorLavabo lavabo;
    
    public Noi (GestorLavabo lb) {
        
        this.lavabo = lb;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                this.lavabo.entraNoi();
                sleep(10);
                this.lavabo.surtNoi();
            } catch (InterruptedException ex) {

            }
        }
    }
}
