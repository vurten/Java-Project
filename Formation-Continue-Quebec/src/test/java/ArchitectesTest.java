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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static junit.framework.TestCase.fail;

public class ArchitectesTest {

    Validation tester = new Validation();
    JsonMockArchitectes declarationArchitectes = new JsonMockArchitectes("A1234","architectes","Jaque"
            ,"Berger",1,"2018-2020",2);

    @Before
    public void beforEach(){
        declarationArchitectes = new JsonMockArchitectes("A1234","architectes","Jaque"
                ,"Berger",1,"2018-2020",2);
    }




    @Test
    public void testerPeriode1() {
        String[] periode = new String[]{"2018-04-01", "2020-04-01"};

        Assert.assertArrayEquals(periode,tester.determinerPeriodeCycle("2018-2020"));
    }

    @Test
    public void testerPeriode2() {
        String[] periode = new String[]{"2014-04-01", "2016-07-01"};

        Assert.assertArrayEquals(periode,tester.determinerPeriodeCycle("2014-2016"));
    }

    @Test
    public void testerPeriode3() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertArrayEquals(periode,tester.determinerPeriodeCycle("2016-2018"));
    }

    @Test
    public void periodeNonFonctionnel1() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2017-2018"));
    }

    @Test
    public void periodeNonFonctionnel2() {
        String[] periode = new String[]{"2014-04-01", "2016-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2014-2016"));
    }

    @Test
    public void verifierOrdre1() throws IOException {
        try {
            Assert.assertEquals("valider l'ordre architectes",tester.validerOrdre("architectes"),"architectes");
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void ordreNonFonctionnel1() throws IOException {
        try {
            tester.validerOrdre("Architectes");
            fail("devrais levé une exeption");
        } catch (NullPointerException e){
        }
    }

    @Test
    public void ordreNonFonctionnel2() throws IOException {
        try {
            tester.validerOrdre("architecte");
            fail("devrais levé une exeption");
        } catch (NullPointerException e){

        }
    }

    @Test
    public void ordreNonFonctionnel3() throws IOException {
        try {
            tester.validerOrdre("12345");
            fail("devrais levé une exeption");
        } catch (NullPointerException e){

        }
    }

    @Test
    public void verifierCycle1() {

        Assert.assertTrue(tester.validerCycle("2014-2016","architectes" ));
    }

    @Test
    public void verifierCycle2() {

        Assert.assertTrue(tester.validerCycle("2016-2018","architectes" ));
    }

    @Test
    public void verifierCycle3() {

        Assert.assertTrue(tester.validerCycle("2018-2020","architectes" ));
    }

    @Test
    public void cycleNonFonctionnel1() {

        Assert.assertFalse(tester.validerCycle("2016-2019","architectes" ));
    }

    @Test
    public void cycleNonFonctionnel2() {

        Assert.assertFalse(tester.validerCycle("2016-2021","architectes" ));
    }

    @Test
    public void cycleNonFonctionnel3() {

        Assert.assertFalse(tester.validerCycle("ABCD-2021","architectes" ));
    }

    @Test
    public void verifierIntervalle1() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2018-04-01","2018-04-01","2020-04-01" ));
    }

    @Test
    public void verifierIntervalle2() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2020-04-01","2018-04-01","2020-04-01" ));
    }

    @Test
    public void verifierIntervalle3() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2019-01-01","2018-04-01","2020-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel1() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2018-03-31","2018-04-01","2020-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel2() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2020-05-01","2018-04-01","2020-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel3() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2016-04-01","2018-04-01","2020-04-01" ));
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

        Assert.assertEquals(tester.validerHeuresCyclePrec(8),7);
    }

    @Test
    public void verifierCyclePrec3() {

        Assert.assertEquals(tester.validerHeuresCyclePrec(4), 4);
    }


    @Test
    public void heuresActivite1() {

        Assert.assertEquals(tester.nbrHeuresActivite(43,42),42);
    }

    @Test
    public void heuresActivite2() {

        Assert.assertEquals(tester.nbrHeuresActivite(0,42),0);
    }

    @Test
    public void heuresActivite3() {

        Assert.assertEquals(tester.nbrHeuresActivite(17,42),17);
    }

    @Test
    public void heuresActiviteNonFonctionnel1() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(43,42),43);
    }

    @Test
    public void heuresActiviteNonFonctionnel2() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(1,42),0);
    }

    @Test
    public void heuresActiviteNonFonctionnel3() {

        Assert.assertNotEquals(tester.nbrHeuresActivite(0,42),1);
    }

    @Test
    public void minActivite1() {

        Assert.assertTrue(tester.validerMinimumActivites(1,0,"Fonctionnel"));
    }

    @Test
    public void minActivite2() {

        Assert.assertTrue(tester.validerMinimumActivites(65,17,"Fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel1() {

        Assert.assertTrue(tester.validerMinimumActivites(0,0,"Non fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel2() {

        Assert.assertFalse(tester.validerMinimumActivites(0,1,"Non fonctionnel"));
    }

    @Test
    public void minActiviteNonFonctionnel3() {

        Assert.assertFalse(tester.validerMinimumActivites(10,40,"Non fonctionnel"));
    }

    @Test
    public void HeuresTotalesMax1() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2018-2020","architectes"),40);
    }

    @Test
    public void HeuresTotalesMax2() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2016-2018","architectes"),42);
    }

    @Test
    public void HeuresTotalesMax3() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2014-2016","architectes"),42);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel1() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020","architectes"),0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel2() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020","architectes"),41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel3() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2018-2020","architectes"),42);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel4() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018","architectes"),43);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel5() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018","architectes"),0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel6() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2018","architectes"),41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel7() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016","architectes"),43);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel8() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016","architectes"),0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel9() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016","architectes"),41);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel10() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2014-2016","architectes"),40);
    }

    @Test
    public void cycle1() {
        Assert.assertTrue(tester.validerCycle("2018-2020", "architectes"));
    }

    @Test
    public void cycle2() {
        Assert.assertTrue(tester.validerCycle("2016-2018", "architectes"));
    }

    @Test
    public void cycle3() {
        Assert.assertTrue(tester.validerCycle("2014-2016", "architectes"));
    }

    @Test
    public void verifierCycleNonFonctionnel1() {
        Assert.assertFalse(tester.validerCycle("2016-2021","architectes" ));
    }

    @Test
    public void verifierCycleNonFonctionnel2() {
        Assert.assertFalse(tester.validerCycle("2014-2020","architectes"));
    }

    @Test
    public void verifierCycleNonFonctionnel3() {
        Assert.assertFalse(tester.validerCycle("2016-2020","architectes" ));
    }

    @Test
    public void verifierCycleNonFonctionnel4() {
        Assert.assertFalse(tester.validerCycle("2018-2020","Architectes" ));
    }

    @Test
    public void verifierCycleNonFonctionnel5()  {
        Assert.assertFalse(tester.validerCycle("2018-2020","architecte" ));
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
        Assert.assertTrue(tester.determinerNumeroPermis("A0000" , "architectes","nom","prenom"));
    }

    @Test
    public void numeroPermis2() {
        Assert.assertTrue(tester.determinerNumeroPermis("A9999", "architectes", "nom", "prenom"));
    }

    @Test
    public void numeroPermis3() {
        Assert.assertTrue(tester.determinerNumeroPermis("A1234" , "architectes","nom","prenom"));
    }

    @Test
    public void numeroPermis4() {
        Assert.assertTrue(tester.determinerNumeroPermis("T0000", "architectes", "nom", "prenom"));
    }

    @Test
    public void numeroPermis5() {
        Assert.assertTrue(tester.determinerNumeroPermis("T9999" , "architectes","nom","prenom"));
    }

    @Test
    public void numeroPermis6() {
        Assert.assertTrue(tester.determinerNumeroPermis("T1234" , "architectes","nom","prenom"));
    }


    @Test
    public void verifiernumeroPermisNonFonctionnel1() {
        Assert.assertFalse(tester.determinerNumeroPermis("A00001" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel2() {
        Assert.assertFalse(tester.determinerNumeroPermis("T00001" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel3() {
        Assert.assertFalse(tester.determinerNumeroPermis("12-34567" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel4() {
        Assert.assertFalse(tester.determinerNumeroPermis("A1234-56" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel5() {
        Assert.assertFalse(tester.determinerNumeroPermis("123-456" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel6() {
        Assert.assertFalse(tester.determinerNumeroPermis("12345" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel7() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ12345" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel8() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ1234" , "architectes","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel9() {
        Assert.assertFalse(tester.determinerNumeroPermis("12345-A5" , "architectes","nom","prenom"));
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
        Assert.assertTrue(declarationArchitectes.validerTableauActivites());
    }

    @Test
    public void formatjsonNonFonctionnel() throws ParseException, IOException {
        declarationArchitectes.ordre = declarationArchitectes.validerOrdre(declarationArchitectes
                .objetJson.getString(declarationArchitectes.CLE_ORDRE));
        declarationArchitectes.periode = declarationArchitectes.determinerPeriodeCycle(declarationArchitectes
                .objetJson.getString(declarationArchitectes.CLE_CYCLE));
        Assert.assertTrue(declarationArchitectes.validerFormatJson(declarationArchitectes.objetJson));
    }

    @Test
    public void calculeCours() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.cours,2);
    }

    @Test
    public void calculeAteliers() throws IOException{

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.atelier,3);
    }

    @Test
    public void calculeSeminaire() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.seminaire,9);
    }

    @Test
    public void calculeLectureDirige() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.lectureDirige,11);
    }

    @Test
    public void calculePresentation() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.presentation,10);
    }

    @Test
    public void calculeProjetDeRecherche() throws IOException{

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.projetDeRecherche,5);
    }

    @Test
    public void calculeColloque() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.colloque,7);
    }

    @Test
    public void calculeConference() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.conference,13);
    }

    @Test
    public void calculeRedactionProfessionnelle() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.redactionProfessionnelle,4);
    }

    @Test
    public void calculeGroupeDeDiscussion() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertEquals(declarationArchitectes.groupeDeDiscussion,6);
    }

    @Test
    public void calculeHeurestotals() throws IOException {

        declarationArchitectes.parcourirLesActivites();
        Assert.assertTrue(declarationArchitectes.validerEtAdditionnerlesHeuresActivitesArchitectes());
    }


    @Test
    public void validerEtadditionnerHeures() throws IOException {
        declarationArchitectes.parcourirLesActivites();
        Assert.assertTrue(declarationArchitectes.validerEtAdditionnerlesHeuresActivites("architectes"));

    }

    @Test
    public void validationprimaire() throws ParseException,IOException {
        declarationArchitectes.validationPrimaire(declarationArchitectes.objetJson);
        Assert.assertTrue(declarationArchitectes.validerFormatJson(declarationArchitectes.objetJson));
    }

    @Test
    public void validationTotalHeures() throws IOException{
        declarationArchitectes.ordre = declarationArchitectes.validerOrdre(declarationArchitectes
                .objetJson.getString(declarationArchitectes.CLE_ORDRE));
        declarationArchitectes.periode = declarationArchitectes.determinerPeriodeCycle(declarationArchitectes
                .objetJson.getString(declarationArchitectes.CLE_CYCLE));
        declarationArchitectes.parcourirLesActivites();
        boolean resultat = declarationArchitectes.validerEtAdditionnerlesHeuresActivites(declarationArchitectes.ordre);
        ;
        Assert.assertTrue(declarationArchitectes.traiterLeResultatFinal(resultat,declarationArchitectes
                .objetJson.getString(declarationArchitectes.CLE_CYCLE),declarationArchitectes.ordre));
    }

    @After
    public void afterEach() {

        declarationArchitectes = null;
    }


}