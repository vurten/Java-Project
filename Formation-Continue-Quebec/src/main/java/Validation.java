/*
 * Copyright 2019 Khaled Zioual , Mohamed Chaouki , Okitapoy Koy Christian.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Validation {

    protected final String CLE_ORDRE = "ordre";
    protected final String CLE_NUM_PERMIS = "numero_de_permis";
    protected final String CLE_CYCLE = "cycle";
    protected final String CLE_HEURES_AN_PREC = "heures_transferees_du_cycle_precedent";
    protected final String CLE_ACIVITES = "activites";
    protected final String CLE_DESCRIPTION_ACIVITES = "description";
    protected final String CLE_CATEGORIE_ACIVITES = "categorie";
    protected final String CLE_HEURES_ACIVITES = "heures";
    protected final String CLE_DATE_ACIVITES = "date";
    protected final String CLE_NOM = "nom";
    protected final String CLE_PRENOM = "prenom";
    protected final String CLE_SEXE = "sexe";
    protected  final String ERR_MSG_NUM_PERMIS_INVALID = "Le numero de permis invalid.";
    protected  final String ERR_MSG_ORDRE_INVALID = "L'ordre n'est pas supporté.";
    protected  final String ERR_MSG_CYCLE_INVALID = "Le cycle n'est pas supporté.";
    protected String[] periode ;
    protected final String[] LISTE_ORDRES = new String[]{"architectes", "géologues", "psychologues","podiatres"};
    protected final String[] LISTE_ACTIVITES = new String[]{"cours", "atelier", "séminaire","colloque","conférence"
            , "lecture dirigée", "présentation","groupe de discussion","projet de recherche","rédaction professionnelle"};


    protected String ordre;
    protected String numeroPermis;
    protected String cycle;
    protected String nom;
    protected String prenom;
    protected int sexe;
    protected int heureAnneePrec;
    protected JSONArray tabloActivites;
    protected String pathFichierSortant;
    protected String pathFichierEntrant;

    protected int cours = 0;
    protected int atelier = 0;
    protected int seminaire = 0;
    protected int colloque = 0;
    protected int conference = 0;
    protected int lectureDirige = 0 ;
    protected int presentation = 0;
    protected int groupeDeDiscussion = 0;
    protected int projetDeRecherche = 0;
    protected int redactionProfessionnelle = 0;
    protected int totalActivites = 0;
    protected int nbrHeuresTotal;

    protected final int TAILLE_MIN_DESCRIPTION = 21;

    protected final int HEURES_ACTIVITES_MIN = 1;
    protected final int HEURES_PREC_MAX = 7;

    protected final String MSG_DEPASSE_HEURES_PREC = "Les heures du cycle"
            + " précédent dépassent le nombre maximum éligible."
            + " Seulement sept heures seront considérées.";

    protected final String ERR_MSG_JSON_INVALID = "Le fichier"
            + " JSON d'entrée est invalide et que le cycle est incomplet.";

    protected final String ERR_MSG_FICHIER_INTROUVABALE = "ERREUR!!! Le fichier JSON"
            + " est introuvable;";

    protected final String ERR_MSG_DEBUT = "L'activité : ";

    protected final String ERR_MSG_FIN_CATEGORIE = " est dans une catégorie non reconnue";

    protected final String ERR_MSG_FIN_DATE = " a été en dehors de l'intervalle "
            + "de la periode du cycle.";

    protected final String ERR_MSG_HEURES_TOT_CATEGORIE = "pour atteindre le min de 17h pour la liste des 6 catégories.";

    protected final String ERR_MSG_HEURES_TOT_COURS_GEOLOGUES = "pour atteindre le minimum de 22 heures pour les cours.";

    protected final String ERR_MSG_HEURES_TOT_PROJET_DE_RECHERCHE_GEOLOGUES = "pour atteindre le minimum de 3 heures "
            +"pour les projets de recherches.";
    protected final String ERR_MSG_HEURES_TOT_GROUPE_DE_DISCUSSION_GEOLOGUES = "pour atteindre le minimun de 1 heure "
            +"pour les groupes de discussion.";

    protected final String ERR_MSG_HEURES_TOT_COURS_PSYCOLOGUES = "pour atteindre le minimum de 25 heures pour les cours.";

    protected final String ERR_MSG_SEXE = "sexe non conforme a la norme ISO 5218.";

    protected final String ERR_MSG_NOM = "nom est vide.";

    protected final String ERR_MSG_PRENOM = "prenom est vide.";

    protected final String ERR_MSG_TAILLE_DESCRIPTION = " represente une description "
            + "courte qui ne depasse pas 20 caracteres.";

    protected final String ERR_MSG_FORMAT_DATE = " presente une date "
            + "qui ne respecte pas la norme ISO 8601.";

    protected final String ERR_MSG_HEURES_TRANSF = "Le champ heures_transferees_du_cycle_precedent "
            + "contient une valeur négative ou invalid";

    protected ArrayList<String> listeCategorieInvalide = new ArrayList<String>();



    /**
     * Méthode qui valide le numéro du permis selon l'ordre professionnel.
     *
     * @param numeroPermis le numero de permis
     * @param ordre l'ordre de la formation continue.
     * @return vrai si le numero de permis et valide pour l'ordre recu en parametre.
     */
    public boolean validerPermis(String numeroPermis , String ordre ,String nom , String prenom) {
        boolean resultat = false;

        switch (ordre) {
            case "architectes": resultat = validerPermisArchitectes(numeroPermis);
                break ;
            case "podiatres": resultat = validerPermisPodiatres(numeroPermis);
                break;
            case "géologues": resultat = validerPermisGeologues(numeroPermis ,nom ,prenom);
                break;
            case "psychologues": resultat = validerPermisPsychologues(numeroPermis);
                break;
        }

        return resultat;
    }


    /**
     * cette methode permet de valider le cycle et le retourne si valide sinon lève une exeption.
     *
     * @param cycle le cycle de la formation continue.
     * @return le cycle valide pour l'ordre entrée en parametre.
     * @throws IOException lève une exception si le cycle ne concorde pas avec l'ordre de la formation continue.
     */
    public String determinerCycle(String cycle) {

        return cycle;
    }


    /**
     * cette methode permet de valider le numero de permis et le retourne si valide sinon lève une exeption.
     * @param numeroPermis le numero de permis recu d'apres le fichier d'entrée json.
     * @return  le numero de permis valide.
     * @throws IOException lève une exception si le numero de permis est invalide.
     */
    public boolean determinerNumeroPermis(String numeroPermis , String ordre ,String nom , String prenom) {
        boolean resultat = validerPermis(numeroPermis , ordre , nom , prenom);

        if (!resultat) {
            ajouterMsgErreur(ERR_MSG_NUM_PERMIS_INVALID);
        }
        return resultat;
    }



    /**
     * Méthode qui determine l'interval du cycle valid
     *
     * @param cycle : le cycle supporté valide pour l'ordre
     * @return periode :  intervalle dates limite inclusivement
     */
    public String[] determinerPeriodeCycle(String cycle) {
        String[] periode = new String[]{"1990-01-01","1990-01-01"};

        switch (cycle) {
            case "2018-2020": periode = new String[]{"2018-04-01", "2020-04-01"};
                break ;
            case "2014-2016": periode = new String[]{"2014-04-01", "2016-07-01"};
                break;
            case "2016-2018": periode = new String[]{"2016-04-01", "2018-04-01"};
                break;
            case "2016-2019": periode = new String[]{"2016-06-01", "2019-06-01"};
                break;
            case "2016-2021": periode = new String[]{"2016-01-01", "2021-01-01"};
                break;
        }
        return periode;
    }


    /**
     * Méthode qui valide l'ordre de la formation continue.
     *
     * @param ordre Ordre professionnel
     * @return vrai si l'ordre est present dans la liste d'ordres accepter faux sinon
     * @throws IOException si l'ordre n'et pas valid.
     */
    public String validerOrdre(String ordre) throws IOException {
        boolean valid = false;
        int index = 0;

        while(!valid && index < LISTE_ORDRES.length) {
            valid = ordre.equals(LISTE_ORDRES[index]);
            index++;
        }
        if (!valid) {
            erreurs(ERR_MSG_ORDRE_INVALID);
        }
        return ordre;
    }


    /**
     * Méthode qui valide le cycle
     *
     * @param cycle : le cycle des l'activités
     * @param ordre : l'ordre professtionnel
     * @return vrai si le cycle est valide pour l'ordre professioel faux sinon
     */
    public boolean validerCycle(String cycle, String ordre) {
        boolean resultat = false;

        if (ordre.equals("architectes") || ordre.equals("podiatres")) {
            resultat = cycle.equals("2014-2016" ) || cycle.equals("2016-2018" ) || cycle.equals("2018-2020" );
        } else if (ordre.equals("géologues")) {
            resultat = cycle.equals("2016-2019");
        } else if (ordre.equals("psychologues")) {
            resultat = cycle.equals("2016-2021");
        }
        if (!resultat) {
            ajouterMsgErreur(ERR_MSG_CYCLE_INVALID);
        }
        return resultat;
    }


    /**
     * Cette methode le format de la date
     *
     * @return boolean : True si le format date est valide
     */
    public boolean validerDate(String intervalleDate, String dateMin , String dateMax , String description) throws ParseException {
        boolean resultat = validerFormatDate(intervalleDate , description);

        if (resultat){
            resultat = validerIntervalleDate(intervalleDate,dateMin,dateMax);
        }
        if (! resultat){
            ajouterMsgErreur(ERR_MSG_DEBUT + description + ERR_MSG_FIN_DATE);
        }
        return resultat;
    }


    /**
     * methode qui valide si la date respecte le format ISO8601 ( yyyy-mm-dd )
     * @param date date de l'activité.
     * @return vrai si la date respecte le format ISO 8601 faux sinon.
     */
    public boolean validerFormatDate(String date , String description) {
        boolean valid = date.charAt(4) == '-' && date.charAt(7) == '-';
        if (valid) {
            for (int i = 0; i <date.length() ; i++) {
                if (i != 4 && i != 7){
                    valid = valid && date.charAt(i)>= '0' && date.charAt(i) <= '9';
                }
            }
        }
        if (! valid) {
            ajouterMsgErreur("L'activité "+ description + ERR_MSG_FORMAT_DATE);
        }
        return valid;
    }


    /**
     * Cette méthode verifie si la date d'une activité se situe bien entre le
     * 1 avril 2018 et le 1 avril 2020
     *
     * @param intervalleDate la date à valider si elle se situe dans l'intevalle ou pas
     * @return si la date se situe dans l'intervalle ou non
     * @throws ParseException
     */
    public boolean validerIntervalleDate(String intervalleDate, String dateMin , String dateMax ) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date min = sdf.parse(dateMin);
            Date max = sdf.parse(dateMax);
            Date date = sdf.parse(intervalleDate);
        return date.compareTo(min) >= 0 && date.compareTo(max) <= 0;
    }


    /**
     * vérifie si le nombre des heures du dernier cycle est un entier plus grand ou egal à 0
     *
     * @param heuresCyclePrec heures du cycle dernier
     * @return heures du dernier cycle >= 0
     */
    public int validerHeuresCyclePrec(double heuresCyclePrec) {
        int resultat = (int) heuresCyclePrec;

        if (heuresCyclePrec != 0 && validerHeuresActivites(heuresCyclePrec,ERR_MSG_HEURES_TRANSF)) {
            if (heuresCyclePrec > 7) {
                resultat = nbrHeuresActivite((int) heuresCyclePrec, HEURES_PREC_MAX);
                ajouterMsgErreur(MSG_DEPASSE_HEURES_PREC);
            }
        }
        return resultat;
    }

    /**
     * vérifie si l'activité fournie une description minimum de 21 caractéres.
     *
     * throw leve une exeption sinon
     */
    public boolean validerTailleDescriptions(String description) {
        boolean resultat = description.length() >= TAILLE_MIN_DESCRIPTION;

        if (!resultat) {
            ajouterMsgErreur("L'activité " + description +ERR_MSG_TAILLE_DESCRIPTION);
        }
        return resultat;
    }

    /**
     * vérifie si le nombre des heures d'une activité est un entier positif
     *
     * throw leve une exeption sinon
     */
    public boolean validerHeuresActivites(double nombreHeuresActivite,String description) throws JSONException {
        boolean resultat = nombreHeuresActivite >= HEURES_ACTIVITES_MIN
                && nombreHeuresActivite == (int) nombreHeuresActivite;

        if (!resultat) {
            ajouterMsgErreur("L'activité "+ description + " presente un Nombre Heures invalid.");
        }
        return resultat;
    }


    /**
     * Cette méthode vérifie si le nombre total d'une activité dépasse le maximum accordé,
     * si c'est le cas, alors le nombre d'heures maximum est retourné, sinon le nombre d'heures initial
     * est retourné
     *
     * @param nbrHeures    le nombre d'heures total d'une activité
     * @param nbrHeuresMax le maximum nombre d'heures que l'activité peut avoir
     * @return le nombre d'heures total final
     */
    public int nbrHeuresActivite(int nbrHeures, int nbrHeuresMax) {
        int nbrHeuresactivite = nbrHeures;

        if (nbrHeures > nbrHeuresMax) {
            nbrHeuresactivite = nbrHeuresMax;
        }
        return nbrHeuresactivite;
    }

    /**
     * Cette méthode ajoute les messages d'erreurs dans un tableau
     *
     * @param msg le message d'erreur à ajouter
     */
    public void ajouterMsgErreur(String msg) {
        listeCategorieInvalide.add(msg);
    }

    /**
     * Cette méthode vérifie si le nombre d'heures total est égale ou plus grand que 40 et aussi si les heures
     * des activités sont valides. Les deux doivent être vrai, sinon la méthode retourne "false"
     *
     * @param heuresValide si le nombre d'heures des activités est valide
     * @return si le nombre d'heures des activités est valide et si le nombre d'heures total est égale ou plus que 40
     */
    public boolean traiterLeResultatFinal(boolean heuresValide, String cycle , String ordre) {
        int nombreHeuresTotalMin = determinerHeuresTotalesMin(cycle , ordre);
        int heuresManquantes ;

        if (nbrHeuresTotal < nombreHeuresTotalMin) {
            heuresManquantes = nombreHeuresTotalMin - nbrHeuresTotal;
            ajouterMsgErreur("Il manque " + heuresManquantes + " heure(s) pour completer le cycle.");
        }
        return (heuresValide && (nbrHeuresTotal >= nombreHeuresTotalMin));
    }

    /**
     * Cette méthode vérifies si la totalité des heures d'une activités est plus grand que certains seuils définit.
     *
     * @param nbrHeures    le nombre d'heures total de l'activité
     * @param nbrHeuresMin le seuil
     * @return si le nombre d'heures est plus grand que le seuil
     */
    public boolean validerMinimumActivites(int nbrHeures, int nbrHeuresMin, String message) {
        boolean resultat = nbrHeures >= nbrHeuresMin;

        if (!resultat) {
            ajouterMsgErreur("Il manque " + ( nbrHeuresMin - nbrHeures)
                    + " heures " +  message );
        }
        return resultat;

    }

    /**
     * Cette méthode interrompt le programme si une exception est levée et elle ajoute un message
     * d'erreur dans le fichier JSON sortant après avoir retirer tous les messages d'erreurs existants.
     *
     * @param msg : le message d'erreur à ajouter.
     */
    public void erreurs(String msg) {
        ajouterMsgErreur(msg);
        creerFichierSortant(false,true);
        System.err.println("ERREUR!!! "+ msg );
        System.exit(-1);
    }

    /**
     * Cette méthode interrompt le programme si une exception est levée les messages  d'erreurs existants
     * sont inscrient dans le fichier Json sortant.
     *
     */
    public void erreurs() throws IOException {
        creerFichierSortant(false,true);
        System.err.println("ERREUR!!! format fichier Json invalide" );
        System.exit(-1);
    }


    /**
     * Cette méthode sert à créer le fichier JSON sortant.
     * Elle fait appel à la classe JsonWriter pour créer une instance "JsonWriter".
     *
     * @param resultat : le résultat de l'evaluation.
     * @param fichierEntrantInvalide : indice pour savoir si le fichier est invalide, True = fichier Json entrant invalide.
     * @throws IOException : si le fichier sortant est introuvable
     */
    public void creerFichierSortant(boolean resultat,boolean fichierEntrantInvalide) {
        try {
            JsonWriter jsonSortant = new JsonWriter(pathFichierSortant, listeCategorieInvalide, resultat);
            jsonSortant.sauverFichierJson();
            mettreAJoursStatistique(fichierEntrantInvalide);
        } catch (IOException io) {
            System.err.println("Une erreur est survenue :  le fichier sortant est introuvable \n Fin du programme");
            System.exit(-1);
        }
    }


    /**
     * Cette Méthode met à jours les valeurs du fichiers statistiques
     * @param fichierEntrantInvalide : si le fichier entrant est invlide , True = fichier invalide
     * @throws IOException
     */
    public void mettreAJoursStatistique(boolean fichierEntrantInvalide) throws IOException {
        Statistiques stats = new Statistiques(pathFichierEntrant,pathFichierSortant,"statistiques.json",
                fichierEntrantInvalide);
        stats.updateStats();
    }


    /**
     * Cette méthode sert à créer le fichier JSON sortant.
     * Elle fait appel à la classe JsonWriter pour créer une instance "JsonWriter".
     *
     * @param cycle : le cycle de la formation continue.
     * @param ordre : l'ordre traiter
     * @return : determine le nombre d'heures total minimum permise a partir
     *           du cycle et de l'ordre.
     */
    public int determinerHeuresTotalesMin(String cycle, String ordre) {
        int heuresTotalMin = 42;

        if (ordre.equals("architectes") && cycle.equals("2018-2020")) {
            heuresTotalMin = 40 ;
        } else if (ordre.equals("géologues")) {
            heuresTotalMin = 55;
        } else if (ordre.equals("psychologues")) {
            heuresTotalMin = 90 ;
        } else if (ordre.equals("podiatres")) {
            heuresTotalMin = 60 ;
        }
        return heuresTotalMin;
    }

    /**
     * Cette methode sert a valider le numero de permis pour l'ordre des architectes
     * @param numeroPermis
     * @return vrai si Le numéro de permis est composé d'une lettre suivie de 4 chiffres.
     * La lettre peut être un A ou un T et c'est toujours une lettre majuscule, faux sinon
     */
    public boolean validerPermisArchitectes(String numeroPermis) {
        boolean valid = false;

        if (numeroPermis.length() == 5) {
            valid = numeroPermis.charAt(0) == 'A' || numeroPermis.charAt(0) == 'T' ;

            for (int i = 1; i < numeroPermis.length(); i++) {
                valid = valid && ( numeroPermis.charAt(i) >= '0' && numeroPermis.charAt(i) <= '9');
            }
        }
        return valid;
    }


    /**
     * Cette methode sert a valider le numero de permis pour l'ordre des psychologues
     * @param numeroPermis
     * @return vrai si Le numéro de permis est composé de 5 chiffres, d'un trait d'union
     *         et de 2 autres chiffres. faux sinon.
     */
    public boolean validerPermisPsychologues(String numeroPermis) {
        boolean valid = numeroPermis.length() == 8 && numeroPermis.charAt(5) == '-';

        if (valid) {
            for (int i = 0; i < numeroPermis.length(); i++) {
                if (i != 5)
                    valid = valid && ( numeroPermis.charAt(i) >= '0' && numeroPermis.charAt(i) <= '9');
            }
        }
        return valid;
    }


    /**
     * Cette methode sert a valider le numero de permis pour l'ordre des podiatres
     * @param numeroPermis
     * @return vrai si Le numéro de permis est composé de 5 chiffres. faux sinon.
     */
    public boolean validerPermisPodiatres(String numeroPermis) {
        boolean valid = numeroPermis.length() == 5;

        if (valid) {
            for (int i = 0; i < numeroPermis.length(); i++) {
                valid = valid && ( numeroPermis.charAt(i) >= '0' && numeroPermis.charAt(i) <= '9');
            }
        }
        return valid;
    }


    /**
     * Cette methode sert a valider le numero de permis pour l'ordre des geologues
     * @param numeroPermis
     * @return  Le numéro de permis est composé de 2 lettres suivies de 4 chiffres.
     * La première lettre du numéro de permis correspond à la première lettre du nom du membre en majuscule.
     * La deuxième lettre du numéro de permis correspond à la première lettre du prénom du membre en majuscule
     */
    public boolean validerPermisGeologues(String numeroPermis ,String nom ,String prenom) {
        boolean valid = numeroPermis.length() == 6
                && validerNom(nom) && validerPrenom(prenom)
                && numeroPermis.charAt(0) == nom.toUpperCase().charAt(0)
                && numeroPermis.charAt(1) == prenom.toUpperCase().charAt(0);
        if (valid ) {
            for (int i = 2; i < numeroPermis.length(); i++) {
                valid = valid && ( numeroPermis.charAt(i) >= '0' && numeroPermis.charAt(i) <= '9');
            }
        }
        return valid;
    }


    /**
     * Cette methode sert a valider le sexe selon la norme ISO 5218.
     * @param sexe  contiendra une valeur numérique.
     * @return vrai si elle respecte la norme ISO 5218 , faux sinon.
     */
    public boolean validerSexe(int sexe) {
        boolean valid = (sexe>=0 && sexe <= 2);

        if (!valid) {
            ajouterMsgErreur(ERR_MSG_SEXE);
        }
        return valid;

    }

    /**
     * Cette methode sert a valider le nom si ce n'esr pas un champ vide
     * @param nom contiendra le nom du membre pour la formation continue
     * @return vrai si le champ n'est pas vide faux sinon.
     */
    public Boolean validerNom(String nom){

        boolean valid = (! nom.isEmpty());
        if (! valid){
            ajouterMsgErreur(ERR_MSG_NOM);
        }
        return valid;
    }

    /**
     * Cette methode sert a valider le prenom si ce n'esr pas un champ vide
     * @param prenom contiendra le prenom du membre pour la formation continue
     * @return vrai si le champ n'est pas vide faux sinon.
     */
    public Boolean validerPrenom(String prenom) {
        boolean valid = (! prenom.isEmpty());

        if (!valid) {
            ajouterMsgErreur(ERR_MSG_PRENOM);
        }
        return valid;
    }

    public String determinerNom(String nom) {

        return nom;
    }

    public String determinerPrenom(String prenom) {

        return prenom;
    }

    public int determinerSexe(int sexe) {

        return sexe;
    }

    /**
     * Cette methode permet de valider une activite
     *
     * @return boolean : True si l'activite est valide
     */
    public boolean validerActivite(String categorie , String description) {
        boolean resultat = false;
        int index=0;
        while (index < LISTE_ACTIVITES.length && ! resultat ) {
            resultat = categorie.equals(LISTE_ACTIVITES[index]);
            index++;
        }
        if (! resultat){
            ajouterMsgErreur(ERR_MSG_DEBUT + description + ERR_MSG_FIN_CATEGORIE);
        }
        return resultat;
    }
}
