package ron.seekbarmarker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * @author Rohan on 13/03/18.
 */

public class SeekbarMarker extends android.support.v7.widget.AppCompatSeekBar {

    private ArrayList<Bookmark> bookmarks = new ArrayList<>();
    private ArrayList<Rect> bookmarksRect = new ArrayList<>();

    private float positionPerSecond = 0;
    private Paint bookmarkPaint;
    private int videoDuration;

    private int bookmarkColor;
    private int bookmarkWidth;

    public SeekbarMarker(Context context) {
        super(context);
        init();
    }

    public SeekbarMarker(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SeekbarMarker,
                0, 0);

        bookmarkColor = a.getColor(R.styleable.SeekbarMarker_marker_color, 0x00);
        bookmarkWidth = a.getInt(R.styleable.SeekbarMarker_marker_width, 2);

        init();
    }

    private void init() {
        bookmarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bookmarkPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bookmarkPaint.setColor(bookmarkColor);
        bookmarkPaint.setStrokeWidth(bookmarkWidth);
    }

    public void addBookmark(int millisec) {
        bookmarks.add(new Bookmark(millisec));
        bookmarksRect.add(new Rect(0, 0, 0, 0));
        invalidate();
    }

    public void addBookmark(int millisec, int drawable) {
        bookmarks.add(new Bookmark(millisec, drawable));
        bookmarksRect.add(new Rect(0, 0, 0, 0));
        invalidate();
    }


    public void setVideoDuration(int duration) {
        this.videoDuration = duration;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (positionPerSecond == 0 && videoDuration != 0) {
            positionPerSecond = (float) getWidth() / (float) videoDuration;
        }


        for (int i = 0; i < bookmarks.size(); i++) {
            Bookmark bookmark = bookmarks.get(i);
            Rect rect = bookmarksRect.get(i);
            Bitmap bitmap = bookmark.getMarkerBitmap();

            if (rect.left == 0 && rect.right == 0 && positionPerSecond != 0) {
                rect.bottom = getHeight();
                rect.left = (int) (positionPerSecond * bookmark.getTime_milsec());
                rect.right = rect.left + 30;
            }

            if (bitmap == null) {
                canvas.drawRect(rect, bookmarkPaint);
            } else {
                canvas.drawBitmap(bookmark.getMarkerBitmap(), null, rect, null);
            }
        }
    }

    private class Bookmark {
        private int time_milsec;
        private int drawableRes;

        public Bookmark(int time_milsec) {
            this.time_milsec = time_milsec;
        }

        public Bookmark(int time_milsec, @DrawableRes int markerImage) {
            this.time_milsec = time_milsec;
            drawableRes = markerImage;
        }

        public int getTime_milsec() {
            return time_milsec;
        }

        public Bitmap getMarkerBitmap() {
            return BitmapFactory.decodeResource(getContext().getResources(), drawableRes);
        }
    }
}