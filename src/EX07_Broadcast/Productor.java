package EX07_Broadcast;

/**
 *
 * @author Gorka
 */
public class Productor extends Thread {
    
    protected BufferBroadcastUn bf;
    protected Object e;
    
    public Productor (BufferBroadcastUn b) {
        
        this.bf = b;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                this.bf.putValue(e);
                sleep(10);
            } catch (InterruptedException ex) {

            }
        }
    }
}
