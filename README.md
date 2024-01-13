# TD et TP IPI Interfacer une source de données en Java

Ce projet est un début d'application web de gestion de salariés aide à domiciles et leurs congés.
Il permet de réaliser les exercices en séance (TD) et exercices de l'évaluation (TP) du cours.

**L'énoncé de ces exercices est en bas de ce README**.

L'énoncé d'un exercice de TD est préfixé par "TD". L'énoncé d'un exercice de TP est préfixé par "TP".
Les exercices optionnels sont aussi préfixés par "BONUS".


## Pré-requis

- Avoir installé un IDE :
    - IntelliJ Ultimate, avec votre adresse IPI sur Jetbrains Student à https://www.jetbrains.com/student/
    - sinon Eclipse, à https://www.eclipse.org/downloads/packages/release/2022-09/r/eclipse-ide-java-developers
- Savoir utiliser Git et les branches (utilisez les capacités Git de votre IDE ou installez-le séparément depuis
  https://git-scm.com/download/win ). Quelques liens :
    - https://learngitbranching.js.org/
    - https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging
- Avoir un compte Github. Voici comment y configurer l'authentification de git par clé SSH :
    - https://docs.github.com/en/authentication/connecting-to-github-with-ssh
    - https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account
- Connaître les bases du développement Java avec Maven (la persistence avec JPA est également utilisée ponctuellement),
  et au moins comment importer et compiler un projet Java dans l'IDE :
    - IntelliJ :
        - Installation de Git : Git > git not installed > Donwload and install
        - Cloner un projet Github : Git > Clone
        - Configuration d'un projet Maven : clic droit sur pom.xml > Add as Maven project ou bien voir IV-B-2 à https://damienrieu.developpez.com/tutoriel/java/nouveautes-intellij-12/?page=page_1
        - Installation de Java : par exemple
            - erreur ne trouve pas le symbol "java" : clic droit sur pom.xml > Build > sur Setup DSK choisir Configure > choisir Download et install
            - "Error running..." : Project JDK is not specified > Configure... > no SDK > Add SDK > Download
        - lancer un build maven complet : Run > Edit configurations > Maven > Create configuration > mettre Working directory au dossier du projet et dans Command line, écrire : clean install
        - problème de sécurisation de connexion :
          (Maven error : unable to find valid certification path to requested targetmaven unable to find valid certification path to requested target
          ou
          unable to access 'https://github.com/mdutoo/....git/': SSL certificate problem: unable to get local issuer certificate)
          mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
          ou dans IntelliJ Run > Edit Configurations > Java Options (sans -D) : maven.wagon.http.ssl.insecure=true maven.wagon.http.ssl.allowall=true
          comme dit à https://stackoverflow.com/questions/45612814/maven-error-pkix-path-building-failed-unable-to-find-valid-certification-path
    - sinon Eclipse : voir https://thierry-leriche-dessirier.developpez.com/tutoriels/java/importer-projet-maven-dans-eclipse-5-min/
- OPTIONNEL Avoir installé postgresql (ou mysql) : https://www.postgresql.org/download/


## Créer la base de données

### H2 (par défaut)

Par défaut, l'application se lance avec la base de données embarquée en mémoire H2.
Comme il n'y a rien à faire pour cela, il est conseillé de commencer comme cela.
L'inconvénient, outre que cela est moins réaliste, est que les données rajoutées
disparaissent à chaque démarrage, à part quelques données de test rajoutées
à l'initialisation (par  

### PostgreSQL (à créer)

Installer PostgreSQL : https://dbeaver.io/download/

Exécuter les lignes de commande plus bas,
- soit dans un outil à installer tel DBeaver : https://dbeaver.io/download/
- soit dans un terminal.

Créer l'utilisateur "ipi" :

en tant qu'administrateur (sous Windows : recherche "cmd" dans les applications et dessus clic droit > "Run as admin", sous linux : sudo su - postgres) :

    $> psql
    $postgresql> create user ipi with password 'ipi' createdb;
    $postgresql> \q

Créer la base de données "ipi_javaio" :

	$> psql -U ipi postgres -h localhost
	$postgresql> create database ipi_javaio encoding 'UTF8';
    $postgresql> \q

Vérifier que l'utilisateur créé peut bien se connecter à cette base :

	$> psql -U ipi ipi_javaio -h localhost

Configurer l'application pour s'en servir :
- dans ```main/resources/application.properties```, décommenter les lignes sous "postgresql - clean setup" et commenter les lignes à propos de H2.
- dans ```pom.xml```, commenter la dépendance à H2 (ou la passer en ```<scope>test</scope>```).


## Exécution

lancer la classe com.ipi.javaio.JavaInterfacerDonneesApplication
- dans l'IDE
  - IntelliJ : l'ouvrir et cliquer sur la flèche verte sur sa gauche
  - Eclipse : clic droit > Run as application),
- avec maven (IDE ou ligne de commande) : ```mvn spring-boot:run```

Puis pointer le navigateur web à http://localhost:8080/ , ou pour afficher une page sans rendu Thymeleaf par exemple http://localhost:8080/home.html (qui contient l'énoncé des exercices à réaliser).

