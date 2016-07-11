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
 * @author alberto
 */
public class Napakalaki {
    private Player currentPlayer;
    private ArrayList<Player> players;
    private Monster currentMonster;
    private CardDealer dealer;
    
    private Napakalaki() {
        currentMonster = null;
        currentPlayer = null;
        players = null;
        dealer = CardDealer.getInstance();
    }
    
    public ArrayList<Monster> getAllMonster(){
        return dealer.getAllMonster();
    }
    
    /**
     * Crea una unica instancia del objeto
     * @return La instancia creada
     */
    public static Napakalaki getInstance() {
        return NapakalakiHolder.INSTANCE;
    }
    
    private static class NapakalakiHolder {

        private static final Napakalaki INSTANCE = new Napakalaki();
    }
    
    /**
     * Inicializa el array @players con tantos jugadores como elementos haya en name
     * @param names contiene los nombres de los jugadores
     */
    
    /** EXAMEN **/
    private void initPlayers(ArrayList<String> names ) {
        players =  new ArrayList<Player>();
        
        if(names.size() > 0)
        players.add(new PlayerBlindado(names.get(0)));
        for (int i = 1; i < names.size(); i++) {
            players.add(new Player(names.get(i)));
        }        
    }
    
    /** FIN EXAMEN **/
    
   /**
    * Decide que jugador es el siguiente en jugar
    * @return el siguiente jugador
    */
    private Player nextPlayer() {
        Random rand = new Random();
        int pos;
        //primera vez
        if(currentPlayer == null)
        {
            pos = rand.nextInt(players.size());
        }
        else{
            pos = players.indexOf(currentPlayer);
            pos++;
            if(pos >= players.size()) pos = 0;
        }
        
        return players.get(pos);
    }
    
    /**
     * Comprueba si el jugador actual cumple con las reglas
     * @return Si puede pasar el turno
     */
    private boolean nextTurnAllowed() {
        if(currentPlayer == null) return true;  //jugador actual no actualizado
        else return currentPlayer.validState();
    }
    
    /**
     * Asigna un enemigo a cada jugador
     */
    private void setEnemies() {
        Random rand = new Random();//creamos una instancia de Random 
        int posAleatoria;
        for (Player player : players) {
            do{
                 posAleatoria = rand.nextInt(players.size());
            }while(posAleatoria == players.indexOf(player));    //el enemigo de un jugador no puede ser el mismo
            
            player.setEnemy(players.get(posAleatoria));
        }
    }
    
    /**
     * Desarrollo de un combate
     * @return resultado del combate
     */
    
    /** EXAMEN  **/
    public CombatResult developCombat() { 
        CombatResult result = currentPlayer.combat(currentMonster);
        CultistPlayer cultist;
        
        if(result == CombatResult.LOSEANDCONVERT && !(currentPlayer instanceof PlayerBlindado)){
            cultist = new CultistPlayer(currentPlayer, dealer.nextCultist());
            players.set(players.indexOf(currentPlayer) , cultist);
            for(Player p : players){
                if(p.getEnemy() == currentPlayer){
                    p.setEnemy(cultist);
                }
            }
            
            currentPlayer = cultist;
        }
        dealer.giveMonsterBack(currentMonster);
        return result;
    }
    
    /** FIN DE EXAMEN **/
    /**
     * Eliminar los tesoros visibles de la lista de tesoros visibles del jugador
     * @param treasures lista de tesoros visibles
     */
    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            currentPlayer.discardVisibleTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    /**
     * Eliminar los tesoros ocultos de la lista de tesoros visibles del jugador
     * @param treasures lista de tesoros ocultos
     */
    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            currentPlayer.discardHiddenTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    /**
     * Funcion para pasar tesoros ocultos a visibles
     * @param treasures de los tesoros ocultos a pasar.
     */
    public void makeTreasuresVisible(ArrayList<Treasure> treasures ) {
        for (Treasure treasure : treasures) {
            currentPlayer.makeTreasureVisible(treasure);
        }
    }
    
    /**
     * Inicializacio del juego
     * @param players listado de nombres de los jugadores
     */
    public void initGame(ArrayList<String> players) {
        this.initPlayers(players);
        this.setEnemies();
        dealer.initCards();
        this.nextTurn();
    }
    
    /**
     * Devuelve el jugador actual
     * @return el jugador actual
     */
    public Player getCurrentPlayer() { return currentPlayer; }
    
    /**
     * Devuelve el monstruo actual
     * @return el monstruo actual
     */
    public Monster getCurrentMonster() {return currentMonster; }
    
    
    public boolean nextTurn() {
        boolean stateOK = this.nextTurnAllowed();

        if(stateOK){
            currentMonster = dealer.nextMonster();
            currentPlayer = this.nextPlayer();
            if(currentPlayer.isDead()){
                currentPlayer.initTreasures();
            }
        }
        
        return stateOK;
    }
    
    /**
     * Comprueba el fin del juego
     * @param result Resultado del combate
     * @return si se ha acabado el juego.
     */
    public boolean endOfGame(CombatResult result) {
        return (result == CombatResult.WINGAME);
    }
}
