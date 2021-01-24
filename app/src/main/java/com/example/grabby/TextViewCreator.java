package com.example.grabby;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class TextViewCreator {
    private final TextView textView;
    private Context context;
    public TextViewCreator(Context context){
        this.context=context;
        textView =new TextView(context);

    }
    public TextViewCreator setLayoutParameters(int width,int height){
        textView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        return this;

    }
    public TextViewCreator setMarginParameters(int top,int bottom,int left,int right){
        int topInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, top, context.getResources()
                        .getDisplayMetrics());

        int bottomInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, bottom, context.getResources()
                        .getDisplayMetrics());

        int rightInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, right, context.getResources()
                        .getDisplayMetrics());

        int leftInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, left, context.getResources()
                        .getDisplayMetrics());

        if(textView.getLayoutParams()==null){
            ViewGroup.MarginLayoutParams marginLayoutParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            marginLayoutParams.setMargins(leftInDp,topInDp,rightInDp,bottomInDp);
            textView.setLayoutParams(marginLayoutParams);

        }else{
            ViewGroup.MarginLayoutParams marginLayoutParams=new ViewGroup.MarginLayoutParams(textView.getLayoutParams());
            marginLayoutParams.setMargins(leftInDp,topInDp,rightInDp,bottomInDp);
            textView.setLayoutParams(marginLayoutParams);

        }
        return this;

    }
    public TextViewCreator setBackground(int resource){
        textView.setBackgroundResource(resource);
        return this;
    }
    public TextViewCreator setText(String text){
        textView.setText(text);
        return this;
    }
    public TextViewCreator setTextSize(int unit,float size){
        textView.setTextSize(unit,size);
        return this;
    }
    public TextView getTextView(){
        return textView;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextViewCreator setElevation(float elevation){
        textView.setElevation(elevation);
        return this;
    }
    public TextViewCreator setPadding(int left,int right,int top,int bottom){
        int topInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, top, context.getResources()
                        .getDisplayMetrics());

        int bottomInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, bottom, context.getResources()
                        .getDisplayMetrics());

        int rightInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, right, context.getResources()
                        .getDisplayMetrics());

        int leftInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, left, context.getResources()
                        .getDisplayMetrics());
        textView.setPadding(leftInDp,topInDp,rightInDp,bottomInDp);
        return this;
    }
}
