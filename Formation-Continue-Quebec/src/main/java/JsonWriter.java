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

import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter {


    private String fichierSortant;
    private boolean resultatFinal;
    private ArrayList<String> tableauErreurs;
    private String listeErreursEnString;
    private String jsonContenuTexte;

    /**
     * Cette méthode  est un constructeur qui construit un objet JsonWrite et affecte
     * les attributs d'instances dans le but de créer un fichier JSON sortant.
     *
     * @param fichierSortant, tableauErreurs : le tableau d'erreurs, resultatFinal : résultat final "booléen"
     */
    public JsonWriter(String fichierSortant, ArrayList<String> tableauErreurs, boolean resultatFinal) {
        this.fichierSortant = fichierSortant;
        this.tableauErreurs = tableauErreurs;
        this.resultatFinal = resultatFinal;
        listeErreursEnString = creerStringDesErreurs(tableauErreurs);
        jsonContenuTexte = creerContenuJson(this.resultatFinal, listeErreursEnString);
    }


    /**
     * un constructeur de la classe avec seulment 2 paramètres.
     * @param fichierSortant : le fichier sortant Json.
     * @param jsonContenuTexte : le constenu Json en String
     */
    public JsonWriter(String fichierSortant, String jsonContenuTexte){
        this.fichierSortant = fichierSortant;
        this.jsonContenuTexte = jsonContenuTexte;
    }
    

    /**
     *Cette methode permet d'afficher le contenu du tableau des erreurs en un tableau de format JSON.
     * @param tableauErreurs : le tableau des erreurs
     * @return un String du tableau d'erreurs
     */
    public static String creerStringDesErreurs (ArrayList<String> tableauErreurs) {
        String erreurs = "";

        if (tableauErreurs.size() == 0) {
            erreurs = "[]";
        }else {
            erreurs = "[\n"+afficherListeErreurs(tableauErreurs,erreurs);
        }
        return erreurs;
    }

    /**
     * Cette methode permet d'afficher le contenu du tableau des erreurs.
     * @param tableauErreurs : le tableau des erreurs
     * @param StringDesErreurs : le début de la chaîne de caractères des erreurs
     * @return le tableau des erreurs en chaîne de caractères.
     */
    public static String afficherListeErreurs(ArrayList<String> tableauErreurs,String StringDesErreurs){
        for (int i = 0; i < tableauErreurs.size(); i++) {
            if (i == tableauErreurs.size() -1) {
                StringDesErreurs += "                \""+tableauErreurs.get(i)+ "\"\n              ]";
            }else{
                StringDesErreurs += "                \""+tableauErreurs.get(i) + "\",\n";
            }
        }
        return StringDesErreurs;
    }


    /**
     * Cette méthode crée la chaine de caractère finale qui sera mise dans le fichier JSON sortant.
     *
     * @param resultatFinal,listeErreursEnString :
     * @return jsonText
     */
    public static String creerContenuJson(boolean resultatFinal, String listeErreursEnString) {
        String jsonText ;

        jsonText = "{\n" +
                "   \"complet\": " + resultatFinal + ",\n" +
                "   \"erreurs\": " + listeErreursEnString +

                "\n}";
        return jsonText;
    }


    /**
     * Cette méthode sauvegarde le fichier JSON sortant.
     *
     * @throws IOException :
     */
    public void sauverFichierJson() throws IOException {
        try {
            Utf8File.saveStringIntoFile(this.fichierSortant, jsonContenuTexte);
        } catch (IOException IO) {
            System.err.println("ERREUR!!! Le fichier " + this.fichierSortant + " est introuvable.");
            System.exit(-1);
        }
    }

}