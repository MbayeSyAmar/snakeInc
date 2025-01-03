
# Snake Inc - Jeu et Serveur

Ce projet contient un jeu **Snake** et un **serveur** pour stocker les scores des joueurs dans une base de données H2.

## Instructions pour lancer le projet

### 1. Démarrer le serveur
Dans un premier terminal, exécutez la commande suivante pour lancer le serveur :
```bash
./gradlew server:bootRun
```
Le serveur sera accessible sur le port `8080`.

### 2. Lancer le jeu
Dans un deuxième terminal, exécutez la commande suivante pour lancer le jeu :
```bash
./gradlew snake:run
```

### 3. Accéder à la base de données H2
Pour visualiser les scores directement dans la base de données H2, ouvrez votre navigateur et allez à l’adresse suivante :
```
http://localhost:8080/h2-console
```

Remplissez les informations de connexion comme indiqué dans le fichier `application.properties` :
- **JDBC URL** : `jdbc:h2:file:./data/snake-db`
- **Username** : `sa`
- **Password** : (laisser vide)

Les scores, le type de serpent, et la date seront stockés automatiquement dans la base de données H2 après chaque partie.

### 4. Requêtes API
Le projet expose également des endpoints API pour interagir avec les scores. Consultez le fichier `ScoreController` pour voir les différentes requêtes disponibles, comme les statistiques des joueurs.

### 5. Lancer les tests
Pour exécuter les tests, utilisez la commande suivante :
```bash
./gradlew test
```

---

## Technologies utilisées
- **Java** 21
- **Spring Boot** 3.4.0
- **H2 Database** (avec persistance dans un fichier)
- **Gradle** 8.5
- **Lombok**
