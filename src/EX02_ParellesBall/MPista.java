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
            this.idAltre = id; 

            while (this.genere[1 - tipus] == 0) {
                this.parella[tipus].awaitUninterruptibly();
            }

            if (this.genere[tipus] != 0 && this.genere[1 - tipus] != 0) {
                this.ocupat[1 - tipus].signal();
            }

            return this.idAltre;

        } finally {
            l.unlock();
        }
    }
}
