
import java.util.*;

public class Carte {

    protected static final List <String> tabNumeroCarte = new ArrayList<String> (
            Arrays.asList ("","A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"));

    protected static final List <String> tabtTypeCarte = new ArrayList<String> (
            Arrays.asList ("H", "D", "S", "C", "JK"));

    protected String numeroCarte;
    protected String typeCarte;

    public Carte(){
    }


    /**
     * cette methode prend un numero de carte et le cherche dans un tableau de carte et retourne son indice
     * @param string le numero du discount
     * @return l'indice du tableau du numero de la carte dans le tableau de numero de carte
     */
    protected static int indice(String c){
        int x = 0;
        for (int i = 0; i < tabNumeroCarte.size(); i++) {
            if(c.equals(tabNumeroCarte.get(i))){
                x = i;
                i = tabNumeroCarte.size();
            }
        }
        return x;
    }



    public Carte(String num, String type){

        if (!(validerCarte(num, tabNumeroCarte))){
            throw new IllegalArgumentException("Illegal card number");
        }

        if (!(validerCarte(type, tabtTypeCarte))){
            throw new IllegalArgumentException("Illegal card type");
        }

        if ((num.equals("") && !(type.equals("JK"))) || (type.equals("JK")) && !(num.equals("")) ){
            throw new IllegalArgumentException("empty string in card number is used only with joker");
        }

        this.numeroCarte = num;
        this.typeCarte = type;
    }


    /**
     * cette methode verifie si c'est une carte valide
     * @param string une carte et une liste de carte
     * @return un true si la carte est une carte valide
     */
    protected boolean validerCarte (String carte,List <String> tabCarte){
        boolean estValide = false;

        for (int i = 0; i < tabCarte.size(); i++) {
            if(carte.equals(tabCarte.get(i))){
                estValide = true;
                i = tabCarte.size();
            }
        }

        return estValide;
    }


    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Carte))
            return false;

        Carte c = (Carte) o;

        return c.numeroCarte.equals(this.numeroCarte) && c.typeCarte.equals(this.typeCarte);
    }


    @Override
    public String toString(){
        return "Numero de Carte : " + numeroCarte + "\nType de Carte   : " + typeCarte;
    }

}
