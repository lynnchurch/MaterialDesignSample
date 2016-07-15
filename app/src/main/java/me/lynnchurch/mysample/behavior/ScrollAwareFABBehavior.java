package me.lynnchurch.mysample.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import me.lynnchurch.mysample.R;

/**
 * Created by Lynn on 2016-7-11.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior
{
    private static final android.view.animation.Interpolator INTERPOLATOR =
            new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs)
    {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes)
    {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        if (dyConsumed > 5 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE)
        {
            animateOut(child);
        } else if (dyConsumed < -5 && child.getVisibility() != View.VISIBLE)
        {
            animateIn(child);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to
    // hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButton button)
    {
        Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.hide_to_bottom);
        anim.setInterpolator(INTERPOLATOR);
        anim.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationStart(Animation animation)
            {
                ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
            }

            public void onAnimationEnd(Animation animation)
            {
                ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                button.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(final Animation animation)
            {
            }
        });
        button.startAnimation(anim);
    }

    // Same animation that FloatingActionButton.Behavior
    // uses to show the FAB when the AppBarLayout enters
    private void animateIn(FloatingActionButton button)
    {
        button.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.show_from_bottom);
        anim.setInterpolator(INTERPOLATOR);
        button.startAnimation(anim);
    }
}