FAQ :
- erreur au démarrage "Cannot find template location: classpath:/templates/" => dans IntelliJ : clic droit sur projet > Add as Maven project


## Architecture du code

Voici l'organisation du code source de l'application :
- code Java de l'application : dans le package com.ipi.javaio
- Controllers Spring MVC (seuls ceux d'export sont développés) : dans le sous-package web
- configuration : main/resources/application.properties


## TD en séance et TP d'évaluation

Après chaque TD ou TP, committez et pushez votre code après l'avoir testé.

CSV :
- [TD] export des mois d'un salarié (avec un mois par ligne) :
  - exécuter l'export à http://localhost:8080/export/salarieAideADomicileMois/csv ; qu'obtient-on ?
  - compléter le développement de l'export par simple concaténation dans SalarieAideADomicileMoisExportCsvService.exportBase(),
avec au mois 3 colonnes, par exemple : la date du premier jour du mois de chaque ligne, le nom de son salarié,
les jours travaillés en année N à la fin de ce mois
  - ajouter la ligne des en-têtes de colonne (nommés d'après les noms des champs dans les classes d'entité
SalarieAideADomicileMois et SalarieAideADomicile)
  - ajouter (si ce n'est fait) la colonne congesPayesAcquisAnneeN dans SalarieAideADomicileMoisExportCsvService.exportBase()
  - NB. pas utilisé pour l'instant : export d'une année d'un salarié http://localhost:8080/export/salarieAideADomicileMois/csv/1/2023
- [TP] export des salariés :
  - exécuter l'export à http://localhost:8080/export/salarieAideADomicile/csv
  - compléter le développement de l'export dans SalarieAideADomicileExportCsvService.exportBase(), avec au moins
5 colonnes, et la ligne des en-têtes de colonne (nommés selon la même règle que précédemment)
  - avec la ligne des en-têtes de colonnes
  - BONUS rajouter l'échappement des double quotes (entourer toute valeur de double quote, et à l'intérieur
de toute valeur doubler toute double quote). Tester en décommentant dans DataInitService.run() sous le commentaire
"décommenter SEULEMENT après avoir implémenté la gestion de l'échappement"

XLSX :
- [TD] export des mois d'un salarié :
  - le tester vide à http://localhost:8080/export/salarieAideADomicileMois/xlsx
  - le développer dans SalarieAideADomicileMoisExportXlsxService.exportBase()
- [TP] export des salariés :
  - le tester vide à http://localhost:8080/export/salarieAideADomicile/xlsx
  - le développer dans SalarieAideADomicileExportXlsxService.exportBase()
- [TD] Créer un export XLSX multi onglet : un pour le salarié donné, et créer un onglet par mois de ce salarié
    chaque onglet contiendra le détail du mois (colonnes : au moins comme dans l'export CSV)
- [TP] BONUS TRIPLE Créer un export XLSX multi onglet :
  - le développer avec un onglet pour l'année donnée, et créer un onglet par salarié pour cette année
      chaque onglet contiendra le détail du mois (colonnes : au moins comme dans l'export CSV)

PDF :
- [TD] créer le PDF d'un mois d'un salarié
  - le tester vide à http://localhost:8080/export/salarieAideADomicileMois/pdf
  - le développer dans SalarieAideADomicileMoisExportPdfService.exportBase() ainsi :
    - en-tête : Nom du salairé, logo de Spring Boot (à télécharger depuis https://spring.io/projects/spring-boot/ )
    - Ajouter le numéro du mois et année, centré et en gras
    - tableau contenant le détail du mois, une ligne par champ (au moins les mêmes champs que dans l'export CSV)
- [TP] créer le PDF d'un salarié
  - le tester vide à http://localhost:8080/export/salarieAideADomicile/pdf
  - le développer dans SalarieAideADomicileMoisExportPdfService.exportBase() ainsi :
    - en-tête : Nom du salairé, logo de Spring Boot (à télécharger depuis https://spring.io/projects/spring-boot/ )
    - tableau contenant le détail du salarié, une ligne par champ (au moins les mêmes champs que dans l'export CSV
    - BONUS : rajouter et calculer l'ancienneté (nombre d'année entre mois de début du contrat et mois en cours)

BONUS Import CSV :
- décommenter la dépendance opencsv dans le fichier pom.xml (configuration du build Maven) à la racine du projet,
s'assurer que l'IDE rebuilde le projet (dans IntelliJ avant la dernière version : clic droit sur le pom.xml > Maven > Reload project)
- dans DataInitService.run() (à l'initialisation lorsque la base de données est vide) est appelée la méthode exportFile()
du service de classe SalarieAideADomicileImportCsvService. Implémenter cette dernière méthode
à l'aide de la librairie OpenCSV pour importer le fichier CSV de salariés à importer salaries_a_importer.csv ainsi :
  - s'aider du slide du cours "Le format CSV en Java - import, avec Open CSV"
  - en utilisant la méthode creerSalarieAideADomicile() du service SalarieAideADomicileService (voir un
exemple d'usage dans DataInitService.run())
  - et à l'aide de Double.parseDouble() pour lire les nombres  et LocalDate.parse() pour lire les dates.

[TD] Ensemble, s'il y a le temps : JasperReports
