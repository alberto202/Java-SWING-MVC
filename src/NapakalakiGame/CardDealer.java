/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Alberto
 */
public class CardDealer {
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Treasure> usedTreasures;
    private ArrayList<Cultist> unusedCultist;
    
    private CardDealer() {
    }
    
    public static CardDealer getInstance() {
        return CardDealerHolder.INSTANCE;
    }
    
    
    private static class CardDealerHolder {

        private static final CardDealer INSTANCE = new CardDealer();
    }
    
    private void initTreasureCardDeck(){
        unusedTreasures = new ArrayList<Treasure>();
        usedTreasures = new ArrayList<Treasure>();
        
        unusedTreasures.add(new Treasure("¡Si mi amo!", 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigacion", 3, TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia acida", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Casco minero", 4, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 1, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alopodo", 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistorica", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 4, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Insecticida", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Garabato mistico", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metalica", 2, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Lanzallamas", 4, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro comicon", 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicon", 5, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 3, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-gnomicon", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necro playboycon", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Porra preternatural", 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Shogulador", 1, TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentaculo de pega", 2, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 1, TreasureKind.SHOES));
        this.shuffleTreasures();
    }
    
    private void initMonsterCardDeck(){
         //Monstruo 1
        unusedMonsters = new ArrayList<Monster>();
        usedMonsters = new ArrayList<Monster>();
        
        BadConsequence badConcequence = new SpecificBadConsequence(
                "Pierdes tu armadura visible y otra oculta"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ARMOR))
                , new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, badConcequence, prize));
        
        //Monstruo 2
        badConcequence = new SpecificBadConsequence(
                "Embobados con el lindo primigenio te descartas de tu casco visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.HELMET))
                , new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, badConcequence, prize));
        
        //Monstruo 3
        badConcequence = new SpecificBadConsequence(
                "El primordial bostezo contagioso. Pierdes el calzado visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.SHOES))
                , new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor Dunwich", 2, badConcequence, prize));    
        
        //Monstruo 4
        badConcequence = new SpecificBadConsequence(
                "Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ONEHAND))
                , new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 14, badConcequence, prize));    
        
        //Monstruo 5
        badConcequence = new NumericBadConsequence(
                "Pierdes todos tus tesoros visibles"
                , 0
                , BadConsequence.MAXTREASURES
                , 0);
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El gorron en el umbral", 10, badConcequence, prize));    
        
        //Monstruo 6
        badConcequence = new SpecificBadConsequence(
                "Pierdes la armadura visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ARMOR))
                , new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, badConcequence, prize)); 
        
        //Monstruo 7
        badConcequence = new SpecificBadConsequence(
                "Sientes bichos bajo la ropa. Descarta la armadura visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ARMOR))
                , new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bichgooth", 2, badConcequence, prize)); 
        
        //Monstruo 8
        badConcequence = new NumericBadConsequence(
                "Pierdes 5 niveles y 3 tesoros visibles"
                , 5
                , 3
                , 0);
        prize = new Prize(4, 2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, badConcequence, prize)); 
        
        //Monstruo 9
        badConcequence = new NumericBadConsequence(
                "Toses los pulmones y pierdes 2 niveles."
                , 2
                , 0
                , 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas", 2, badConcequence, prize)); 
        
        //Monstruo 10
        badConcequence = new DeathBadConsecuence(
                "Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto");
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, badConcequence, prize));
        
        //Monstruo 11
        badConcequence = new NumericBadConsequence(
                "Pierdes 2 niveles y 2 tesoros ocultos."
                , 2
                , 0
                , 2);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, badConcequence, prize));
        
        //Monstruo 12
        badConcequence = new SpecificBadConsequence(
                "Te intentas escaquear. Pierdes una mano visible."
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ONEHAND))
                , new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, badConcequence, prize));
        
        //Monstruo 13
        badConcequence = new NumericBadConsequence(
                "Da mucho asquito. Pierdes 3 niveles"
                , 3
                , 0
                , 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, badConcequence, prize));
        
        //Monstruo 14
        badConcequence = new DeathBadConsecuence(
                "No le hace gracia que pronuncien mal su nombre. Estas muerto");
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("YskhtihyssgGoth", 12, badConcequence, prize));
        
        //Monstruo 15
        badConcequence = new DeathBadConsecuence(
                "La familia te atrapa. Estás muerto.");
        prize = new Prize(4, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, badConcequence, prize));
        
        //Monstruo 16
        badConcequence = new SpecificBadConsequence(
                "La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible"
                , 2
                , new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS))
                , new ArrayList());
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, badConcequence, prize));
        
        //Monstruo 17
        badConcequence = new SpecificBadConsequence(
                "Te asusta en la noche. Pierdes un casco visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.HELMET))
                , new ArrayList());
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia", 5, badConcequence, prize)); 
        
        //Monstruo 18
        badConcequence = new NumericBadConsequence(
                "Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles."
                , 2
                , 5
                , 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("El lenguas", 20, badConcequence, prize)); 
        
        //Monstruo 19
        badConcequence = new NumericBadConsequence(
                "Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos."
                , 3
                , BadConsequence.MAXTREASURES
                , 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bicefalo", 20, badConcequence, prize)); 
        
        //Monstruo 20
       badConcequence = new SpecificBadConsequence(
                "Te asusta en la noche. Pierdes un casco visible"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.ONEHAND))
                , new ArrayList());
        prize = new Prize(3, 1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable", 20, badConcequence, prize, -2)); 
               
        //Monstruo 21
        badConcequence = new NumericBadConsequence(
                "Pierdes 3 tesoros visible"
                , 0
                , 3
                , 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Testigos Oculares", 6, badConcequence, prize, 2)); 
        
        //Monstruo 22
        badConcequence = new DeathBadConsecuence(
                "Hoy no es tu de suerte. Mueres");
        prize = new Prize(2, 5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, badConcequence, prize, 4)); 
        
        //Monstruo 22
        badConcequence = new NumericBadConsequence(
                "Tu gobierno te recorta dos niveles"
                , 2
                , 0
                , 0);
        prize = new Prize(2, 1);
        unusedMonsters.add(new Monster("Serpiente politico", 8, badConcequence, prize, -2)); 
        
        //Monstruo 23
        badConcequence = new SpecificBadConsequence(
                "Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas"
                , 0
                , new ArrayList(Arrays.asList(TreasureKind.HELMET, TreasureKind.ARMOR))
                , new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)));
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, badConcequence, prize, 5));
        
        //Monstruo 24
        badConcequence = new NumericBadConsequence(
                "Pierdes 2 niveles"
                , 2
                , 0
                , 0);
        prize = new Prize(4, 2);
        unusedMonsters.add(new Monster("Shoggoth", 16, badConcequence, prize, -4));
        
        //Monstruo 24
        badConcequence = new NumericBadConsequence(
                "Pintalabios negro. Pierdes 2 niveles"
                , 2
                , 0
                , 0);
        prize = new Prize(1, 1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, badConcequence, prize, -3));
        this.shuffleMonsters();
    }
    
    private void initCultistCardDeck(){
         //cartas de jugadores sectarios
        unusedCultist = new ArrayList<Cultist>();
        
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 1));
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 2));
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 1));
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 2));
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 1));
        unusedCultist.add(new Cultist("No puedes dejar de ser sectario", 1));
        
        Collections.shuffle(unusedCultist);
    }
    
    private void shuffleTreasures() {
         Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters() {
        Collections.shuffle(unusedMonsters);
    }
    
    private void shuffleCultist() {
        Collections.shuffle(unusedCultist);
    }
    
    /**
     * Devuelve el siguiente tesoro
     * @return el siguiente tesoro
     */
    public Treasure nextTreasure(){ 
        Treasure treasure;
        
        if(unusedTreasures.size() == 0){
            unusedTreasures = usedTreasures;
            usedTreasures.clear();
            this.shuffleTreasures();
        }
        
        treasure = unusedTreasures.get(0);
        unusedTreasures.remove(0);
        usedTreasures.add(treasure);
        return treasure;
    }
    
    public ArrayList<Monster> getAllMonster(){
        return unusedMonsters;
    }
    /**
     * Devuelve el siguiente monstruo
     * @return el siguiente monstruo
     */
    public Monster nextMonster(){
        Monster monster;
        
        if(unusedMonsters.size() == 0){
            unusedMonsters = usedMonsters;
            usedMonsters.clear();
            this.shuffleMonsters();
        }
        
        monster = unusedMonsters.get(0);
        unusedMonsters.remove(0);
        usedMonsters.add(monster);
        return monster;
    }
    
    /**
     * Devuelve el siguiente cultist
     * @return el siguiente monstruo
     */
    public Cultist nextCultist(){
        
        Cultist cultist = unusedCultist.get(0);
        unusedCultist.remove(0);
        return cultist;
    }
    
    public void giveTreasureBack(Treasure t){
        usedTreasures.add(t);
    }
    public void giveMonsterBack(Monster m){
        usedMonsters.add(m);
    }
    public void initCards(){
        initTreasureCardDeck();
        initMonsterCardDeck();
        initCultistCardDeck();
    }   
}
