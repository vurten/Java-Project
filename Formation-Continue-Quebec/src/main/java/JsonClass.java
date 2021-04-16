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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.ArrayUtils;

public class JsonClass extends Validation {


    /**
     * Cette méthode est un constructeur qui va chercher des informations du fichier JSON entrant.
     * La méthode utlise des fonctions provenant de librairies externes.
     * la méthode lève une exception si le fichier entrant est introuvable ou si le fichier JSON entrant est invalide.
     * @param fichier : le fichier JSON entrant , pathFichierSortant : le fichier JSON sortant:
     * @throws IOException, JSONException :
     */
    public JsonClass(String fichier, String pathFichierSortant) throws IOException, JSONException, ParseException {
        pathFichierEntrant = fichier;
        try {
            lireJson(fichier, pathFichierSortant);
        } catch (JSONException e) {
            erreurs(ERR_MSG_JSON_INVALID);
        } catch (FileNotFoundException e){
            System.err.println(ERR_MSG_FICHIER_INTROUVABALE);
            System.exit(-1);
        }
    }
    public JsonClass(){}


    /**
     * Les librairies externes sont utilisées pour chercher les information ci-dessous:
     * -le tableau d'activités
     * -le numéro de permis
     * -les heures du dernier cycle
     * la méthode lève une exception si le fichier entrant est introuvable ou si le fichier JSON entrant est invalide.
     *
     * @param fichier : le fichier JSON entrant , pathFichierSortant : le fichier JSON sortant:
     * @throws IOException, JSONException :
     */
    public void lireJson(String fichier, String pathFichierSortant) throws IOException, JSONException, ParseException {
        this.pathFichierSortant = pathFichierSortant;
        JSONObject objetJson = (JSONObject) JSONSerializer.toJSON(Utf8File.loadFileIntoString(fichier));
        tabloActivites = (JSONArray) JSONSerializer.toJSON(objetJson.getString(CLE_ACIVITES));
        validationPrimaire(objetJson);

        if (ordre.equals("architectes") || ordre.equals("podiatres")) {
            heureAnneePrec = validerHeuresCyclePrec(objetJson.getDouble(CLE_HEURES_AN_PREC));
        }
    }

    /**
     * cette methode permet la validation de l'odre, numeroPermis, cycle et periode de la formation continue.
     *
     * @param objetJson objet Json contenant les parametres lue dans le fichier d'entrée
     * @throws IOException lève une exception si l'odre, numeroPermis, cycle et periode sont invalides.
     */

    public void validationPrimaire(JSONObject objetJson) throws ParseException,IOException {
        ordre = validerOrdre(objetJson.getString(CLE_ORDRE));
        periode = determinerPeriodeCycle(objetJson.getString(CLE_CYCLE));
        if (! validerFormatJson(objetJson)) {
            erreurs();
        }
        numeroPermis = objetJson.getString(CLE_NUM_PERMIS );
        nom = determinerNom(objetJson.getString(CLE_NOM));
        prenom = determinerPrenom(objetJson.getString(CLE_PRENOM));
        sexe = determinerSexe(objetJson.getInt(CLE_SEXE));
        cycle = determinerCycle(objetJson.getString(CLE_CYCLE));

    }


    /**
     * Pour chauque activité parcourue, elle vérifie si une erreur existe et si c'est le cas,
     * un message approprié est ajouté dans le tableau de messages d'erreurs.
     * Si l'activité passe la validation, l'objet JSON concernant l'activité est envoyé à la
     * méthode "calculerHeuresChaqueActivites" pour faire l'addition des heures.
     *
     * @throws IOException :
     */

    public void parcourirLesActivites() throws IOException {
        try {
            tableauActivites();
        } catch (JSONException e) {
            erreurs(ERR_MSG_JSON_INVALID);
        }
    }


    /**
     * Cette méthode parcourt le tableau d'activités du fichier JSON entrant.
     * 
     * @throws ParseException :
     */
    public void tableauActivites() {
        for (int i = 0; i < tabloActivites.size(); i++) {
            JSONObject jsonObject = tabloActivites.getJSONObject(i);
                calculerHeuresChaqueActivites(jsonObject);
            }
    }



    /**
     * Cette méthode a pour but d'additionner les heures de chaque activité qui a passé la validation
     * provenant de la classe "parcourirLesActivites".
     * Les heures de chaque activités sont additionnées individuellement et de plus,
     * le total des activités (cours,atelier,séminaire,colloque,conférence,lecture dirigée) sont
     * additionnées et affectées dans la variable totalActivites.
     *
     * @param jsonObject : l'objet JSON provenant du tableau d'activités:
     */
    public void calculerHeuresChaqueActivites(JSONObject jsonObject) {
        String categorie = jsonObject.getString(CLE_CATEGORIE_ACIVITES);

        switch (categorie) {
            case "cours":
                cours += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "atelier":
                atelier += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "séminaire":
                seminaire += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "colloque":
                colloque += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "conférence":
                conference += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "lecture dirigée":
                lectureDirige += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "présentation":
                presentation += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "groupe de discussion":
                groupeDeDiscussion += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "projet de recherche":
                projetDeRecherche += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
            case "rédaction professionnelle":
                redactionProfessionnelle += jsonObject.getInt(CLE_HEURES_ACIVITES);
                break;
        }
    }


