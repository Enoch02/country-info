# Country Info App
## Overview

This is an Android application that allows users to view a list of countries and see detailed information about a selected country. This functionality is provided by [Restful countries API](https://restfulcountries.com/api-documentation/version/1).

## Prerequisites for building
- [Latest Android Studio](https://developer.android.com/studio)
- Android SDK (installed during Android Studio setup)
- An Android device or emulator

## Project Setup
- Clone the repository:  
`git clone https://github.com/Enoch02/country-info`
- Open the project in Android Studio.
- Sync Gradle by selecting **File > Sync Project with Gradle Files**.
- Obtain the API key:
	- Visit [this page](https://restfulcountries.com/request-access-token).
	- Enter your email and website URL.
	- Copy the token that appears.
	- Create a file called `secrets.xml` at the path `app/src/main/res/values`.
	- Add the following to the created file:
	```xml
	<?xml version="1.0" encoding="utf-8"?>   
  <resources>
    <string name="country_api_key">[secret key here]</string>
  </resources>
	```
- Run the app on an emulator or a connected device.


## Contributing
Follow these steps to contribute:
- Fork the repository.
- Create a new branch:
`git checkout -b feature-branch`
- Make your changes and commit:
`git commit -m "Add new feature"`
- Push to your fork and create a Pull Request.
