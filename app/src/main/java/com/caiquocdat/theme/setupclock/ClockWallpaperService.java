package com.caiquocdat.theme.setupclock;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Base64;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.caiquocdat.theme.R;

public class ClockWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new ClockWallpaperEngine();
    }

    private class ClockWallpaperEngine extends Engine {
        private CustomClockTheme customClock;
        private final Handler handler = new Handler(); // Create a Handler

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            customClock = new CustomClockTheme(ClockWallpaperService.this,resizeImage(getDrawableFromPreferences(getApplicationContext(),"value")));
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            if (visible) {
                draw();
            } else {
                handler.removeCallbacks(drawRunner); // Remove callback when not visible
            }
        }

        private final Runnable drawRunner = new Runnable() { // Create a Runnable
            @Override
            public void run() {
                draw();
            }
        };

        private void draw() {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    // Clear the canvas
                    canvas.drawColor(Color.BLACK); // Replace Color.BLACK with the background color of your clock
                    customClock.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            if (isVisible()) {
                handler.postDelayed(drawRunner, 1000); // Use Handler's postDelayed
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawRunner); // Remove callback when Engine is destroyed
        }
    }
    public Drawable getDrawableFromPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);
        String encodedBitmap = preferences.getString(key, null);
        if (encodedBitmap != null) {
            Bitmap bitmap = decodeFromBase64(encodedBitmap);
            return new BitmapDrawable(context.getResources(), bitmap);
        }
        return null; // Return null if no value found
    }

    public Bitmap decodeFromBase64(String encodedBitmap) {
        byte[] byteArray = Base64.decode(encodedBitmap, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
    public Bitmap resizeImage(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        float scale = 1.2f;

        Bitmap bitmapResized = getResizedBitmap(image, width, height+200, scale);;
        return bitmapResized;
    }
    public Bitmap getResizedBitmap(Drawable image, int screenWidth, int screenHeight, float scale) {
        // Chuyển Drawable thành Bitmap
        Bitmap inputBitmap = ((BitmapDrawable) image).getBitmap();

        // Tính tỷ lệ giữa chiều rộng và chiều dài của ảnh
        float aspectRatio = inputBitmap.getWidth() / (float) inputBitmap.getHeight();

        // Tính chiều rộng và chiều dài mới cho ảnh dựa trên tỷ lệ
        int width = Math.round(screenHeight * aspectRatio);
        int height = screenHeight;

        // Thay đổi kích thước ảnh theo chiều rộng và chiều dài mới
        Bitmap outputBitmap = Bitmap.createScaledBitmap(inputBitmap, width, height, false);

        // Tạo một ma trận để phóng đại ảnh
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale, outputBitmap.getWidth() / 2f, outputBitmap.getHeight() / 2f);

        // Tạo một ảnh mới đã được phóng đại từ ảnh gốc
        outputBitmap = Bitmap.createBitmap(outputBitmap, 0, 0, outputBitmap.getWidth(), outputBitmap.getHeight(), matrix, true);

        // Nếu chiều rộng của ảnh lớn hơn chiều rộng màn hình, cắt ảnh để vừa với màn hình
        if (outputBitmap.getWidth() > screenWidth) {
            outputBitmap = Bitmap.createBitmap(outputBitmap, outputBitmap.getWidth() / 2 - screenWidth / 2, 0, screenWidth, screenHeight);
        }

        // Nếu chiều dài của ảnh lớn hơn chiều dài màn hình, cắt ảnh để vừa với màn hình
        if (outputBitmap.getHeight() > screenHeight) {
            outputBitmap = Bitmap.createBitmap(outputBitmap, 0, outputBitmap.getHeight() / 2 - screenHeight / 2, screenWidth, screenHeight);
        }

        return outputBitmap;
    }
}
