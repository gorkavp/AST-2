package EX01MonitorGrups;


public class Test {

    public static void main(String[] args) {
        int[] G = {5,2};
        int F=100;
        MonitorGrups m = new MonitorGrups(G);

        for (int i = 0; i < F; i++) {
            new Fil(i, m).start();
        }
    }
}
