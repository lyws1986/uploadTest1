package com.xiaoye.uploadtest1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseSetupActivity extends Activity implements View.OnClickListener {
     // 该抽象类，作为四个设置向导的基类，定义共同的方法
     private SharedPreferences mSharedPreferences;
     private static String TAG = "BaseSetupActivity";
 
     // 声明一个手势识别器
     private GestureDetector mGestureDetector;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
 
        // 初始化手势识别器
        // 2.初始化手势识别器
        // Basic Default Base Simple
        mGestureDetector = new GestureDetector(this,
               new GestureDetector.SimpleOnGestureListener() {
 
                   /**
                    * 划屏对应的方法. e1 手指第一次触摸屏幕 e2 手指离开屏幕瞬间 velocityX 水平方向移动的速度
                    * <span style="text-decoration: underline;">px</span>/s velocityY 竖直方向移动的速度
                    */
                   @Override
                   public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                      if (Math.abs(velocityX) < 100) {
                          Log.i(TAG, "移动的太慢,动作不合法");
                          return true;
                      }
 
                      if ((e2.getRawX() - e1.getRawX()) > 200) {
                          showPre(null);
                          return true;
                      }
                      if ((e1.getRawX() - e2.getRawX()) > 200) {
                          showNext(null);
                          return true;
                      }
                      // 其实返回的就是false
                      return super.onFling(e1, e2, velocityX, velocityY);
                   }
 
               });
 
        // 子类中初始化布局
        initView();
 
     }
 
     // 设置当前的view布局
     public abstract void initView();
 
     // 显示下一个界面
     public abstract void showNext(View view);
 
     // 显示上一个界面
     public abstract void showPre(View view);
 
     // 3.activity被触摸的时候调用的方法 , 需要在这个里面让手势识别器 生效
     @Override
     public boolean onTouchEvent(MotionEvent event) {
        // 让手势识别器 生效
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
     }


    // 显示上一个界面
    public abstract void Onclick(View v);
    @Override
    public void onClick(View v) {
    }





}