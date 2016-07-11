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
public class DeathBadConsecuence extends NumericBadConsequence{

    public DeathBadConsecuence(String text) {
        super(text, Player.MAXLEVEL, MAXTREASURES, MAXTREASURES);
    }
    
    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        return new DeathBadConsecuence(text);
    }
    
    @Override
    public String toString(){
        return "Estás muerto. Pierdes todos tus niveles y todos tus tesoros.";
    }
}
