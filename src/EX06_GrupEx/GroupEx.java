package EX06_GrupEx;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Gorka
 */
public class GroupEx {

    private final ReentrantLock l = new ReentrantLock();
    private final Condition altres = l.newCondition();
    private final Condition nouGrup = l.newCondition();

    private final int C;

    private int numArribats;
    private int numAcabats;

    public GroupEx(int n) {

        this.C = n;
        this.numAcabats = 0;
        this.numArribats = 0;
    }

    public void enter() {

        l.lock();
        try {

            while (this.numArribats >= this.C) {

                this.nouGrup.awaitUninterruptibly();
            }

            this.numArribats++;

            while (this.numArribats < this.C) {

                this.altres.awaitUninterruptibly();
            }

            this.numAcabats++;

            if (this.numAcabats < this.C) {

                this.altres.signal();
                
            } else if (this.numAcabats == this.C) {
                
                System.out.println("Es permet accedir " + this.C + " fils i han accedit " + this.numAcabats + " fils");
            }

        } finally {
            l.unlock();
        }
    }

    public void exit() {

        l.lock();
        try {

            if (this.numAcabats == this.C) {

                this.numAcabats = 0;
                this.numArribats = 0;
                this.nouGrup.signalAll();
            }

        } finally {
            l.unlock();
        }
    }
}
