package com.example.basagrisitakip.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.basagrisitakip.R;

public class ButtonForList extends LinearLayout {

    private TextView headerTv, descriptionTv;

    public ButtonForList(Context context) {
        super(context);
    }

    public ButtonForList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ButtonForList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context, AttributeSet attributeSet){
        inflate(context,R.layout.list_button,this);

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                R.styleable.ButtonForList, 0, 0);
        String header = typedArray.getString(R.styleable.ButtonForList_ListHeaderName);
        String description = typedArray.getString(R.styleable.ButtonForList_ListDescriptionOfList);

        typedArray.recycle();

        initComponents();
        setHeaderTv(header);
        setDescriptionTv(description);

    }

    public void initComponents (){
        headerTv = (TextView) findViewById(R.id.list_button_header);
        descriptionTv = (TextView) findViewById(R.id.list_button_description);
    }

    public void setHeaderTv(String value){
        headerTv.setText(value);
    }

    public void setDescriptionTv(String value){
        descriptionTv.setText(value);
    }

    public String getDescriptionText(){
        return descriptionTv.getText().toString();
    }

    public String getHeaderText(){
        return headerTv.getText().toString();
    }

}
