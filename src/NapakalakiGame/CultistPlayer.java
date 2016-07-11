/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.Random;

/**
 *
 * @author alberto
 */
public class CultistPlayer extends Player{
    static private int TotalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c) {
        super(p);
        myCultistCard = c;
        TotalCultistPlayers += 1;
    }
    
    public int getCombatLevel()
    {
        return super.getCombatLevel() + myCultistCard.getGainedLevels() * TotalCultistPlayers;
    }
    
    protected int getOponentLevel(Monster m)
    {
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    protected boolean shouldConvert()
    {
        return false;
    }
    
    protected Treasure giveMeATreasure()
    {
        Random rand = new Random();
        Treasure treasure = null;
        
        if(this.getVisibleTreasures().size() > 0)
            treasure = this.getVisibleTreasures().get(rand.nextInt(this.getVisibleTreasures().size()));
        return treasure; 
    }
    
    protected boolean canYouGiveMeATreasure(){
        return !this.getVisibleTreasures().isEmpty();
    }
    
    public int getTotalCultistPlayers()
    {
        return TotalCultistPlayers;
    }
    
     @Override
    public String toString() {
        return "Cultlist Nombre: " + this.getName() + " Nivel: " + this.getCombatLevel();
    }
    
}
