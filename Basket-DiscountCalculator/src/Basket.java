import java.util.*;

/**
 * Created by William Flageol on 2020-04-30.
 * Chaouki Mohamed CHAM27088802 2020-05-14.
 */
public class Basket {

    private Book[] livres;

    public Basket(Book[] books) {
        this. livres = books;

    }

    /**
     * cette methode calcul le nombre de livres differents existant dans un pannier
     *
     * @return le nombre de livres differents
     */
    public long howManyDifferent() {

        Set<Book> nonDupliquer = new HashSet<Book>();

        for (Book livre : livres) {
            nonDupliquer.add(livre);
        }

        return nonDupliquer.size();
    }

    /**
     * cette methode retourne la longueur de livres total dans un pannier
     * @return le nombre de livres
     */
    public int howManyBooks() {

        return livres.length;
    }

    /**
     * cette methode verifie si le pannier est vide
     * @return vrai si vide sinon faux
     */
    public boolean isEmpty() {

        return livres.length == 0;
    }

    /**
     * cette methode fait un tri du pannier par occurence et met celui qui a le plus d'occurencre en premier
     * ensuite elle prend le numero du discount et enleve tous les livres differents
     * qui sont egaux au numero du discount et retourne un nouveau panier
     * @param int le numero du discount
     * @return un nouveau panier sans le nombre de livres differents qui match avec le numero de discount
     */
    public Basket removeDifferent(int nb) {
        List<Book> arrayLivres = new ArrayList<Book>();
        Collections.addAll(arrayLivres, livres);

        arrayLivres.sort(Comparator.comparing(i -> Collections.frequency(arrayLivres, i)).reversed());

        List<Book> arrayNonDupliquer = new ArrayList<Book>(trierParOccurence ());

        List<Book> bookSupprimer = new ArrayList<Book>();


        for (int i = 0; i < arrayNonDupliquer.size() && nb > 0; i++) {
            bookSupprimer.add(arrayNonDupliquer.get(i));
            nb--;
        }


        for (Book b: bookSupprimer) {
            if (arrayLivres.contains(b))
                arrayLivres.remove(b);
        }

        return new Basket(arrayLivres.toArray(new Book[arrayLivres.size()]));
    }


    /**
     * cette methode fait un tri du pannier par occurence. Le plus haut en premier,
     * et retourne une liste non duplique
     * @return un nouveau panier sans le nombre de livres differents qui match avec le numero de discount
     */
    private List<Book> trierParOccurence (){

        List<Book> arrayLivres = new ArrayList<Book>();
        Collections.addAll(arrayLivres, livres);

        arrayLivres.sort(Comparator.comparing(i -> Collections.frequency(arrayLivres, i)).reversed());

        Set<Book> nonDupliquer = new HashSet<Book>(arrayLivres);
        List<Book> arrayNonDupliquer = new ArrayList<Book>(nonDupliquer);

        List<Book> trierEtNonDupliquer = new ArrayList<Book>();


        for (int i = 0; i < arrayLivres.size() ; i++){

                for (int j = 0; j < arrayNonDupliquer.size(); j++) {

                    if ((arrayLivres.get(i)).equals(arrayNonDupliquer.get(j))) {

                        trierEtNonDupliquer.add(arrayLivres.get(i));
                        arrayNonDupliquer.remove(arrayNonDupliquer.get(j));
                    }
                }

        }

        return trierEtNonDupliquer;

    }

}
