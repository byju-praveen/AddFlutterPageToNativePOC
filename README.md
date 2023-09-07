
# AddFlutterPageToNativePOC
Add flutter page to native page and then again call native page from flutter


Steps to add flutter page as AAR
1.Build flutter ARR
2.Follow the instruction given below to add it to native code

Consuming the Module
  1. Open <host>/app/build.gradle
  2. Ensure you have the repositories configured, otherwise add them:

      String storageUrl = System.env.FLUTTER_STORAGE_BASE_URL ?: "https://storage.googleapis.com"
      repositories {
        maven {
            url '/Users/praveen/AndroidStudioProjects/Native_to_flutter/flutterdemo/build/host/outputs/repo'
        }
        maven {
            url "$storageUrl/download.flutter.io"
        }
      }

  3. Make the host app depend on the Flutter module:

    dependencies {
      debugImplementation 'com.example.flutterdemo:flutter_debug:1.0'
      profileImplementation 'com.example.flutterdemo:flutter_profile:1.0'
      releaseImplementation 'com.example.flutterdemo:flutter_release:1.0'
    }


  4. Add the `profile` build type:

    android {
      buildTypes {
        profile {
          initWith debug
        }
      }
    }

To learn more, visit https://flutter.dev/go/build-aar



<img width="408" alt="Screenshot 2023-09-07 at 2 13 21 PM" src="https://github.com/byju-praveen/AddFlutterPageToNativePOC/assets/121926334/892ee37f-5fb7-46d0-92c1-25c7c56483a0">



<img width="408" alt="Screenshot 2023-09-07 at 2 13 41 PM" src="https://github.com/byju-praveen/AddFlutterPageToNativePOC/assets/121926334/499df909-bfa8-49f9-bf81-6ba8d46a50ee">



<img width="407" alt="Screenshot 2023-09-07 at 2 14 00 PM" src="https://github.com/byju-praveen/AddFlutterPageToNativePOC/assets/121926334/aedd4ae5-6d70-45ea-9b26-d1cc0a725e46">
