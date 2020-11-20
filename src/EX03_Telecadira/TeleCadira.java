package EX03_Telecadira;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TeleCadira {

    private final ReentrantLock l = new ReentrantLock();
    private final Condition lliure = l.newCondition();
    private final Condition altres = l.newCondition();
    private final Condition totsDins = l.newCondition();

    private final int C;

    private int dins;
    private int esp;
    private int acabat;
    private String str;

    public TeleCadira(int C) {
        this.C = C;
        dins = 0;
        str = "";
    }

    public void esperar(int id) {
        l.lock();
        try {

            this.esp++;

            while (this.esp != this.C) {

                this.str += id + ", ";
                this.lliure.awaitUninterruptibly();
            }

            while (this.esp == this.C) {

                this.altres.awaitUninterruptibly();
            }

            if (this.esp == this.C) {

                this.esp = 0;
                this.lliure.signalAll();

            }

        } finally {
            l.unlock();
        }
    }

    public void seure(int id) {
        l.lock();
        try {

            this.dins++;
            while (this.dins != this.C) {
                this.totsDins.awaitUninterruptibly();
            }

            if (this.dins == this.C) {

                System.out.println("surt cadira: [" + this.str + "]");
                this.dins = 0;
                this.totsDins.awaitUninterruptibly();
            }

        } finally {
            l.unlock();
        }
    }
}
