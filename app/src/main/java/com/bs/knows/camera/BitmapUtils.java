package com.bs.knows.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class BitmapUtils {

    /**
     * 保存图片为JPEG
     *
     * @param bitmap 图片文件
     * @param path   保存地址
     */
    public static void saveJPGE_After(Context context, Bitmap bitmap, String path, int quality) {
        File file = new File(path);
        makeDir(file);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                out.flush();
                out.close();
            }
            updateResources(context, file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void makeDir(File file) {
        File tempPath = new File(Objects.requireNonNull(file.getParent()));
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
    }

    private static void updateResources(Context context, String path) {
        MediaScannerConnection.scanFile(context, new String[]{path}, null, null);
    }
}
