package EX07_Broadcast;

/**
 *
 * @author Gorka
 */
public class Test extends Thread {

    @Override
    public void run() {

        int consumidors = 100;
        BufferBroadcastUn lb = new BufferBroadcastUn(consumidors);

        new Productor(lb).start();

        for (int i = 0; i < consumidors; i++) {

            new Consumidor(lb, i, consumidors).start();
        }

        try {
            for (int i = 0; i < consumidors; i++) {

                new Consumidor(lb, i, consumidors).join();
            }
        } catch (Exception e) {
        }
        System.out.println("fi simulació");
    }

    public static void main(String[] args) {
        new Test().start();
    }
}
