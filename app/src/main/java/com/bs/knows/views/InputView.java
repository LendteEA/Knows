package com.bs.knows.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bs.knows.R;

/**
 * input_icon 输入框图标
 * input_hint 输入框提示内容
 * is_password 输入的内容是否需要以密文的形式展示
 */
public class InputView extends FrameLayout {

    private EditText mEtInput;

    public InputView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    //5.1以上才调用
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * InputView初始化方法
     */
    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) return;
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.inputView);

        int inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.drawable.ic_phone);
        String inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        boolean isPassword = typedArray.getBoolean(R.styleable.inputView_is_password, false);

        typedArray.recycle();

//       绑定layout布局
        View mView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        ImageView mIvIcon = mView.findViewById(R.id.iv_icon);
        mEtInput= mView.findViewById(R.id.et_input);

//        布局关联属性
        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);
        mEtInput.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_CLASS_PHONE);
        addView(mView);

    }

    /**
     * 返回输入内容
     * @return
     */
    public String getInputStr(){
        return mEtInput.getText().toString().trim();
    }
}
