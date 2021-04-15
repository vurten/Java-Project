
import java.lang.*;
import java.util.*;


public class PokerHandAnalyzer extends Carte {

    private  List <Carte> tableauDeCinqCarte = new ArrayList<Carte>();
    private  final Carte carteJoker = new Carte("","JK");



    public  Hand analyzeHand(final String[] hand)  {
        if (hand.length != 5)
            throw new IllegalArgumentException("Wrong number of cards in hand.");

        Hand h = Hand.HighCard;
        creerTableauDeCinqCarte(hand);
        trierParNumero();

        if(royalFlush()){
            h = Hand.RoyalFlush;
        }else if (straightFlush()){
            h = Hand.StraightFlush;
        }else if (fourOfAKind()){
            h = Hand.FourOfAKind;
        }else if (fullHouse()){
            h = Hand.FullHouse;
        }else if (flush()){
            h = Hand.Flush;
        }else if (straight()){
            h = Hand.Straight;
        }else if (threeOfAKind()){
            h = Hand.ThreeOfAKind;
        }else if (twoPair()){
            h = Hand.TwoPair;
        }else if (pair()){
            h = Hand.Pair;
        }

        return h;
    }

    private int compteurDePair(){
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        int point = 0;
        int pointTemp = 0;
        String numCarte = "";
        int cptPair = 0;

        for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {

            if(!(tableauDeCinqCarte.get(i).numeroCarte.equals(
                    numCarte))) {
                for (int j = nbrJok; j < tableauDeCinqCarte.size(); j++) {

                    if (tableauDeCinqCarte.get(i).numeroCarte.equals(tableauDeCinqCarte.get(j).numeroCarte)) {
                        point++;
                        numCarte = tableauDeCinqCarte.get(i).numeroCarte;
                    }
                }
            }

            if (pointTemp < point && point < 3){
                pointTemp = point;
            }

            if(pointTemp == 2){
                cptPair++;
                pointTemp = 0;
            }
            point = 0;
        }

        return cptPair;
    }


    private  boolean twoPair(){
        boolean estTwoPair = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        int cptPair = compteurDePair();

        if (cptPair == 2 && nbrJok == 0 ){
            estTwoPair = true;
        }else if (cptPair == 1 && nbrJok == 1){
            estTwoPair =true;
        }

        return estTwoPair;
    }


