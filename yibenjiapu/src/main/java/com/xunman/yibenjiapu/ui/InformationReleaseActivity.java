package com.xunman.yibenjiapu.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.adapter.FaceGVAdapter;
import com.xunman.yibenjiapu.adapter.FaceVPAdapter;
import com.xunman.yibenjiapu.adapter.GridAdapter;
import com.xunman.yibenjiapu.application.BaseApplication;
import com.xunman.yibenjiapu.dao.Login;
import com.xunman.yibenjiapu.gif.AnimatedGifDrawable;
import com.xunman.yibenjiapu.gif.AnimatedImageSpan;
import com.xunman.yibenjiapu.utils.Bimp;
import com.xunman.yibenjiapu.utils.FileUtils;
import com.xunman.yibenjiapu.utils.HttpImpl;
import com.xunman.yibenjiapu.utils.LogUtils;
import com.xunman.yibenjiapu.utils.MyPopupWindow;
import com.xunman.yibenjiapu.utils.SystemUtils;
import com.xunman.yibenjiapu.utils.ToastUtil;
import com.xunman.yibenjiapu.view.NoScrollGridView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名： MyProject
 * 创建者： lwk
 * 创建时间：  2017/4/27
 * 包名：com.xunman.yibenjiapu.ui
 * 文件名： ${name}
 * 描述：  资讯发布界面
 */

public class InformationReleaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Bundle bundle;
    /**
     * 返回按钮
     */
    private TextView information_release_back;
    /**
     * 表情按钮
     */
    private RelativeLayout rlInformationIconFace;
    /**
     * 放置图片GridView
     */
    private RelativeLayout rlPicture;
    private GridAdapter mAdapter;
    private NoScrollGridView mGridView;
    public static String mImagePath;
    private static final int TAKE_PICTURE = 0x000000;
    private String mImageFileName;
    //发表按钮
    private TextView tvSend;
    //发表资讯标题
    private EditText etInformationTitle;

    /**
     * 表情布局
     */
    private LinearLayout chat_face_container;
    /**
     * 发布内容输入框
     */
    private EditText expressionTextInput;
    private RelativeLayout rl_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_information_release);
        initStaticFaces();
        initView();
