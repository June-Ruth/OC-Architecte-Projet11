# Reporting
## Rappel des attentes
Les attentes sont exprimées dans le document [Exigences pour le développement](Exigences%20pour%20le%20développement.pdf).

## Constat
### Fonctionnalités
Le POC permet à travers une interface simple de renseigner une spécialité ainsi qu’une position (latitude, longitude), de trouver l’hôpital le plus proche disposant d’une place et de réserver automatiquement un lit avec un numéro de réservation associée.
La gestion des disponibilités est simulée, celle-ci dépendant d’un autre service existant.
La liste des hôpitaux existants est renseignée à partir des données fournies par le NHS. Les spécialités associées sont simulées. Les données réelles pourront être récupérées par un autre service existant.

### Choix techniques
L’API backend est conçue en respectant les principes REST et SOLID sur une pile technologique Java – Spring permettant la connexion de microservices.
L’interface est fournie par une application basée sur le framework React JS qui vient interroger directement notre API backend.
Le code est versionné par Git selon un workflow fourni.

### Performance
Pour 800 utilisateurs sur une même instance, les temps de réponse par requête varient de 4000 ms à 40000 ms

### Protection des données
L’API proposé dans le POC n’utilise pas de données patient. L’accès à l’application nécessite une connexion de type Basic Auth fourni par Spring Security.

### CI/CD
Un système de CI/CD a été mis en place permettant l’exécution automatique des tests et le déploiement potentiel des artefacts de production. Les standards d’utilisation de la CI ont été spécifiés dans le repository.
Les tests end-to-end n’ont pas été intégré à la CI.

## Analyse
### Fonctionnalités
Les fonctionnalités envisagées pour le développement sont faisables en reliant les services du POC aux services existant pour la gestion des hôpitaux et des lits disponibles.

### Choix techniques
Les choix techniques respectent le consortium de technologies imposé par l’entreprise. Ils se basent sur des piles technologiques communes, pour lesquels il est facile de trouver des ressources aussi bien humaines que documentaires afin de garantir le développement futur.
La conception de l’API respecte les normes nécessaires à son inscription dans une architecture microservice en proposant de s’appuyer sur les autres services existants et en centrant son objet autour d’un ensemble de fonctionnalité cohérent.

### Performance
Les performances attendues pour la production ne sont pas atteintes par le POC. Cependant, il est possible d’améliorer ses performances en optimisant l’utilisation du multithreading et en faisant le choix d’une solution de calcul de trajet payante tel que l’API de GraphHopper ou de Google Maps. En effet, ces API disposent de solution de calcul plus efficiente par l’utilisation d’algorithme avancée (notamment par le biais de matrice) qui nous permettrait d’atteindre nos objectifs. Ces solutions ne peuvent être testées sans financement complémentaire.

### Protection des données
L’API proposé dans le POC n’utilise pas de données patient afin de limiter leur exposition, ces-dernières n’étant pas indispensable aux fonctionnalités attendues.
L’accès à l’application est sécurisé afin de garantir son utilisation par les seuls destinataires. Cette sécurisation peut être augmenté en passant facilement d’un système d’authentification basique à un système d’authentification par token disponible sur le framework utilisé (JWT et OAuth).

### CI/CD
Le POC supporte l’utilisation d’une CI/CD. Il conviendra cependant d’apporter les correctifs nécessaires afin de prendre en compte l’intégralité des tests pour les développements futurs et de valider la méthode de mise en production des artefacts validés.


## Conclusion

- Fonctionnalités : OK
- Choix techniques : OK
- Performance : NOK – Correctifs existants
- Protection des données : OK
- CI/CD : OK

Au vue des attentes sur le projet, des apports du POC et des correctifs possibles existants, la faisabilité du projet d’un système d’intervention d’urgence en temps réel est validée.
