# QAppbar
## 1、QToolbar是什么

标题栏控件,基于Toolbar控件封装

* 有返回按钮
* 左标题
* 居中标题
* 子标题
* 右侧菜单

优势：

继承于Toolbar，Toolbar的API QToolbar都支持，会toolbar可轻松上手



效果图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210616173940400.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FpbndlaTE5OTM=,size_16,color_FFFFFF,t_70)

## 2、如何使用

xml中引入com.qw.widget.appbar.QToolbar控件

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
    
    <com.qw.widget.appbar.QToolbar
        style="?attr/toolbarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:title="居中的标题"
        app:menu="@menu/toolbar"
        app:titleGravity="center"
        app:navigationIcon="@drawable/ic_baseline_arrow_back_ios_24" />

    <com.qw.widget.appbar.QToolbar
        style="?attr/toolbarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        app:title="居左标题"
        app:menu="@menu/toolbar"
        app:navigationIcon="@drawable/ic_baseline_arrow_back_ios_24"
        app:titleGravity="left" />
</LinearLayout>
```

属性介绍：

**app:titleGravity** 标题方向值有：left，center 默认center

## 3、自定义样式

```xml
<style name="Theme.QAppbar" parent="Theme.MaterialComponents.DayNight.NoActionBar">
    <!-- Primary brand color. -->
    <item name="colorPrimary">@color/purple_500</item>
    <item name="colorPrimaryVariant">@color/purple_700</item>
    <item name="colorOnPrimary">@color/white</item>
    <!-- Secondary brand color. -->
    <item name="colorSecondary">@color/teal_200</item>
    <item name="colorSecondaryVariant">@color/teal_700</item>
    <item name="colorOnSecondary">@color/black</item>
    <!-- Status bar color. -->
    <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>
    <!-- Customize your theme here. -->
    <item name="toolbarStyle">@style/Toolbar</item>
</style>
<style name="Toolbar" parent="Widget.MaterialComponents.Toolbar.PrimarySurface">
    <!-- 重写titleTextAppearance即可 -->
    <item name="titleTextAppearance">@style/TitleTextAppearance</item>
    <!-- 文字顔色 -->
    <item name="titleTextColor">?attr/colorOnPrimary</item>
    <item name="titleGravity">center</item>
</style>
<style name="TitleTextAppearance">
    <!-- 文字大小 -->
    <item name="android:textSize">@dimen/abc_text_size_title_material_toolbar</item>
    <!-- 文字顔色 -->
    <item name="android:textColor">?android:attr/textColorPrimary</item>
</style>
```

注意：Toolbar在设置控件文本是先设置 `titleTextAppearance` 后设置 `titleTextColor`

**toolbar** 源码 setTitle方法

```java
public void setTitle(CharSequence title) {
    if (!TextUtils.isEmpty(title)) {
        if (mTitleTextView == null) {
            final Context context = getContext();
            mTitleTextView = new AppCompatTextView(context);
            mTitleTextView.setSingleLine();
            mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            if (mTitleTextAppearance != 0) {
                mTitleTextView.setTextAppearance(context, mTitleTextAppearance);
            }
            if (mTitleTextColor != null) {
                mTitleTextView.setTextColor(mTitleTextColor);
            }
        }
        if (!isChildOrHidden(mTitleTextView)) {
            addSystemView(mTitleTextView, true);
        }
    } else if (mTitleTextView != null && isChildOrHidden(mTitleTextView)) {
        removeView(mTitleTextView);
        mHiddenViews.remove(mTitleTextView);
    }
    if (mTitleTextView != null) {
        mTitleTextView.setText(title);
    }
    mTitleText = title;
}
```

`8-10行`设置文本样式  `11-13行`设置文本颜色

## 4、github地址

[qinweiforandroid/QAppbar (github.com)](https://github.com/qinweiforandroid/QAppbar)

如有问题可以与我联系 QQ:435231045  请注明来源
