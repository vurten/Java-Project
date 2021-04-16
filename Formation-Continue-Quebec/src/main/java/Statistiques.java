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
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.io.IOException;


public class Statistiques {


    final String CLE_DECL_TOTAL = "le nombre total de déclarations traitées";
    final String CLE_DECL_TOTAL_COMPLET = "le nombre total de déclarations complètes";
    final String CLE_DECL_TOTAL_INCOMPLET = "le nombre total de déclarations incomplètes ou invalides";
    final String CLE_DECL_HOMMES = "le nombre total de déclarations faites par des hommes";
    final String CLE_DECL_FEMMES = "le nombre total de déclarations faites par des femmes";
    final String CLE_DECL_AUTRE_SEXE = "le nombre total de déclarations faites par des gens de sexe inconnu";
    final String CLE_NBR_ACTIVITES = "le nombre total d'activités dans les déclarations";
    final String CLE_COURS = "le nombre d'activités pour la catégorie (cours)";
    final String CLE_ATELIER = "le nombre d'activités pour la catégorie (atelier)";
    final String CLE_SEMINAIRE = "le nombre d'activités pour la catégorie (séminaire)";
    final String CLE_COLLOQUE = "le nombre d'activités pour la catégorie (colloque)";
    final String CLE_CONFERENCE = "le nombre d'activités pour la catégorie (conférence)";
    final String CLE_LECTURE_DIRIGEE = "le nombre d'activités pour la catégorie (lecture dirigée)";
    final String CLE_PRESENTATION = "le nombre d'activités pour la catégorie (présentation)";
    final String CLE_GOUPE_DISCUSSION = "le nombre d'activités pour la catégorie (groupe de discussion)";
    final String CLE_PROJET_RECHERCHE = "le nombre d'activités pour la catégorie (projet de recherche)";
    final String CLE_REDACTION_PROFESSIONNELLE = "le nombre d'activités pour la catégorie (rédaction professionnelle)";
    final String CLE_COMPLET_ARCHITECTES = "le nombre total de déclarations valides et complètes pour l'ordre des architectes";
    final String CLE_COMPLET_GEOLOGUES = "le nombre total de déclarations valides et complètes pour l'ordre des géologues";
    final String CLE_COMPLET_PSYCHOLOGUES = "le nombre total de déclarations valides et complètes pour l'ordre des psychologues";
    final String CLE_COMPLET_PODIATRES = "le nombre total de déclarations valides et complètes pour l'ordre des podiatres";
    final String CLE_INCOMPLET_ARCHITECTES = "le nombre total de déclarations valides et incomplètes pour l'ordre des architectes";
    final String CLE_INCOMPLET_GEOLOGUES = "le nombre total de déclarations valides et incomplètes pour l'ordre des géologues";
    final String CLE_INCOMPLET_PSYCHOLOGUES = "le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues";
    final String CLE_INCOMPLET_PODIATRES = "le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres";
    final String CLE_DECL_PERMIS_INVALIDE = "le nombre de déclarations soumises avec un numéro de permis invalide";
    final String MSG_PERMIS_INVALID = "Le numero de permis invalide.";



    private String statistiqueString = "{\n" +
            "  \"le nombre total de déclarations traitées\": 0,\n" +
            "  \"le nombre total de déclarations complètes\": 0,\n" +
            "  \"le nombre total de déclarations incomplètes ou invalides\": 0,\n" +
            "  \"le nombre total de déclarations faites par des hommes\": 0,\n" +
            "  \"le nombre total de déclarations faites par des femmes\": 0,\n" +
            "  \"le nombre total de déclarations faites par des gens de sexe inconnu\": 0,\n" +
            "  \"le nombre total d'activités dans les déclarations\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (cours)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (atelier)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (séminaire)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (colloque)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (conférence)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (lecture dirigée)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (présentation)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (groupe de discussion)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (projet de recherche)\": 0,\n" +
            "  \"le nombre d'activités pour la catégorie (rédaction professionnelle)\": 0,\n" +
            "  \"le nombre total de déclarations valides et complètes pour l'ordre des architectes\": 0,\n" +
            "  \"le nombre total de déclarations valides et complètes pour l'ordre des géologues\": 0,\n" +
            "  \"le nombre total de déclarations valides et complètes pour l'ordre des psychologues\": 0,\n" +
            "  \"le nombre total de déclarations valides et complètes pour l'ordre des podiatres\": 0,\n" +
            "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des architectes\": 0,\n" +
            "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des géologues\": 0,\n" +
            "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues\": 0,\n" +
            "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres\": 0,\n" +
            "  \"le nombre de déclarations soumises avec un numéro de permis invalide\": 0\n" +
            "}";

