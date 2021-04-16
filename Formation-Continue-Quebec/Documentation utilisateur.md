### FORMATION CONTINUE MANUEL D’UTILISATION POUR UTILISATEUR

  

 1. #### Exigences matérielles :
Vous devez avoir un ordinateur, un clavier et une souris. Sans ça, vous ne pourriez pas utiliser le logiciel.

2. #### Exigences logicielles :
- IntelliJ version: 2019.1
- Java 11

3. #### Paramètres du programme :
Le programme prend en paramètre un fichier dont l’extension est **.json**. Sans ce type de fichier là, vous ne pourriez pas utiliser le logiciel. Vous allez voir apparaître un message d’erreur. (Vous allez voir en dessous le contenu de ce fichier-là plus en détails).

4. #### Fichier de sortie:
Le logiciel va générer deux fichiers de sorties en format **.json**. Le premier fichier sera un fichier résultat et l’autre fichier va contenir des statistiques. (Vous allez voir en dessous le contenu de ces fichiers-là plus en détails).

Pour le fichier qui contient les statistiques, l'utilisateur exécutera le logiciel en spécifiant, en paramètres, l'option **-S** et les statistiques seront affichées à la console. 
- Par exemple : java -jar FormationContinue -S

L'option **-SR**  réinitialise les statistiques, c'est-à-dire les remettre à 0. 
- Par exemple : java -jar FormationContinue -SR

5. #### Structure du fichier d'entrée exemple
Votre fichier d’entrée dont l’extension est .json doit exactement ressemble a ça :
```
{
"ordre": "géologues",
"nom": "Berger",
"prenom": "Jacque",
"sexe": 0,
"numero_de_permis": "BJ3453",
"cycle": "2016-2019",
"heures_transferees_du_cycle_precedent": 1,
"activités": [{
"description": "Cours sur la déontologie",
"categorie": "cours",
"heures": 4,
"date": "2019-03-20"
}]
}
```

6. #### Signification des champs du fichier d’entrée

- pour "ordre": il faut juste écrire des lettres entre des guillemets “ ” et finir avec une virgule ,

- pour "nom": il faut juste écrire des lettres entre des guillemets “ ” et finir avec une virgule ,

- pour "prenom" : il faut juste écrire des lettres entre des guillemets “ ” et finir avec une virgule ,

- pour "sexe": il faut juste écrire des chiffres et finir avec une virgule (ne mettez pas vos chiffres entre des guillemets)

- pour "numero_de_permis": il faut écrire des chiffres et des lettres entre des guillemets “ ” et finir par une virgule ,

- pour "cycle": il faut juste écrire des chiffres et mettre un tiré – entre les chiffres puis mettre cela entre des guillemets “ ” et finir avec une virgule ,

- pour "heures_transferees_du_cycle_precedent": il faut juste écrire des chiffres et finir avec une virgule (ne mettez pas vos chiffres entre des guillemets) “ “

- pour "description": il faut juste écrire des lettres entre des guillemets “ ” et finir avec une virgule ,

- pour "categorie": il faut juste écrire des lettres entre des guillemets “ ” et finir avec une virgule ,

- pour "heures": il faut juste écrire des chiffres et finir avec une virgule (ne mettez pas vos chiffres entre des guillemets)

- pour "date": elle doit respecter cet ordre AAAA-MM-JJ la mettre entre des guillemets “ ” et finir avec une virgule ,

Si vous souhaitez ajouter d’autres activités vous devez faire une virgule , après l’accolade fermante } de "date" et commencez à ajouter d'autres activités.

7. #### Structure des fichiers de sortie

  

- #### Fichier résultat exemple :
```
{
"complet": false,
"erreurs": ["l'activité :
"il manque 18 heures pour atteindre le minimum de 22 heures pour les cours.",
"il manque 33 heure(s) pour compléter le cycle."]
}
```
- #### Signification des champs du fichier résultat

- pour  "complet": vrai si la personne a complété sa formation continue sinon c’est faux

- pour "erreurs": le logiciel vérifiera le fichier puis affichera toutes les erreurs qu’il va trouve

  