//        initExpression();
        BaseApplication.list.add(this);

    }

    private void initView() {
        information_release_back = (TextView) findViewById(R.id.information_release_back);
        rlInformationIconFace = (RelativeLayout) findViewById(R.id.rl_information_icon_face);
        expressionTextInput = (EditText) findViewById(R.id.et_information_input);
        rl_test = (RelativeLayout) findViewById(R.id.rl_test);
        rlPicture = (RelativeLayout) findViewById(R.id.rl_picture);
        etInformationTitle = (EditText) findViewById(R.id.et_information_title);
        chat_face_container = (LinearLayout) findViewById(R.id.chat_face_container);
        mDotsLayout = (LinearLayout) findViewById(R.id.face_dots_container);
        mViewPager = (ViewPager) findViewById(R.id.face_viewpager);
        mViewPager.addOnPageChangeListener(new PageChange());


        rlPicture.setOnClickListener(this);
        mAdapter = new GridAdapter(this);
        mAdapter.update();
        mGridView = (NoScrollGridView) findViewById(R.id.gv_gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.mBmps.size()) {
                    showPopupWindow();
                } else {
                    Intent intent = new Intent(InformationReleaseActivity.this, PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });

        InitViewPager();
        //表情按钮
        rlPicture.setOnClickListener(this);
        tvSend = (TextView) findViewById(R.id.tv_send);
        information_release_back.setFocusable(true);
        information_release_back.setFocusableInTouchMode(true);
        information_release_back.requestFocus();

        tvSend.setOnClickListener(this);
        information_release_back.setOnClickListener(this);
        rlInformationIconFace.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        mAdapter.update();
        super.onRestart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // 拍照
            if (requestCode == TAKE_PICTURE) {
                if (Bimp.mDrr.size() < 9 && resultCode == -1) {
                    File file = new File(Environment.getExternalStorageDirectory() + "/" + mImageFileName);
                    mImagePath = file.getPath();
                    Bimp.mDrr.add(mImagePath);
                }
            }
        }
    }

    /**
     * 拍照
     */
    public void photo() {
        // 随机缓存照片名
        mImageFileName = FileUtils.getFileNameForSystemTime(".jpg");
        // 首先判断SD卡是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), mImageFileName)));
            startActivityForResult(intent, TAKE_PICTURE);
        } else {
            ToastUtil.t(this, "内存卡不存在");
        }
    }

    /**
     * 弹出选择照相和相册的选项框
     */
    private void showPopupWindow() {
        isKeyboardShownToHideKeyboard();
        MyPopupWindow popupWindow = new MyPopupWindow(this);
        String[] str = {"拍照", "从相册中选择"};
        popupWindow.showPopupWindowForFoot(str, new MyPopupWindow.Callback() {
            @Override
            public void callback(String text, int position) {
                switch (position) {
                    case 0:
                        //判断是否有拍照权限
                        if (ContextCompat.checkSelfPermission(InformationReleaseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                            ActivityCompat.requestPermissions(InformationReleaseActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
                        } else {
                            //有权限，直接拍照
                            photo();
                        }
                        break;
                    case 1:
                        verifyStoragePermissions(InformationReleaseActivity.this);
                        break;
                }
            }
        });
    }


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 6.0判断是否有访问sd卡得权限
     *
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            Intent intent = new Intent(InformationReleaseActivity.this, TestPicActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.information_release_back:
                FileUtils.deleteDir();
                Bimp.mBmps.clear();
                Bimp.mDrr.clear();
                Bimp.mMax = 0;
                finish();
                break;
            case R.id.rl_information_icon_face:

                hideSoftInputView();
                if (chat_face_container.getVisibility() == View.GONE) {
                    chat_face_container.setVisibility(View.VISIBLE);
                } else {
                    chat_face_container.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_picture:
                if (mGridView.getVisibility() == View.GONE) {
                    mGridView.setVisibility(View.VISIBLE);
                } else if (mGridView.getVisibility() == View.VISIBLE) {
                    mGridView.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_send:
                send();
                break;
        }
    }

    //网络访问进度条弹框
    private ProgressDialog dialog;

    //发布资讯
    private void send() {
        //设置一个progressdialog的弹窗
        dialog = ProgressDialog.show(InformationReleaseActivity.this, null, "正在发布，请稍候...", true, false);
        //获取用户输入标题、内容
        String etContents = expressionTextInput.getText().toString();
        // 对内容做处理
        SpannableStringBuilder sb = handler(expressionTextInput, etContents);
        String infoTitle = etInformationTitle.getText().toString();
        final List<File> listfile = new ArrayList<>();
        LogUtils.e("etContents", "输入框里面的内容：=====" + sb);
        //上传的图片文件SD卡地址，存放在bimp.mDrr中
        for (int i = 0; i < Bimp.mDrr.size(); i++) {
            listfile.add(new File(Bimp.mDrr.get(i)));
        }
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                LogUtils.e("INFO", msg.obj.toString());
                if (JSON.parseObject(msg.obj.toString()).getInteger("result") == 11) {
                    // 删除缓存
                    //FileUtils.deleteDir();
                    listfile.clear();
                    Bimp.mBmps.clear();
                    Bimp.mDrr.clear();
                    Bimp.mMax = 0;
                    ToastUtil.t(InformationReleaseActivity.this, "发送成功");
                    if (dialog.isShowing()) dialog.dismiss();
                    finish();
                } else if (JSON.parseObject(msg.obj.toString()).getInteger("result") == 12) {
                    ToastUtil.t(InformationReleaseActivity.this, "发送失败");
                    if (dialog.isShowing()) dialog.dismiss();
                }
            }
        };
        //发送信息给服务器
        Map<String, Object> DataMap = new HashMap<>();
        DataMap.put("userid", Integer.valueOf(Login.getLoginInfo("id").toString()));
        DataMap.put("username", Login.getLoginInfo("u_nickname").toString());
        DataMap.put("userhead", Login.getLoginInfo("u_datapath").toString() + Login.getLoginInfo("u_head_url").toString());
        DataMap.put("contents", etContents);
        DataMap.put("title", infoTitle);
        LogUtils.e("DataMap", JSON.toJSONString(DataMap));
        DataMap.put("files", listfile);
        new HttpImpl(DataMap, "http://172.16.1.132:8080/Genealogy/servlet2/info/PublishInfo.xml", HttpImpl.POSTMethd, handler).start();
    }


    private SpannableStringBuilder handler(final TextView gifTextView, String content) {
        SpannableStringBuilder sb = new SpannableStringBuilder(content);
        String regex = "(\\#\\[face/png/f_static_)\\d{3}(.png\\]\\#)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            String tempText = m.group();
            try {
                String num = tempText.substring("#[face/png/f_static_".length(), tempText.length() - ".png]#".length());
                String gif = "face/gif/f" + num + ".gif";
                /**
                 * 如果open这里不抛异常说明存在gif，则显示对应的gif
                 * 否则说明gif找不到，则显示png
                 * */
                InputStream is = this.getAssets().open(gif);
                sb.setSpan(new AnimatedImageSpan(new AnimatedGifDrawable(is, new AnimatedGifDrawable.UpdateListener() {
                            @Override
                            public void update() {
                                gifTextView.postInvalidate();
                            }
                        })), m.start(), m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                is.close();
            } catch (Exception e) {
                String png = tempText.substring("#[".length(), tempText.length() - "]#".length());
                try {
                    sb.setSpan(new ImageSpan(this, BitmapFactory.decodeStream(this.getAssets().open(png))), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return sb;
    }


    private ViewPager mViewPager;
    /**
     * 表情下小圆点布局
     */
    private LinearLayout mDotsLayout;
    /**
     * 表情图标
     */
    private ImageView image_face;
    /**
     * 每行的表情数量
     */
    private int columns = 6;
    /**
     * 共有几行
     */
    private int rows = 4;
    private List<View> views = new ArrayList<>();
    private List<String> staticFacesList;


    /**
     * 初始化表情
     */
    private void InitViewPager() {
        // 获取页数
        for (int i = 0; i < getPagerCount(); i++) {
            views.add(viewPagerItem(i));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(16, 16);
            mDotsLayout.addView(dotsItem(i), params);
        }
        FaceVPAdapter mVpAdapter = new FaceVPAdapter(views);
        mViewPager.setAdapter(mVpAdapter);
        mDotsLayout.getChildAt(0).setSelected(true);
    }

    private int getPagerCount() {
        int count = staticFacesList.size();
        return count % (columns * rows - 1) == 0 ? count / (columns * rows - 1)
                : count / (columns * rows - 1) + 1;
    }

    private View viewPagerItem(int position) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.face_gridview, null);//表情布局
        GridView gridview = (GridView) layout.findViewById(R.id.chart_face_gv);
        /**
         * 注：因为每一页末尾都有一个删除图标，所以每一页的实际表情columns *　rows　－　1; 空出最后一个位置给删除图标
         * */
        List<String> subList = new ArrayList<String>();
        subList.addAll(staticFacesList
                .subList(position * (columns * rows - 1),
                        (columns * rows - 1) * (position + 1) > staticFacesList
                                .size() ? staticFacesList.size() : (columns
                                * rows - 1)
                                * (position + 1)));
        /**
         * 末尾添加删除图标
         * */
        subList.add("emotion_del_normal.png");
        FaceGVAdapter mGvAdapter = new FaceGVAdapter(subList, this);
        gridview.setAdapter(mGvAdapter);
        gridview.setNumColumns(columns);
        // 单击表情执行的操作
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String png = ((TextView) ((LinearLayout) view).getChildAt(1)).getText().toString();
                    if (!png.contains("emotion_del_normal")) {// 如果不是删除图标
                        insert(getFace(png));
                    } else {
                        delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return gridview;
    }


    /**
     * 表情页改变时，dots效果也要跟着改变
     */
    class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
                mDotsLayout.getChildAt(i).setSelected(false);
            }
            mDotsLayout.getChildAt(arg0).setSelected(true);
        }

    }

    /**
     * 初始化表情列表staticFacesList
     */
    private void initStaticFaces() {
        try {
            staticFacesList = new ArrayList<String>();
            String[] faces = getAssets().list("face/png");
            //将Assets中的表情名称转为字符串一一添加进staticFacesList
            for (int i = 0; i < faces.length; i++) {
                staticFacesList.add(faces[i]);
            }
            //去掉删除图片
            staticFacesList.remove("emotion_del_normal.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private SpannableStringBuilder getFace(String png) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        try {
            /**
             * 经过测试，虽然这里tempText被替换为png显示，但是但我单击发送按钮时，获取到輸入框的内容是tempText的值而不是png
             * 所以这里对这个tempText值做特殊处理
             * 格式：#[face/png/f_static_000.png]#，以方便判斷當前圖片是哪一個
             * */
            String tempText = "#[" + png + "]#";
            sb.append(tempText);
            sb.setSpan(
                    new ImageSpan(InformationReleaseActivity.this, BitmapFactory
                            .decodeStream(getAssets().open(png))), sb.length()
                            - tempText.length(), sb.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb;
    }

    /**
     * 向输入框里添加表情
     */
    private void insert(CharSequence text) {
        int iCursorStart = Selection.getSelectionStart((expressionTextInput.getText()));
        int iCursorEnd = Selection.getSelectionEnd((expressionTextInput.getText()));
        if (iCursorStart != iCursorEnd) {
            ((Editable) expressionTextInput.getText()).replace(iCursorStart, iCursorEnd, "");
        }
        int iCursor = Selection.getSelectionEnd((expressionTextInput.getText()));
        ((Editable) expressionTextInput.getText()).insert(iCursor, text);
    }


    /**
     * 删除图标执行事件
     * 注：如果删除的是表情，在删除时实际删除的是tempText即图片占位的字符串，所以必需一次性删除掉tempText，才能将图片删除
     */
    private void delete() {
        if (expressionTextInput.getText().length() != 0) {
            int iCursorEnd = Selection.getSelectionEnd(expressionTextInput.getText());
            int iCursorStart = Selection.getSelectionStart(expressionTextInput.getText());
            if (iCursorEnd > 0) {
                if (iCursorEnd == iCursorStart) {
                    if (isDeletePng(iCursorEnd)) {
                        String st = "#[face/png/f_static_000.png]#";
                        ((Editable) expressionTextInput.getText()).delete(
                                iCursorEnd - st.length(), iCursorEnd);
                    } else {
                        ((Editable) expressionTextInput.getText()).delete(iCursorEnd - 1,
                                iCursorEnd);
                    }
                } else {
                    ((Editable) expressionTextInput.getText()).delete(iCursorStart,
                            iCursorEnd);
                }
            }
        }
    }


    /**
     * 判断即将删除的字符串是否是图片占位字符串tempText 如果是：则讲删除整个tempText
     **/
    private boolean isDeletePng(int cursor) {
        String st = "#[face/png/f_static_000.png]#";
        String content = expressionTextInput.getText().toString().substring(0, cursor);
        if (content.length() >= st.length()) {
            String checkStr = content.substring(content.length() - st.length(),
                    content.length());
            String regex = "(\\#\\[face/png/f_static_)\\d{3}(.png\\]\\#)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(checkStr);
            return m.matches();
        }
        return false;
    }

    private ImageView dotsItem(int position) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dot_image, null);
        ImageView iv = (ImageView) layout.findViewById(R.id.face_dot);
        iv.setId(position);
        return iv;
    }


    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 判断软键盘是否弹起如弹起则隐藏
     */
    private void isKeyboardShownToHideKeyboard() {
        if (SystemUtils.isKeyboardShown(expressionTextInput.getRootView())) {
            SystemUtils.hideKeyboard(this, expressionTextInput.getApplicationWindowToken());
        }
    }

}
