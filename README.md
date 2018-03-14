# SeekbarMarker
Add bookmark on seekbar on specific time

# Setup

In the build.gradle of your project add:

```gradle
repositories {
    maven {
       url "https://dl.bintray.com/daupawar/maven"
    }
}
```

In the build.gradle of your app module add:

```gradle
dependencies {
    implementation 'com.rohanpawar:seekbarmarker:1.0'
}
```

# Usage
```xml
 <ron.seekbarmarker.SeekbarMarker
        android:id="@+id/timeline"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:padding="10dp"
        app:marker_color="#88ff053a"
        app:marker_width="3" />
```
'marker_color' and 'marker_width' are optional parameters, set if you dont want to use custom drawble image

```java
seekbar = findViewById(R.id.timeline);

//with drawable
seekbar.addBookmark(50 * 1000, R.drawable.orange_flag);
seekbar.addBookmark(120 * 1000,R.drawable.black_flag);

//without drwable
seekbar.addBookmark(170 * 1000);
```

# License
Copyright [2018] [rohan pawar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
