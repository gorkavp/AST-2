package EX10_Dobles_mixtes_de_tenis;

/**
 *
 * @author Gorka
 */
public class Noia implements Runnable {
    
    protected Doblesmixtes dobles;
    
    public Noia (Doblesmixtes db) {
        
        this.dobles = db;   
    }
    
    public void run() {
        
        dobles.noiaPreparada();
    }
}

