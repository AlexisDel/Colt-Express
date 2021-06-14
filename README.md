# Projet POGL S4 "Colt Express"

![colt-express](https://user-images.githubusercontent.com/22059248/121907524-6adc2700-cd2c-11eb-85a6-420d68069e09.png)

## Nota bene

:warning: Java 15 est nécessaire pour build et run ce projet.
Open JDK 15 est téléchargable à cette adresse : https://jdk.java.net/archive/

## Implémentations

#### Base (Partie 1.3 à 2.2)
*La totalité des éléments des parties suivantes a été implémenté.*

* (1.3) Modèle réduit
* (1.4) Une belle vue
    * NB : Nous n'avons pas suivi les instructions fournis par le sujet concernant
      cette partie à savoir utiliser les classes et interfaces fournies dans *Conway*.
* (2.0) Une poignée de dollars
* (2.1) Tout est sous controle
* (2.2) Echanges de plombs entre amis
    * NB : Le nombre de joueurs a été fixé à 2 et le nombre de balles à 5.
      (le nombre de joueurs n'est pas configurable pour facilité l'implémentation de
      l'affichage graphique)


#### Ajouts (Partie 2.3)

* Affichage graphique élaboré
* Aléatoire de la partie
    * La position des bandits et des butins est aléatoire
    * Le type de butin ainsi que leur quantité est aléatoire (sauf le magot de la
      locomotive)
* Le Marshall est mobile même pendant la phase de planification.
* Lorsqu'un bandit tire sur le marshall, il est immobilisé pendant 1 seconde, et sa
  nervosité augment


## Choix d'architecture

Comme il était demandé dans la partie 1.2, nous avons fait le choix d'une architecture MVC.
Nous avons donc 3 packages :

* controller
* engine
* graphics

Ainsi dans le package `controller`, on retrouve la classe `GameControlleur` qui gère les entrées utilisateur et qui correspond bien entendu au contrôleur de l'architecture MVC

Nous avons ensuite le package `engine` qui contient la classe `GameEngine` qui gère toutes les données de la partie. On retrouve aussi dans le sous-package `gameElements` toutes les classes correspondantes aux éléments présents dans notre jeu tel que `Train`, `Bandit`, `Marshall`, etc...
Ce package correspond donc au modèle du MVC

Enfin, dans le package `graphics` qui correspond à la vue du MVC, on trouve la classe `GameDisplay` qui est la fenêtre de notre jeu. À cette fenêtre, on ajoute deux éléments graphiques qui se trouve dans le sous-package `graphicElements` qui sont les boutons et l'affichage principal de notre jeu.


## Choix d'implémentation

Dans la classe `Train`, il y a une ArrayList contenant des "Entity", cette ArrayList correspond à notre "table de jeu" et contient tous les éléments présents dans le jeu.

Toutes les classes représentant des éléments du jeu implémentent la classe `Entity`.

Les Entity représentant des éléments du jeu que l'on peut trouver dans notre ArrayList sont :
* `Marshall` et `Bandit` qui implémentent la classe `Character`, elle-même
  implémentant la classe `Entity`
* `Bounty` chaque butin à un type qui permet de savoir si c'est un bijou, une bourse
  ou le magot

Chaque `Entity` a des attributs coordonnés x et y qui définissent sa postion dans le train.

Le choix d'une ArrayList nous permet d'ajouter et d'enlever des entités simplement ainsi que d'itérer sur la liste des entités.

## Les problèmes

Lors de la réalisation de la partie graphique, nous nous sommes rendu compte que gérer plus de 2 joueurs généré beaucoup de problèmes, en autre le fait de devoir refaire la partie input pour que plus de 2 personnes puissent enregistrer des actions. Par manque de temps, nous avons décidé de fixer le nombre de joueurs à 2, cependant, le game engine supporte plus de 2 joueurs.


## Suite

Voici ce que l'on avait imaginé, mais qu'on n'a pas eu le temps d'implémenter :
* Fenêtre de lancement du jeu permettant de configurer le nombre de wagons ainsi que
  le nombre de joueurs.
* Amélioration de l'écran de fin de partie


## Code co-écrit ou emprunté

L'entièreté du code a été écrite par nous-même, avec l'aide de la javadoc et stackoverflow :wink:
(notamment pour `Timer` et `ThreadLocalRandom`)
