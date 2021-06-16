package com.qw.widget.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.MaterialToolbar;

import java.lang.reflect.Field;


/**
 * Created by qinwei on 16/7/18 上午10:08
 */
public class QToolbar extends MaterialToolbar {
    public static final int GRAVITY_LEFT = 0;
    public static final int GRAVITY_CENTER = 1;
    private int titleGravity;
    private TextView mTitleLabel;
    private CharSequence title;

    public QToolbar(Context context) {
        super(context);
        initializeView(context, null);
    }


    public QToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    public QToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        mTitleLabel = new TextView(context);
        mTitleLabel.setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        mTitleLabel.setMaxLines(1);
        addView(mTitleLabel, params);
        parseAttrs(context, attrs);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QToolbar);
        titleGravity = a.getInteger(R.styleable.QToolbar_titleGravity, Gravity.CENTER);
        try {
            Class<?> toolbarClazz = getClass().getSuperclass().getSuperclass();
            Field mTitleTextAppearance =toolbarClazz.getDeclaredField("mTitleTextAppearance");
            Field mTitleTextColor =toolbarClazz.getDeclaredField("mTitleTextColor");
            mTitleTextAppearance.setAccessible(true);
            mTitleTextColor.setAccessible(true);
            mTitleLabel.setTextAppearance(getContext(), (int) mTitleTextAppearance.get(this));
            mTitleLabel.setTextColor((ColorStateList) mTitleTextColor.get(this));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        a.recycle();
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    /**
     * 设置title显示方位
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        this.title=title;
        if (titleGravity == GRAVITY_CENTER) {
            if(mTitleLabel!=null){
                mTitleLabel.setText(title);
            }
            super.setTitle("");
        } else {
            if(mTitleLabel!=null){
                mTitleLabel.setText("");
            }
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    /**
     * 设置文本方向
     *
     * @param gravity
     */
    public void setTextGravity(int gravity) {
        this.titleGravity = gravity;
    }
}