    protected String fichierEntrant;
    protected String fichierResultat;
    protected String fichierStats;
    protected boolean fichierEntrantInvalide;

    protected int declarationTotales;
    protected int declarationCompletes = 0;
    protected int declarationIncompletes = 0;
    protected int declarationHommes = 0;
    protected int declarationFemmes = 0;
    protected int declarationAutreSexe = 0;
    protected int nombreActiviteTotal = 0;

    protected int nbrCours = 0;
    protected int nbrAtelier = 0;
    protected int nbrSeminaire = 0;
    protected int nbrColloque = 0;
    protected int nbrConference = 0;
    protected int nbrLectureDirigee = 0;
    protected int nbrPresentation = 0;
    protected int nbrGroupeDiscussion = 0;
    protected int nbrProjetRecherche = 0;
    protected int nbrRedactionProfesionnel = 0;

    protected int declarationArchitectesComplet = 0;
    protected int declarationGeoloqueComplet = 0;
    protected int declarationPsycholoqueComplet = 0;
    protected int declarationPodiatresComplet = 0;

    protected int declarationArchitectesIncomplet = 0;
    protected int declarationGeoloqueIncomplet = 0;
    protected int declarationPsycholoqueIncomplet = 0;
    protected int declarationPodiatresIncomplet = 0;
    protected int declarationPermisInvalide = 0;


    /**
     * Constructeur de la class Statistiques.
     * @param fichierEntrant : le fichier Json entrant
     * @param fichierResultat : le fichier Json sortant
     * @param fichierStats : le fichier des statistiques.
     * @param fichierEntrantInvalide : indique si le fichier entrant est invalide, True = fichier invalide.
     */
    public Statistiques (String fichierEntrant,String fichierResultat,String fichierStats,boolean fichierEntrantInvalide) {
        this.fichierEntrant = fichierEntrant;
        this.fichierResultat = fichierResultat;
        this.fichierStats = fichierStats;
        this.fichierEntrantInvalide = fichierEntrantInvalide;
    }


    /**
     * constructeur de la classe statistiques.
     * @param fichierStats : le fichier de statistiques
     * @param fichierEntrantInvalide: indique si le fichier entrant est invalide, True = fichier invalide.
     */
    public Statistiques (String fichierStats,boolean fichierEntrantInvalide) {
        this.fichierStats = fichierStats;
        this.fichierEntrantInvalide = fichierEntrantInvalide;
    }

    /**
     * Cette classe débute la mise à jours des données. Elle commence par incrémenter le nombres
     * total de déclarations traitées, ensuite elle verifie si le fichier entrant est invalide.
     * Si le fichier entrant est invalide, elle incrémente le nombre de déclarations invalide et
     * elle va vérifer si le numéro de permis et invalide, pour ensuite finir le traitement sans altérer les
     * autres valeurs.
     * Si le fichier entrant est valide,elle mettre à jours les valeurs des déclaration valides, ensuite
     * la classe poursuit le traitment vers la verification du sexe.
     * @throws IOException
     */
    public void updateStats() throws IOException {
        declarationTotales++;
        if (!fichierEntrantInvalide){
            updateStatsCompletOuIncomplet();
            updateStatsSexe();
        }else{
            declarationIncompletes++;
            updatePermisInvalide();
        }
    }


    /**
     * Cette méthode met à jours les valeurs d'une déclaration valide.
     * @throws IOException
     */
    public void updateStatsCompletOuIncomplet() throws IOException{
        if (verifierResultatDeclaration(fichierResultat)) {
            declarationCompletes++;
            updateDeclarationParOrdreComplet(recupererObjetJson(fichierEntrant).getString("ordre"));
        }else {
            declarationIncompletes++;
            updateDeclarationParOrdreInvalide(recupererObjetJson(fichierEntrant).getString("ordre"));
        }
    }


