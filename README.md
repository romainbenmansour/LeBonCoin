# Base-Repo
Base implementation for any project

# Version Catalog
The app uses a version catalog to better handle external dependencies within the app. It is being
currently implemented as of 05/10/23

# Compose
The app uses Compose for UI purposes. It's coupled with coroutines / flows. Basically every view will have it's own UIStateView willing to accepte UIState. it's implemented as such to handle different possible UI states such as LOADING, ERROR, DEFAULT, SUCCESS and so on. It is generic for easier reusing and factoring. 
We shall have a data class with every data needed to display for a given view. This is achieved through the use of class called "transformer" in charge of instanciating that data class. It allows for proper testing by testing the different mappings happening inside. The transformer also acts as a way to decouple the Domain from the view to have easy maintening.

# Compose Navigation
In app navigation will be handled through compose navigation. Multiples graphs handling has been implemented

# Data
We shall pass request data from the Data module as requested by the Domain through the use of Status (a sealed class with possible outputs such as ERROR, SUCCESS). Flows are also an option obviously

# Testing
Main framework used to code various unit tests is Mockk. Turbine is also used to better test flows.

 # TODO
 
 1. Handle Bottom Nav Bar
 2. Add More Graphs
 3. More to come
