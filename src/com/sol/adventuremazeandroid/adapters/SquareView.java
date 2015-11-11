package com.sol.adventuremazeandroid.adapters;

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
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec)
    {
        final int width = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh)
    {
        super.onSizeChanged(w, w, oldw, oldh);
    }
}