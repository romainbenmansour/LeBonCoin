# LeBonCoin
Technical test requested by LeBonCoin

# Version Catalog
The app uses a version catalog to better handle external dependencies within the app. 

# Main Concept
Since the webservice returns 5000 results and no pagination was enabled right off the bat, it was mandatory to implement 
some kind of paging so that the app would still have great performance. To do so, it was decided to fetch the data once -
only if not already cached within the app - then to cache it and finally offer a way to get that data as paged items.

# Config changes
It's pretty clear config changes is important for this test in order to offer a smooth ux. On config changes, the content is not restarted 
and we keep the same offset as we were before the screen orientation change. This was archieved thanks to one kotlin extension available with paging lib used : 

public fun <T : Any> Flow<PagingData<T>>.cachedIn(
scope: CoroutineScope
): Flow<PagingData<T>> = cachedIn(scope, null)

# DI
Hilt is used here to handle dependency injection. There is a few modules classes where we have our
bind and provide methods.

# Architecture
Clean Archi coupled with MVVM has been used for this test. This is pretty standard nowadays.

# App
ViewModels exposes state flows to be collected from composables for example. We have a UIState state flow allowing us
to display loading, errors or view content depending on the case. We map Status to UIState via an extension. 
If we fail to fetch data, we want to show it to the user.
If we are fetching the data, we also want to show it to the user.

# Data
Data here mainly contains albums related classes. We have the repository in charge of managing the Album POJO.
it decides if we should fetch data from the remote or simply return the cache. 
That knowledge of remote / local should not be known outside of the repository and is not visible in the domain layer.

Then we have the remote data source using retrofit to get the album. It's sole job is to get it and that's it.

The local data source uses Room to handle our caching needs. It doesn't handle Albums directly but rather CachedAlbum for better decoupling.

# Domain
Didn't have too many business logic to implement. It was mainly used as pass-through with the Data layer.

# Tests
Main framework used to code various unit tests is Mockk (https://mockk.io/). A few classes have been tested such as : 
1. Use cases
2. AlbumRepositoryImpl
3. Both DataSources (RetrofitDataSource & RoomDataSource)
4. CachedAlbum
