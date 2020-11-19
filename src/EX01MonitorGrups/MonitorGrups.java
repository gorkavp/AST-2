package EX01MonitorGrups;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorGrups {

    private final int[] G;
    private final ReentrantLock l = new ReentrantLock();
    private final Condition acabaGrup = l.newCondition();
    private final Condition[] altres;

    private boolean grupDins = false;
    private final int[] numGrup;
    private int numAcabats;

    private String dins = "";

    public MonitorGrups(int[] G) {
        this.G = G;
        numGrup = new int[G.length];
        altres = new Condition[G.length];
        for (int i = 0; i < G.length; i++) {
            altres[i] = l.newCondition();
        }
    }

    public void entrar(int id, int tipus) {

        l.lock();
        try {

            while (!this.grupDins) {
                if (tipus == 0) {
                    this.dins += id;
                } else {
                    this.dins += id;
                }
                this.numGrup[tipus]++;
                if (this.numGrup[tipus] < this.G[tipus]) {
                    this.altres[tipus].awaitUninterruptibly();
                } else {
                    this.grupDins = true;
                }
            }
            this.numGrup[tipus] = 0;
            this.numAcabats++;
            System.out.println(this.dins);

        } finally {
            l.unlock();
        }

    }

    public void sortir(int id, int tipus) {

        l.lock();
        try {
            this.grupDins = false;
            if (this.numAcabats == 0) {
                this.altres[tipus].signalAll();
            }

        } finally {
            l.unlock();
        }
    }
}
