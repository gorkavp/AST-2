package EX01MonitorGrups;


public class Fil extends Thread {

    private final int id;
    private final MonitorGrups m;
    private final int nTipus = 2;

    public Fil(int id, MonitorGrups m) {
        this.id = id;
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            m.entrar(id, id % nTipus);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException ex) {

            }
            m.sortir(id, id % nTipus);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException ex) {

            }
        }
    }

}
