# SwiftlyExercise

## Overview
To view the app, clone it, open in Android Studio, and choose "Run 'app'". A few things to keep in mind.

1. Based on the canvasUnits (16 total) that come back, some tiles are
very small (3x3 units) or very wide (14x4). Because of this, I created 3 different layouts to be used.
These layouts also either 1) shrink the image and/or the pricing text or 2) remove certain elements from
the tile to scale safely and degrade gracefully.

2. To refresh the Manager Specials, swipe down on the listings.

3. I decided to go with the approach to just hit the API each time the app was loaded or a refresh swipe was
triggered. Normally, I may set up some sort of sync schedule and save the data into a database so
the user still has access while offline. Additionally, depending on the amount of data, it potentially could be
faster then hitting the API each time.

