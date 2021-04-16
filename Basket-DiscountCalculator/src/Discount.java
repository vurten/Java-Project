/**
 * Created by William Flageol on 2020-04-30.
 * Chaouki Mohamed CHAM27088802 2020-05-14.
 */
public class Discount {

    private int numeroRabais;
    private double pourcentageRabais;

    public Discount(int n, double p) {
        this.numeroRabais = n;
        this.pourcentageRabais = p;

    }


    /**
     * cette methode retourne vrai si le nombre de livres differents dans le panier est
     * supperieur ou egal au nombre de livres associe au discount
     * @param Basket un pannier
     * @return vrai pour savoir si on peut appliquer le rabais ou pas
     */
    public boolean canBeApplied(Basket b) {

        return b.howManyDifferent() >= numeroRabais;
    }



    /**
     * cette methode retourne le prix du panier incluant le rabais
     * @param double Prix de base
     * @return le prix de base * le nombre de livres * le rabais appliquer
     */
    public double apply(double basePrice) {

        return basePrice * numeroRabais * pourcentageRabais;
    }



    /**
     * cette methode supprime les livres payes du pannier
     * @param Basket un pannier
     * @return un pannier qui contient des livres non payes
     */
    public Basket removePaidBooks(Basket b) {

        return  b.removeDifferent(numeroRabais);
    }
}