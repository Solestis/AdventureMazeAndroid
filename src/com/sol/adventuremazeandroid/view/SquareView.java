package com.sol.adventuremazeandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class SquareView extends View
{

    public SquareView(final Context context)
    {
        super(context);
    }

    public SquareView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareView(final Context context, final AttributeSet attrs, final int defStyle)
    {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int width = MeasureSpec.getSize(widthMeasureSpec);
       int height = width;
       heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
       super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        super.onSizeChanged(w, w, oldw, oldh);
    }
}