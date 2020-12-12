package EX10_Dobles_mixtes_de_tenis;

/**
 *
 * @author Gorka
 */
public class Test {

    public static void main(String[] args) {

        int jugadors = 100;
        Doblesmixtes m = new Doblesmixtes();

        for (int i = 0; i < jugadors; i++) {

            new Thread(new Noia(m)).start();
            new Thread(new Noi(m)).start();
        }
    }
}
