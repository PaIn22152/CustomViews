package com.perdev.viewlib.widget.helper;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Project    CustomViews-git
 * Path       com.perdev.viewlib.widget.helper
 * Date       2019/11/15 - 16:59
 * Author     Payne.
 * About      类描述：
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
        float y = startPoint.y + fraction * (endPoint.y - startPoint.y);
        Point point = new Point((int) x, (int) y);
        return point;
    }

}
