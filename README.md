# Wordle Game

## Présentation du projet

Le projet **Wordle Game** est une implémentation en Java du célèbre jeu **Wordle**. Le jeu consiste à deviner un mot de 5 lettres en un maximum de 6 essais. Après chaque tentative, le jeu fournit un retour pour chaque lettre :

- **Vert** : La lettre est correcte et à la bonne position.
- **Jaune** : La lettre est correcte mais à la mauvaise position.
- **Gris** : La lettre n'est pas dans le mot.

## Prérequis

- **Java 11+** : Le projet nécessite Java 11 ou une version supérieure.
- **Maven** : Utilisé pour la gestion des dépendances et la compilation du projet.
- **JUnit 5** : Pour l'exécution des tests unitaires.


## Lancer le projet

Pour lancer le projet, suivez ces étapes :

1. Clonez le repository et ouvrez le projet dans votre IDE préféré.
2. Placez-vous dans le répertoire contenant le fichier pom.xml (Wordle/wordle),
3. Compilez le projet avec Maven :
    ```bash
    mvn clean install
    ```

4. Exécutez la commande suivante pour démarrer le jeu :
    ```bash
    mvn exec:java 
    ```

Cela exécutera la classe `Main.java` et démarrera le jeu Wordle.

## Tests unitaires

Le projet inclut des tests unitaires pour s'assurer que le jeu fonctionne correctement. Pour exécuter les tests, utilisez la commande suivante :

```bash
mvn clean test
```

Après avoir générer les tests unitaires, il faut génèrer la couverture. Pour cela il faut éxecuter la commande suivante :

```bash
mvn jacoco:report
```

Le rapport sera généré dans le répertoire target/site/jacoco/. Ouvrez le fichier index.html pour visualiser la couverture de code.
