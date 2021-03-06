/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import NapakalakiGame.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alberto
 */
public class NapakalakiView extends javax.swing.JFrame {

    Napakalaki NapakalakiModel;
    Player PlayerModel;
    Monster monsterModel;
    /**
     * Creates new form NapakalakiView
     */
    public NapakalakiView() {
        initComponents();
    }
    
    public void setNapakalaki(Napakalaki game){
        NapakalakiModel = game;
        this.ajustarPropiedades();
        
        currentMonster.setMonster(game.getCurrentMonster());
        currentMonster.setVisible(false);
        btCombat.setEnabled(false);
        btNextTurn.setEnabled(false);
        currentPlayer.setPlayer(game.getCurrentPlayer());
        currentPlayer.setNapakalaki(game);
    }
       
    public void setPlayer(Player player){
        PlayerModel = player;
    }
    
    public void ajustarPropiedades(){
        ImageIcon image = new ImageIcon(getClass().getResource("img/icon.jpg"));
        ImageIcon monsterImg = new ImageIcon(getClass().getResource("/GUI/monsters/Bicefalo.jpg"));
        
        //SPGeneral.setDividerLocation(monsterImg.getIconWidth() + 5);
        //SPButtons.setDividerLocation(monsterImg.getIconHeight() + 1150);
        //System.out.println(monsterImg.getIconWidth() + " - " + monsterImg.getIconHeight());
        
        this.setTitle("Napakalaki");
        this.setIconImage(image.getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SPGeneral = new javax.swing.JSplitPane();
        SPButtons = new javax.swing.JSplitPane();
        currentMonster = new GUI.MonsterView();
        jPanel1 = new javax.swing.JPanel();
        btMeetToMonster = new javax.swing.JButton();
        btNextTurn = new javax.swing.JButton();
        btCombat = new javax.swing.JButton();
        currentPlayer = new GUI.PlayerView();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Napakalaki Game");
        setBackground(new java.awt.Color(254, 254, 254));

        SPGeneral.setDividerLocation(200);
        SPGeneral.setContinuousLayout(true);

        SPButtons.setDividerLocation(450);
        SPButtons.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        SPButtons.setContinuousLayout(true);
        SPButtons.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        currentMonster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        currentMonster.setMinimumSize(new java.awt.Dimension(50, 22));
        SPButtons.setLeftComponent(currentMonster);

        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        btMeetToMonster.setBackground(new java.awt.Color(238, 241, 254));
        btMeetToMonster.setForeground(new java.awt.Color(54, 63, 92));
        btMeetToMonster.setText("Meet to Monster");
        btMeetToMonster.setMaximumSize(new java.awt.Dimension(80, 23));
        btMeetToMonster.setMinimumSize(new java.awt.Dimension(80, 23));
        btMeetToMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMeetToMonsterActionPerformed(evt);
            }
        });
        jPanel1.add(btMeetToMonster, java.awt.BorderLayout.PAGE_START);

        btNextTurn.setBackground(new java.awt.Color(238, 241, 254));
        btNextTurn.setForeground(new java.awt.Color(54, 63, 92));
        btNextTurn.setText("Next turn");
        btNextTurn.setMaximumSize(new java.awt.Dimension(80, 23));
        btNextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNextTurnActionPerformed(evt);
            }
        });
        jPanel1.add(btNextTurn, java.awt.BorderLayout.PAGE_END);

        btCombat.setBackground(new java.awt.Color(238, 241, 254));
        btCombat.setForeground(new java.awt.Color(54, 63, 92));
        btCombat.setText("Combat");
        btCombat.setMaximumSize(new java.awt.Dimension(80, 23));
        btCombat.setMinimumSize(new java.awt.Dimension(80, 23));
        btCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCombatActionPerformed(evt);
            }
        });
        jPanel1.add(btCombat, java.awt.BorderLayout.CENTER);

        SPButtons.setBottomComponent(jPanel1);

        SPGeneral.setLeftComponent(SPButtons);

        currentPlayer.setNapakalaki(null);
        SPGeneral.setRightComponent(currentPlayer);

        getContentPane().add(SPGeneral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMeetToMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMeetToMonsterActionPerformed
        // TODO add your handling code here:
        currentMonster.setVisible(true);
        currentPlayer.visibleBtMakeTreasure(false);
        currentPlayer.visibleBtDiscardAllTreasure(false);
        currentPlayer.visibleBtDiscardTreasure(false);
        currentPlayer.visibleBtStealTreasure(false);
        btCombat.setEnabled(true);
        btNextTurn.setEnabled(false);
        btMeetToMonster.setEnabled(false);
        SPButtons.setDividerLocation(currentMonster.getHeight()+15);
    }//GEN-LAST:event_btMeetToMonsterActionPerformed

    private void btNextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNextTurnActionPerformed
        // TODO add your handling code here:
        //despues del combate permitimos descartes y robar un tesoro al enemigo
        
        if(NapakalakiModel.nextTurn()){
            currentPlayer.visibleBtMakeTreasure(true);
            currentPlayer.visibleBtDiscardAllTreasure(true);
            currentPlayer.visibleBtDiscardTreasure(true);
            currentPlayer.visibleBtStealTreasure(false);

            btCombat.setEnabled(false);
            btNextTurn.setEnabled(false);
            btMeetToMonster.setEnabled(true);
            
            currentMonster.setMonster(NapakalakiModel.getCurrentMonster());
            currentMonster.setVisible(false);
            btCombat.setEnabled(false);
            btNextTurn.setEnabled(false);
            currentPlayer.setPlayer(NapakalakiModel.getCurrentPlayer());
            currentPlayer.setNapakalaki(NapakalakiModel);
        }
        else{
            JOptionPane.showOptionDialog(
                this, "Imposible pasar el turno",
                "No es posible pasar el turno",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.ERROR_MESSAGE, 
                null,
                new Object[]{" Aceptar "},"aceptar");
        }
    }//GEN-LAST:event_btNextTurnActionPerformed

    private void btCombatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCombatActionPerformed
        // TODO add your handling code here:
        CombatResult resultado = NapakalakiModel.developCombat();
        String mensaje = "";
        switch(resultado){
            case WIN:
                mensaje = "Has ganado el combate";
                break;
            case WINGAME:
                mensaje = "Has ganado el juego";
                break;
            case LOSE:
                mensaje = "Has perdido el combate";
                break;
            case LOSEANDCONVERT:
                mensaje = "Has perdido el combate y te conviertes";
                break;
            default:
                break; 
        }
        
        JOptionPane.showOptionDialog(
                this, mensaje,
                "Resultado del combate",
                ((resultado == CombatResult.WIN || resultado == CombatResult.WINGAME) ? JOptionPane.OK_OPTION : JOptionPane.ERROR_MESSAGE),
                ((resultado == CombatResult.WIN || resultado == CombatResult.WINGAME) ? JOptionPane.OK_OPTION : JOptionPane.ERROR_MESSAGE), 
                null,
                new Object[]{" Aceptar "},"aceptar");
        
        PlayerModel = NapakalakiModel.getCurrentPlayer();
        currentPlayer.setPlayer(NapakalakiModel.getCurrentPlayer());
        
        //despues del combate permitimos descartes y robar un tesoro al enemigo
        
        currentPlayer.visibleBtMakeTreasure(true);
        currentPlayer.visibleBtDiscardAllTreasure(true);
        currentPlayer.visibleBtDiscardTreasure(true);
        currentPlayer.visibleBtStealTreasure(PlayerModel.canISteal());
        
        btCombat.setEnabled(false);
        btNextTurn.setEnabled(true);
        btMeetToMonster.setEnabled(false);
    }//GEN-LAST:event_btCombatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane SPButtons;
    private javax.swing.JSplitPane SPGeneral;
    private javax.swing.JButton btCombat;
    private javax.swing.JButton btMeetToMonster;
    private javax.swing.JButton btNextTurn;
    private GUI.MonsterView currentMonster;
    private GUI.PlayerView currentPlayer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
