package EX02_ParellesBall;

public class Fil extends Thread {

    private final MPista m;
    private final int id;
    private final int N;

    public Fil(MPista m, int N, int id) {
        this.m = m;
        this.id = id;
        this.N = N;
    }

    @Override
    public void run() {
        try {
            for (int j = 0; j < N; j++) {
                sleep(((int) (Math.random() * 10)));
                int parella = m.ballar(id);
                System.out.println("Nova parella: (" + this.id + "," + parella + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
