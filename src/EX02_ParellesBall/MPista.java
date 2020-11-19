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
            return 0;
        } finally {
            l.unlock();
        }
    }

}
