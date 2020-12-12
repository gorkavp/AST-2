package EX11_Lavabo_Unisex;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Gorka
 */
public class GestorLavabo {

    private final ReentrantLock l = new ReentrantLock();
    private final Condition noisPreparats;
    private final Condition noiesPreparades;
    private final Condition noiesEsperant;
    private final Condition noisEsperant;

    private boolean ocupat;
    private boolean acces;
    private int noies;
    private int nois;
    private int ocupants;
    private int partits;

    private String dins = "";

    public GestorLavabo() {

        this.noies = 0;
        this.nois = 0;
        this.ocupants = 0;
        this.partits = 0;
        this.ocupat = false;
        this.acces = false;
        this.noisPreparats = l.newCondition();
        this.noiesPreparades = l.newCondition();
        this.noiesEsperant = l.newCondition();
        this.noisEsperant = l.newCondition();
    }

    public void entraNoia() {

        this.l.lock();

        try {

            while (this.noies >= 2) {

                this.noiesEsperant.awaitUninterruptibly();
            }

            this.noies++;

            while (this.noies < 2 || this.ocupat) {

                this.acces = true;
                this.noiesPreparades.awaitUninterruptibly();
                this.ocupat = false;
            }

            this.ocupants++;
            this.ocupat = true;

            if (this.ocupants < 2) {

                this.noiesPreparades.signal();

            } else {

                System.out.println("Han entrat dues noies");
            }

        } finally {
            this.l.unlock();
        }
    }

    public void entraNoi() {

        this.l.lock();

        try {

            while (this.ocupat || this.acces) {

                this.noisEsperant.awaitUninterruptibly();
            }
            this.ocupat = true;
            this.ocupants++;
            System.out.println("Ha entrat un noi");

        } finally {
            this.l.unlock();
        }
    }

    public void surtNoia() {

        this.l.lock();

        try {

            this.ocupants--;
            if (this.ocupants == 0) {
                System.out.println("Han sortit dues noies");
                this.ocupat = false;
                this.acces = false;
                this.noies = 0;
                this.noisEsperant.signal();
                this.noiesEsperant.signalAll();
            }
        } finally {
            this.l.unlock();
        }
    }

    public void surtNoi() {

        this.l.lock();

        try {

            this.ocupants--;
            if (this.ocupants == 0) {
                System.out.println("Ha sortit un noi");
                this.ocupat = false;
                this.noisEsperant.signal();
                this.noiesPreparades.signalAll();
            }
        } finally {
            this.l.unlock();
        }
    }
}
