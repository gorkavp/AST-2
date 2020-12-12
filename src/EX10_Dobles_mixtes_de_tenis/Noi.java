package EX10_Dobles_mixtes_de_tenis;

/**
 *
 * @author Gorka
 */
public class Noi implements Runnable {
    
    protected Doblesmixtes dobles;
    
    public Noi (Doblesmixtes db) {
        
        this.dobles = db;
    }
    
    public void run() {
        
        dobles.noiPreparat();
    }
}
