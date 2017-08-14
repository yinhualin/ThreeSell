package axhome.com.threesell.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.youngkaaa.ycircleview.CircleView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import axhome.com.threesell.R;
import axhome.com.threesell.utils.MyUtils;
import axhome.com.threesell.utils.NUtils;
import axhome.com.threesell.view.CircleImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static axhome.com.threesell.R.id.et_phone;

public class UserInfoActivity extends AppCompatActivity {

    @InjectView(R.id.iv_head)
    CircleImageView ivHead;
    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.iv_userinfo_dian)
    ImageView ivUserinfoDian;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.iv_uerinfo_edit)
    ImageView ivUerinfoEdit;
    @InjectView(R.id.iv_userinfo_ewm)
    ImageView ivUserinfoEwm;
    @InjectView(R.id.tv_userinfo_phone)
    EditText tvUserinfoPhone;
    @InjectView(R.id.tv_registertime)
    EditText tvRegistertime;
    @InjectView(R.id.tv_homeaddress)
    EditText tvHomeaddress;
    boolean flag = true;
    @InjectView(R.id.rl_userinfo_share)
    RelativeLayout rlUserinfoShare;
    private PopupWindow popWnd;
    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;

    private Intent data;
    private int state;
    private Bitmap bitmap1;
    private Bitmap bitmap2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.iv_head, R.id.back, R.id.iv_uerinfo_edit,R.id.rl_userinfo_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                //头像选择
              getHeadImage();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.iv_uerinfo_edit:
                if (flag) {
                    tvUserinfoPhone.setFocusable(true);
                    tvRegistertime.setFocusable(true);
                    tvHomeaddress.setFocusable(true);
                    tvUserinfoPhone.setBackgroundResource(R.drawable.shape_userinfo_bg);
                    tvRegistertime.setBackgroundResource(R.drawable.shape_userinfo_bg);
                    tvHomeaddress.setBackgroundResource(R.drawable.shape_userinfo_bg);
                    tvUserinfoPhone.setFocusableInTouchMode(true);
                    tvRegistertime.setFocusableInTouchMode(true);
                    tvHomeaddress.setFocusableInTouchMode(true);
                    flag = false;
                } else {
                    tvUserinfoPhone.setFocusable(false);
                    tvRegistertime.setFocusable(false);
                    tvHomeaddress.setFocusable(false);
                    tvUserinfoPhone.setBackgroundResource(0);
                    tvRegistertime.setBackgroundResource(0);
                    tvHomeaddress.setBackgroundResource(0);
                    tvUserinfoPhone.setFocusableInTouchMode(false);
                    tvRegistertime.setFocusableInTouchMode(false);
                    tvHomeaddress.setFocusableInTouchMode(false);
                    flag = true;
                }
                break;
            case R.id.rl_userinfo_share:
                showPopUpWindow();
                break;
        }
    }
    private void showPopUpWindow() {
        View contentView = LayoutInflater.from(UserInfoActivity.this).inflate(R.layout.item_dialog, null);
        popWnd = new PopupWindow(UserInfoActivity.this);
        popWnd.setContentView(contentView);

        popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


        popWnd.setTouchable(true);
        popWnd.setFocusable(true);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        backgroundAlpha(0.6f);

        //添加pop窗口关闭事件
        popWnd.setOnDismissListener(new poponDismissListener());

        popWnd.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popWnd.dismiss();
                    return true;
                }
                return false;
            }
        });

        //popWnd.showAsDropDown(mTvLine, 200, 0);
        popWnd.showAtLocation(UserInfoActivity.this.findViewById(R.id.rl_userinfo_share),
                Gravity.BOTTOM, 0, 0);

    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 添加头像
     */
    private void getHeadImage() {
        final CharSequence[] items = {"相册", "拍照"};
        android.support.v7.app.AlertDialog dlg = new android.support.v7.app.AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("选择图片")
                .setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        if (item == 0) {
                            gallery();

                        } else {
                            checkPermission(3);
                        }
                    }
                }).create();
        dlg.show();
    }
    /*
      * 从相册获取
      */
    public void gallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PHOTO_REQUEST_GALLERY);
    }
    /*
       * 从相机获取
       */
    public void camera() {
        // 拍照
        //设置图片的保存路径,作为全局变量
        Date date = new Date(System.currentTimeMillis());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg";
        File temp = new File(imageFilePath);
        //获取文件的Uri
        imageFileUri = Uri.fromFile(temp);
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
        it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
        startActivityForResult(it, PHOTO_REQUEST_CAREMA);
    }
    public void checkPermission(int state) {
        if (state == 3) {
            int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        CODE_FOR_CAMERA_PERMISSION);
                return;
            } else {
                camera();
            }
        } else {
            int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        CODE_FOR_WRITE_PERMISSION);
                return;
            } else {
                if (state == 1) {
                    fun1();
                } else if (state == 2) {
                    fun2();
                }
            }
        }
    }
    private void fun1() {
        //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
        Uri newUri = Uri.parse("file:///" + getPath(this, data.getData()));
        Date date = new Date(System.currentTimeMillis());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg";
        File temp = new File(imageFilePath);
        //获取文件的Uri
        outputUri = Uri.fromFile(temp);
        cropImageUri(this, newUri, outputUri);
    }
    private void fun2() {
        Date date = new Date(System.currentTimeMillis());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg";
        File temp = new File(imageFilePath);
        //获取文件的Uri
        outputUri = Uri.fromFile(temp);
        cropImageUri(UserInfoActivity.this, imageFileUri, outputUri);
    }
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    //调用系统的剪裁处理图片并保存至imageUri中
    public static void cropImageUri(Activity activity, Uri orgUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (resultCode == -1) {
                this.data = data;
                checkPermission(1);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (resultCode == -1) {
                //从相机拍摄保存的Uri中取出图片，调用系统剪裁工具
                if (imageFilePath != null) {
                    state = 2;
                    checkPermission(2);
                }
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                    ivHead.setImageBitmap(bitmap1);
                    // uploadHeadToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }




}
