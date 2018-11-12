# MovieSearch
# NOTE: Please add the API key to gradle.properties.

1. At a high level, how does your app work?
This app will take "Movie title" as search query text and use a public API to fetch the movie data and then udpate the UI with the received data.

2. What design decisions did you make and why?
I used MVVM as the design pattern for this Movie Search feature of this app. Because ViewModel is lifecycle-aware component it avoids the boilerplate code like what we do for MVP. 

3. What design patterns or architecture might be necessary in the future?
As project gets more complex, Clean Architecture is a good choice

4. How would we extend your app if we had to add functionality?
I can create a new feature path for it and implement the details about this feature.

5. How might you change the architecture of the app if the data model and end point code needed to be reused in a second app?
This app could be converted from an app module to a library module with a little bit of change. We can then build app into a aar file for other app to use.

6. What documentation, websites, papers, etc. did you consult for this assignment?
I used developer.android.com, stack.com, medium.com.

7. What thirdparty libraries or other tools does your application use? How did you choose each library or framework you used?
I used Retrofit, OkHttp, Glide, Gson, Dagger. I chose these libs because they are very useful and can save me a lot of efforts so that I can focust on how to make the app.

8. How long did you spend on this exercise? If you had unlimited time to spend on this, how would you spend it and how would you prioritize each item?
It took me around 3 hours. If I have unlimited time, I will apply data binding and unload UI logic from UI classes. Also, I would implement local storage for persistence data storage.
Still, I will plan on what tools and libs to use first and layout the app architecture and then start coding.

9. If you were going to implement a level of automated testing to prepare this for a production environment,
how would you go about doing so?
I would impement Instrument test cases using Espresso. I did not do that at this time. I will add some basic tests if I get a chance.
If you were unable to finish the exercise, what issues did you face and how did you plan on finishing the
exercise?
N.A.
