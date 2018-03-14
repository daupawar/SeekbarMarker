# SeekbarMarker
Add bookmark on seekbar on specific time

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

```java
seekbar = findViewById(R.id.timeline);

//with drawable
seekbar.addBookmark(50 * 1000, R.drawable.orange_flag);
seekbar.addBookmark(120 * 1000,R.drawable.black_flag);

//without drwable
seekbar.addBookmark(170 * 1000);
```
