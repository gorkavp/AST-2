package EX02_ParellesBall;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MPista {

    private final ReentrantLock l;
    private final Condition[] parella;
    private final Condition[] ocupat;
    private final int[] genere;
    private int idAltre;
    private String str;

    public MPista() {
        l = new ReentrantLock();
        parella = new Condition[2];
        ocupat = new Condition[2];
        genere = new int[2];
        for (int i = 0; i < 2; i++) {
            parella[i] = l.newCondition();
            ocupat[i] = l.newCondition();
            genere[i] = 0;
        }
    }

    public int ballar(int id) {
        l.lock();
        try {

            int tipus = id % 2;
            while (this.genere[tipus] == 1) {
                this.ocupat[tipus].awaitUninterruptibly();
            }
            this.genere[tipus]++;

            while (this.genere[1 - tipus] == 0) {

                this.idAltre = id;
                this.parella[tipus].awaitUninterruptibly();
                this.genere[tipus] = 0;
            }

            if (this.genere[tipus] != 0 && this.genere[1 - tipus] != 0) {

                System.out.print("Nova parella: (" + id + "," + this.idAltre + ")");
                this.parella[1 - tipus].signal();
                this.idAltre = id;

            } else {

                System.out.println("->(" + id + "," + this.idAltre + ")");
                this.genere[1 - tipus] = 0;
                this.ocupat[tipus].signalAll();
                this.ocupat[1 - tipus].signalAll();
            }

            return this.idAltre;

        } finally {
            l.unlock();
        }
    }
}
