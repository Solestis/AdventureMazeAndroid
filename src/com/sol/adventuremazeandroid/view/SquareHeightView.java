package com.sol.adventuremazeandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class SquareHeightView extends View
{

    public SquareHeightView(final Context context)
    {
        super(context);
    }

    public SquareHeightView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareHeightView(final Context context, final AttributeSet attrs, final int defStyle)
    {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	int height = MeasureSpec.getSize(heightMeasureSpec);
    	int width = height;
    	widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        super.onSizeChanged(h, h, oldw, oldh);
    }
}