package moe.notify.animenotifier.helpers.glide;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

class AnimateColorMatrixColorFilter {
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

  private void setColorMatrix(ColorMatrix matrix) {
        mMatrix = matrix;
        mFilter = new ColorMatrixColorFilter(matrix);
    }

}
