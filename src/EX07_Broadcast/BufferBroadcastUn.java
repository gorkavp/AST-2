package EX07_Broadcast;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Gorka
 */
public class BufferBroadcastUn {

    protected Object espai;

    private final ReentrantLock l;
    private final Condition p;
    private final Condition c;

    private final int N;

    protected boolean[] disponible;

    public BufferBroadcastUn(int n) {

        this.N = n;
        this.l = new ReentrantLock();
        this.c = l.newCondition();
        this.p = l.newCondition();
        
        for (int i = 0; i < N; i++) {
            this.disponible[i] = false;
        }
    }

    public void putValue(Object v) {

        l.lock();
        try {

            for (int i = 0; i < N; i++) {

                while (!this.disponible[i]) {

                    p.awaitUninterruptibly();
                }
            }

            this.espai = v;

            for (int i = 0; i < N; i++) {

                this.disponible[i] = true;
            }

            c.signalAll();

        } finally {
            l.unlock();
        }
    }

    public Object getValue(int id) {

        l.lock();
        try {

            while (!this.disponible[id]) {

                c.awaitUninterruptibly();
            }

            this.disponible[id] = false;
            p.signal();
            return this.espai;

        } finally {
            l.unlock();
        }
    }
}
