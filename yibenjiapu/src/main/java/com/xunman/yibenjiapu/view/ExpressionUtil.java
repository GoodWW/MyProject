package com.xunman.yibenjiapu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.xunman.yibenjiapu.mode.ExpressionMode;

/**
 * Created by lwk on 2017.4.27.
 * 表情操作
 */
public class ExpressionUtil {
    /**
     * 转换dp
     *
     * @param dpValue
     * @param context
     * @return
     */
    public static int dip2px(float dpValue, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 插入表情
     */
    public static void installExpression(Context mCon, EditText mEditTextContent, ExpressionMode expressionMode) {
        Bitmap bitmap = BitmapFactory.decodeResource(mCon.getResources(), expressionMode.getResources());
        bitmap = Bitmap.createScaledBitmap(bitmap, dip2px(30, mCon), dip2px(30, mCon), true);
        ImageSpan imageSpan = new ImageSpan(mCon, bitmap);
        SpannableString spannableString = new SpannableString(expressionMode.getValue().substring(1, expressionMode.getValue().length() - 1));
        spannableString.setSpan(imageSpan, 0, expressionMode.getValue().length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEditTextContent.getText().insert(mEditTextContent.getSelectionStart(), spannableString);
    }

}
