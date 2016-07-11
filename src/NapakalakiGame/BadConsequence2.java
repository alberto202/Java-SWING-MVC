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
public class BadConsequence2 {

    static int MAXTREASURES = 10;
    private String text;
    private int levels, nVisiblesTreasures, nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    public BadConsequence2(String text, int levels, int nVisible, int nHidden)
    {
        this.text = text;
        this.levels = levels;
        this.nVisiblesTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
    }
    
    public BadConsequence2(String text, boolean death)
    {
        this.text = text;
        this.death = death;
        this.levels = Player.MAXLEVEL;
        this.nVisiblesTreasures = MAXTREASURES;
        this.nHiddenTreasures = MAXTREASURES;
    }
 
    public BadConsequence2(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden)
    {
        this.text = text;
        this.levels = levels;
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;
    }
    
    public boolean isEmpty(){
        return levels == 0 && !death && nHiddenTreasures == 0 && nVisiblesTreasures == 0 && specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty();
    }

    public int getLevels() {
        return levels;
    }

    public int getNVisiblesTreasures() {
        return nVisiblesTreasures;
    }

    public int getNHiddenTreasures() {
        return nHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }
    
    /**
     * Actualiza el mal rollo para que el tesoro visible t no forme parte del mismo
     * @param t tesoso a substraer
     */
    public void substractVisibleTreasure(Treasure t) {
        this.specificVisibleTreasures.remove(t);
    }
    
    /**
     * Actualiza el mal rollo para que el tesoro oculto t no forme parte del mismo
     * @param t tesoso a substraer
     */
    public void substractHiddenTreasure(Treasure t) {
        this.specificHiddenTreasures.remove(t);
    }
    
    /**
     * 
     * @param v tesoros visibles de los que dispone el jugador
     * @param h tesoros ocultos de los que dispone el jugador
     * @return mal rollo a ajustar al cliente;
     */
    public BadConsequence2 adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence2 sbc = null;
        ArrayList<TreasureKind> vEliminar = new ArrayList<TreasureKind>();
        ArrayList<TreasureKind> hEliminar = new ArrayList<TreasureKind>();
        
        if(this.nHiddenTreasures > 0 || this.nVisiblesTreasures > 0){
            sbc = new BadConsequence2("", 0, Math.min(this.nVisiblesTreasures, v.size()), Math.min(this.nHiddenTreasures, h.size()));
        }
        else{
            for (Treasure treasure: v) {
                if(this.specificVisibleTreasures.indexOf(treasure.getType()) != -1){
                    vEliminar.add(treasure.getType());
                }
            }
            
            for (Treasure treasure: h) {
                if(this.specificHiddenTreasures.indexOf(treasure.getType()) != -1){
                    hEliminar.add(treasure.getType());
                }
            }
            sbc = new BadConsequence2("", 0, vEliminar, hEliminar);
        }
        
        return sbc;
    }
    
    @Override
    public String toString() {
        return "Mal rollo: " + this.text + " Niveles: " + this.levels + " Muerte: " + this.death;
    }
}
