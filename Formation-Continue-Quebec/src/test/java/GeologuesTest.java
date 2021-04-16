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


import org.junit.*;

import java.io.IOException;
import java.text.ParseException;

import static junit.framework.TestCase.fail;

public class GeologuesTest {

    Validation tester = new Validation();
    JsonMockGeologues declarationGeologues = new JsonMockGeologues("BJ3822","géologues","Jaque"
                ,"Berger",1,"2016-2019");

    @Before
    public void beforEach(){
        declarationGeologues = new JsonMockGeologues("BJ3822","géologues","Jaque"
                ,"Berger",1,"2016-2019");
    }

    @Test
    public void testerPeriode1() {
        String[] periode = new String[]{"2016-06-01", "2019-06-01"};

        Assert.assertArrayEquals(periode,tester.determinerPeriodeCycle("2016-2019"));
    }

    @Test
    public void periodeNonFonctionnel1() {
        String[] periode = new String[]{"2016-04-01", "2018-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2019"));
    }

    @Test
    public void periodeNonFonctionnel2() {
        String[] periode = new String[]{"2018-04-01", "2020-04-01"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2019"));
    }

    @Test
    public void periodeNonFonctionnel3() {
        String[] periode = new String[]{"2016-06-01", "2019-06-02"};

        Assert.assertNotEquals(periode,tester.determinerPeriodeCycle("2016-2019"));
    }

    @Test
    public void verifierOrdre1() throws IOException {
        try {
            Assert.assertEquals("valider l'ordre géologues", tester.validerOrdre("géologues") ,"géologues");
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void ordreNonFonctionnel1() throws IOException {
        try {
            tester.validerOrdre("Géologues");
            fail("devrais levé une exeption");
        } catch (NullPointerException e){
        }
    }

    @Test
    public void ordreNonFonctionnel2() throws IOException {
        try {
            tester.validerOrdre("géologue");
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
        Assert.assertTrue(tester.validerCycle("2016-2019","géologues" ));
    }

    @Test
    public void cycleNonFonctionnel1() {

        Assert.assertFalse(tester.validerCycle("2018-2020","géologues" ));
    }

    @Test
    public void cycleNonFonctionnel2() {

        Assert.assertFalse(tester.validerCycle("2016-2021","géologues" ));
    }

    @Test
    public void cycleNonFonctionnel3() {

        Assert.assertFalse(tester.validerCycle("ABCD-2021","géologues" ));
    }

    @Test
    public void verifierIntervalle1() throws ParseException {
        try {
            Assert.assertTrue(tester.validerIntervalleDate("2018-04-01","2016-06-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void verifierIntervalle2() throws ParseException {
        try {
            Assert.assertTrue(tester.validerIntervalleDate("2016-06-01","2016-06-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void verifierIntervalle3() throws ParseException {
        try {
            Assert.assertTrue(tester.validerIntervalleDate("2019-06-01","2016-06-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void intervalleNonFonctionnel1() throws ParseException {
        try {
            Assert.assertFalse(tester.validerIntervalleDate("2016-05-31","2016-06-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void intervalleNonFonctionnel2() throws ParseException {
        try {
            Assert.assertFalse(tester.validerIntervalleDate("2019-06-02","2016-04-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void intervalleNonFonctionnel3() throws ParseException {
        try {
            Assert.assertFalse(tester.validerIntervalleDate("2016-04-01","2016-06-01","2019-06-01" ));
        } catch (NullPointerException e){
            fail("ne devrais pas levé une exeption");
        }
    }

    @Test
    public void dateFonctionnel1() throws ParseException {
        Assert.assertTrue(tester.validerDate("2016-06-01","2016-06-01","2019-06-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel2() throws ParseException {
        Assert.assertTrue(tester.validerDate("2019-06-01","2016-06-01","2019-06-01" ,"description de l'activitee"));
    }

    @Test
    public void dateFonctionnel3() throws ParseException {
        Assert.assertTrue(tester.validerDate("2019-04-01","2016-06-01","2019-06-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel1() throws ParseException {
        Assert.assertFalse(tester.validerDate("2016-04-01","2016-06-01","2019-06-01" ,"description de l'activitee"));
    }

    @Test
    public void dateNonFonctionnel2() throws ParseException {
        Assert.assertFalse(tester.validerDate("2021-04-01","2016-06-01","2019-06-01" ,"description de l'activitee"));
    }

    @Test
    public void intervalleNonFonctionnel4()  {
            Assert.assertFalse(tester.validerFormatDate("2016-06-0A",tester.ERR_MSG_FORMAT_DATE));
    }

    @Test
    public void HeuresTotalesMax() {

        Assert.assertEquals(tester.determinerHeuresTotalesMin("2016-2019","géologues"),55);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel1() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2019","géologues"),40);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel2() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2019","géologues"),0);
    }

    @Test
    public void HeuresTotalesMaxNonFonctionnel3() {

        Assert.assertNotEquals(tester.determinerHeuresTotalesMin("2016-2019","géologues"),56);
    }

    @Test
    public void cycle1() {
        Assert.assertEquals(tester.determinerCycle("2016-2019"), "2016-2019");
    }

    @Test
    public void verifierCycleNonFonctionnel1() {
        Assert.assertFalse(tester.validerCycle("2018-2020","géologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel2()  {
        Assert.assertFalse(tester.validerCycle("2016-2021","géologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel3() {
        Assert.assertFalse(tester.validerCycle("2014-2016","géologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel4()  {
        Assert.assertFalse(tester.validerCycle("2016-2019","Géologues"));
    }

    @Test
    public void verifierCycleNonFonctionnel5()  {
        Assert.assertFalse(tester.validerCycle("2016-2019","Géologue"));
    }

    @Test
    public void verifierCycleNonFonctionnel6()  {
        Assert.assertFalse(tester.validerCycle("2016-2019","géologue"));
    }

    @Test
    public void numeroPermis1() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP1111" , "géologues","nom","prenom"));
    }

    @Test
    public void numeroPermis2() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP9999" , "géologues","nom","prenom"));
    }

    @Test
    public void numeroPermis3() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP1234" , "géologues","nom","prenom"));
    }

    @Test
    public void numeroPermis4() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP1234" , "géologues","Nom","Prenom"));
    }

    @Test
    public void numeroPermis5() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP1234" , "géologues","nom","Prenom"));
    }

    @Test
    public void numeroPermis6() {
        Assert.assertTrue(tester.determinerNumeroPermis("NP1234" , "géologues","Nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel1() {
        Assert.assertFalse(tester.determinerNumeroPermis("A0001" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel2() {
        Assert.assertFalse(tester.determinerNumeroPermis("T0001" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel3() {
        Assert.assertFalse(tester.determinerNumeroPermis("12-34567" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel4() {
        Assert.assertFalse(tester.determinerNumeroPermis("A1234-56" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel5() {
        Assert.assertFalse(tester.determinerNumeroPermis("123-456" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel6()  {
        Assert.assertFalse(tester.determinerNumeroPermis("12345" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel7() {
        Assert.assertFalse(tester.determinerNumeroPermis("np1234" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel8() {
        Assert.assertFalse(tester.determinerNumeroPermis("pn1234" , "géologues","nom","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionnel9() {
        Assert.assertFalse(tester.determinerNumeroPermis("NP1234" , "géologues","","prenom"));
    }

    @Test
    public void verifiernumeroPermisNonFonctionne20()  {
        Assert.assertFalse(tester.determinerNumeroPermis("NP1234" , "géologues","nom",""));
    }

    @Test
    public void verifiernumeroPermisNonFonctionne21()  {
        Assert.assertFalse(tester.determinerNumeroPermis("NP1234" , "géologues","",""));
    }

    @Test
    public void numeropermis(){
        Assert.assertTrue(tester.validerPermisGeologues("BJ3453","Berger","Jacque"));
    }

    @Test
    public void numeropermis2(){
        Assert.assertTrue(tester.determinerNumeroPermis("BJ3453","géologues","Berger","Jacque"));
    }

    @Test
    public void numeropermis3(){
        Assert.assertTrue(tester.validerPermis("BJ3453","géologues","Berger","Jacque"));
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
        Assert.assertTrue(declarationGeologues.validerTableauActivites());
    }

    @Test
    public void formatjsonNonFonctionnel() throws ParseException, IOException {
        declarationGeologues.ordre = declarationGeologues.validerOrdre(declarationGeologues
                .objetJson.getString(declarationGeologues.CLE_ORDRE));
        declarationGeologues.periode = declarationGeologues.determinerPeriodeCycle(declarationGeologues
                .objetJson.getString(declarationGeologues.CLE_CYCLE));
        Assert.assertTrue(declarationGeologues.validerFormatJson(declarationGeologues.objetJson));
    }

    @Test
    public void calculeCours() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.cours,22);
    }

    @Test
    public void calculeAteliers() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.atelier,3);
    }

    @Test
    public void calculeSeminaire() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.seminaire,9);
    }

    @Test
    public void calculeLectureDirige() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.lectureDirige,11);
    }

    @Test
    public void calculePresentation() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.presentation,10);
    }

    @Test
    public void calculeProjetDeRecherche() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.projetDeRecherche,5);
    }

    @Test
    public void calculeColloque() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.colloque,7);
    }

    @Test
    public void calculeConference() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.conference,13);
    }

    @Test
    public void calculeRedactionProfessionnelle() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.redactionProfessionnelle,4);
    }

    @Test
    public void calculeGroupeDeDiscussion() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertEquals(declarationGeologues.groupeDeDiscussion,6);
    }

    @Test
    public void calculeHeurestotals() throws IOException {

        declarationGeologues.parcourirLesActivites();
        Assert.assertTrue(declarationGeologues.validerEtAdditionnerlesHeuresActivitesArchitectes());
    }


    @Test
    public void validerEtadditionnerHeures() throws IOException {
        declarationGeologues.parcourirLesActivites();
        Assert.assertTrue(declarationGeologues.validerEtAdditionnerlesHeuresActivites("géologues"));
    }

    @Test
    public void validationprimaire() throws ParseException,IOException {
        declarationGeologues.validationPrimaire(declarationGeologues.objetJson);
        Assert.assertTrue(declarationGeologues.validerFormatJson(declarationGeologues.objetJson));
    }

    @Test
    public void validationTotalHeures() throws IOException{
        declarationGeologues.ordre = declarationGeologues.validerOrdre(declarationGeologues
                .objetJson.getString(declarationGeologues.CLE_ORDRE));
        declarationGeologues.periode = declarationGeologues.determinerPeriodeCycle(declarationGeologues
                .objetJson.getString(declarationGeologues.CLE_CYCLE));
        declarationGeologues.parcourirLesActivites();
        boolean resultat = declarationGeologues.validerEtAdditionnerlesHeuresActivites(declarationGeologues.ordre);
        ;
        Assert.assertTrue(declarationGeologues.traiterLeResultatFinal(resultat,declarationGeologues
                .objetJson.getString(declarationGeologues.CLE_CYCLE),declarationGeologues.ordre));
    }

    @After
    public void afterEach() {

        declarationGeologues = null;
    }




}
