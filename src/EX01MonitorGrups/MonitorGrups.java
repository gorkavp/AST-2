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

            while (this.numGrup[tipus] >= this.G[tipus] || this.grupDins) {

                this.acabaGrup.awaitUninterruptibly();
            }

            this.numGrup[tipus]++;

            while (this.numGrup[tipus] < this.G[tipus]) {

                this.altres[tipus].awaitUninterruptibly();
            }

            this.numAcabats++;
            this.grupDins = true;

            if (this.numAcabats < this.G[tipus]) {

                this.dins += id + ", ";
                this.altres[tipus].signal();
                
            } else {
                
                this.dins += id;
            }
        } finally {
            l.unlock();
        }
    }

    public void sortir(int id, int tipus) {

        l.lock();
        try {
            this.numAcabats = this.numAcabats - 1;
            if (this.numAcabats == 0) {
                System.out.println("[" + this.dins + "]");
                this.grupDins = false;
                this.numGrup[tipus] = 0;
                this.dins = "";
                this.acabaGrup.signalAll();
            }
        } finally {
            l.unlock();
        }
    }
}
