package gorman.bettingassistant.model;

import org.ejml.simple.SimpleMatrix;
import org.w3c.dom.Attr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlgoTesting {
    public static void main(String[] args) {
        /*
        example chelsea liverpool. last matches -> 2 draws, 2 liverpool, 1 chelsea
        last chelsea -> 3 loses, 2 draws
        last liverpool -> 3 wins, 2 loses

        considering liverpool with chelsea as opponent
        (opponent ratio is opposite -> for name=win, opponentRatio is for losing)
         */
        AHP ahp = new AHP();
        Alternative win = new Alternative("win", 1.58, 0.6, 0.6, 0.4);
        Alternative draw = new Alternative("draw", 1.86, 0, 0.4, 0.4);
        Alternative lose = new Alternative("lose", 2.14, 0.4, 0, 0.2);

        ahp.addAlternative("win", win);
        ahp.addAlternative("draw", draw);
        ahp.addAlternative("lose", lose);

        ahp.createMatrixes();
    }
}