- #### Fichier statistiques exemple
```
{
  "le nombre total de déclarations traitées": 2,
  "le nombre total de déclarations complètes": 2,
  "le nombre total de déclarations incomplètes ou invalides": 0,
  "le nombre total de déclarations faites par des hommes": 2,
  "le nombre total de déclarations faites par des femmes": 0,
  "le nombre total de déclarations faites par des gens de sexe inconnu": 0,
  "le nombre total d'activités dans les déclarations": 10,
  "le nombre d'activités pour la catégorie (cours)": 2,
  "le nombre d'activités pour la catégorie (atelier)": 2,
  "le nombre d'activités pour la catégorie (séminaire)": 2,
  "le nombre d'activités pour la catégorie (colloque)": 0,
  "le nombre d'activités pour la catégorie (conférence)": 0,
  "le nombre d'activités pour la catégorie (lecture dirigée)": 0,
  "le nombre d'activités pour la catégorie (présentation)": 0,
  "le nombre d'activités pour la catégorie (groupe de discussion)": 2,
  "le nombre d'activités pour la catégorie (projet de recherche)": 2,
  "le nombre d'activités pour la catégorie (rédaction professionnelle)": 0,
  "le nombre total de déclarations valides et complètes pour l'ordre des architectes": 0,
  "le nombre total de déclarations valides et complètes pour l'ordre des géologues": 2,
  "le nombre total de déclarations valides et complètes pour l'ordre des psychologues": 0,
  "le nombre total de déclarations valides et complètes pour l'ordre des podiatres": 0,
  "le nombre total de déclarations valides et incomplètes pour l'ordre des architectes": 0,
  "le nombre total de déclarations valides et incomplètes pour l'ordre des géologues": 0,
  "le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues": 0,
  "le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres": 0,
  "le nombre de déclarations soumises avec un numéro de permis invalide": 0
}
 ```

- #### Signification des champs du fichier résultat

- pour "le nombre total de déclarations traitées": le nombre total de déclarations traitées.
- pour "le nombre total de déclarations complètes": le nombre total de déclarations complètes.
- pour "le nombre total de déclarations incomplètes ou invalides": le nombre total de déclarations incomplètes ou invalides.
- pour "le nombre total de déclarations faites par des hommes": le nombre total de déclarations faites par des hommes.
- pour "le nombre total de déclarations faites par des femmes": le nombre total de déclarations faites par des femmes.
- pour "le nombre total de déclarations faites par des gens de sexe inconnu": le nombre total de déclarations faites par des gens de sexe inconnu.
- pour "le nombre total d'activités dans les déclarations": le nombre total d'activités dans les déclarations.
- pour "le nombre d'activités pour la catégorie (cours)": le nombre d'activités pour la catégorie cours.
- pour "le nombre d'activités pour la catégorie (atelier)": le nombre d'activités pour la catégorie atelier.
- pour "le nombre d'activités pour la catégorie (séminaire)": le nombre d'activités pour la catégorie séminaire.
- pour "le nombre d'activités pour la catégorie (colloque)": le nombre d'activités pour la catégorie colloque.
- pour "le nombre d'activités pour la catégorie (conférence)": le nombre d'activités pour la catégorie conférence.
- pour "le nombre d'activités pour la catégorie (lecture dirigée)": le nombre d'activités pour la catégorie lecture dirigée.
- pour "le nombre d'activités pour la catégorie (présentation)": le nombre d'activités pour la catégorie présentation.
- pour "le nombre d'activités pour la catégorie (groupe de discussion)": le nombre d'activités pour la catégorie groupe de discussion.
- pour "le nombre d'activités pour la catégorie (projet de recherche)": le nombre d'activités pour la catégorie projet de recherche.
- pour "le nombre d'activités pour la catégorie (rédaction professionnelle)": le nombre d'activités pour la catégorie rédaction professionnelle.
- pour "le nombre total de déclarations valides et complètes pour l'ordre des architectes": le nombre total de déclarations valides et complètes pour l'ordre des architectes.
- pour "le nombre total de déclarations valides et complètes pour l'ordre des géologues": le nombre total de déclarations valides et complètes pour l'ordre des géologues.
- pour "le nombre total de déclarations valides et complètes pour l'ordre des psychologues": le nombre total de déclarations valides et complètes pour l'ordre des psychologues.
- pour "le nombre total de déclarations valides et complètes pour l'ordre des podiatres": le nombre total de déclarations valides et complètes pour l'ordre des podiatres.
- pour "le nombre total de déclarations valides et incomplètes pour l'ordre des architectes": le nombre total de déclarations valides et incomplètes pour l'ordre des architectes.
- pour "le nombre total de déclarations valides et incomplètes pour l'ordre des géologues": le nombre total de déclarations valides et incomplètes pour l'ordre des géologues.
- pour "le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues": le nombre total de déclarations valides et incomplètes pour l'ordre des psychologues.
- pour "le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres": le nombre total de déclarations valides et incomplètes pour l'ordre des podiatres.
- pour "le nombre de déclarations soumises avec un numéro de permis invalide": le nombre de déclarations soumises avec un numéro de permis invalide.
