# Mini Projet - Framework d'Injection des Dépendances

## Introduction

Ce projet implémente un **framework d'injection de dépendances** en Java, inspiré de frameworks comme **Spring IOC**. Il permet d’injecter des dépendances dans des classes de manière **automatique** à l'aide de **annotations** et de fichiers **XML**.

![image](https://github.com/user-attachments/assets/958bc122-9366-4d00-8ac0-4ba377cb289d)

*Capture d'architecture du projet et de l'injection de dépendances.*

## Technologies utilisées

- **Java** : Langage de programmation principal.
- **JAXB** : Utilisé pour le parsing et la gestion des fichiers XML.
- **Annotations personnalisées** : `@Component`, `@inject`.

## Description du projet

L'injection de dépendances est un concept essentiel dans les architectures logicielles modernes, permettant de séparer la création des objets de leur utilisation. Dans ce projet, j’ai implémenté deux mécanismes pour réaliser cette injection :
1. **Injection via annotations** : Utilisation d'annotations personnalisées pour déclarer les beans et injecter leurs dépendances.
2. **Injection via XML** : Utilisation d’un fichier XML pour configurer l’injection des dépendances. Ce fichier est chargé et analysé à l’aide de JAXB.

![image](https://github.com/user-attachments/assets/92e14b80-e642-4933-83e3-6c78db3b85e0)
*Capture du fichier `beans.xml` montrant la configuration des beans.*

## Fonctionnalités

### Injection via annotations
L'injection est réalisée grâce à des annotations personnalisées, telles que `@Component` pour définir un bean et `@Autowired` pour injecter les dépendances automatiquement dans les champs ou via des méthodes setter.

![image](https://github.com/user-attachments/assets/9d705f0c-171a-4308-9afe-f462fe7db64c)
*Capture de l'injection via annotations avec les annotations `@Component` et `@inject`.*

### Injection via XML
Les dépendances peuvent être définies dans un fichier `beans.xml`, qui est chargé par le framework et utilisé pour instancier les beans et injecter les dépendances.

![image](https://github.com/user-attachments/assets/25a64807-609e-4901-957e-a03ec47909f9)

*Capture du processus de lecture du XML avec JAXB*
![image](https://github.com/user-attachments/assets/77ac5a27-722d-4f0f-b1e8-a0cef5bbf9d4)
*Capture de création des instances (beans sans injection)*


### Types d'injection supportés
- **Injection par champ (field)** : Injection directe dans les champs des classes.
  ![image](https://github.com/user-attachments/assets/8011f4fc-62bd-43e8-853d-c2add1d4faf3)

- **Injection par setter** : Injection via des méthodes setter publiques.
  ![image](https://github.com/user-attachments/assets/2fb1e091-c654-4620-90d7-0e61ed8f69b6)

- **Injection par constructeur** : Injection via le constructeur de la classe, en passant les dépendances comme paramètres.
  ![image](https://github.com/user-attachments/assets/521e7f9e-78c9-4f26-9a43-6aaeaea7dd33)


## Détails d'implémentation

### `XmlIoCContainer.java`
Cette classe permet de charger le fichier XML de configuration des beans, d'instancier les classes correspondantes et d’injecter les dépendances selon les types définis dans le fichier XML.

### `AnnotationIoCContainer.java`
Cette classe gère l’injection des dépendances via annotations, en scannant les annotations `@Component` et `@Autowired` pour lier les beans.

### `Main.java` (Annotations)
Ce fichier de test vérifie que l’injection par annotations fonctionne en créant des instances de classes avec des annotations et en affichant les résultats.

![image](https://github.com/user-attachments/assets/96e8d747-cc4e-4838-8643-673a2adc5f04)

*Exécution de l'injection de dépendances via annotations dans le fichier `Main.java`.*

### `MainXml.java` (XML)
Ce fichier de test vérifie que l’injection via fichier XML fonctionne correctement, en utilisant le fichier `beans.xml` pour injecter les dépendances dans les classes.

![image](https://github.com/user-attachments/assets/2ee74874-cf78-47c8-a844-605d6b1a55df)
 
*Exécution de l'injection de dépendances via XML dans le fichier `MainXml.java`.*
