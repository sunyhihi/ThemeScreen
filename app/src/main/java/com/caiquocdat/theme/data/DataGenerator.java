package com.caiquocdat.theme.data;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.caiquocdat.theme.R;
import com.caiquocdat.theme.model.FavouritesModel;
import com.caiquocdat.theme.model.ThemeModel;

import java.util.ArrayList;

public class DataGenerator {
    private static ArrayList<ThemeModel> themeModels,clockMoels;
    public static void initializeClockData(Context context) {
        clockMoels = new ArrayList<>();
        String image_7 = "https://i.pinimg.com/564x/dc/aa/fd/dcaafd57efe8c277246e26d701cd7d71.jpg";
        ArrayList<FavouritesModel> imageList_7 = new ArrayList<>();

        imageList_7.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/39/2f/9c/392f9c98514ac00c46bfb7d7a3e8b041.jpg"));
        imageList_7.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/a9/59/7c/a9597c9e0154492577dcd20065f70f00.jpg"));
        imageList_7.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/72/c5/ef/72c5ef56e0bb93a44eaf25d9fa172ecf.jpg"));
        imageList_7.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/13/f9/c2/13f9c24545190533e54d2279f175948d.jpg"));
        imageList_7.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/19/68/42/196842ec3189f977afe2ef32ed6000f6.jpg"));
        imageList_7.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/7d/3e/9e/7d3e9e49bb53fe8d3667f24da723727d.jpg"));
        imageList_7.add(new FavouritesModel(7,"false","https://i.pinimg.com/564x/19/8b/b0/198bb0722a365dff848ec5c0ffa88075.jpg"));
        imageList_7.add(new FavouritesModel(8,"false","https://i.pinimg.com/564x/d9/f4/d6/d9f4d6b9d49c5f79d47b137d4f184600.jpg"));
        imageList_7.add(new FavouritesModel(9,"false","https://i.pinimg.com/564x/1b/c3/0e/1bc30e50eb1344c9424b55582c774548.jpg"));
        imageList_7.add(new FavouritesModel(10,"false","https://i.pinimg.com/564x/66/47/12/664712bc65b287a240122f03b64cc78d.jpg"));
        imageList_7.add(new FavouritesModel(11,"false","https://i.pinimg.com/564x/ac/ae/80/acae8073ecebd0d72badb9997e1da227.jpg"));
        imageList_7.add(new FavouritesModel(12,"false","https://i.pinimg.com/564x/5f/15/63/5f156398bc049e08d2fc721ba9b295d4.jpg"));
        imageList_7.add(new FavouritesModel(13,"false","https://i.pinimg.com/564x/5b/40/f9/5b40f95da4dcfda3dff2ef0257be4e88.jpg"));
        imageList_7.add(new FavouritesModel(14,"false","https://i.pinimg.com/564x/a3/59/2a/a3592a38eb8db7ca7aa1bc9aa958e385.jpg"));
        imageList_7.add(new FavouritesModel(15,"false","https://i.pinimg.com/564x/9e/a3/24/9ea324a29f41977d4b84bd378fb36ba1.jpg"));
        clockMoels.add(new ThemeModel(1, "Built-In Clock", image_7,imageList_7));

    }

