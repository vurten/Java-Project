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


import net.sf.json.JSONObject;

import java.io.IOException;

public class Main {

    public static final String MSG_RESULTAT_SUCCES = "Le fichier sortant a été crée avec succès, veuillez ouvrir le fichier pour voir les résultats."
            + "\n\nFin du programme";
    public static final String MSG_CONFIRMER_RESET = "Les statistiques ont été réinitialisées avec succès.";
    public static final String FICHIER_DE_STATISTIQUES= "statistiques.json";
    public static final String ERREUR_SYNTHAXE = "!!ERREUR  veuillez respecter l'une des synthaxes suivantes :\n"
            +" * pour afficher les statestique veuillez entree uniquement comme argument : -S\n"
            +" * pour renisialiser les statestique entree uniquement comme argument : -SR\n"
            +" * pour produire une declaration veuillez donner le chemin de votre fichier d'entree (exemple : declaration.json).\n"
            +"   comme premier argument et le chemin ainsi que le nom voulu pour le resultat (resultat.json) comme deuxieme argument."
            +" * le programme supporte juste les fichiers de format JSON.";
    public static boolean heuresValide;
    public static boolean resultatFinal;


    /**
     * Cette méthode affiche les statistiques sur la console.
     * @param indiceReset: indice pour réinitialiser ou afficher les statistiques, -S = afficher, -SR = réinitialiser.
     * @throws IOException
     */
    public static void afficherStatistiques(String indiceReset) throws IOException {
        Statistiques stats = new Statistiques(FICHIER_DE_STATISTIQUES,false);

        if (indiceReset.equals("-S")) {
            JSONObject objetJson = stats.recupererObjetJson(FICHIER_DE_STATISTIQUES);
            System.out.println(objetJson.toString(2));
        }else if (indiceReset.equals("-SR")) {
            reinitialiserStats(stats);
        }
    }

    /**
     * Cette méthode réinitialise les statistiques .
     * @param stats : un oblet Statistique
     * @throws IOException
     */
    public static void reinitialiserStats(Statistiques stats) throws IOException {
        stats.updateFichierStatistiques();
        System.out.println(MSG_CONFIRMER_RESET);
    }


    /**
     * Cette méthode permet de creer l'objet Json et parcours toutes les activites ensuite elle cree un fichier
     * sortant Json ainsi qu'un message du resulat final
     *
     * @throws IOException
     */
    public static void longeurEgale2(String[] args) throws Exception {
        JsonClass objet = new JsonClass(args[0], args[1]);
        objet.parcourirLesActivites();
        heuresValide = objet.validerEtAdditionnerlesHeuresActivites(objet.ordre);
        resultatFinal = objet.traiterLeResultatFinal(heuresValide,objet.cycle,objet.ordre);
        objet.creerFichierSortant(resultatFinal,false);
        System.out.println(MSG_RESULTAT_SUCCES);
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 2 && args[0].endsWith(".json") && args[1].endsWith(".json")) {
            longeurEgale2(args);
        } else if (args.length == 1 && (args[0].equals("-S")) || args[0].equals("-SR")) {
            afficherStatistiques(args[0]);
        }else{
            System.out.println(ERREUR_SYNTHAXE);
        }
    }
}