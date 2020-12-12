package EX10_Dobles_mixtes_de_tenis;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Gorka
 */
public class Doblesmixtes {

    private final ReentrantLock l = new ReentrantLock();
    private final Condition noisPreparats = l.newCondition();
    private final Condition noiesPreparades = l.newCondition();
    private final Condition noiesEsperant = l.newCondition();
    private final Condition noisEsperant = l.newCondition();

    private int noies;
    private int nois;
    private int jugadors;
    private int partits;

    private String dins = "";

    public Doblesmixtes() {

        this.noies = 0;
        this.nois = 0;
        this.jugadors = 0;
        this.partits = 0;
    }

    public void noiaPreparada() {

        this.l.lock();

        try {

            while (this.noies >= 2) {

                this.noiesEsperant.awaitUninterruptibly();
            }

            this.noies++;

            while (this.noies < 2 || this.nois != 2) {

                this.noiesPreparades.awaitUninterruptibly();
            }

            this.jugadors++;

            if (this.jugadors < 4) {

                this.noisPreparats.signal();

            } else {

                this.partits++;
                System.out.println("Partit " + this.partits + " : " + this.jugadors + " jugadors preparats per jugar");
                this.jugadors = 0;
                this.nois = 0;
                this.noies = 0;
                this.noisEsperant.signalAll();
                this.noiesEsperant.signalAll();
            }
        } finally {
            this.l.unlock();
        }
    }

    public void noiPreparat() {

        this.l.lock();

        try {

            while (this.nois >= 2) {

                this.noisEsperant.awaitUninterruptibly();
            }

            this.nois++;

            while (this.noies < 2 || this.nois != 2) {

                this.noisPreparats.awaitUninterruptibly();
            }

            this.jugadors++;

            if (this.jugadors < 4) {

                this.noiesPreparades.signal();

            } else {

                this.partits++;
                System.out.println("Partit " + this.partits + " : " + this.jugadors + " jugadors preparats per jugar");
                this.jugadors = 0;
                this.nois = 0;
                this.noies = 0;
                this.noisEsperant.signalAll();
                this.noiesEsperant.signalAll();
            }
        } finally {
            this.l.unlock();
        }
    }
}
