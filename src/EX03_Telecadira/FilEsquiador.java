package EX03_Telecadira;

public class FilEsquiador extends Thread {

    private final int N;
    private final TeleCadira t;
    private final int id;

    public FilEsquiador(int N, TeleCadira t, int id) {
        this.N = N;
        this.t = t;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            try {
                sleep(((int) (Math.random() * 100)));
                t.esperar(id);
                t.seure(id);
                sleep(10);
                //Aqui se suposa que arrriben a dalt
                //i baixa esquiant
            } catch (InterruptedException ex) {

            }
        }
    }
}
