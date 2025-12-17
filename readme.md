# Description

Cette application permet de récupérer des informations sur les personnages de la série **Rick & Morty**.  
L'application propose une liste des personnages ainsi que des informations individuelles pour chacun d’eux.

Elle utilise l’API **Rick & Morty** comme source de données :  
👉 https://rickandmortyapi.com/

# Architecture et Structure

L'application est structurée selon le modèle **Clean Architecture**, dans lequel on sépare les couches **UI**, **Domain** et **Data**.

- **UI** : Screens et ViewModels correspondants  
- **Domain** : Modèles de données et contrats (Repository)  
- **Data** : Appels API et implémentations des contrats

# Stack

## Kotlin
L'application est développée avec le langage **Kotlin**, basé sur Java.  
C’est le langage officiellement supporté par Google pour le développement mobile.

## Jetpack Compose
L'application utilise **Jetpack Compose**, principalement pour la structure de l’interface et la navigation.

## Coroutines / Flow
L'application utilise les **Coroutines** et **Flow** pour gérer les appels asynchrones à l’API.
