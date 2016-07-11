/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class NumericBadConsequence extends BadConsequence{

    private int nVisiblesTreasures;
    private int nHiddenTreasures;
    
    public NumericBadConsequence(String text, int levels, int nVisible, int nHidden)
    {
        super(text, levels);
        this.nVisiblesTreasures = nVisible;
        this.nHiddenTreasures = nHidden;
    }

    @Override
    public boolean isEmpty() {
        return levels == 0 && nHiddenTreasures == 0 && nVisiblesTreasures == 0;
    }

    @Override
    public void substractVisibleTreasure(Treasure t) {
        if(nVisiblesTreasures > 0) this.nVisiblesTreasures--;
    }

    @Override
    public void substractHiddenTreasure(Treasure t) {
        if(nHiddenTreasures > 0) this.nHiddenTreasures--;
    }

    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        return new NumericBadConsequence(text, levels, Math.min(this.nVisiblesTreasures, v.size()), Math.min(this.nHiddenTreasures, h.size()));
    }
    
    @Override
    public String toString(){
        String response = "";
        
        if(nVisiblesTreasures > 0 || nHiddenTreasures > 0)
            response = "Pierdes: Tesoros Visibles: "+nVisiblesTreasures+", Tesoros Ocultos: "+nHiddenTreasures;
        
        return response;
    }
}
