package com.caiquocdat.theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.caiquocdat.theme.adapter.CustomPagerAdapter;
import com.caiquocdat.theme.data.DataGenerator;
import com.caiquocdat.theme.databinding.ActivityDetailThemeBinding;
import com.caiquocdat.theme.databinding.ActivityHomeBinding;
import com.caiquocdat.theme.databinding.ActivityThemeBinding;
import com.caiquocdat.theme.model.FavouritesModel;
import com.caiquocdat.theme.model.ThemeModel;
import com.caiquocdat.theme.setupclock.ClockView;
import com.caiquocdat.theme.setupclock.ClockWallpaperService;
import com.caiquocdat.theme.setupclock.CustomClockTheme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailThemeActivity extends AppCompatActivity {
    private ActivityDetailThemeBinding activityDetailThemeBinding;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int YOUR_REQUEST_CODE = 1;
    int potition_detail;
    private int potition_check = 0;
    Bitmap bitmap_theme;
    private static final String PREFERENCES_NAME = "favourites_preferences";
    private static final String KEY_FAVOURITES_LIST = "favourites_list";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    List<FavouritesModel> listDrawableNew;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailThemeBinding = ActivityDetailThemeBinding.inflate(getLayoutInflater());
        View view = activityDetailThemeBinding.getRoot();
        setContentView(view);
        hideSystemUI();
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        Intent intent = getIntent();
        potition_detail = intent.getIntExtra("potition", 0);
        if (getFavouritesList() == null) {
            saveFavouritesList(getDrawableTheme());
        }
        List<FavouritesModel> listDrawable = getFavouritesList();
        if (listDrawable != null) {
            checkFavourite();
            check = listDrawable.get(potition_check).getCheck();
            if (check.equals("true")) {
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart_check);
                activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
            } else {
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart);
                activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
            }
            String drawable = listDrawable.get(potition_check).getPath();
            new GetImageFromUrlTask().execute(drawable);
//        saveDrawableToPreferences(DetailThemeActivity.this,"value");

            CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(this, getDrawableTheme());
            activityDetailThemeBinding.viewpager.setAdapter(customPagerAdapter);
        }


        activityDetailThemeBinding.shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FavouritesModel> listDrawable = getDrawableTheme();
                String drawable = listDrawable.get(potition_check).getPath();
                shareImage(drawable, DetailThemeActivity.this, "Theme");
            }
        });
        activityDetailThemeBinding.downloadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FavouritesModel> listDrawable = getDrawableTheme();
                String drawable = listDrawable.get(potition_check).getPath();
                Log.d("Test_12", "onClick: " + drawable);
                downloadAndSetWallpaper(drawable, getApplicationContext());
                if (potition_detail == 7) {
                    new GetImageFromUrlTask().execute(drawable);
                    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(DetailThemeActivity.this, ClockWallpaperService.class));
                    startActivityForResult(intent, YOUR_REQUEST_CODE);
                }
                Toast.makeText(DetailThemeActivity.this, "Ảnh đã được đặt làm hình nền", Toast.LENGTH_SHORT).show();

            }
        });
        activityDetailThemeBinding.heartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FavouritesModel> listDrawable_check = getFavouritesList();
                String check = listDrawable_check.get(potition_check).getCheck();
                if (check.equals("false")) {
                    listDrawableNew = getFavouritesList();
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart_check);
                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
                    for (int i = 0; i < listDrawableNew.size(); i++) {
                        if (i == potition_check) {
                            FavouritesModel item = listDrawableNew.get(i);
                            item.setCheck("true");
                            listDrawable.set(i, item); // Cập nhật lại danh sách tại vị trí đã thay đổi
                            break;
                        }
                    }
                    saveFavouritesList(listDrawableNew);
                } else {
                    listDrawableNew = getFavouritesList();
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart);
                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
                    for (int i = 0; i < listDrawableNew.size(); i++) {
                        if (i == potition_check) {
                            FavouritesModel item = listDrawableNew.get(i);
                            item.setCheck("false");
                            listDrawable.set(i, item); // Cập nhật lại danh sách tại vị trí đã thay đổi
                            break;
                        }
                    }
                    saveFavouritesList(listDrawableNew);
                }
                if (potition_detail==9) {
                    CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(DetailThemeActivity.this, getDrawableTheme());
                    customPagerAdapter.notifyDataSetChanged();
                    activityDetailThemeBinding.viewpager.setAdapter(customPagerAdapter);
                }