    public static void initializeData(Context context) {
        themeModels = new ArrayList<>();

        String image_1 = "https://i.pinimg.com/564x/5b/09/2f/5b092f2efbf49f2ba254c095279b7542.jpg"; // Cập nhật đường dẫn tại đây
        ArrayList<FavouritesModel> imageList_1 = new ArrayList<>();
        imageList_1.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/cd/1d/3b/cd1d3ba9af373c9d675926c567f8a787.jpg"));
        imageList_1.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/51/01/22/5101221127fce7975b59638913b39818.jpg"));
        imageList_1.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/f9/69/4d/f9694d732898ecb7cd09691c5c502d2f.jpg"));
        imageList_1.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/36/83/01/368301b2f232095c5fbe77a582d92a61.jpg"));
        imageList_1.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/aa/d5/85/aad5852535a860cddc1e28c90f5683db.jpg"));
        imageList_1.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/c1/27/b3/c127b301861e7f21fcc8a16446d40de4.jpg"));
        themeModels.add(new ThemeModel(1, "Luxury", image_1, imageList_1));

        String image_2 = "https://i.pinimg.com/564x/a3/28/33/a328331632ae2c4a2cb89fc1b569159d.jpg"; // Cập nhật đường dẫn tại đây
        ArrayList<FavouritesModel> imageList_2 = new ArrayList<>();
        imageList_2.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/4d/3a/d4/4d3ad4de4d9a76e339174c89db415609.jpg"));
        imageList_2.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/f9/b1/77/f9b1779583cfc1604d61a3b26dfd46ec.jpg"));
        imageList_2.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/d6/d1/0d/d6d10da78df9575973fac22ce41339b9.jpg"));
        imageList_2.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/f9/b1/77/f9b1779583cfc1604d61a3b26dfd46ec.jpg"));
        imageList_2.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/ab/08/55/ab0855c536505e543f6d4d0634c9a6af.jpg"));
        imageList_2.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/a6/fa/71/a6fa7106180bcff521f56c8564cccf4c.jpg"));
        // Add more paths as needed
        themeModels.add(new ThemeModel(2, "Classic", image_2, imageList_2));

        String image_3 = "https://i.pinimg.com/564x/71/c2/31/71c2311a35f26eca8619d93c4d0c027c.jpg"; // Cập nhật đường dẫn tại đây
        ArrayList<FavouritesModel> imageList_3 = new ArrayList<>();
        imageList_3.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/b5/c9/b3/b5c9b366e342c3ac537f3747bb7756e7.jpg"));
        imageList_3.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/06/40/3b/06403bdbd7127e2ec9fa30e242b3f6ab.jpg"));
        imageList_3.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/9e/3a/58/9e3a58606fea3714161c86a194734255.jpg"));
        imageList_3.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/90/d7/29/90d729dbb1021ddc641c8273b683cbe7.jpg"));
        imageList_3.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/12/03/85/1203855e314914adcc77abd77dcd41f0.jpg"));
        imageList_3.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/9f/8b/76/9f8b766419fe9ceffba672117295cdd7.jpg"));
        // Add more paths as needed
        themeModels.add(new ThemeModel(3, "Fiction", image_3, imageList_3));
        String image_4 = "https://i.pinimg.com/564x/64/9b/55/649b550475cb397349b3b318771d5dbb.jpg"; // Cập nhật đường dẫn tại đây
        ArrayList<FavouritesModel> imageList_4 = new ArrayList<>();

        imageList_4.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/a3/b5/d3/a3b5d32b17daa95819ce85d57e63875b.jpg"));
        imageList_4.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/7d/35/71/7d35711bffc5cb253268b55929d8a184.jpg"));
        imageList_4.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/60/65/23/606523805f6cabe7cbf24e1cfd61ec30.jpg"));
        imageList_4.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/eb/8d/35/eb8d3519d1e754f427f397bafa1de023.jpg"));
        imageList_4.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/87/b7/7f/87b77fde6b1c0be3985105b53425ded7.jpg"));
        imageList_4.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/96/1a/98/961a98b674ce0899ae9dc79508e542ff.jpg"));
        themeModels.add(new ThemeModel(4, "Hourglass", image_4,imageList_4));
        String image_5 = "https://i.pinimg.com/564x/87/90/3e/87903e31c216b8c49e79cdd15d2edd2b.jpg";
        ArrayList<FavouritesModel> imageList_5 = new ArrayList<>();

        imageList_5.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/a5/2c/5e/a52c5eb317e4addc80e453657a41112f.jpg"));
        imageList_5.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/f2/e0/35/f2e035faaed5212e808afdeedf029fcf.jpg"));
        imageList_5.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/4b/64/3a/4b643a977c49ed221ee87f443efb106d.jpg"));
        imageList_5.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/77/f0/0d/77f00d22c452bf06832215b7b7b3d354.jpg"));
        imageList_5.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/90/48/17/9048173fa72bcd7a667d9cb7c18be710.jpg"));
        imageList_5.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/1e/83/47/1e8347a5c96a1c5e5b0ecf26d43b6fe4.jpg"));
        themeModels.add(new ThemeModel(5, "Modern", image_5,imageList_5));
        String image_6 = "https://i.pinimg.com/564x/e5/b0/ac/e5b0ac90daa927df7627635cde88f4ba.jpg";
        ArrayList<FavouritesModel> imageList_6 = new ArrayList<>();

        imageList_6.add(new FavouritesModel(1,"false","https://i.pinimg.com/564x/03/f6/1b/03f61b34cd02667faf6d9c110dadf951.jpg"));
        imageList_6.add(new FavouritesModel(2,"false","https://i.pinimg.com/564x/08/fe/d1/08fed10b09a3f0d53b9efe5b4a6dac2e.jpg"));
        imageList_6.add(new FavouritesModel(3,"false","https://i.pinimg.com/564x/88/a9/9e/88a99e992e1d206cee5e60126a699b30.jpg"));
        imageList_6.add(new FavouritesModel(4,"false","https://i.pinimg.com/564x/31/e7/b2/31e7b268cb7ea8fee41803d6b0004884.jpg"));
        imageList_6.add(new FavouritesModel(5,"false","https://i.pinimg.com/564x/15/0f/b3/150fb3911c397427710bc571ed46a421.jpg"));
        imageList_6.add(new FavouritesModel(6,"false","https://i.pinimg.com/564x/4a/c4/af/4ac4af0f1f1ffe628d1341077b6e931e.jpg"));
        themeModels.add(new ThemeModel(6, "Recall", image_6,imageList_6));
        // Add more theme models as needed
    }

    public static ArrayList<ThemeModel> getThemeModels() {
        return themeModels;
    }
    public static ArrayList<ThemeModel> getThemeClockModels() {
        return clockMoels;
    }
}
