# MVVM-architecture-sample

This is a simple sample repository about MVVM architecture with the network and local data manipulation. In this repo, data is recieved from https://api.themoviedb.org/3/ end-point and saved in cache ROOM storage for offline compatible.

## Libraries used

- navigation
- room
- coroutines
- retrofit
- GSON
- Paging
- Hilt
- Timber
- View Binding

## Terminology

- Get JSON response from server with retrofit 
- Make consideration to manipulate data from DB or Fetch to server in NetworkBoundResource
- Encapsulate data with loading, success, failed, error states in ViewModel
- Observe the encapulated LiveData from ViewModel from Activity
- Show in RecyclerView with endless scroll (Paging Library)

> Simple usage of network and local data, 
> Some Higher Order Functions, 
> Inheritance usage in BaseFragment:BaseActivities: ,
> ViewBinding usage,
> Navigation usage with Bottom Navigation Drawer and ActionBar,
> OkHttpProfiler for network debugging,
> Corrotines for asynchronous execution