    private  boolean straight(){
        boolean estStraight = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        String numTemp = numeroTemporaire(tableauDeCinqCarte);
        int indice = indice(numTemp);
        int point = 0;
        int pointA = 1;

        if(nbrJok == 0){

            for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {
                if(tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice))){
                indice++;
                point++;
                }
            }
        }else if(nbrJok == 1) {

            for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {
                if (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice+1))) {
                    indice++;
                    point++;
                }
            }
        }if(nbrJok == 2) {

            for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {
                if (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice+1)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(indice+2))) {
                    indice++;
                    point++;
                }
            }
        }

        if(tableauDeCinqCarte.get(nbrJok).numeroCarte.equals("A") ){
            for (int i = nbrJok+1; i < tableauDeCinqCarte.size(); i++) {
                if (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(10)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(11)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(12)) ||
                        tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(13))){
                    pointA++;
                }
            }
        }

        if(point == 5 || pointA == 5){
            estStraight = true;
        }else if((point == 4 && nbrJok == 1) || (pointA == 4 && nbrJok == 1)){
            estStraight = true;
        }else if ((point == 3 && nbrJok == 2) || (pointA == 3 && nbrJok == 2)){
            estStraight = true;
        }

        return estStraight;
    }


    private boolean flush(){
        boolean estFlush = false;
        int point = 0;
        int nbrJok = nbrJoker(tableauDeCinqCarte);

        for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {
            for (int j = nbrJok; j < tableauDeCinqCarte.size() ; j++) {
                if(tableauDeCinqCarte.get(i).typeCarte.equals(tableauDeCinqCarte.get(j).typeCarte)){
                    point++;
                }
            }
            if (point == 5){
                estFlush = true;
                break;
            }else if (point == 4 && nbrJok == 1){
                estFlush = true;
                break;
            }else if (point == 3 && nbrJok == 2){
                estFlush = true;
                break;
            }else {
                point = 0;
            }
        }

        return estFlush;
    }



    private boolean fullHouse(){
        boolean estFullHouse = false;

        int cptPair = compteurDePair();
        int nbrJok = nbrJoker(tableauDeCinqCarte);

        if ((cptPair == 2) && (nbrJok == 1)){

            estFullHouse = true;
        }else if (threeOfAKind() && pair()){

            estFullHouse = true;
        }

        return estFullHouse;
    }


    private boolean threeOfAKind(){
        boolean estThreeOfAKind = false;
        int point = 0;
        int pointTemp = 0;
        String numCarte = "";
        int nbrJok = nbrJoker(tableauDeCinqCarte);

            for (int i = nbrJok; i < tableauDeCinqCarte.size() ; i++) {

                if(!(tableauDeCinqCarte.get(i).numeroCarte.equals(
                        numCarte))) {
                    for (int j = nbrJok; j < tableauDeCinqCarte.size(); j++) {

                        if (tableauDeCinqCarte.get(i).numeroCarte.equals(tableauDeCinqCarte.get(j).numeroCarte)) {
                            point++;
                            numCarte = tableauDeCinqCarte.get(i).numeroCarte;
                        }
                    }
                }

                if (pointTemp < point){
                    pointTemp = point;
                }
                point = 0;
            }

        if (pointTemp == 3 && nbrJok == 0){
            estThreeOfAKind = true;
        }else if (pointTemp == 2 && nbrJok == 1){
            estThreeOfAKind =true;
        }else if (nbrJok == 2){
            estThreeOfAKind = true;
        }

        return estThreeOfAKind;
    }


    private boolean pair(){
        boolean estPair = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        int point = 0;
        int pointTemp = 0;
        String numCarte = "";

        for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {

            if(!(tableauDeCinqCarte.get(i).numeroCarte.equals(
                    numCarte))) {
                for (int j = nbrJok; j < tableauDeCinqCarte.size(); j++) {

                    if (tableauDeCinqCarte.get(i).numeroCarte.equals(tableauDeCinqCarte.get(j).numeroCarte)) {
                        point++;
                        numCarte = tableauDeCinqCarte.get(i).numeroCarte;
                    }
                }
            }

            if (pointTemp < point && point < 3){
                pointTemp = point;
            }
            point = 0;
        }

        if (pointTemp == 2 && nbrJok == 0 ){
            estPair = true;
        }else if (pointTemp == 1 && nbrJok == 1){
            estPair =true;
        }

        return estPair;
    }



    private boolean fourOfAKind() {
        boolean estFourOfAKind = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        String numTemp = numeroTemporaire(tableauDeCinqCarte);
        int point = 0;

        for (int i = nbrJok; i < tableauDeCinqCarte.size(); i++) {
            if (tableauDeCinqCarte.get(i).numeroCarte.equals(numTemp)) {
                point++;

                if (point == 4 && nbrJok == 0){
                    break;
                }else if (point == 3 && nbrJok == 1){
                    break;
                }else if (point == 2 && nbrJok == 2){
                    break;
                }
            }else {
                point = 0;
                point++;
            }
            numTemp = tableauDeCinqCarte.get(i).numeroCarte;
        }

        if (point == 4 && nbrJok == 0) {
            estFourOfAKind = true;
        } else if (point == 3 && nbrJok == 1) {
            estFourOfAKind = true;
        } else if (point == 2 && nbrJok == 2) {
            estFourOfAKind = true;
        }
        return estFourOfAKind;
    }



    private boolean straightFlush(){
        boolean estStraightFlush = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        String suitTemp = suitTemporaire(tableauDeCinqCarte);
        int point = 0;
        int j = 0;

        for (int i = 0; i < tableauDeCinqCarte.size(); i++) {
            if (!tableauDeCinqCarte.get(i).equals(carteJoker)){
                j = indice(tableauDeCinqCarte.get(i).numeroCarte);
                break;
            }
        }

        if(nbrJok == 0) {
            for (int i = 0; i < tableauDeCinqCarte.size(); i++) {
                if ((tableauDeCinqCarte.get(i).typeCarte.equals(suitTemp)) &&
                        (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j)))) {
                    point++;
                    j++;
                }
            }
            if (point == 5){
                estStraightFlush = true;
            }
        } else if(nbrJok == 1) {
            for (int i = 1; i < tableauDeCinqCarte.size(); i++) {
                if ((tableauDeCinqCarte.get(i).typeCarte.equals(suitTemp)) &&
                        (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j)) ||
                                tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j+1)))) {
                    point++;
                    j++;
                }
            }
            if (point == 4){
                estStraightFlush = true;
            }
        } else if(nbrJok == 2) {
            for (int i = 2; i < tableauDeCinqCarte.size(); i++) {
                if ((tableauDeCinqCarte.get(i).typeCarte.equals(suitTemp)) &&
                        (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j)) ||
                                tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j+1)) ||
                                tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(j+2)))) {
                    point++;
                    j++;
                }
            }
            if (point == 3){
                estStraightFlush = true;
            }
        }

        return estStraightFlush;
    }

    /**
     * cette methode prend une liste de carte trier et retourne la Suit de la carte du premier indice
     * @param List de carte trier du plus petit numero de carte au plus grand
     * @return Suit de la carte
     */
    private String suitTemporaire(List<Carte> c){
        String suitTemp = "";

        for (int i = 0; i <tableauDeCinqCarte.size() ; i++) {

            if (!(tableauDeCinqCarte.get(i).equals(carteJoker))){
                suitTemp = tableauDeCinqCarte.get(i).typeCarte;
                i = tableauDeCinqCarte.size();
            }
        }

        return suitTemp;
    }

    /**
     * cette methode prend une liste de carte trier et retourne le numer de la carte du premier indice
     * @param List de carte trier du plus petit numero de carte au plus grand
     * @return numero de la carte
     */
    private String numeroTemporaire(List<Carte> c){
        String numTemp = "";

        for (int i = 0; i <tableauDeCinqCarte.size() ; i++) {

            if (!(tableauDeCinqCarte.get(i).equals(carteJoker))){
                numTemp = tableauDeCinqCarte.get(i).numeroCarte;
                i = tableauDeCinqCarte.size();
            }
        }

        return numTemp;
    }


    private boolean royalFlush(){
        boolean estRoyalFlush = false;
        int nbrJok = nbrJoker(tableauDeCinqCarte);
        String suitTemp = suitTemporaire(tableauDeCinqCarte);
        int point = 0;

        for (int i = 0; i < tableauDeCinqCarte.size(); i++) {
            if ((tableauDeCinqCarte.get(i).typeCarte.equals(suitTemp)) &&
                    (tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(1)) ||
                            tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(10)) ||
                            tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(11)) ||
                            tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(12)) ||
                            tableauDeCinqCarte.get(i).numeroCarte.equals(tabNumeroCarte.get(13)))){
                point++;
            }
        }
        if (point == 5){
            estRoyalFlush = true;
        }else if (point == 4 && nbrJok == 1){
            estRoyalFlush = true;
        }else if (point == 3 && nbrJok == 2){
            estRoyalFlush = true;
        }

        return estRoyalFlush;
    }

    /**
     * cette methode fait un tri de la main du plus petit numero au plus grand
     */
    private void trierParNumero(){
        tableauDeCinqCarte.sort((Comparator.<Carte>
                comparingInt(carte1 -> carte1.indice(carte1.numeroCarte))
                .thenComparingInt(carte2 -> carte2.indice(carte2.numeroCarte))));
    }

    /**
     * cette methode prend une liste de carte trier et retourne le nombre de joker dans la liste
     * @param List de carte trier du plus petit numero de carte au plus grand
     * @return le nombre de joker dans la liste de carte
     */
    private int nbrJoker(List<Carte> c) {
        int cpt = 0;

        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).equals(carteJoker)){
                cpt++;
            }
        }

        return cpt;
    }


    /**
     * cette methode transforme les strings en entree en objet de carte
     * @param String[] les cartes dans la main en forme de string
     * @return les 5 objets Cartes qu on a dans la main
     */
    private void creerTableauDeCinqCarte(String[] hand){
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].equals("JK")){
                tableauDeCinqCarte.add(new Carte("",hand[i]));
            }else {
                tableauDeCinqCarte.add(new Carte(hand[i].charAt(0)+"", hand[i].charAt(1)+""));
            }
        }
    }
}
