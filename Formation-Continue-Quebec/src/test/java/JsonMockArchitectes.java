import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonMockArchitectes extends JsonClass{

    protected JSONObject objetJson;

    public JsonMockArchitectes(String numero_de_permis,String ordre,String prenom
            ,String nom,int sexe,String cycle,int heures_transferees_du_cycle_precedent) {
        super();
        this.tabloActivites = activiteArchitecte();
        this.objetJson = retournerUnJSONObject( numero_de_permis, ordre, prenom, nom, sexe, cycle, heures_transferees_du_cycle_precedent);
        this.periode = determinerPeriodeCycle(cycle);
    }


    /**
     *
     * @return
     */
    public JSONObject retournerUnJSONObject(String numero_de_permis,String ordre,String prenom
                        ,String nom,int sexe,String cycle,int heures_transferees_du_cycle_precedent) {
        JSONObject architecte = new JSONObject();
        architecte.accumulate("numero_de_permis",numero_de_permis);
        architecte.accumulate("ordre", ordre);
        architecte.accumulate("prenom",prenom);
        architecte.accumulate("nom",nom);
        architecte.accumulate("sexe",sexe);
        architecte.accumulate("cycle",cycle);
        architecte.accumulate("heures_transferees_du_cycle_precedent",heures_transferees_du_cycle_precedent);
        architecte.accumulate("activites",tabloActivites);
        return architecte;
    }

    /**
     *
     * @return
     */
    public JSONArray activiteArchitecte() {
        JSONObject activite = new JSONObject();
        JSONArray activites_json = new JSONArray();

        activite.accumulate("description", "Cours sur la déontologie");
        activite.accumulate("categorie", "cours");
        activite.accumulate("heures", 2);
        activite.accumulate("date", "2018-09-20");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description", "atelier sur la déontologie");
        activite.accumulate("categorie", "atelier");
        activite.accumulate("heures", 3);
        activite.accumulate("date", "2019-03-20");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description", "Rédaction pour le magazine Architecture moderne");
        activite.accumulate("categorie", "rédaction professionnelle");
        activite.accumulate("heures", 4);
        activite.accumulate("date","2018-10-22");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description", "projet de recherche 02");
        activite.accumulate("categorie", "projet de recherche");
        activite.accumulate("heures", 5);
        activite.accumulate("date","2019-10-22");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description","Participation à un groupe de discussion");
        activite.accumulate("categorie","groupe de discussion");
        activite.accumulate("heures",6);
        activite.accumulate("date","2018-06-22");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description","Participation à un colloque");
        activite.accumulate("categorie","colloque");
        activite.accumulate("heures",7);
        activite.accumulate("date","2019-07-20");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description","Participation à un Séminaire");
        activite.accumulate("categorie","séminaire");
        activite.accumulate("heures",9);
        activite.accumulate("date","2018-06-01");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description","Participation à un Séminaire");
        activite.accumulate("categorie","présentation");
        activite.accumulate("heures",10);
        activite.accumulate("date","2019-06-01");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description","Participation à une Lecture dirigée");
        activite.accumulate("categorie","lecture dirigée");
        activite.accumulate("heures",11);
        activite.accumulate("date","2018-06-01");
        activites_json.add(activite);

        activite =  new JSONObject();
        activite.accumulate("description", "conférence sur la déontologie");
        activite.accumulate("categorie", "conférence");
        activite.accumulate("heures", 13);
        activite.accumulate("date", "2019-03-20");
        activites_json.add(activite);

        return activites_json;
    }

}