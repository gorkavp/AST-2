package EX03_Telecadira;

public class Test extends Thread {

    @Override
    public void run() {
        int N = 1;  //pujades que fa cada esquiador
        int M = 100; //Número d'esquiadors
        int C = 5;  //Places del telecadira

        TeleCadira b = new TeleCadira(C);

        FilEsquiador[] fil = new FilEsquiador[M];
        for (int i = 0; i < M; i++) {
            fil[i] = new FilEsquiador(N, b, i);
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
