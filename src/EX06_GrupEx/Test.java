package EX06_GrupEx;

/**
 *
 * @author Gorka
 */
public class Test extends Thread {
    
     @Override
    public void run() {
        int N = 1;  //número de cops que el fil vol accedir al recurs
        int M = 100; //Número de fils
        int C = 5;  //Número de fils que poden accedir al recurs alhora

        GroupEx b = new GroupEx(C);

        Fil[] fil = new Fil[M];
        for (int i = 0; i < M; i++) {
            fil[i] = new Fil(N, b);
            fil[i].start();
        }

        try {
            for (int i = 0; i < M; i++) {
                fil[i].join();
            }
        } catch (Exception e) {
        }
        System.out.println("fi simulació");
    }

    public static void main(String[] args) {
        new Test().start();
    }
    
}
