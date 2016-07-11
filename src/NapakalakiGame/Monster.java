/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author Alberto
 */

public class Monster {

    
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsequence;
    private int levelChangeAgainstCultistPlayer;
    
    public Monster(String n, int l, BadConsequence b, Prize p)
    {
        this.name = n;
        this.combatLevel = l;
        this.badConsequence = b;
        this.prize = p;
        this.levelChangeAgainstCultistPlayer = 0;
    }
    
    public Monster(String n, int l, BadConsequence b, Prize p, int IC)
    {
        this.name = n;
        this.combatLevel = l;
        this.badConsequence = b;
        this.prize = p;
        this.levelChangeAgainstCultistPlayer = IC;
    }
    
    public String getName() {
        return name.toString();
    }

    public int getCombatLevel() {
        return combatLevel;
    }    
    
    public BadConsequence getBadConsequence() {
        return badConsequence;
    }
    
    public int getLevelsGained() {
        return prize.getLevel();
    }
    public int getTreasuresGained() {
        return prize.getTreasures();
    }
    
    public int getCombatLevelAgainstCultistPlayer()
    {
        return this.levelChangeAgainstCultistPlayer + this.combatLevel;
    }
    
    @Override
    public String toString() {
        return "Nombre: " + this.name + " Nivel: " + Integer.toString(this.combatLevel);
    }
}

