/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Random;
import GUI.Dice;

/**
 *
 * @author Alberto
 */
public class Player {
    static int MAXLEVEL = 10;
    private String name;
    private int level = 1;
    private boolean dead = true;
    private boolean canISteal = true;
    private boolean canIDownloadLevel = true;
    private int leveltotalDecrement = 0;
            
    private ArrayList<Treasure> visibleTreasures;
    private ArrayList<Treasure> hiddenTreasures;
    protected Player enemy;
    private BadConsequence pendingBadConsequence;
    
    public Player(String name)
    {
        this.name = name;
        this.level = 1;
        visibleTreasures = new ArrayList<Treasure>();
        hiddenTreasures = new ArrayList<Treasure>();
        pendingBadConsequence = new NumericBadConsequence("",0, 0, 0);
    }
    
    public Player(Player p)
    {
        this.name = p.name;
        this.level = p.level;
        this.visibleTreasures = p.visibleTreasures;
        this.hiddenTreasures = p.hiddenTreasures;
        this.dead = p.dead;
        this.canISteal = p.canISteal;
        this.pendingBadConsequence = p.pendingBadConsequence;     
        this.enemy = p.enemy;
        this.canIDownloadLevel = p.canIDownloadLevel;
        this.leveltotalDecrement = p.leveltotalDecrement;
    }
    
    private void bringToLife(){
        dead = false;
    }
    
     /** EXAMEN  **/
        public boolean getcanIDownloadLevel(){
            return canIDownloadLevel;
        }
        
        public void setcanIDownloadLevel(boolean valor){
            canIDownloadLevel = valor;
        }
        
        public void decrementLevelEnemy(ArrayList<Treasure> treasuresSelected){
            int total = 0;
            if(canIDownloadLevel){
                for(Treasure t : treasuresSelected){
                    total += t.getBonus();
                    hiddenTreasures.remove(t);
                    //Eliminamos los tesoros de la mala consecuencia si los tiene
                    this.discardHiddenTreasure(t);
                }
                
                if (total > 4 ) total = 4;
                canIDownloadLevel = false;
                
                enemy.decrementLevel(total);
            }
        }
        
        public void decrementLevel(int total){
            leveltotalDecrement = total;
            level -= leveltotalDecrement;
            if(level < 1) level = 1;
        }
        
   
       
     /** FIN DE EXAMEN **/
        
    public int getCombatLevel(){
       int level_total = level;
       for(Treasure t:visibleTreasures )
       {
           level_total += t.getBonus();
       }

       return level_total;
   }
        
    public void incrementLevels(int i){
        level += i;
        
        if(level > MAXLEVEL)
            level = MAXLEVEL;
    }
    
    public void decrementLevels(int i){
     level -= i;
        
        if(level < 1)
            level = 1;
    }
    
    public void setPandingBadConcequence(BadConsequence b){
        pendingBadConsequence = b;
    }
    