//                if (check.equals("true")){
//                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart_check);
//                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
//                }else{
//                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart);
//                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
//                }
            }
        });
        activityDetailThemeBinding.backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (potition_detail == 7) {
//                    Intent intent = new Intent(DetailThemeActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                } if (potition_detail == 8) {
//                    Intent intent = new Intent(DetailThemeActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                }else {
//                    Intent intent = new Intent(DetailThemeActivity.this, ThemeActivity.class);
//                    startActivity(intent);
//                }
                finish();
            }
        });
        activityDetailThemeBinding.nextImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = activityDetailThemeBinding.viewpager.getCurrentItem();
                if (current < activityDetailThemeBinding.viewpager.getAdapter().getCount() - 1) {
                    activityDetailThemeBinding.viewpager.setCurrentItem(current + 1);
                }
            }
        });
        activityDetailThemeBinding.beforeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = activityDetailThemeBinding.viewpager.getCurrentItem();
                if (current > 0) {
                    activityDetailThemeBinding.viewpager.setCurrentItem(current - 1);
                }
            }
        });
        activityDetailThemeBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // This method will be invoked when the current page is scrolled
            }

            @Override
            public void onPageSelected(int position) {
                // This method will be invoked when a new page becomes selected
                // You can handle your logic here
                potition_check = position;
                List<FavouritesModel> listDrawable = getFavouritesList();
                String check = listDrawable.get(position).getCheck();

                if (check.equals("true")) {
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart_check);
                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
                } else {
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_heart);
                    activityDetailThemeBinding.heartImg.setImageDrawable(drawable);
                }

                activityDetailThemeBinding.shareImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<FavouritesModel> listDrawable = getDrawableTheme();
                        String drawable = listDrawable.get(position).getPath();
                        shareImage(drawable, DetailThemeActivity.this, "Theme");

                    }
                });
                activityDetailThemeBinding.downloadImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<FavouritesModel> listDrawable = getDrawableTheme();
                        String drawable = listDrawable.get(position).getPath();
                        downloadAndSetWallpaper(drawable, v.getContext());
                        Toast.makeText(DetailThemeActivity.this, "Ảnh đã được đặt làm hình nền", Toast.LENGTH_SHORT).show();
                        if (potition_detail == 7) {
                            new GetImageFromUrlTask().execute(drawable);
                            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(DetailThemeActivity.this, ClockWallpaperService.class));
                            startActivityForResult(intent, YOUR_REQUEST_CODE);
                        }

                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            }
        });
    }

    private void checkFavourite() {
        if (getCheckedFavouritesList() == null || getCheckedFavouritesList().size() == 0) {
            Toast.makeText(this, "Bạn chưa thêm ảnh yêu thích!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveDrawableToPreferences(Context context, String key) {
        List<FavouritesModel> listDrawable = getDrawableTheme();
        String drawable = listDrawable.get(potition_check).getPath();
//        Bitmap bitmap = drawableToBitmap(drawable);
        String encodedBitmap = encodeToBase64(bitmap_theme);

        SharedPreferences preferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.putString(key, encodedBitmap);
        editor.apply();
    }

    public Bitmap drawableToBitmap(String imageUrl) {
        try {
            InputStream in = new URL(imageUrl).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encodeToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private BroadcastReceiver wallpaperChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Start drawing the clock here
            if (ContextCompat.checkSelfPermission(DetailThemeActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted, request for permission
                ActivityCompat.requestPermissions(DetailThemeActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // Return null since we don't have permission

            } else {
                // Permission has been granted

                Intent intent_1 = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent_1.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(DetailThemeActivity.this, ClockWallpaperService.class));
                startActivityForResult(intent_1, YOUR_REQUEST_CODE);
            }
        }
    };

    public void downloadAndSetWallpaper(final String imagePath, final Context context) {
        // Chạy trong một luồng khác vì việc tải và thiết lập hình nền có thể tốn thời gian
        new Thread(new Runnable() {
            public void run() {
                try {
                    // Tải hình ảnh từ đường dẫn
                    InputStream in = new URL(imagePath).openStream();
                    bitmap_theme = BitmapFactory.decodeStream(in);

                    // Lưu ảnh vào MediaStore
                    String savedImageURL = MediaStore.Images.Media.insertImage(
                            context.getContentResolver(),
                            bitmap_theme,
                            "title",
                            "description"
                    );

                    // Thông báo cho người dùng biết ảnh đã được lưu
                    // Đặt hình nền
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                    wallpaperManager.setBitmap(resizeImage(bitmap_theme, context));

                    // Thông báo cho người dùng biết hình nền đã được thiết lập

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Bitmap resizeImage(Bitmap image, Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        float scale = 1.2f;

        Bitmap bitmapResized = getResizedBitmap(image, width, height, scale);
        return bitmapResized;
    }

    public Bitmap getResizedBitmap(Bitmap inputBitmap, int screenWidth, int screenHeight, float scale) {
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


    public void shareImage(String imagePath, Context context, String appName) {
        new AsyncTask<String, Void, File>() {
            @Override
            protected File doInBackground(String... strings) {
                String imageUrl = strings[0];
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);

                    // Lưu vào bộ nhớ tạm
                    File cachePath = new File(context.getCacheDir(), "images");
                    cachePath.mkdirs(); // tạo thư mục nếu không tồn tại
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png");
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    stream.close();

                    return new File(cachePath, "image.png");
                } catch (Exception e) {
                    Log.e(appName, "Lỗi khi tải hình ảnh", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(File imageFile) {
                if (imageFile != null && imageFile.exists()) {
                    // Chia sẻ hình ảnh
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/png");
                    Uri photoUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", imageFile);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, photoUri);
                    context.startActivity(Intent.createChooser(shareIntent, "Chia sẻ hình ảnh qua"));
                } else {
                    Log.e(appName, "Tệp hình ảnh không tồn tại");
                }
            }
        }.execute(imagePath);
    }


    ArrayList<FavouritesModel> getDrawableTheme() {
        if (potition_detail == 7) {
            activityDetailThemeBinding.clockView.setVisibility(View.VISIBLE);
            DataGenerator.initializeClockData(this);
            ArrayList<ThemeModel> themeModels = DataGenerator.getThemeClockModels();
            ArrayList<FavouritesModel> listDrawable = themeModels.get(0).getListDetailImagePaths();
            return listDrawable;
        } else if (potition_detail == 8) {
            DataGenerator.initializeData(this);
            ArrayList<FavouritesModel> allImages = new ArrayList<>();
            ArrayList<ThemeModel> themeModels = DataGenerator.getThemeModels();
            for (ThemeModel themeModel : themeModels) {
                allImages.addAll(themeModel.getListDetailImagePaths());
            }
            return allImages;
        }
        if (potition_detail == 9) {
            ArrayList<FavouritesModel> allImages = new ArrayList<>();
            allImages = getCheckedFavouritesList();
            return allImages;
        } else {
            DataGenerator.initializeData(this);
            ArrayList<ThemeModel> themeModels = DataGenerator.getThemeModels();
            ArrayList<FavouritesModel> listDrawable = themeModels.get(potition_detail).getListDetailImagePaths();
            return listDrawable;
        }
    }

    //    void setUpTheme(){
//        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        Point size = new Point();
//        display.getRealSize(size);
//        int width = size.x;
//        int height = size.y;
//        Log.d("Test_1", "width: "+width+", height: "+height);
//
//        Bitmap bitmap = ((BitmapDrawable) getDrawableTheme()).getBitmap();
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 800, 600, true);
//
//        WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailThemeActivity.this);
//        try {
//            wallpaperManager.setBitmap(scaledBitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onResume() {
        hideSystemUI();
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, you can get the wallpaper now
                    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, ClockWallpaperService.class));
                    startActivityForResult(intent, YOUR_REQUEST_CODE);
                    // Handle the wallpaper bitmap as you want
                } else {
                    // Permission was denied, handle the failure
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Người dùng đã cài hình nền thành công
//                finish();
                finishAffinity();
            } else {
                // Người dùng đã hủy hoặc thất bại trong việc cài hình nền
//                finish();
            }
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(wallpaperChangedReceiver);
//    }

    private class GetImageFromUrlTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            String encodedBitmap = encodeToBase64(result);

            SharedPreferences preferences = getSharedPreferences("theme", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("value");
            editor.putString("value", encodedBitmap);
            editor.apply();

        }
    }


    public void saveFavouritesList(List<FavouritesModel> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(list);
        editor.putString(KEY_FAVOURITES_LIST, json);
        editor.apply();
    }

    public ArrayList<FavouritesModel> getFavouritesList() {
        String json = sharedPreferences.getString(KEY_FAVOURITES_LIST, null);
        Type type = new TypeToken<ArrayList<FavouritesModel>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<FavouritesModel> getCheckedFavouritesList() {
        ArrayList<FavouritesModel> allFavourites = getFavouritesList();
        if (allFavourites != null) {
            ArrayList<FavouritesModel> checkedFavourites = new ArrayList<>();

            for (FavouritesModel favouritesModel : allFavourites) {
                if ("true".equals(favouritesModel.getCheck())) {
                    checkedFavourites.add(favouritesModel);
                }
            }
            return checkedFavourites;
        }
        return null;
    }
}