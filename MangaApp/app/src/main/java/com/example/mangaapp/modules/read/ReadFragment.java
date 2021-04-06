package com.example.mangaapp.modules.read;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.mangaapp.R;
import com.example.mangaapp.common.FragmentView;
import com.example.mangaapp.common.Utilities;
import com.example.mangaapp.components.CustomProgress;
import com.example.mangaapp.databinding.FragmentReadBinding;
import com.example.mangaapp.models.Read;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;


public class ReadFragment extends FragmentView<ReadContract.Presenter, FragmentReadBinding> implements ReadContract.View, View.OnTouchListener {
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    int index = 0;
    private ImageView imageView;
    @Override
    protected FragmentReadBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentReadBinding.inflate(inflater, container, false);
    }

    @Override
    protected void init() {
        showBottomBar(false);
        CustomProgress.FadingCircle(getBinding().progressCircular);
        showProgress();
//        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        getBinding().headertitle.iconLeft.setImageDrawable(getContext().getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));
        getBinding().headertitle.iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                showBottomBar(false);
            }
        });
        getPresenter().getAllImage();

    }

    @Override
    public void showProgress() {
        getBinding().progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        getBinding().progressCircular.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadImage(List<Read> list) {

        if (list.size() > 0) {
            Utilities.loadImage(getContext(), list.get(index).getU(), getBinding().photoView);
            getBinding().currentPage.setText(list.get(index).getN() + "/" + list.size());
        }

        getBinding().backPage.setOnClickListener(v -> {
            if (index > 0) {
                index--;
                Utilities.loadImage(getContext(), list.get(index).getU(), getBinding().photoView);
                getBinding().currentPage.setText(list.get(index).getN() + "/" + list.size());

            }

        });


        getBinding().nextPage.setOnClickListener(v -> {
            if (index < (list.size() - 1)) {
                index++;
                Utilities.loadImage(getContext(), list.get(index).getU(), getBinding().photoView);
                getBinding().currentPage.setText(list.get(index).getN() + "/" + list.size());
            }
        });

        hideProgress();

    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        return false;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onT
//        return scaleGestureDetector.onTouchEvent(event);
//    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scaleFactor *= scaleGestureDetector.getScaleFactor();
//            getBinding().imageView.setScaleX(scaleFactor);
//            getBinding().imageView.setScaleY(scaleFactor);
//            return true;
//        }
//    }

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        imageView = (ImageView) v;
        dumpEvent(event);

        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());

                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);

                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    // ...
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        imageView.setImageMatrix(matrix);
        return true;
    }

    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_INDEX_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
    }

    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}