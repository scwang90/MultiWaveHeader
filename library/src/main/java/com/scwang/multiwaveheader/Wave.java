package com.scwang.multiwaveheader;

import android.graphics.Path;

/**
 * 水波对象
 * Created by SCWANG on 2017/12/11.
 */
class Wave {

    static int DP = 2;

    Path path;
    int offsetX;
    int offsetY;
    int velocity;
    int width;

    @SuppressWarnings("PointlessArithmeticExpression")
    Wave(int offsetX, int offsetY, int velocity, float scaleX, float scaleY, int w, int h, int wave) {
        w = (int) (scaleX * w / 2);
        wave = (int) (scaleY * wave);

        this.width = 4 * w;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.velocity = velocity;

        path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, h - wave / 2);

        if (DP < 1) {
            DP = 1;
        }
        for (int x = DP; x < width; x += DP) {
            path.lineTo(x, h - wave / 2 - wave / 2 * (float) Math.sin(4.0 * Math.PI * x / width));
        }
        path.lineTo(width, h - wave / 2);
        path.lineTo(width, 0);
        path.close();


    }
}