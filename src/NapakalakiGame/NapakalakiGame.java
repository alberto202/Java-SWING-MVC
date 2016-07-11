
package NapakalakiGame;

import GUI.*;
import java.io.IOException;
import java.util.ArrayList;

public class NapakalakiGame {

    public static void main(String[] args) throws IOException {
      Napakalaki game = Napakalaki.getInstance();
      NapakalakiView napakalakiView = new NapakalakiView();
      Dice.createInstance(napakalakiView);
      PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
      ArrayList<String> names = namesCapture.getNames();
      game.initGame(names);
      napakalakiView.setNapakalaki(game);
      
      napakalakiView.setVisible(true);      
      
    }
}