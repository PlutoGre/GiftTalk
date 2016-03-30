package com.pluto.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/3/10.
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!没做完！！！！！！！！！！！！！
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;
        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        int heightSum = 0;
        int widthSum = 30;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childViewMeasuredWidth = childView.getMeasuredWidth();
            int childViewMeasuredHeight = childView.getMeasuredHeight();
            widthSum += childViewMeasuredWidth;
            if (widthSum > sizeWidth) {
                widthSum = childViewMeasuredWidth+30;
                heightSum += childViewMeasuredHeight;
                heightSum+=30;
            }
            widthSum+=30;
            if (i==childCount-1){
                heightSum+=childViewMeasuredHeight;
            }
        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : widthSum, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : heightSum);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int childCount = getChildCount();
        int heightSum = 0;
        int widthSum = 30;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childViewMeasuredWidth = childView.getMeasuredWidth();
            int childViewMeasuredHeight = childView.getMeasuredHeight();
            widthSum += childViewMeasuredWidth;
            if (widthSum > measuredWidth) {
                widthSum = childViewMeasuredWidth+30;
                heightSum += childViewMeasuredHeight;
                heightSum+=30;
            }
            childView.layout(widthSum - childViewMeasuredWidth, heightSum
                    , widthSum, heightSum + childViewMeasuredHeight);
            widthSum+=30;
        }
    }
}
