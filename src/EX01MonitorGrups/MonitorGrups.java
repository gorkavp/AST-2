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
    private int[] numAcabats;

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
                this.numGrup[tipus]++;
                if (this.numGrup[tipus] < this.G[tipus]) {
                    this.altres[tipus].awaitUninterruptibly();
                    this.grupDins = true;
                } else {
                    this.grupDins = true;
                }
            }
            if (tipus == 0 ) {
                System.out.print(id - 8 + ", ");
                System.out.print(id - 6 + ", ");
                System.out.print(id - 4 + ", ");
                System.out.print(id - 2 + ", ");
                System.out.println(id + ", ");
            } else {
                System.out.println(id - 2 + ", " + id);
            }
            this.numGrup[tipus] = 0;
        } finally {
            l.unlock();
        }

    }

    public void sortir(int id, int tipus) {

        l.lock();
        try {
            this.grupDins = false;
            this.altres[tipus].signalAll();
        } finally {
            l.unlock();
        }
    }
}
