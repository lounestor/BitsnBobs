package com.mad.lou;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class BmiProgress extends ProgressBar {



	private String text = "";
	private int textColor = Color.WHITE;
	private float textSize = 15;

	public BmiProgress(Context context) {	
	    super(context);
	
	}
		
	
	public BmiProgress(Context context, AttributeSet attrs) {
	
	    super(context, attrs);
	
	}
	
	
	
	public BmiProgress(Context context, AttributeSet attrs, int defStyle) {
	
	    super(context, attrs, defStyle);
	
	}
	
	@Override
	
	protected synchronized void onDraw(Canvas canvas) {
	
	    super.onDraw(canvas);
	
	    //create an instance of class Paint, set color and font size
	
	    Paint textPaint = new Paint();	
	    textPaint.setAntiAlias(true);	
	    textPaint.setColor(textColor);	
	    textPaint.setTextSize(textSize);
	
	    //to shown text in the middle
	
	    Rect bounds = new Rect();	
	    textPaint.getTextBounds(text, 0, text.length(), bounds);
	
	    //store font size in bounds variable calculate it's position
	
	    int x = getWidth() / 2 - bounds.centerX();	
	    int y = getHeight() / 2 - bounds.centerY();
	
	    //draw text 
	
	    canvas.drawText(text, x, y, textPaint);
	
	}
	
	public String getText() {
		    return text;
	
	}
	
	@SuppressWarnings("unused")
	private void setAttrs(AttributeSet attrs) {
	    if (attrs != null) {
	        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BmiProgress, 0, 0);
	        setText(a.getString(R.styleable.BmiProgress_text));
	        setTextColor(a.getColor(R.styleable.BmiProgress_textColor, Color.GRAY));
	        setTextSize(a.getDimension(R.styleable.BmiProgress_textSize, 15));
	        a.recycle();
	    }
	}



	
	public synchronized void setText(String text) {
	
	    if (text != null) {	
	        this.text = text;
	
	    } else {
	
	        this.text = "";
	
	    }
	
	    postInvalidate();
	
	}
	
		
	public int getTextColor() {
	
	    return textColor;
	
	}
	
	
	
	public synchronized void setTextColor(int textColor) {
	
	    this.textColor = textColor;	
	    postInvalidate();
	
	}
	
	
	
	public float getTextSize() {	
	    return textSize;	
	}
	
	
	
	public synchronized void setTextSize(float textSize) {
	
	    this.textSize = textSize;
	
	    	postInvalidate();
	
		}


	public void setProgressDrawable(int progressbar) {
		// TODO Auto-generated method stub
		
	}



}