    public BadConsequence getPandingBadConcequence(){
        return pendingBadConsequence;
    }
    /**
     * Funcion encargada de aplicar el buen rollo del monstruo
     * @param m monstruo
     */
    public void applyPrize(Monster m){
        Treasure treasure;
        int nLevels = m.getLevelsGained();
        incrementLevels(nLevels);
        
        int nTreasures = m.getTreasuresGained();
        if(nTreasures > 0){
            CardDealer dealer = CardDealer.getInstance();
            for (int i=0; i<nTreasures; i++)
            {
                treasure = dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }
        
    }
    
    /**
     * Funcion encargada de aplicar el mal rollo del monstruo
     * @param m monstruo
     */
    public void applyBadConsequence(Monster m){
        BadConsequence badconcequence = m.getBadConsequence();
        int nLevels = badconcequence.getLevels();
        
        decrementLevels(nLevels);
        
        BadConsequence pendingBad = badconcequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        pendingBad.setLevels(0);
        setPandingBadConcequence(pendingBad);
    }
    
    /**
     * Comprueba si un tesoro se puede pasar de oculto a visible
     * @param t es el tesoro oculto
     * @return si es posible
     */
    public boolean canMakeTreasureVisible(Treasure t){
        int element = this.howManyVisibleTreasures(t.getType());
        boolean iCan = false;
        if(element == 2 || hiddenTreasures.indexOf(t) == -1)    //Numero maximo de elementos o tesoro no existe en oculto
            iCan = false;
        else{
            switch(element){
                case 0:
                    if(t.getType() != TreasureKind.BOTHHANDS && t.getType() != TreasureKind.ONEHAND )
                        iCan = true;
                    else if(t.getType() == TreasureKind.BOTHHANDS)
                        iCan = this.howManyVisibleTreasures(TreasureKind.ONEHAND) == 0;
                    else if(t.getType() == TreasureKind.ONEHAND)
                        iCan = this.howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 0;
                    break;
                case 1:
                    if(t.getType() != TreasureKind.ONEHAND )
                        iCan = false;
                    else if(t.getType() == TreasureKind.ONEHAND)
                        iCan = this.howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 0;
                    break;
            }
        }
        return iCan;
    }
    
    public int howManyVisibleTreasures(TreasureKind tKind){
        int totalKind = 0;
        for(Treasure t:visibleTreasures){
            if(t.getType() == tKind) totalKind++;
        }
        return totalKind;
    }
    
    public void dieIfNoTreasures(){
        if(visibleTreasures.isEmpty() && hiddenTreasures.isEmpty())
            dead = true;
    }
    
    /**
     * Devuelve un tesoro elegido al azar de los tesoros ocultos
     * @return 
     */
    protected Treasure giveMeATreasure() {
        Random rand = new Random();
        Treasure treasure = null;
        
        if(hiddenTreasures.size() > 0){
            treasure = hiddenTreasures.get(rand.nextInt(hiddenTreasures.size()));
            hiddenTreasures.remove(treasure);
        }
        return treasure; 
    }
        
    protected boolean canYouGiveMeATreasure() {
        return !hiddenTreasures.isEmpty() && !visibleTreasures.isEmpty();
    }
    public void haveStolen() {
        this.canISteal = false;
    }

    public String getName(){ return this.name; }
    public boolean isDead() {return dead;}
    public ArrayList<Treasure> getHiddenTreasures() {return hiddenTreasures;   }
    public ArrayList<Treasure> getVisibleTreasures() {return visibleTreasures;   }
    
    public CombatResult combat(Monster m) {
        CombatResult result;
        if(this.getOponentLevel(m) < this.getCombatLevel())
        {
            this.applyPrize(m);
            if(this.level >= MAXLEVEL) 
                result = CombatResult.WINGAME;
            else
                result = CombatResult.WIN;
        }else{  
            this.applyBadConsequence(m);
            if(this.shouldConvert()){
                result = CombatResult.LOSEANDCONVERT;
            }
            else{
                result = CombatResult.LOSE;
            }
        }
        return result;
    }
    
    public void makeTreasureVisible(Treasure t) {
        if(this.canMakeTreasureVisible(t)){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }
    
    
    public void discardVisibleTreasure(Treasure t) {
        visibleTreasures.remove(t);
        if(pendingBadConsequence != null || !pendingBadConsequence.isEmpty()){
            pendingBadConsequence.substractVisibleTreasure(t);
        }
        this.dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t) {
        hiddenTreasures.remove(t);
        if(pendingBadConsequence != null || !pendingBadConsequence.isEmpty()){
            pendingBadConsequence.substractHiddenTreasure(t);
        }
        this.dieIfNoTreasures();
    }
    public boolean validState() {        
        return pendingBadConsequence != null || (pendingBadConsequence.isEmpty() && hiddenTreasures.size() <= 4);
    }
    
    /**
     * Cuando un jugador esta sin tesoros o en su primer turno 
     * hay que proporcionarle tesoros.
     */
    public void initTreasures() {
        CardDealer dealer = CardDealer.getInstance();
        Dice dice = Dice.getInstance();
        Treasure treasure = null;
        int number;
        
        this.bringToLife();
        treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);
        number = dice.nextNumber();
        if(number > 1){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        if(number == 6){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
    }
    
    public int getLevels() {return level;}
    
    /**
     * Robar un tesoro del enemigo
     * @return tesoro del enemigo
     */
    public Treasure stealTreasure() { 
        Treasure treasure = null;

        if(this.canISteal() && enemy.canYouGiveMeATreasure()){
            treasure = enemy.giveMeATreasure();
            this.hiddenTreasures.add(treasure);
            this.haveStolen();
        }
        return treasure;    
    }
    
    public Player getEnemy() {
        return this.enemy;
    }
    
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }
    public boolean canISteal() {
        return this.canISteal;
    }
    
    /**
     * Descartarte de todos los tesoros.
     */
    public void discardAllTreasures() {
        ArrayList<Treasure> vt = new ArrayList<>(visibleTreasures);
        ArrayList<Treasure> ht = new ArrayList<>(hiddenTreasures);

        for (Treasure visibleTreasure : vt) {
            discardVisibleTreasure(visibleTreasure);
        }
        for (Treasure hiddenTreasure : ht) {
            discardHiddenTreasure(hiddenTreasure);
        }
    }
    
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    
    protected boolean shouldConvert(){
        boolean result = false;
        Dice dice = Dice.getInstance();
        if( dice.nextNumber() == 1){
            result = true;
        }
        return result;
    }
        
    @Override
    public String toString() {
        return "Nombre: " + this.name + " Nivel: " + this.level;
    }
    
}
