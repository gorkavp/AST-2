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

            while (this.esp >= this.C) {

                this.altres.awaitUninterruptibly();
            }

            this.esp++;

            while (this.esp < this.C) {

                this.lliure.awaitUninterruptibly();
            }

            this.acabat++;

            if (this.acabat < this.C) {

                this.lliure.signal();
            }

        } finally {
            l.unlock();
        }
    }

    public void seure(int id) {
        l.lock();
        try {

            this.dins++;
            
            while (this.dins < this.C) {
                this.str += id + ", ";
                this.totsDins.awaitUninterruptibly();
            }

            if (this.dins != this.C) {
                this.totsDins.signal();

            } else {

                this.str += id;
                System.out.println("surt cadira: [" + this.str + "]");
                this.dins = 0;
                this.esp = 0;
                this.acabat = 0;
                this.str = "";
                this.altres.signalAll();
            }

        } finally {
            l.unlock();
        }
    }
}
