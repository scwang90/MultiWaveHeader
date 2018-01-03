package com.scwang.multiwaveheader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

/**
 * 多重水波纹
 * Created by SCWANG on 2017/12/11.
 */
@SuppressWarnings("unused")
public class MultiWaveHeader extends View {

    private Paint mPaint = new Paint();
    private Matrix mMatrix = new Matrix();
    private List<Wave> mltWave = new ArrayList<>();
    private int mWaveHeight;
    private int mStartColor;
    private int mCloseColor;
    private float mAlphaColor;
    private long mLastTime = 0;

    public MultiWaveHeader(Context context) {
        super(context);
        this.initView(context, null);
    }

    public MultiWaveHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public MultiWaveHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiWaveHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        Wave.DP = dp2px(1);

        mPaint.setAntiAlias(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MultiWaveHeader);

        mWaveHeight = ta.getDimensionPixelOffset(R.styleable.MultiWaveHeader_mwhWaveHeight, dp2px(50));
        mStartColor = ta.getColor(R.styleable.MultiWaveHeader_mwhStartColor, 0xff1372CF);
        mCloseColor = ta.getColor(R.styleable.MultiWaveHeader_mwhCloseColor, 0xFF40B5FF);
        mAlphaColor = ta.getFloat(R.styleable.MultiWaveHeader_mwhAlphaColor, 0.3f);

        if (ta.hasValue(R.styleable.MultiWaveHeader_mwhWaves)) {
            setTag(ta.getString(R.styleable.MultiWaveHeader_mwhWaves));
        } else if (getTag() == null) {
            setTag("70,25,1.4,1.4,-26\n" +
                    "100,5,1.4,1.2,15\n" +
                    "420,0,1.15,1,-10\n" +
                    "520,10,1.7,1.5,20\n" +
                    "220,0,1,1,-15");
        }

        ta.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateWavePath(w,h);
    }

    private void updateWavePath(int w, int h) {
        int wave = mWaveHeight;

        int startColor = ColorUtils.setAlphaComponent(mStartColor, (int)(mAlphaColor*255));
        int closeColor = ColorUtils.setAlphaComponent(mCloseColor, (int)(mAlphaColor*255));
        mPaint.setShader(new LinearGradient(0, 0, w, w, startColor, closeColor, Shader.TileMode.CLAMP));

        mltWave.clear();

        if (getTag() instanceof String) {
            String[] waves = getTag().toString().split("\\s+");
            for (String twave : waves) {
                String[] args = twave.split("\\s*,\\s*");
                if (args.length == 5) {
                    mltWave.add(new Wave(dp2px(parseFloat(args[0])), dp2px(parseFloat(args[1])),dp2px(parseFloat(args[4])), parseFloat(args[2]), parseFloat(args[3]), w, h, wave));
                }
            }
        } else {
            mltWave.add(new Wave(dp2px(50), dp2px(0), dp2px(5), 1.7f, 2f, w, h, wave));
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mltWave.size() > 0) {
            long thisTime = System.currentTimeMillis();
            for (Wave wave : mltWave) {
                mMatrix.reset();
                canvas.save();
                if (mLastTime > 0 && wave.velocity != 0) {
                    int offsetx = (wave.offsetX + (int) (wave.velocity * (thisTime - mLastTime) / 1000f));
                    if (wave.velocity > 0) {
                        offsetx %= wave.width / 2;
                    } else {
                        while (offsetx < 0) {
                            offsetx += (wave.width / 2);
                        }
                    }
                    mMatrix.setTranslate(offsetx, 0);
                    canvas.translate(-offsetx, -wave.offsetY);
                } else{
                    mMatrix.setTranslate(wave.offsetX, 0);
                    canvas.translate(-wave.offsetX, -wave.offsetY);
                }
                mPaint.getShader().setLocalMatrix(mMatrix);
                canvas.drawPath(wave.path, mPaint);
                canvas.restore();
            }
            if (mLastTime == 0) {
                mLastTime = thisTime;
            }
        }
        invalidate();
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }

}
