package EX02_ParellesBall;

public class Test {

    public static void main(String[] args) {

        int M = 50;
        int N = 2;

        MPista m = new MPista();

        Fil[] fil = new Fil[M];
        for (int i = 0; i < M; i++) {
            fil[i] = new Fil(m, N, i);
            fil[i].start();
        }

        try {
            for (int i = 0; i < M; i++) {
                fil[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fi simulació.");
    }
}
