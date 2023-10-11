package com.caiquocdat.theme.setupclock;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.core.app.ActivityCompat;

import java.util.Calendar;

public class CustomClock {
    private Bitmap backgroundBitmap;
    private int height, width = 0;
    private int padding = 0;
    private int fontSize = 0;
    private int numeralSpacing = 0;
    private int handTruncation, hourHandTruncation = 0;
    private int radius = 0;
    private boolean isInit;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect rect = new Rect();
    private Paint centerPaint, handPaint, rimPaint, numberPaint;
    private Context context;

    public CustomClock(Context context) {
        this.context = context;
        backgroundBitmap = getWallpaperBitmap(context);
    }

    public Bitmap getWallpaperBitmap(Context context) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

        }
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        if (wallpaperDrawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) wallpaperDrawable).getBitmap();
        } else {
            int width = wallpaperDrawable.getIntrinsicWidth() <= 0 ? 1 : wallpaperDrawable.getIntrinsicWidth();
            int height = wallpaperDrawable.getIntrinsicHeight() <= 0 ? 1 : wallpaperDrawable.getIntrinsicHeight();

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            wallpaperDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            wallpaperDrawable.draw(canvas);
            return bitmap;
        }
    }

    private void initClock(int width, int height) {
        this.height = height;
        this.width = width;
        padding = numeralSpacing + 100;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                context.getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 3 - padding;
        handTruncation = min / 30;
        hourHandTruncation = min / 15;

        centerPaint = new Paint();
        centerPaint.setColor(Color.BLACK);
        centerPaint.setStyle(Paint.Style.FILL);

        handPaint = new Paint();
        handPaint.setColor(Color.WHITE);
        handPaint.setStrokeWidth(8);

        rimPaint = new Paint();
        rimPaint.setColor(Color.WHITE);
        rimPaint.setStrokeWidth(20);
        rimPaint.setStyle(Paint.Style.STROKE);
        rimPaint.setAntiAlias(true);

        numberPaint = new Paint();
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextSize(fontSize);

        isInit = true;
    }

    public void draw(Canvas canvas) {
        if (!isInit) {
            initClock(canvas.getWidth(), canvas.getHeight());
        }
//        if (backgroundBitmap != null) {
//            canvas.drawBitmap(backgroundBitmap, 0, 0, null);
//        }


        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, rimPaint);
        canvas.drawCircle(width / 2, height / 2, 12, centerPaint);

        for (int number : numbers) {
            String tmp = String.valueOf(number);
            numberPaint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
            canvas.drawText(tmp, x, y, numberPaint);
        }

        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + c.get(Calendar.MINUTE) / 60) * 5f, true);
        drawHand(canvas, c.get(Calendar.MINUTE), false);
        drawHand(canvas, c.get(Calendar.SECOND), false);

        canvas.drawCircle(width / 2, height / 2, 12, centerPaint);

        canvas.save();
        canvas.restore();
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2, height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),
                handPaint);
    }
}
