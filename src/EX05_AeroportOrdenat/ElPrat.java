package EX05_AeroportOrdenat;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ElPrat {

    private final int N;
    private int pistaAct = -1;
    private int numEnlairant = 0;
    private final ReentrantLock l = new ReentrantLock();
    private final ArrayList<Condition> espEnl = new ArrayList<>();
    private String ordreArr = "";
    private String ordreEnl = "";

    public ElPrat(int N) {
        this.N = N;
    }

    public void permisEnlairar(int numPista) {
        l.lock();
        try {

            if (!this.espEnl.isEmpty() && this.pistaAct == -1) {
                this.espEnl.get(0).signal();
            }

            Condition c = null;

            while ((!this.espEnl.isEmpty() && c == null) || this.pistaAct != numPista) {

                if (c == null) {

                    ordreArr = ordreArr + numPista;
                    c = this.l.newCondition();
                    this.espEnl.add(c);
                }
                c.awaitUninterruptibly();

                if (this.numEnlairant == 0) {

                    this.pistaAct = numPista;
                }
            }

            if (c != null) {
                this.numEnlairant++;
                ordreEnl = ordreEnl + numPista;
                this.espEnl.remove(c);
                if (!this.espEnl.isEmpty()) {
                    this.espEnl.get(0).signal();
                }
            }
        } finally {
            l.unlock();
        }
    }

    public void fiEnlairar(int numPista) {
        l.lock();
        try {
            numEnlairant = numEnlairant - 1;
            if (numEnlairant == 0) {
                pistaAct = -1;
                ordreEnl = ordreEnl + "*";
                if (!this.espEnl.isEmpty()) {
                    this.espEnl.get(0).signal();
                }
            }
        } finally {
            l.unlock();
        }
    }

    public void mostrar() {
        System.out.println(ordreArr);
        String ordreEnlNet = ordreEnl.replace("*", "");
        System.out.println(ordreEnlNet);
        System.out.println(ordreEnl);
        if (ordreArr.equals(ordreEnlNet)) {
            System.out.println("S'han enlairat per ordre de petició");
        } else {
            System.out.println("NO s'han enlairat per ordre de petició");
        }
    }

}
