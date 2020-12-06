package EX04_Aeroport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ElPrat {

    private final int N;
    private int pistaAct = -1;
    private int numEnlairant = 0;
    private final ReentrantLock l = new ReentrantLock();
    private final Condition espEnl = l.newCondition();
    private String ordreArr = "";
    private String ordreEnl = "";

    public ElPrat(int N) {
        this.N = N;
    }

    public void permisEnlairar(int numPista) {

        l.lock();
        try {

            ordreArr = ordreArr + numPista;

            if (this.numEnlairant == 0) {
                
                this.pistaAct = numPista;
            }

            while (this.pistaAct != numPista && this.pistaAct != -1) {

                this.espEnl.awaitUninterruptibly();
            }

            if (this.pistaAct == numPista) {

                this.numEnlairant++;
                ordreEnl = ordreEnl + numPista;

            } else {

                this.numEnlairant++;
                ordreEnl = ordreEnl + numPista;
                this.pistaAct = numPista;

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
                this.espEnl.signalAll();
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