    /**
     * Cette méthode incrémente le nombre de déclarations faites par type de sexe.
     * @throws IOException
     */
    public void updateStatsSexe() throws IOException {
        int sexe = recupererObjetJson(fichierEntrant).getInt("sexe");

        if (sexe == 0) {
            declarationAutreSexe++;
        }else if (sexe == 1) {
            declarationHommes++;
        }else if (sexe == 2) {
            declarationFemmes++;
        }
        updateStatsActivitesTotal();
    }

    /**
     * Cette méthode incrémente le nombre total d'activités.
     * @throws IOException
     */
    public void updateStatsActivitesTotal() throws IOException {
        JSONArray activites = recupererArrayJson(fichierEntrant,"activites");
        nombreActiviteTotal += activites.size();
        updateActiviteParCategorie(activites);
    }


    /**
     * cette méthode pourcourt le tableau d'activités pour incrémenter le nombre d'activité par catégorie.
     * @param tabloActivites : le tableau d'activités du fichier entrant.
     * @throws IOException
     */
    public void updateActiviteParCategorie(JSONArray tabloActivites) throws IOException {
        for (int i = 0; i < tabloActivites.size(); i++) {
            JSONObject objetJson = tabloActivites.getJSONObject(i);
            incrementerUneCategorie(objetJson.getString("categorie"));
        }
        updatePermisInvalide();
    }


    /**
     * Cette méthode incrémente le nombre de déclarations avec un numéro de permis invalide
     * @throws IOException
     */
    public void updatePermisInvalide() throws IOException {
        JSONArray tabloErreurs = recupererArrayJson(fichierResultat,"erreurs");

        if (tabloErreurs.contains(MSG_PERMIS_INVALID)) {
            declarationPermisInvalide++;
        }
        updateFichierDesstats();
    }

    /**
     *
     * Cette méthode met á jours le fichier statistiques.json.
     * @throws IOException
     */
    public void updateFichierDesstats() throws IOException {
        updateValues();
        updateStatistiqueString();
        updateFichierStatistiques();
    }

    /**
     * Cette méthode incrémente le nombre de déclarations valides et complètes par ordre. professionnel.
     * @param ordre : le nom de l'ordre professionnel.
     */
    public void updateDeclarationParOrdreComplet(String ordre) {
        switch (ordre) {
            case "architectes":
                declarationArchitectesComplet++;
                break;
            case "géologues":
                declarationGeoloqueComplet++;
                break;
            case "psychologues":
                declarationPsycholoqueComplet++;
                break;
            case "podiatres":
                declarationPodiatresComplet++;
                break;
        }
    }


    /**
     * Cette méthode incrémente le nombre de déclarations valides et incomplètes par ordre. professionnel.
     * @param ordre: le nom de l'ordre professionnel
     */
    public void updateDeclarationParOrdreInvalide(String ordre) {
        switch (ordre) {
            case "architectes":
                declarationArchitectesIncomplet++;
                break;
            case "géologues":
                declarationGeoloqueIncomplet++;
                break;
            case "psychologues":
                declarationPsycholoqueIncomplet++;
                break;
            case "podiatres":
                declarationPodiatresIncomplet++;
                break;
        }
    }


    /**
     * Cette méthode incrémente le nombre d'activités par catégorie.
     * @param categorie : le nom de la catégorie.
     */
    public void incrementerUneCategorie(String categorie){
        switch (categorie) {
            case "cours":
                nbrCours++;
                break;
            case "atelier":
                nbrAtelier++;
                break;
            case "séminaire":
                nbrSeminaire++;
                break;
            case "colloque":
                nbrColloque++;
                break;
            case "conférence":
                nbrConference++;
                break;
            case "lecture dirigée":
                nbrLectureDirigee++;
                break;
            case "présentation":
                nbrPresentation++;
                break;
            case "groupe de discussion":
                nbrGroupeDiscussion++;
                break;
            case "projet de recherche":
                nbrProjetRecherche++;
                break;
            case "rédaction professionnelle":
                nbrRedactionProfesionnel++;
                break;
        }
    }

