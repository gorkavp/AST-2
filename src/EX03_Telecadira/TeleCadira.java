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
            //...
        } finally {
            l.unlock();
        }
    }

    public void seure(int id) {
        l.lock();
        try {
            //...
        } finally {
            l.unlock();
        }
    }
}
