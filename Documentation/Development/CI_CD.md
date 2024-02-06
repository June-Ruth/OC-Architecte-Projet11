# Intégration et déploiement continu (CI/CD)

La pipeline de CI est paramétrée afin d’exécuter un travail spécifique à l’application backend et un autre concernant l’application frontend à l’aide de GitHub Actions.
Les fichiers de configuration sont disponibles à la source du projet dans le package dédié `.github/workflows`.

La pipeline est construite de telle manière que les tests sont exécutés sur demande et à chaque  pull request. Une pull request ne peut pas être valide sans la bonne exécution des tests afin de limiter l’introduction de bug ou de comportement indésirable dans la branche principale.
## Backend
Le fichier `check-backend.yml` définit les étapes de CI/CD spécifique à l’application backend du POC. Il exécute les tests unitaires et d’intégration présents dans le backend. Le résultat des tests et l’artefact de l’application sont disponibles dans les livrables de sortie de la pipeline.
## Frontend
Le fichier `check-frontend.yml` définit les étapes de CI/CD spécifique à l’application frontend du POC. Il exécute les tests unitaires et d’intégration présents dans le frontend. Le résultat des tests et l’artefact de l’application sont disponibles dans les livrables de sortie de la pipeline.

*NB : Actuellement il n’existe pas de tests spécifiques à l’application frontend dû à la simplicité de l’interface.*
## Application
Le fichier `check-app.yml` définit les étapes de CI spécifique à l’application dans sa globalité. La CI est conçue afin de faire tourner les tests end-to-end sur les principaux OS utilisés (Windows, Linux – Ubuntu et MacOS) afin de garantir une compatibilité sur un large éventail de configuration. Le résultat des tests est disponible dans les artefacts de la pipeline.

*NB : Actuellement les tests-end-to-end ne sont pas accessibles depuis la CI.*
