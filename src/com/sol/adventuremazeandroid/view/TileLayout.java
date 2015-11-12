package com.sol.adventuremazeandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class TileLayout extends FrameLayout {

	public TileLayout(Context context) {
		super(context);
	}

	public TileLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TileLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TileLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
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