    /**
     * Cette méthode verifier si la déclaration est complète.
     * @param fichierResultat : le fichier Json sortant.
     * @return : True si la déclaration est complète.
     * @throws IOException
     */
    public Boolean verifierResultatDeclaration(String fichierResultat) throws IOException {
        return recupererObjetJson(fichierResultat).getBoolean("complet");
    }


    /**
     * Cette méthode sert à retourner un objet Json.
     * @param fichierJson : le fichier Json.
     * @return : l'objet Json
     * @throws IOException
     */
    public JSONObject recupererObjetJson(String fichierJson) throws IOException {
        return (JSONObject) JSONSerializer.toJSON(Utf8File.loadFileIntoString(fichierJson));
    }


    /**
     * Cette meéthode retourne un tableau d'un fichier Json.
     * @param fichierJson : le fichier Json
     * @param cle : la clé du tableau Json.
     * @return : un tableau Json.
     * @throws IOException
     */
    public JSONArray recupererArrayJson(String fichierJson,String cle) throws IOException {
        JSONObject objetJson = recupererObjetJson(fichierJson);

        return (JSONArray) JSONSerializer.toJSON(objetJson.getString(cle));
    }

    /**
     * Cette méthode met à jours les valeurs des statistiques.
     * @throws IOException
     */
    public void updateValues()throws IOException {
        JSONObject objetJson = recupererObjetJson(fichierStats);
        declarationTotales += objetJson.getInt(CLE_DECL_TOTAL);
        declarationCompletes += objetJson.getInt(CLE_DECL_TOTAL_COMPLET);
        declarationIncompletes += objetJson.getInt(CLE_DECL_TOTAL_INCOMPLET);
        declarationHommes += objetJson.getInt(CLE_DECL_HOMMES);
        declarationFemmes += objetJson.getInt(CLE_DECL_FEMMES);
        declarationAutreSexe += objetJson.getInt(CLE_DECL_AUTRE_SEXE);
        nombreActiviteTotal += objetJson.getInt(CLE_NBR_ACTIVITES);
        updateValuesSuite1(objetJson);
        updateValuesSuite2(objetJson);
    }

    /**
     * Cette méthode met à jours les valeurs des statistiques.
     * @throws IOException
     */
    public void updateValuesSuite1(JSONObject objetJson) {
        nbrCours += objetJson.getInt(CLE_COURS);
        nbrAtelier += objetJson.getInt(CLE_ATELIER);
        nbrSeminaire += objetJson.getInt(CLE_SEMINAIRE);
        nbrColloque += objetJson.getInt(CLE_COLLOQUE);
        nbrConference += objetJson.getInt(CLE_CONFERENCE);
        nbrLectureDirigee += objetJson.getInt(CLE_LECTURE_DIRIGEE);
        nbrPresentation += objetJson.getInt(CLE_PRESENTATION);
        nbrGroupeDiscussion += objetJson.getInt(CLE_GOUPE_DISCUSSION);
        nbrProjetRecherche += objetJson.getInt(CLE_PROJET_RECHERCHE);
        nbrRedactionProfesionnel += objetJson.getInt(CLE_REDACTION_PROFESSIONNELLE);
    }

    /**
     * Cette méthode met à jours les valeurs de statistiques.
     * @throws IOException
     */
    public void updateValuesSuite2(JSONObject objetJson) {
        declarationArchitectesComplet += objetJson.getInt(CLE_COMPLET_ARCHITECTES);
        declarationGeoloqueComplet += objetJson.getInt(CLE_COMPLET_GEOLOGUES);
        declarationPsycholoqueComplet += objetJson.getInt(CLE_COMPLET_PSYCHOLOGUES);
        declarationPodiatresComplet += objetJson.getInt(CLE_COMPLET_PODIATRES);

        declarationArchitectesIncomplet += objetJson.getInt(CLE_INCOMPLET_ARCHITECTES);
        declarationGeoloqueIncomplet += objetJson.getInt(CLE_INCOMPLET_GEOLOGUES);
        declarationPsycholoqueIncomplet += objetJson.getInt(CLE_INCOMPLET_PSYCHOLOGUES);
        declarationPodiatresIncomplet += objetJson.getInt(CLE_INCOMPLET_PODIATRES);
        declarationPermisInvalide += objetJson.getInt(CLE_DECL_PERMIS_INVALIDE);
    }


