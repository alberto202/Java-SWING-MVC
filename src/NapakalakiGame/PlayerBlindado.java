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
public class PlayerBlindado extends Player{

    public PlayerBlindado(String name) {
        super(name);
    }
    
    public boolean canMakeTreasureVisible(Treasure t){
        if(t.getType() == TreasureKind.ARMOR)
            return true;
        else return super.canMakeTreasureVisible(t);
    }
    
    @Override
    public void decrementLevelEnemy(ArrayList<Treasure> treasuresSelected){
        int total = 0;

        if(getcanIDownloadLevel()){
            for(Treasure t : treasuresSelected){
                if(t.getType() != TreasureKind.ARMOR)
                    total += t.getBonus();
                this.getHiddenTreasures().remove(t);
                //Eliminamos los tesoros de la mala consecuencia si los tiene
                this.discardHiddenTreasure(t);
            }

            if (total > 4 ) total = 4;
            this.setcanIDownloadLevel(false);

            enemy.decrementLevel(total);
        }
    }    
}