    /**
     * Cette méthode retourne le nombre total d'heures de toutes les activités.
     *
     * @return le nombre total d'heures :
     */
    public int additionnerLesHeures() {
        return totalActivites + presentation +
                groupeDeDiscussion + projetDeRecherche + redactionProfessionnelle;
    }


    /**
     * Cette methode valide si les  restrictions sur les heures de chaque activités sont respectées
     * pour l'ordre des architectes.
     *
     * @return boolean : resultat sur la restriction de minimum 17 heures pour les activités
     * (cours,atelier,séminaire,colloque,conférence,lecture dirigée):
     */
    public boolean validerEtAdditionnerlesHeuresActivitesArchitectes() {
        totalActivites= cours + atelier + seminaire + colloque + conference+ lectureDirige + heureAnneePrec;
        presentation = nbrHeuresActivite(presentation, 23);
        groupeDeDiscussion = nbrHeuresActivite(groupeDeDiscussion, 17);
        projetDeRecherche = nbrHeuresActivite(projetDeRecherche, 23);
        redactionProfessionnelle = nbrHeuresActivite(redactionProfessionnelle, 17);
        nbrHeuresTotal = additionnerLesHeures();
        return validerMinimumActivites(totalActivites, 17, ERR_MSG_HEURES_TOT_CATEGORIE);
    }


    /**
     * Cette methode valide si les  restrictions sur les heures de chaque activités sont respectées
     * pour l'ordre des geologues.
     *
     * @return boolean : vrai si  resultat sur la restriction de minimum 22 heures activités de cours
     *                  , minimum 3 heures activités projet de rechereche et 1 heure faux sinon.
     */
    public boolean validerEtAdditionnerlesHeuresActivitesGeologues() {
        totalActivites= cours + atelier + seminaire + colloque + conference + lectureDirige;
        nbrHeuresTotal = additionnerLesHeures();
        return  validerMinimumActivites(cours, 22,ERR_MSG_HEURES_TOT_COURS_GEOLOGUES)
                & validerMinimumActivites(projetDeRecherche, 3,ERR_MSG_HEURES_TOT_PROJET_DE_RECHERCHE_GEOLOGUES)
                & validerMinimumActivites(groupeDeDiscussion, 1,ERR_MSG_HEURES_TOT_GROUPE_DE_DISCUSSION_GEOLOGUES);
    }


    /**
     * Cette methode valide si les  restrictions sur les heures de chaque activités sont respectées
     * pour l'ordre des psychologues.
     *
     * @return boolean : vrai si  resultat sur la restriction de minimum 25 heures activités de cours
     *                  faux sinon.
     */
    public boolean validerEtAdditionnerlesHeuresActivitesPsychologues() {
        conference = nbrHeuresActivite(conference, 15);
        totalActivites = cours + atelier + seminaire + colloque + conference + lectureDirige;
        nbrHeuresTotal = additionnerLesHeures();
        return validerMinimumActivites(cours, 25,ERR_MSG_HEURES_TOT_COURS_PSYCOLOGUES);
    }


    /**
     * Cette methode valide si les  restrictions sur les heures de chaque activités sont respectées
     * pour chaque ordre et cycle en particulier.
     *
     * @return boolean : resultat sur la restriction de minimum 17 heures pour les activités
     * (cours,atelier,séminaire,colloque,conférence,lecture dirigée):
     */
    public boolean validerEtAdditionnerlesHeuresActivites(String ordre) {
        boolean resultat ;

        if (ordre.equals("géologues")) {
            resultat = validerEtAdditionnerlesHeuresActivitesGeologues();
        }else if (ordre.equals("psychologues")) {
            resultat = validerEtAdditionnerlesHeuresActivitesPsychologues();
        }else {
            resultat = validerEtAdditionnerlesHeuresActivitesArchitectes();
        }
        return resultat;
    }

    /**
     * Cette methode verifie si toutes les activites sont valides
     *
     * @return boolean : true si tout est valide
     */
    public boolean validerTableauActivites() throws ParseException {
        boolean resultat = true;

        for (int i = 0; i < tabloActivites.size(); i++) {
            JSONObject jsonObject = tabloActivites.getJSONObject(i);
            resultat = resultat & validerTailleDescriptions(jsonObject.getString(CLE_DESCRIPTION_ACIVITES))
                     & validerHeuresActivites(jsonObject.getDouble(CLE_HEURES_ACIVITES),jsonObject.getString(CLE_DESCRIPTION_ACIVITES))
                     & validerActivite(jsonObject.getString(CLE_CATEGORIE_ACIVITES),jsonObject.getString(CLE_DESCRIPTION_ACIVITES))
                     & validerDate(jsonObject.getString(CLE_DATE_ACIVITES),periode[0],periode[1],jsonObject.getString(CLE_DESCRIPTION_ACIVITES));
        }
        return resultat;
    }

    /**
     * Cette methode verifie si le fichier json est valide
     *
     * @return boolean : true si le fichier Json est valide
     */
    public boolean validerFormatJson(JSONObject objetJson) throws ParseException {
        return  validerNom(objetJson.getString(CLE_NOM))
                & validerPrenom(objetJson.getString(CLE_PRENOM)) & validerSexe(objetJson.getInt(CLE_SEXE))
                & determinerNumeroPermis(objetJson.getString(CLE_NUM_PERMIS) , objetJson.getString(CLE_ORDRE)
                                                ,objetJson.getString(CLE_NOM),objetJson.getString(CLE_PRENOM))
                & validerCycle(objetJson.getString(CLE_CYCLE),ordre)
                & validerTableauActivites();
    }

}
