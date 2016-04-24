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
		
		// ����Բ
		Paint paintCircle = new Paint();
		paintCircle.setStyle(Paint.Style.STROKE);
		//���ÿ���ݣ���ֹ���ű任��ͼƬ���־�ݱ�Ե
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeWidth(5);
		//getWidth() �� getHeight()��View�Լ��ķ��� ��ȡView�Ŀ�͸�
		//���ǻ�һ����View���ĵ�ΪԲ�ģ��뾶ΪView���һ���Բ
		//��������������Ĵ��ݣ�����Ҳ���Կ�����Canvas������ԭ����View���Ͻ�
		canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, paintCircle);
		
		//���̶ȵĻ���
		Paint paintDegree = new Paint();
		paintDegree.setTextAlign(Paint.Align.CENTER);
		//��0��(12��)��ʼ���̶�
		for(int i = 0; i < 12; i ++){
			//���������
			if(i == 0 || i == 3 || i == 6 || i == 9){
				//����Ŀ̶ȣ��߸������ʼ��ȸ���
				paintDegree.setStrokeWidth(5);
				paintDegree.setTextSize(30);
				//����Լ���ĥһ������ߵ������յ��
				canvas.drawLine(this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2, 
						this.getWidth()/2, 
						this.getHeight()/2 - this.getWidth()/2 + 60, 
						paintDegree);
				
				String clockTime = null;
				//������һ�㶼��0����ʾΪ12��
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
			//����ط������Ǿ��õ�����ת����ϵ�ķ������ﵽÿ30�Ƚǻ�һ���̶�
			//ע�⣬�������ת����ϵ����������ת������Ҳ����˵������ִ������������㿴�����κ�Ч���ģ�
			canvas.rotate(30, this.getWidth()/2,this.getHeight()/2);
		}
	}
	
	//��������û����дonMeasure
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
