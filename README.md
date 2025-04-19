# Partie 1 - Injection de Dépendances avec Couplage Faible

##  Introduction

Ce projet a pour but d'illustrer le principe du **couplage faible** en Java à travers l'utilisation d'interfaces et d'implémentations indépendantes, tout en mettant en œuvre différentes techniques d'injection de dépendances.

Les méthodes d'injection couvertes sont :

- Injection par instanciation **statique**
- Injection par instanciation **dynamique (réflexion)**
- Injection via **Spring Framework** :
  - Version **XML**
  - Version **annotations**

---

##  Technologies utilisées 

- Java
- Spring Framework

---

##  Structure du projet 
![image](https://github.com/user-attachments/assets/1c2ae942-e9ef-4ce2-a306-2096a6dd828e)
*Capture d'architecture du projet et de l'injection de dépendances.*

## Détail des composants 

###  DAO

- **`IDao.java`**  
  Interface définissant la méthode `getData()`.

- **`DaoImpl.java`**  
  Implémentation simple retournant une valeur fixe.
  
  ![image](https://github.com/user-attachments/assets/b6faf44a-77c8-4e55-81f9-c94e642397c9)


- **`DaoImplV2.java`**  
  Deuxième implémentation pour illustrer le remplacement de dépendance.
  ![image](https://github.com/user-attachments/assets/85b45ff8-d8d1-4f09-b514-eb971692469a)


---

### Métier

- **`IMetier.java`**  
  Interface métier avec la méthode `calcul()`.

- **`IMetierImpl.java`**  
  Implémentation de `IMetier` utilisant un objet `IDao` injecté.
  ![image](https://github.com/user-attachments/assets/ceeab0ba-bf57-4945-921e-c016ac5688f2)


---

###  Présentation

- **`Pres1.java`**  
  Injection des dépendances **par instanciation statique** (manuel, couplage fort).

  ![image](https://github.com/user-attachments/assets/69f13b27-9d56-4e93-b6cc-d3348d7c1cfe)


- **`Pres2.java`**  
  Injection **par instanciation dynamique** (réflexion avec `Class.forName`, couplage faible).
  ![image](https://github.com/user-attachments/assets/d259223c-6f49-4ec3-8821-a31fdd1aede5)
   **Injection par constructeur**
  ![image](https://github.com/user-attachments/assets/44a094fc-5e10-45a9-a99b-2da77e0e3e8c)
  **Injection par setter**

- **`PresSpringXML.java`**  
  Utilisation de **Spring avec configuration XML** (`config.xml`).
  ![image](https://github.com/user-attachments/assets/8c2d4d53-76b8-4448-9f22-88bcc1fa9281)


- **`presSpringAnnotation.java`**  
  Utilisation de **Spring avec annotations** (`@Component`, `@Autowired`).
  ![image](https://github.com/user-attachments/assets/caa45da6-46ac-453e-b8d6-8a7b2ef2aaf5)

---

###  Configuration

- **`config.xml`**  
  Définition des beans pour Spring (IoC via XML).
  ![image](https://github.com/user-attachments/assets/4c604913-aa43-4d54-8320-84af55680d24)

---
## Conclusion
L’utilisation de la réflexion en Java permet d’instancier dynamiquement des classes et d’injecter leurs dépendances sans connaître les implémentations exactes à l’avance. Cela permet de créer des architectures flexibles et faiblement couplées, facilitant la réutilisabilité, l’évolution et les tests unitaires.
👉 Cette approche est la base du fonctionnement de frameworks comme Spring, qui automatisent cette injection via des fichiers XML ou des annotations.

 ---
# Partie 2 - Mini Projet :Framework d'Injection des Dépendances

## Introduction

Ce projet implémente un **framework d'injection de dépendances** en Java, inspiré de frameworks comme **Spring IOC**. Il permet d’injecter des dépendances dans des classes de manière **automatique** à l'aide de **annotations** et de fichiers **XML**.

![image](https://github.com/user-attachments/assets/958bc122-9366-4d00-8ac0-4ba377cb289d)

*Capture d'architecture du projet Framework de l'injection de dépendances.*

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

---

## Conclusion

Ce mini-projet a permis de comprendre et d'implémenter un mécanisme simple d'injection de dépendances en Java. Ce framework peut être étendu pour inclure davantage de fonctionnalités comme la gestion de la portée des beans (singleton, prototype) ou la configuration dynamique des beans.

Améliorations possibles :

Ajouter la gestion des scopes des beans (singleton, prototype).

Implémenter l’injection de dépendances par interface.
 
