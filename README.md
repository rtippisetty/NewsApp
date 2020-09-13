# README #

### What is this repository for? ###
* This is a sample App demonstrating News article display.
* First page displays "list of news articles cards"
* Onclick of any item will lanuch respective website for details view
* Version
1.0

### How do I get set up? ###

1. Clone the repository
2. Open the project in Android Studio
3. Initiate Build
4. Push the binary on to Any Android Device
5. Launch the App, you will see the result

### Architecture guidelines ###
1. Application is designed and implemented with MVVM Architecture style
2. Application is structured into 3 high level packages

* data [Model] -> contains Model and network helper classes
* View [View] -> contains view related classes (NewsListActivity, NewsListAdapter)
* viewmodel [ViewModel] ->contains viewmodel related classes (NewsViewModel)

### Libraries/Components used ###
1. Retrofit for networking
2. androidx.*
3. Picasso for image download
4. Few other libraries for extension functions

######
