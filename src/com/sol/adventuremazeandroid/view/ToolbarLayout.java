package com.sol.adventuremazeandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ToolbarLayout extends FrameLayout {

	public ToolbarLayout(Context context) {
		super(context);
	}

	public ToolbarLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
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
