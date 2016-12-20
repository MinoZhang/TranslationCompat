package com.learn.translationcompat.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 上传图片工具类
 */
public class UploadPicUtil {


    /**
     * 打开相册
     *
     * @param activity
     * @param requestCode 请求码
     */
    public static void openPhotoAlbum(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");// 相片类型
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开相机
     *
     * @param activity
     * @param requestCode 请求码
     */
    public static File openCamera(Activity activity, int requestCode) {
        FileUtils mFileUtils = new FileUtils(activity);
        File photoDir = new File(mFileUtils.getStorageDirectory());
        Log.i("openCamera: ",mFileUtils.getStorageDirectory().toString());
        if (!photoDir.exists()) {
            photoDir.mkdirs();//创建照片的存储目录
        }
        File mCameraImageFile = new File(photoDir, getPhotoFileName());// 给新照的照片文件命名
        final Intent intent = getTakePickIntent(mCameraImageFile);
        activity.startActivityForResult(intent, requestCode);
        return mCameraImageFile;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyy_MM_dd_HH_mm_ss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 获得打开相机的Intent
     *
     * @param f
     * @return
     */
    private static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 根据view的宽度，动态缩放bitmap尺寸
     *
     * @param width view的宽度
     */
    public static Bitmap getScaledBitmap(String filePath, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(filePath, options);
        //获得压缩比例
        int sampleSize = options.outWidth > width ? options.outWidth / width + 1 : 1;
        options.inJustDecodeBounds = false;//计算好压缩比例后，这次可以去加载原图了
        options.inSampleSize = sampleSize;//设置为刚才计算的压缩比例
        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 加载剪裁
     *
     * @param activity
     * @param imageUri
     * @param requestCode 请求码
     */
    public static void doCropPhoto(Activity activity, Uri imageUri, int requestCode) {
        // 启动gallery去剪辑这个照片
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 400);// 输出图片大小
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 剪裁图片
     *
     * @param activity
     * @param imageUri    图片uri
     * @param outputX     裁剪区的宽
     * @param outputY     裁剪区的高
     * @param requestCode 请求码
     */
    public static void doCropPhoto(Activity activity, Uri imageUri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);//X方向上的比例
        intent.putExtra("aspectY", 2);//Y方向上的比例
        intent.putExtra("outputX", outputX);//裁剪区的宽
        intent.putExtra("outputY", outputY);//裁剪区的高
        intent.putExtra("scale", true);//是否保留比例
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//URI指向相应的file:///...(会改变原始图片)
        intent.putExtra("return-data", true);//是否将数据保留在Bitmap中返回
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//bitmap返回格式
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取图片角度
     *
     * @param filePath 路径
     */
    public static int getPhotoAngle(String filePath) {
        try {
            //根据图片的filepath获取到一个ExifInterface的对象
            ExifInterface exif = new ExifInterface(filePath);
            // 读取图片方向信息
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            // 计算旋转角度
            int angle = 0;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;
                default:
                    angle = 0;
                    break;
            }
            return angle;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将图片角度设置为0
     *
     * @param bitmap 要旋转的图片
     * @param angle  旋转角度
     * @return
     */
    public static Bitmap setPhotoAngle(Bitmap bitmap, int angle) {
        // 旋转图片
        Matrix m = new Matrix();
        m.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }

    /**
     * 获得Bitmap的二进制byte[]数组
     */
    public static byte[] getPhotoByte(Bitmap bitmap) {
        //把图片压缩到流中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//如果不压缩是100，表示压缩率为0
        return baos.toByteArray();
    }

}