    /**
     * Cette méthode met à jours le String de statistiques.
     * @throws IOException
     */
    public void updateStatistiqueString() {
        statistiqueString = "{\n  \"le nombre total de déclarations traitées\": "+declarationTotales+",\n" +
                "  \"le nombre total de déclarations complètes\": "+declarationCompletes+",\n" +
                "  \"le nombre total de déclarations incomplètes ou invalides\": "+declarationIncompletes+",\n" +
                "  \"le nombre total de déclarations faites par des hommes\": "+declarationHommes+",\n" +
                "  \"le nombre total de déclarations faites par des femmes\": "+declarationFemmes+",\n" +
                "  \"le nombre total de déclarations faites par des gens de sexe inconnu\": "+declarationAutreSexe+",\n" +
                "  \"le nombre total d'activités dans les déclarations\": "+nombreActiviteTotal+",\n" +
                "  \"le nombre d'activités pour la catégorie (cours)\": "+nbrCours+",\n" +
                "  \"le nombre d'activités pour la catégorie (atelier)\": "+nbrAtelier+",\n" +
                updateStatistiqueStringSuite1()+updateStatistiqueStringSuite2();
    }


    /**
     * Cette méthode met à jours le String de statistiques.
     * @throws IOException
     */
    public String updateStatistiqueStringSuite1() {
        return  "  \"le nombre d'activités pour la catégorie (séminaire)\": "+nbrSeminaire+",\n" +
                "  \"le nombre d'activités pour la catégorie (colloque)\": "+nbrColloque+",\n" +
                "  \"le nombre d'activités pour la catégorie (conférence)\": "+nbrConference+",\n" +
                "  \"le nombre d'activités pour la catégorie (lecture dirigée)\": "+nbrLectureDirigee+",\n" +
                "  \"le nombre d'activités pour la catégorie (présentation)\": "+nbrPresentation+",\n" +
                "  \"le nombre d'activités pour la catégorie (groupe de discussion)\": "+nbrGroupeDiscussion+",\n" +
                "  \"le nombre d'activités pour la catégorie (projet de recherche)\": "+nbrProjetRecherche+",\n" +
                "  \"le nombre d'activités pour la catégorie (rédaction professionnelle)\": "+nbrRedactionProfesionnel+",\n" +
                "  \"le nombre total de déclarations valides et complètes pour l'ordre des architectes\": "+declarationArchitectesComplet+",\n" +
                "  \"le nombre total de déclarations valides et complètes pour l'ordre des géologues\": "+declarationGeoloqueComplet+",\n";
    }


    /**
     * Cette méthode met à jours le String de statistiques.
     * @throws IOException
     */
    public String updateStatistiqueStringSuite2() {
        return  "  \"le nombre total de déclarations valides et complètes pour l'ordre des psychologues\": "+declarationPsycholoqueComplet+",\n" +
                "  \"le nombre total de déclarations valides et complètes pour l'ordre des podiatres\": "+declarationPodiatresComplet+",\n" +
                "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des architectes\": "+declarationArchitectesIncomplet+",\n" +
                "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des géologues\": "+declarationGeoloqueIncomplet+",\n" +
                "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues\": "+declarationPsycholoqueIncomplet+",\n" +
                "  \"le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres\": "+declarationPodiatresIncomplet+",\n" +
                "  \"le nombre de déclarations soumises avec un numéro de permis invalide\": "+declarationPermisInvalide+"\n}";
    }



    /**
     * Cette méthode met à jours le fichier de statistiques à 0.
     * @throws IOException
     */
    public void updateFichierStatistiques() throws IOException {
        JsonWriter ecrireFichierStats = new JsonWriter(fichierStats,statistiqueString);

        ecrireFichierStats.sauverFichierJson();
    }

}
