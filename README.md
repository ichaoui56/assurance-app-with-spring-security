
# Application de gestion des contrats d'assurance avec Spring Security (Session-Based Authentication)

## Contexte du projet

L'objectif de ce projet est de sécuriser une application de gestion des contrats d'assurance en utilisant Spring Security avec une authentification basée sur des sessions. Les utilisateurs s'authentifient et leurs informations sont stockées dans une base de données. L'application permet de gérer les contrats d'assurance tout en garantissant la sécurité et la confidentialité des données.

## Fonctionnalités principales

- **Gestion des utilisateurs** : Les utilisateurs et leurs rôles (ROLE_USER, ROLE_ADMIN) sont stockés dans une base de données.
- **Authentification basée sur les sessions** : Utilisation de Spring Security avec une gestion des sessions pour sécuriser les accès à l'application.
- **Profil TEST** : Le profil Spring `TEST` permet une authentification avec n'importe quel mot de passe pour les tests.
- **Suivi des activités utilisateur** : L'application enregistre l'activité des utilisateurs pour une consultation ultérieure par les administrateurs.
- **Déploiement Docker/Jenkins** : L'application peut être déployée en utilisant Docker et Jenkins.

## Prérequis

- JDK 17 ou supérieur
- Spring Boot
- Docker
- Jenkins
- Une base de données relationnelle PostgreSQL

## Configuration

### 1. Base de données

- Configurez votre base de données en modifiant les paramètres dans le fichier `application.properties` ou `application.yml`.
  
Exemple :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/assurance_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

### 2. Authentification

- L'application utilise une authentification basée sur les sessions. Les utilisateurs doivent s'authentifier avec leurs identifiants (nom d'utilisateur et mot de passe). Le rôle des utilisateurs (ROLE_USER ou ROLE_ADMIN) est attribué au moment de l'authentification et stocké dans la base de données.

### 3. Enregistrement des activités utilisateur

- L'activité des utilisateurs (connexion, déconnexion, actions importantes) est enregistrée dans une table spécifique de la base de données afin qu'un administrateur puisse la consulter.

## Déploiement avec Docker

1. **Construire l'image Docker** :
   Utilisez la commande suivante pour construire l'image Docker de l'application :
   ```bash
   docker build -t gestion-assurance .
   ```

2. **Exécuter l'application avec Docker** :
   Pour lancer l'application dans un conteneur Docker :
   ```bash
   docker run -p 8080:8080 gestion-assurance
   ```

## Déploiement avec Jenkins

1. Configurez un job Jenkins pour automatiser le déploiement.
2. Ajoutez les étapes suivantes dans le pipeline :
   - **Build** : Utilisez Maven ou Gradle pour compiler le projet.
   - **Test** : Exécutez les tests unitaires avec JUnit.
   - **Dockerize** : Créez l'image Docker à l'aide du Dockerfile.
   - **Deploy** : Déployez l'application sur le serveur souhaité.

## Structure du projet

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── assurance/
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── model/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── assurance/
│                   └── tests/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

## Sécurité

### Authentification

L'authentification est gérée par Spring Security avec une session persistante. L'authentification est basée sur les rôles : `ROLE_USER` et `ROLE_ADMIN`.

### Profil TEST

Lorsque le profil Spring `TEST` est activé, il permet de contourner les mots de passe pour faciliter les tests, mais cette option ne doit être utilisée que dans un environnement de développement.

## Aide au développement

Pour toute question ou problème rencontré, veuillez consulter la documentation officielle de [Spring Security](https://spring.io/projects/spring-security) et de [Docker](https://docs.docker.com/).

