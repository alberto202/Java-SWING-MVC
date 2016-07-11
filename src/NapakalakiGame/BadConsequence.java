/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public abstract class BadConsequence {

    static int MAXTREASURES = 10;
    protected String text;
    protected int levels;
    
    public BadConsequence(String text, int levels)
    {
        this.text = text;
        this.levels = levels;
    }
    
    
    public int getLevels() {
        return levels;
    }
    
    public void setLevels(int n) {
        levels = n;
    }
    public abstract boolean isEmpty();
    
        
    /**
     * Actualiza el mal rollo para que el tesoro visible t no forme parte del mismo
     * @param t tesoso a substraer
     */
    public abstract void substractVisibleTreasure(Treasure t);
    /**
     * Actualiza el mal rollo para que el tesoro oculto t no forme parte del mismo
     * @param t tesoso a substraer
     */
    public abstract void substractHiddenTreasure(Treasure t);
    
    /**
     * 
     * @param v tesoros visibles de los que dispone el jugador
     * @param h tesoros ocultos de los que dispone el jugador
     * @return mal rollo a ajustar al cliente;
     */
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);
    
    @Override
    public String toString() {
        return "Mal rollo: " + this.text + " Niveles: " + this.levels;
    }
}
