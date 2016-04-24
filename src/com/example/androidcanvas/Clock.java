package com.example.androidcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Clock extends View{

	public Clock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Clock(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
		// 画外圆
		Paint paintCircle = new Paint();
		paintCircle.setStyle(Paint.Style.STROKE);
		//设置抗锯齿，防止缩放变换后图片出现锯齿边缘
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeWidth(5);
		//getWidth() 和 getHeight()是View自己的方法 获取View的宽和高
		//我们画一个以View中心点为圆心，半径为View宽度一半的圆
		//从这个函数参数的传递，我们也可以看出，Canvas的坐标原点在View左上角
		canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, paintCircle);
		
		//画刻度的画笔
		Paint paintDegree = new Paint();
		paintDegree.setTextAlign(Paint.Align.CENTER);
		//从0点(12点)开始画刻度
		for(int i = 0; i < 12; i ++){
			//区分特殊点
			if(i == 0 || i == 3 || i == 6 || i == 9){
				//特殊的刻度，线更长，笔尖宽度更大
				paintDegree.setStrokeWidth(5);
				paintDegree.setTextSize(30);
				//大家自己琢磨一下这个线的起点和终点吧
				canvas.drawLine(this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2, 
						this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2 + 60, 
						paintDegree);
				
				String clockTime = null;
				//表盘上一般都把0点显示为12点
				if(i == 0)
					clockTime = String.valueOf(i + 12);
				else
					clockTime = String.valueOf(i);
				canvas.drawText(clockTime, this.getWidth()/2, this.getHeight()/2 - this.getWidth()/2 + 90, paintDegree);
			}else{
				paintDegree.setStrokeWidth(3);
				paintDegree.setTextSize(15);
				canvas.drawLine(this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2, 
						this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2 + 30, 
						paintDegree);
				String clockTime = String.valueOf(i);
				canvas.drawText(clockTime, this.getWidth()/2, this.getHeight()/2 - this.getWidth()/2 + 60, paintDegree);
			}
			//这个地方，我们就用到了旋转坐标系的方法来达到每30度角画一个刻度
			//注意，这个是旋转坐标系！并不是旋转画布，也就是说，单独执行这个方法，你看不到任何效果的！
			canvas.rotate(30, this.getWidth()/2,this.getHeight()/2);
		}
	}
	
	//这里我们没有重写onMeasure
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
