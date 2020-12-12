
package EX06_GrupEx;

/**
 *
 * @author Gorka
 */
public class Fil extends Thread {
    
    private final int N;
    private final GroupEx b;

    public Fil(int N, GroupEx b) {
        this.N = N;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                this.b.enter();
                 sleep(10);
                this.b.exit();
            } catch (InterruptedException ex) {

            }
        }
    }
}