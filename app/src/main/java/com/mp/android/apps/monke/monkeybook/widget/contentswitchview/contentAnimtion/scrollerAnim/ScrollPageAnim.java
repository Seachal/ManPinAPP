package com.mp.android.apps.monke.monkeybook.widget.contentswitchview.contentAnimtion.scrollerAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.mp.android.apps.monke.monkeybook.widget.contentswitchview.BookContentView;
import com.mp.android.apps.monke.monkeybook.widget.contentswitchview.ContentSwitchView;
import com.mp.android.apps.monke.monkeybook.widget.contentswitchview.contentAnimtion.MyPageAnimation;
import com.mp.android.apps.utils.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by newbiechen on 17-7-23.
 * 原理:仿照ListView源码实现的上下滑动效果
 * Alter by: zeroAngus
 * <p>
 * 问题:
 * 1. 向上翻页，重复的问题 (完成)
 * 2. 滑动卡顿的问题。原因:由于绘制的数据过多造成的卡顿问题。 (主要是文字绘制需要的时长比较多) 解决办法：做文字缓冲
 * 3. 弱网环境下，显示的问题
 */
public class ScrollPageAnim extends PageAnimation {
    private static final String TAG = "ScrollAnimation";
    // 滑动追踪的时间
    private static final int VELOCITY_DURATION = 1000;
    private VelocityTracker mVelocity;

    // 整个Bitmap的背景显示
    private Bitmap mBgBitmap;

    // 下一个展示的图片
    private Bitmap mNextBitmap;

    // 被废弃的图片列表
    private ArrayDeque<BookContentView> mScrapViews;
    // 正在被利用的图片列表
    private ArrayList<BookContentView> mActiveViews = new ArrayList<>(2);

    // 是否处于刷新阶段
    private boolean isRefresh = true;

    public ScrollPageAnim(int w, int h, int marginWidth, int marginHeight, View view, OnPageChangeListener listener) {
        super(w, h, marginWidth, marginHeight, view, listener);
        initWidget();
    }


    private void initWidget() {
        isRefresh = false;
    }



    // 底部填充
    private Iterator<BookContentView> downIt;


    /**
     * 重置位移
     */
    public void resetBitmap() {
        isRefresh = true;
        // 将所有的Active加入到Scrap中
        for (BookContentView view : mActiveViews) {
            mScrapViews.add(view);
        }
        // 清除所有的Active
        mActiveViews.clear();
        isRefresh = false;
    }

    public boolean onTouchEvent(MotionEvent event, MyPageAnimation.onLayoutStatus onLayoutStatus, List<BookContentView> viewContents, ContentSwitchView contentSwitchView, ContentSwitchView.LoadDataListener loadDataListener) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        // 初始化速度追踪器
        if (mVelocity == null) {
            mVelocity = VelocityTracker.obtain();
        }

        mVelocity.addMovement(event);
        // 设置触碰点
        setTouchPoint(x, y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isRunning = false;
                // 设置起始点
                setStartPoint(x, y);
                // 停止动画
                abortAnim();
                Logger.d("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocity.computeCurrentVelocity(VELOCITY_DURATION);
                isRunning = true;
                // 进行刷新
                mView.postInvalidate();
                Logger.d("onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                isRunning = false;
                // 开启动画
                startAnim();
                // 删除检测器
                mVelocity.recycle();
                mVelocity = null;
                Logger.d("onTouchEvent: ACTION_UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                try {
                    mVelocity.recycle(); // if velocityTracker won't be used should be recycled
                    mVelocity = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.translate(0, mMarginHeight);
        canvas.restore();
    }


    public synchronized void startAnim() {
        isRunning = true;
        mScroller.startScroll(0,0,0,500,2);
//        mScroller.fling(0, (int) mTouchY, 0, (int) mVelocity.getYVelocity()
//                , 0, 0, Integer.MAX_VALUE * -1, Integer.MAX_VALUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void scrollAnim() {
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            setTouchPoint(x, y);
            if (mScroller.getFinalX() == x && mScroller.getFinalY() == y) {
                isRunning = false;
            }
            mView.postInvalidate();
        }
    }

    public void abortAnim() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
            isRunning = false;
        }
    }

}
