package gorman.bettingassistant.algorithm;

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
        Alternative draw = new Alternative("draw", 1.86, 0.001, 0.4, 0.4);
        Alternative lose = new Alternative("lose", 2.14, 0.4, 0.001, 0.2);

        ahp.addAlternative(win);
        ahp.addAlternative(draw);
        ahp.addAlternative(lose);

        ahp.createMatrixes();
        ahp.createPrioritizationVectors();
        System.out.println(ahp.getBestAlternative());
    }
}
