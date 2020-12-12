package EX07_Broadcast;

/**
 *
 * @author Gorka
 */
public class Consumidor extends Thread {

    protected BufferBroadcastUn bf;
    protected Object e;
    protected int id;
    protected final int N;

    public Consumidor(BufferBroadcastUn b, int id, int N) {

        this.bf = b;
        this.id = id;
        this.N = N;
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                this.bf.getValue(i);
                sleep(10);
            } catch (InterruptedException ex) {

            }
        }
    }
}
