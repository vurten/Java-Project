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

public class PsychologuesTest {

    Validation tester = new Validation();
    JsonMockPsychologues declarationPsychologues = new JsonMockPsychologues("83723-34","psychologues","Jaque"
            ,"Berger",1,"2018-2020");

    @Before
    public void beforEach(){
        declarationPsychologues = new JsonMockPsychologues("83723-34","psychologues","Jaque"
                ,"Berger",1,"2016-2021");
    }

    @Test
    public void testerPeriode1() {
        String[] periode = new String[]{"2016-01-01", "2021-01-01"};

        Assert.assertArrayEquals(periode,tester.determinerPeriodeCycle("2016-2021"));
    }

    @Test
    public void periodeNonFonctionnel1() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2021"));
    }

    @Test
    public void periodeNonFonctionnel2() {
        String[] periode = new String[]{"2018-04-01", "2020-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2021"));
    }

    @Test
    public void periodeNonFonctionnel3() {
        String[] periode = new String[]{"2016-06-01", "2019-01-02"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2021"));
    }

    @Test
    public void verifierOrdre1() throws IOException {
        try {
            Assert.assertEquals("valider l'ordre psychologues",tester.validerOrdre("psychologues"), "psychologues");
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void ordreNonFonctionnel1() throws IOException {
        try {
            tester.validerOrdre("Psychologues");
            fail("devrais levé une exeption");
        } catch (NullPointerException e){
        }
    }

    @Test
    public void ordreNonFonctionnel2() throws IOException {
        try {
            tester.validerOrdre("psychologue");
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

        Assert.assertTrue(tester.validerCycle("2016-2021","psychologues" ));
    }

    @Test
    public void cycleNonFonctionnel1() {

        Assert.assertFalse(tester.validerCycle("2018-2020","psychologues" ));
    }

    @Test
    public void cycleNonFonctionnel2() {

        Assert.assertFalse(tester.validerCycle("2016-2019","psychologues" ));
    }

    @Test
    public void cycleNonFonctionnel3() {

        Assert.assertFalse(tester.validerCycle("ABCD-2021","psychologues" ));
    }

    @Test
    public void verifierIntervalle1() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2016-04-01","2016-04-01","2021-04-01" ));
    }

    @Test
    public void verifierIntervalle2() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2021-04-01","2016-04-01","2021-04-01" ));
    }

    @Test
    public void verifierIntervalle3() throws ParseException {
        Assert.assertTrue(tester.validerIntervalleDate("2019-06-01","2016-04-01","2021-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel1() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2016-03-31","2016-04-01","2021-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel2() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2021-04-02","2016-04-01","2021-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel3() throws ParseException {
        Assert.assertFalse(tester.validerIntervalleDate("2014-04-01","2016-04-01","2021-04-01" ));
    }

    @Test
    public void intervalleNonFonctionnel4() {
        Assert.assertFalse(tester.validerFormatDate("2016-04-0A",tester.ERR_MSG_FORMAT_DATE));
    }

    @Test
    public void dateFonctionnel1() throws ParseException {
        Assert.assertTrue(tester.validerDate("2016-01-01","2016-01-01","2021-01-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel2() throws ParseException {
        Assert.assertTrue(tester.validerDate("2021-01-01","2016-01-01","2021-01-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel3() throws ParseException {
        Assert.assertTrue(tester.validerDate("2019-04-01","2016-06-01","2021-01-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel1() throws ParseException {
        Assert.assertFalse(tester.validerDate("2015-04-01","2016-01-01","2021-01-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel2() throws ParseException {
        Assert.assertFalse(tester.validerDate("2021-04-01","2016-01-01","2021-01-01" ,"description de l'activitee"));
    }

    @Test
    public void HeuresTotalesMax() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2016-2021","psychologues"),90);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel1() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2021","psychologues"),40);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel2() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2021","psychologues"),0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel3() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2021","psychologues"),91);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel4() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2021","psychologues"),-1);
    }

    @Test
    public void cycle1() {
        Assert.assertEquals(tester.determinerCycle("2016-2021"), "2016-2021");
    }

    @Test
    public void verifierCycleNonFonctionnel1() {
        Assert.assertFalse(tester.validerCycle("2018-2020","psychologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel2() {
        Assert.assertFalse(tester.validerCycle("2014-2016","psychologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel3() {
        Assert.assertFalse(tester.validerCycle("2016-2020","psychologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel4() {
        Assert.assertFalse(tester.validerCycle("2018-2020","Psychologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel5() {
        Assert.assertFalse(tester.validerCycle("2016-2021","psychologue"));
    }

    @Test
    public void verifierCycleNonFonctionnel6() {
        Assert.assertFalse(tester.validerCycle("2016-2021","Psychologues"));
    }

    @Test
    public void numeroPermis1() {
        Assert.assertTrue(tester.determinerNumeroPermis("11111-11" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis2() {
        Assert.assertTrue(tester.determinerNumeroPermis("22222-22" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis3() {

        Assert.assertTrue(tester.determinerNumeroPermis("33333-33" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis4() {

        Assert.assertTrue(tester.determinerNumeroPermis("99999-99" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis5() {
        Assert.assertTrue(tester.determinerNumeroPermis("44444-44" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis6() {
        Assert.assertTrue(tester.determinerNumeroPermis("55555-55" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis7() {
        Assert.assertTrue(tester.determinerNumeroPermis("66666-66" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis8() {
        Assert.assertTrue(tester.determinerNumeroPermis("77777-77" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis9() {
        Assert.assertTrue(tester.determinerNumeroPermis("88888-88" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis10() {
        Assert.assertTrue(tester.determinerNumeroPermis("00000-00" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis11() {
        Assert.assertTrue(tester.determinerNumeroPermis("01234-56" , "psychologues","nom","prenom"));
    }

    @Test
    public void numeroPermis12() {
        Assert.assertTrue(tester.determinerNumeroPermis("74563-98" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel1() {
        Assert.assertFalse(tester.determinerNumeroPermis("A0001" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel2() {
        Assert.assertFalse(tester.determinerNumeroPermis("T0001" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel3() {
        Assert.assertFalse(tester.determinerNumeroPermis("12-34567" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel4() {
        Assert.assertFalse(tester.determinerNumeroPermis("A1234-56" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel5() {
        Assert.assertFalse(tester.determinerNumeroPermis("123-456" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel6() {
        Assert.assertFalse(tester.determinerNumeroPermis("12345" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel7() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ12345" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel8() {
        Assert.assertFalse(tester.determinerNumeroPermis("BJ1234" , "psychologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel9() {
        Assert.assertFalse(tester.determinerNumeroPermis("12345-A5" , "psychologues","nom","prenom"));
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
        Assert.assertTrue(declarationPsychologues.validerTableauActivites());
    }

    @Test
    public void formatjsonNonFonctionnel() throws ParseException, IOException {
        declarationPsychologues.ordre = declarationPsychologues.validerOrdre(declarationPsychologues
                .objetJson.getString(declarationPsychologues.CLE_ORDRE));
        declarationPsychologues.periode = declarationPsychologues.determinerPeriodeCycle(declarationPsychologues
                .objetJson.getString(declarationPsychologues.CLE_CYCLE));
        Assert.assertTrue(declarationPsychologues.validerFormatJson(declarationPsychologues.objetJson));
    }

    @Test
    public void calculeCours() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.cours,25);
    }

    @Test
    public void calculeAteliers() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.atelier,3);
    }

    @Test
    public void calculeSeminaire() throws IOException{

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.seminaire,9);
    }

    @Test
    public void calculeLectureDirige() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.lectureDirige,11);
    }

    @Test
    public void calculePresentation() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.presentation,10);
    }

    @Test
    public void calculeProjetDeRecherche() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.projetDeRecherche,5);
    }

    @Test
    public void calculeColloque() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.colloque,7);
    }

    @Test
    public void calculeConference() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.conference,15);
    }

    @Test
    public void calculeRedactionProfessionnelle() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.redactionProfessionnelle,4);
    }

    @Test
    public void calculeGroupeDeDiscussion() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertEquals(declarationPsychologues.groupeDeDiscussion,6);
    }

    @Test
    public void calculeHeurestotals() throws IOException {

        declarationPsychologues.parcourirLesActivites();
        Assert.assertTrue(declarationPsychologues.validerEtAdditionnerlesHeuresActivitesArchitectes());
    }


    @Test
    public void validerEtadditionnerHeures() throws IOException {
        declarationPsychologues.parcourirLesActivites();
        Assert.assertTrue(declarationPsychologues.validerEtAdditionnerlesHeuresActivites("psychologues"));

    }

    @Test
    public void validationprimaire() throws ParseException,IOException {
        declarationPsychologues.validationPrimaire(declarationPsychologues.objetJson);
        Assert.assertTrue(declarationPsychologues.validerFormatJson(declarationPsychologues.objetJson));
    }

    @Test
    public void validationTotalHeures() throws IOException{
        declarationPsychologues.ordre = declarationPsychologues.validerOrdre(declarationPsychologues
                .objetJson.getString(declarationPsychologues.CLE_ORDRE));
        declarationPsychologues.periode = declarationPsychologues.determinerPeriodeCycle(declarationPsychologues
                .objetJson.getString(declarationPsychologues.CLE_CYCLE));
        declarationPsychologues.parcourirLesActivites();
        boolean resultat = declarationPsychologues.validerEtAdditionnerlesHeuresActivites(declarationPsychologues.ordre);
        ;
        Assert.assertTrue(declarationPsychologues.traiterLeResultatFinal(resultat,declarationPsychologues
                .objetJson.getString(declarationPsychologues.CLE_CYCLE),declarationPsychologues.ordre));
    }



    @After
    public void afterEach() {

        declarationPsychologues = null;
    }

}
