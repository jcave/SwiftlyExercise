# Swiftly Exercise

To view the app, clone the repository, open it in Android Studio, then choose "Run 'app'".

1. Based on the canvasUnits (16 total) and height/width data that come back from the endpoint, some tiles are
very small (3x3 units) or very wide (14x4). Because of this, I created 3 different layouts to be used.
These layouts also either 1) shrink the image and/or the pricing text or 2) remove certain elements from
the tile to scale safely and degrade gracefully.

2. To refresh the Manager Specials, swipe down on the listings.

3. I decided to go with the approach of hitting the API each time the screen was loaded or a refresh swipe was
triggered. Depending on the amount of data and if there was a need for browsing the specials offline, I may have 
set up a sync schedule and saved the data into a database instead. 

Please reach out to me for any comments or questions that I can help answer. Thanks!