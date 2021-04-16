/* Copyright 2019 Khaled Zioual , Mohamed Chaouki , Okitapoy Koy Christian.
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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static junit.framework.TestCase.fail;

public class PodiatresTest {


    Validation tester = new Validation();
    JsonMockPodiatres declarationPodiatres = new JsonMockPodiatres("12345","podiatres","Jaque"
                    ,"Berger",1,"2018-2020",2);

    @Before
    public void beforEach() {
        declarationPodiatres = new JsonMockPodiatres("12345","podiatres","Jaque"
                ,"Berger",1,"2018-2020",2);
    }


    @Test
    public void testerPeriode1() {
        String[] periode = new String[]{"2018-04-01", "2020-04-01"};

        Assert.assertArrayEquals(periode, tester.determinerPeriodeCycle("2018-2020"));
    }

    @Test
    public void testerPeriode2() {
        String[] periode = new String[]{"2014-04-01", "2016-07-01"};

        Assert.assertArrayEquals(periode, tester.determinerPeriodeCycle("2014-2016"));
    }

    @Test
    public void testerPeriode3() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertArrayEquals(periode, tester.determinerPeriodeCycle("2016-2018"));
    }

    @Test
    public void periodeNonFonctionnel1() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertNotEquals(periode, tester.determinerPeriodeCycle("2017-2018"));
    }

    @Test
    public void periodeNonFonctionnel2() {
        String[] periode = new String[]{"2014-04-01", "2016-04-01"};

        Assert.assertNotEquals(periode, tester.determinerPeriodeCycle("2014-2016"));
    }

    @Test
    public void verifierOrdre1() throws IOException {
        try {
            Assert.assertEquals("valider l'ordre podiatres", tester.validerOrdre("podiatres"), "podiatres");
        } catch (NullPointerException e) {
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void ordreNonFonctionnel1() throws IOException {
        try {
            tester.validerOrdre("podiatre");
            fail("devrais levé une exeption");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void ordreNonFonctionnel2() throws IOException {
        try {
            tester.validerOrdre("Podiatres");
            fail("devrais levé une exeption");
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void ordreNonFonctionnel3() throws IOException {
        try {
            tester.validerOrdre("12345");
            fail("devrais levé une exeption");
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void verifierCycle1() {

        Assert.assertTrue(tester.validerCycle("2014-2016", "podiatres"));
    }

    @Test
    public void verifierCycle2() {

        Assert.assertTrue(tester.validerCycle("2016-2018", "podiatres"));
    }

    @Test
    public void verifierCycle3() {

        Assert.assertTrue(tester.validerCycle("2018-2020", "podiatres"));
    }

    @Test
    public void cycleNonFonctionnel1() {

        Assert.assertFalse(tester.validerCycle("2016-2019", "podiatres"));
    }

    @Test
    public void cycleNonFonctionnel2() {

        Assert.assertFalse(tester.validerCycle("2016-2021", "podiatres"));
    }

    @Test
    public void cycleNonFonctionnel3() {

        Assert.assertFalse(tester.validerCycle("ABCD-2021", "podiatres"));
    }

    @Test
    public void verifierIntervalle1() throws ParseException, IOException {
        Assert.assertTrue(tester.validerIntervalleDate("2018-04-01", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void verifierIntervalle2() throws ParseException, IOException {
        Assert.assertTrue(tester.validerIntervalleDate("2020-04-01", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void verifierIntervalle3() throws ParseException, IOException {
        Assert.assertTrue(tester.validerIntervalleDate("2019-01-01", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void intervalleNonFonctionnel1() throws ParseException, IOException {
        Assert.assertFalse(tester.validerIntervalleDate("2018-03-31", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void intervalleNonFonctionnel2() throws ParseException, IOException {
        Assert.assertFalse(tester.validerIntervalleDate("2020-05-01", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void intervalleNonFonctionnel3() throws ParseException, IOException {
        Assert.assertFalse(tester.validerIntervalleDate("2016-04-01", "2018-04-01", "2020-04-01"));
    }

    @Test
    public void dateFonctionnel1() throws ParseException {
        Assert.assertTrue(tester.validerDate("2018-04-01","2018-04-01","2020-04-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel2() throws ParseException {
        Assert.assertTrue(tester.validerDate("2020-04-01","2018-04-01","2020-04-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel3() throws ParseException {
        Assert.assertTrue(tester.validerDate("2019-04-01","2018-04-01","2020-04-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel1() throws ParseException {
        Assert.assertFalse(tester.validerDate("2016-04-01","2018-04-01","2020-04-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel2() throws ParseException {
        Assert.assertFalse(tester.validerDate("2021-04-01","2018-04-01","2020-04-01" ,"description de l'activitee"));
    }



    @Test
    public void verifierCyclePrec2() {

        Assert.assertEquals(tester.validerHeuresCyclePrec(8), 7);
    }

    @Test
    public void verifierCyclePrec3() {

        Assert.assertEquals(tester.validerHeuresCyclePrec(4), 4);
    }


    @Test
    public void heuresActivite1() {

        Assert.assertEquals(tester.nbrHeuresActivite(43, 42), 42);
    }

    @Test
    public void heuresActivite2() {

        Assert.assertEquals(tester.nbrHeuresActivite(0, 42), 0);
    }

    @Test
    public void heuresActivite3() {

        Assert.assertEquals(tester.nbrHeuresActivite(17, 42), 17);
    }

    @Test
    public void heuresActiviteNonFonctionnel1() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(43, 42), 43);
    }

    @Test
    public void heuresActiviteNonFonctionnel2() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(1, 42), 0);
    }

    @Test
    public void heuresActiviteNonFonctionnel3() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(0, 42), 1);
    }

    @Test
    public void minActivite1() {

        Assert.assertTrue(tester.validerMinimumActivites(1, 0, "Fonctionnel"));
    }

    @Test
    public void minActivite2() {

        Assert.assertTrue(tester.validerMinimumActivites(65, 17, "Fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel1() {

        Assert.assertTrue(tester.validerMinimumActivites(0, 0, "Non fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel2() {

        Assert.assertFalse(tester.validerMinimumActivites(0, 1, "Non fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel3() {

        Assert.assertFalse(tester.validerMinimumActivites(10, 40, "Non fonctionnel"));
    }

    @Test
    public void HeuresTotalesMax1() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2018-2020", "podiatres"), 60);
    }

    @Test
    public void HeuresTotalesMax2() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2016-2018", "podiatres"), 60);
    }

    @Test
    public void HeuresTotalesMax3() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2014-2016", "podiatres"), 60);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel1() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020", "podiatres"), 0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel2() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020", "podiatres"), 41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel3() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020", "podiatres"), 42);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel4() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018", "podiatres"), 43);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel5() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018", "podiatres"), 0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel6() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018", "podiatres"), 41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel7() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016", "podiatres"), 43);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel8() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016", "podiatres"), 0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel9() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016", "podiatres"), 41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel10() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016", "podiatres"), 40);
    }

    @Test
    public void cycle1() {
        Assert.assertTrue(tester.validerCycle("2018-2020", "podiatres"));
    }

    @Test
    public void cycle2() {
        Assert.assertTrue(tester.validerCycle("2016-2018", "podiatres"));
    }

    @Test
    public void cycle3() {
        Assert.assertTrue(tester.validerCycle("2014-2016", "podiatres"));
    }

    @Test
    public void verifierCycleNonFonctionnel1() {
        Assert.assertFalse(tester.validerCycle("2016-2021", "podiatres"));
    }

    @Test
    public void verifierCycleNonFonctionnel2() {
        Assert.assertFalse(tester.validerCycle("2014-2020", "podiatres"));
    }

    @Test
    public void verifierCycleNonFonctionnel3() {
        Assert.assertFalse(tester.validerCycle("2016-2020", "podiatres"));
    }

    @Test
    public void verifierCycleNonFonctionnel4() {
        Assert.assertFalse(tester.validerCycle("2018-2020", "Podiatres"));
    }

    @Test
    public void verifierCycleNonFonctionnel5() {
        Assert.assertFalse(tester.validerCycle("2018-2020", "podiatre"));
    }

    @Test
    public void validerTailleDescriptions1() {
        Assert.assertFalse(tester.validerTailleDescriptions("Z12345"));
    }

    @Test
    public void validerTailleDescriptions2() {
        Assert.assertFalse(tester.validerTailleDescriptions("0123456789012345678"));
    }

    @Test
    public void validerTailleDescriptions3() {
        Assert.assertFalse(tester.validerTailleDescriptions(""));
    }

    @Test
    public void TailleDescriptions() {
        Assert.assertTrue(tester.validerTailleDescriptions("012345678901234567890"));
    }

    @Test
    public void numeroPermis1() {
        Assert.assertTrue(tester.determinerNumeroPermis("83453", "podiatres", "nom", "prenom"));
    }

    @Test
    public void numeroPermis2() {
        Assert.assertTrue(tester.determinerNumeroPermis("00000", "podiatres", "nom", "prenom"));
    }

    @Test
    public void numeroPermis3() {
        Assert.assertTrue(tester.determinerNumeroPermis("99999", "podiatres", "nom", "prenom"));
    }

    @Test
    public void numeroPermis4() {
        Assert.assertTrue(tester.determinerNumeroPermis("12345", "podiatres", "nom", "prenom"));
    }

    @Test
    public void numeroPermis5() {
        Assert.assertTrue(tester.determinerNumeroPermis("55555", "podiatres", "nom", "prenom"));
    }

    @Test
    public void numeroPermis6() {
        Assert.assertTrue(tester.determinerNumeroPermis("98765", "podiatres", "nom", "prenom"));
    }


    @Test
    public void verifiernumeroPermisNonFonctionnel1() {
        Assert.assertFalse(tester.determinerNumeroPermis("A00001", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel2() {
        Assert.assertFalse(tester.determinerNumeroPermis("T00001", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel3() {
        Assert.assertFalse(tester.determinerNumeroPermis("12-34567", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel4() {
        Assert.assertFalse(tester.determinerNumeroPermis("A1234-56", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel5() {
        Assert.assertFalse(tester.determinerNumeroPermis("123-456", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel6() {
        Assert.assertFalse(tester.determinerNumeroPermis("123456", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel7() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ12345", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel8() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ1234", "podiatres", "nom", "prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel9() {
        Assert.assertFalse(tester.determinerNumeroPermis("12345-A5", "podiatres", "nom", "prenom"));
    }

    @Test
    public void validerSexe0() {
        Assert.assertTrue(tester.validerSexe(0));
    }

    @Test
    public void validerSexe1() {
        Assert.assertTrue(tester.validerSexe(1));
    }

    @Test
    public void validerSexe2() {
        Assert.assertTrue(tester.validerSexe(2));
    }

    @Test
    public void sexeNonFonctionnel1() {
        Assert.assertFalse(tester.validerSexe(3));
    }

    @Test
    public void sexeNonFonctionnel2() {

        Assert.assertFalse(tester.validerSexe(-1));
    }

    @Test
    public void activiteeFonctionnel1() {
        Assert.assertTrue(tester.validerActivite("cours", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel2() {
        Assert.assertTrue(tester.validerActivite("atelier", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel3() {
        Assert.assertTrue(tester.validerActivite("séminaire", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel4() {
        Assert.assertTrue(tester.validerActivite("colloque", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel5() {
        Assert.assertTrue(tester.validerActivite("conférence", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel6() {
        Assert.assertTrue(tester.validerActivite("lecture dirigée", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel7() {
        Assert.assertTrue(tester.validerActivite("présentation", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel8() {
        Assert.assertTrue(tester.validerActivite("groupe de discussion", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel9() {
        Assert.assertTrue(tester.validerActivite("projet de recherche", "description de l'activitee"));
    }

    @Test
    public void activiteeFonctionnel10() {
        Assert.assertTrue(tester.validerActivite("rédaction professionnelle", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel1() {
        Assert.assertFalse(tester.validerActivite("Cours", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel2() {
        Assert.assertFalse(tester.validerActivite("Atelier", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel3() {
        Assert.assertFalse(tester.validerActivite("Séminaire", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel4() {
        Assert.assertFalse(tester.validerActivite("Colloque", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel5() {
        Assert.assertFalse(tester.validerActivite("Conférence", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel6() {
        Assert.assertFalse(tester.validerActivite("Lecture dirigée", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel7() {
        Assert.assertFalse(tester.validerActivite("Présentation", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel8() {
        Assert.assertFalse(tester.validerActivite("Groupe de discussion", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel9() {
        Assert.assertFalse(tester.validerActivite("Projet de recherche", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel10() {
        Assert.assertFalse(tester.validerActivite("Rédaction professionnelle", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel11() {
        Assert.assertFalse(tester.validerActivite("coursconférence", "description de l'activitee"));
    }

    @Test
    public void activiteeNonFonctionnel12() {
        Assert.assertFalse(tester.validerActivite("activitee", "description de l'activitee"));
    }

    @Test
    public void tableauActiviteNonFonctionnel() throws ParseException {
        Assert.assertTrue(declarationPodiatres.validerTableauActivites());
    }

    @Test
    public void formatjsonNonFonctionnel() throws ParseException, IOException {
        declarationPodiatres.ordre = declarationPodiatres.validerOrdre(declarationPodiatres
                .objetJson.getString(declarationPodiatres.CLE_ORDRE));
        declarationPodiatres.periode = declarationPodiatres.determinerPeriodeCycle(declarationPodiatres
                .objetJson.getString(declarationPodiatres.CLE_CYCLE));
        Assert.assertTrue(declarationPodiatres.validerFormatJson(declarationPodiatres.objetJson));
    }

    @Test
    public void calculeCours() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.cours,2);
    }

    @Test
    public void calculeAteliers() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.atelier,3);
    }

    @Test
    public void calculeSeminaire() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.seminaire,9);
    }

    @Test
    public void calculeLectureDirige() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.lectureDirige,11);
    }

    @Test
    public void calculePresentation() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.presentation,10);
    }

    @Test
    public void calculeProjetDeRecherche() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.projetDeRecherche,5);
    }

    @Test
    public void calculeColloque() throws IOException{

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.colloque,7);
    }

    @Test
    public void calculeConference() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.conference,13);
    }

    @Test
    public void calculeRedactionProfessionnelle() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.redactionProfessionnelle,4);
    }

    @Test
    public void calculeGroupeDeDiscussion() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertEquals(declarationPodiatres.groupeDeDiscussion,6);
    }

    @Test
    public void calculeHeurestotals() throws IOException {

        declarationPodiatres.parcourirLesActivites();
        Assert.assertTrue(declarationPodiatres.validerEtAdditionnerlesHeuresActivitesArchitectes());
    }
    @Test
    public void validerEtadditionnerHeures() throws IOException {
        declarationPodiatres.parcourirLesActivites();
        Assert.assertTrue(declarationPodiatres.validerEtAdditionnerlesHeuresActivites("podiatres"));

    }

    @Test
    public void validationPrimaire1() throws  ParseException , IOException {
        declarationPodiatres.validationPrimaire(declarationPodiatres.objetJson);
        Assert.assertTrue(declarationPodiatres.validerFormatJson(declarationPodiatres.objetJson));

    }

    @Test
    public void validationTotalHeures() throws IOException{
        declarationPodiatres.ordre = declarationPodiatres.validerOrdre(declarationPodiatres
                .objetJson.getString(declarationPodiatres.CLE_ORDRE));
        declarationPodiatres.periode = declarationPodiatres.determinerPeriodeCycle(declarationPodiatres
                .objetJson.getString(declarationPodiatres.CLE_CYCLE));
        declarationPodiatres.parcourirLesActivites();
        boolean resultat = declarationPodiatres.validerEtAdditionnerlesHeuresActivites(declarationPodiatres.ordre);
        ;
        Assert.assertTrue(declarationPodiatres.traiterLeResultatFinal(resultat,declarationPodiatres
                .objetJson.getString(declarationPodiatres.CLE_CYCLE),declarationPodiatres.ordre));
    }

    @After
    public void afterEach() {

        declarationPodiatres = null;
    }



}

