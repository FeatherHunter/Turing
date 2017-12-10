package com.hao.turing.Splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hao.turing.R;

/**
 * @description: 启动界面(Viewpager+fragment)，页面切换的最后一页，能跳转进入MainActivity.
 * @date：2017/12/10
 * @author wch
 **/
public class SplashActivity extends FragmentActivity {

    //记录当前Fragment的下标-0,1,2
    private int mintCurrentFragment;

    //radiogroup实现下标
    RadioGroup mBottomRadioGroup;

    ViewPager mViewPager;
    //fragment的类名-用于PagerViewAdapter的getitem
    private String[] fragments=new String[]{
            SplashOneFragment.class.getName(),
            SplashTwoFragment.class.getName(),
            SplashThreeFragment.class.getName()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mBottomRadioGroup = findViewById(R.id.splash_bottom_radiogroup);

        /*
        * 给ViewPager加上Adapter
        * */
        mViewPager = findViewById(R.id.splash_viewpager);
        //getSupportFragmentManager()需要Activity为FragmentActivity
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //根据fragment的包名得到对应的fragment
                return Fragment.instantiate(SplashActivity.this, fragments[position]);
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        //设置viewpager页面改变的监听器-用于切换页面
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mintCurrentFragment = position; //viewpager的页面和下面的按钮对应
                ((RadioButton)mBottomRadioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认选定第一个radiobutton
        ((RadioButton)mBottomRadioGroup.getChildAt(0)).setChecked(true);
    }
}
