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
            //...
            ordreEnl = ordreEnl + numPista;
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
                //...
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
