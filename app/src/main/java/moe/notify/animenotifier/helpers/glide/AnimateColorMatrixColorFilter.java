package moe.notify.animenotifier.helpers.glide;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;


public class AnimateColorMatrixColorFilter {
    private ColorMatrixColorFilter mFilter;
    private ColorMatrix mMatrix;

    public AnimateColorMatrixColorFilter(ColorMatrix matrix) {
        setColorMatrix(matrix);
    }

    public ColorMatrixColorFilter getColorFilter() {
        return mFilter;
    }

    public ColorMatrix getColorMatrix() {
        return mMatrix;
    }

    public void setColorMatrix(ColorMatrix matrix) {
        mMatrix = matrix;
        mFilter = new ColorMatrixColorFilter(matrix);
    }

}
