package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cunoraz.gifview.library.GifView;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class BloodPressure extends AppCompatActivity {

    Button button;
    HorizontalBarChart barChart;
    int[] color=new int[]{Color.RED,Color.YELLOW,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);



        button=findViewById(R.id.button);
        barChart=findViewById(R.id.bar_chart);
        CustomBarChartRender barChartRender = new CustomBarChartRender(barChart,barChart.getAnimator(), barChart.getViewPortHandler());
        barChartRender.setRadius(20);
        barChart.setRenderer(barChartRender);
        BarDataSet barDataSet= new BarDataSet(dataValues(),"bar data set");
        barDataSet.setColors(color);
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setEnabled(false);
        final GifView gifView=findViewById(R.id.gif2);

        gifView.play();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gifView.isPaused()) {
                    gifView.play();
                }
                else {
                    gifView.pause();
                }
            }
        });
    }

    private ArrayList<BarEntry> dataValues(){
        ArrayList<BarEntry> data = new ArrayList<>();
        data.add(new BarEntry(0, new float[]{2,5.5f,4}));
        data.add(new BarEntry(2, new float[]{2,6.5f,4}));
        return  data;
    }
}

class CustomBarChartRender extends BarChartRenderer {

    private RectF mBarShadowRectBuffer = new RectF();

    private int mRadius;

    public CustomBarChartRender(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
        mBarBorderPaint.setColor(dataSet.getBarBorderColor());
        mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(dataSet.getBarBorderWidth()));
        mShadowPaint.setColor(dataSet.getBarShadowColor());
        boolean drawBorder = dataSet.getBarBorderWidth() > 0f;

        float phaseX = mAnimator.getPhaseX();
        float phaseY = mAnimator.getPhaseY();

        if (mChart.isDrawBarShadowEnabled()) {
            mShadowPaint.setColor(dataSet.getBarShadowColor());

            BarData barData = mChart.getBarData();

            float barWidth = barData.getBarWidth();
            float barWidthHalf = barWidth / 2.0f;
            float x;

            int i = 0;
            double count = Math.min(Math.ceil((int) (double) ((float) dataSet.getEntryCount() * phaseX)), dataSet.getEntryCount());
            while (i < count) {

                BarEntry e = dataSet.getEntryForIndex(i);

                x = e.getX();

                mBarShadowRectBuffer.left = x - barWidthHalf;
                mBarShadowRectBuffer.right = x + barWidthHalf;

                trans.rectValueToPixel(mBarShadowRectBuffer);

                if (!mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right)) {
                    i++;
                    continue;
                }

                if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left))
                    break;

                mBarShadowRectBuffer.top = mViewPortHandler.contentTop();
                mBarShadowRectBuffer.bottom = mViewPortHandler.contentBottom();

                c.drawRoundRect(mBarRect, mRadius, mRadius, mShadowPaint);
                i++;
            }
        }

        // initialize the buffer
        BarBuffer buffer = mBarBuffers[index];
        buffer.setPhases(phaseX, phaseY);
        buffer.setDataSet(index);
        buffer.setInverted(mChart.isInverted(dataSet.getAxisDependency()));
        buffer.setBarWidth(mChart.getBarData().getBarWidth());

        buffer.feed(dataSet);

        trans.pointValuesToPixel(buffer.buffer);

        boolean isSingleColor = dataSet.getColors().size() == 1;

        if (isSingleColor) {
            mRenderPaint.setColor(dataSet.getColor());
        }

        int j = 0;
        while (j < buffer.size()) {

            if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                j += 4;
                continue;
            }

            if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j]))
                break;

            if (!isSingleColor) {
                // Set the color for the currently drawn value. If the index
                // is out of bounds, reuse colors.
                mRenderPaint.setColor(dataSet.getColor(j / 4));
            }

            if (dataSet.getGradientColor() != null) {
                GradientColor gradientColor = dataSet.getGradientColor();
                mRenderPaint.setShader(new LinearGradient(
                        buffer.buffer[j],
                        buffer.buffer[j + 3],
                        buffer.buffer[j],
                        buffer.buffer[j + 1],
                        gradientColor.getStartColor(),
                        gradientColor.getEndColor(),
                        android.graphics.Shader.TileMode.MIRROR));
            }

            if (dataSet.getGradientColors() != null) {
                mRenderPaint.setShader(new LinearGradient(
                        buffer.buffer[j],
                        buffer.buffer[j + 3],
                        buffer.buffer[j],
                        buffer.buffer[j + 1],
                        dataSet.getGradientColor(j / 4).getStartColor(),
                        dataSet.getGradientColor(j / 4).getEndColor(),
                        Shader.TileMode.MIRROR));
            }
            Path path2 = roundRect(new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2], buffer.buffer[j + 3]), mRadius, mRadius, true, true, false, false);
            c.drawPath(path2, mRenderPaint);
            if (drawBorder) {
                Path path = roundRect(new RectF(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                        buffer.buffer[j + 3]), mRadius, mRadius, true, true, false, false);
                c.drawPath(path, mBarBorderPaint);
            }
            j += 4;
        }

    }

    private Path roundRect(RectF rect, float rx, float ry, boolean tl, boolean tr, boolean br, boolean bl) {
        float top = rect.top;
        float left = rect.left;
        float right = rect.right;
        float bottom = rect.bottom;
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        if (tr)
            path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        else {
            path.rLineTo(0, -ry);
            path.rLineTo(-rx, 0);
        }
        path.rLineTo(-widthMinusCorners, 0);
        if (tl)
            path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        else {
            path.rLineTo(-rx, 0);
            path.rLineTo(0, ry);
        }
        path.rLineTo(0, heightMinusCorners);

        if (bl)
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        else {
            path.rLineTo(0, ry);
            path.rLineTo(rx, 0);
        }

        path.rLineTo(widthMinusCorners, 0);
        if (br)
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        else {
            path.rLineTo(rx, 0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;
    }}
