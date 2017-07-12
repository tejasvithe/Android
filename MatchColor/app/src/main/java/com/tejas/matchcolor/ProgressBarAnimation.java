package com.tejas.matchcolor;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation{
    private ProgressBar progressBar;
    private float from;
    private float  to;
    ICallback callback;

    public ProgressBarAnimation(ICallback callback ,ProgressBar progressBar, float from, float to) {
        super();
        this.callback = callback;
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        System.out.println(value);
        if(value> 99)
        callback.onProgressFinished();
    }

    interface ICallback {
        void onProgressFinished();
    }
}