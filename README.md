# MeadHead - Système d’intervention d’urgence en temps réel - POC
## Application Backend
### Préalables
L’application nécessite les variables d’environnement suivante :
```
DATABASE_DRIVER: com.mysql.cj.jdbc.Driver
DATABASE_URL: addresse de la base de donnée
DATABASE_USERNAME: utilisateur de la base de donnée
DATABASE_PASSWORD: mot de passe de la base de donnée
```
Ces variables peuvent être insérées dans un fichier .env à la racine du projet.

Le fichier OSM contenant les données géographiques de l’Angleterre doit être téléchargé et ajouter dans le package resources.
Il est téléchargeable en cliquant [ici](https://download.geofabrik.de/europe/united-kingdom/england-latest.osm.pbf).
### Exécution
Pour lancer l’application backend, exécuter `./gradlew bootRun` depuis le dossier MedHeadBackEnd.
L’application est paramétrée pour tourner sur http://localhost/8081
### Tests
Pour exécuter les tests en local, un environnement docker doit être disponible.
Exécuter `./gradlew test` depuis le dossier MedHeadBackEnd.
## Application Frontend
### Préalables
Pour fonctionner correctement, l’application frontend nécessite le lancement de l’application backend. L’hôte et le port de l’application backend doivent être déclarés dans un fichier .env à la racine du projet comme suit :
```
REACT_APP_HOST = localhost
REACT_APP_PORT = 8081
```
### Exécution
Pour lancer l’application frontend, exécuter `npm start` depuis le dossier MedHeadFrontEnd/medheadfrontend.
L’application est paramétrée pour tourner sur http://localhost/3000
### Tests
Pour exécuter les tests en local, exécuter `npm test` depuis le dossier MedHeadFrontEnd/medheadfrontend.
## Tests end-to-end
Les tests end-to-end sont disponibles dans le dossier MedHeadFrontEnd/selenium.
Pour exécuter les tests en local, un environnement docker doit être disponible.
Exécuter `./gradlew localTests` depuis le dossier MedHeadFrontEnd/selenium.

## Documentation d'architecture
Les documents d'architecture existant peuvent être retrouvés dans le dossier `Documentation/Architecture`.

[Document de définition de l'architecture](Documentation/Architecture/Document%20de%20définition%20de%20l'architecture.pdf)

[Données de référence sur les spécialités NHS](Documentation/Architecture/Données%20de%20référence%20sur%20les%20spécialités%20NHS.pdf)

[Déclaration des travaux d'architecture](Documentation/Architecture/Déclaration%20des%20travaux%20d’architecture.pdf)

[Principe de l'architecture](Documentation/Architecture/Principes%20de%20l'architecture.pdf)

[Exigences pour le développement](Documentation/Architecture/Exigences%20pour%20le%20développement.pdf)

Le reporting du POC faisant part de nos conclusions est disponible dans ce même dossier.

[Reporting](Documentation/Architecture/REPORTING.md)

## Standards de développement
Les documents faisant part des standards de développement attentdus pour le projet peuvent être retrouvés dans le dossier `Documentation/Development.`

[Intégration et déploiement continu](Documentation/Development/GIT.md)

[Workflow Git](Documentation/Development/CI_CD.md)