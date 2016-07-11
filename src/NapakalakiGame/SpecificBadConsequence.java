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
public class SpecificBadConsequence extends BadConsequence{

    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    public SpecificBadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden)
    {
        super(text, levels);
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;
    }

    @Override
    public boolean isEmpty() {
        return specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty();
    }

    @Override
    public void substractVisibleTreasure(Treasure t) {
        this.specificVisibleTreasures.remove(t.getType());
    }

    @Override
    public void substractHiddenTreasure(Treasure t) {
        this.specificHiddenTreasures.remove(t.getType());
    }

    @Override
    public SpecificBadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        SpecificBadConsequence sbc = null;
        ArrayList<TreasureKind> vEliminar = new ArrayList<>();
        ArrayList<TreasureKind> hEliminar = new ArrayList<>();
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
        
        sbc = new SpecificBadConsequence("", 0, vEliminar, hEliminar);
        return sbc;
    }
    
    @Override
    public String toString(){
        
        String response = "";
        if(!specificVisibleTreasures.isEmpty() || specificHiddenTreasures.isEmpty())
            response = "Pierdes: ";
        
        if (!specificVisibleTreasures.isEmpty()){
            response += " Tesoros visibles que pierdes: ";
            for (TreasureKind specificVisibleTreasure : specificVisibleTreasures) {
                response += specificVisibleTreasure + " ";
            }
        }

        if (!specificHiddenTreasures.isEmpty()){
            response += ", Tesoros ocultos que pierdes: ";
            for (TreasureKind specificHiddenTreasure : specificHiddenTreasures) {
                response += specificHiddenTreasure + " ";
            }
        }
        return response;
    }
}
