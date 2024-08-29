package com.chrisgalhur.tareapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.OnBoardingPresenterImpl;
import com.chrisgalhur.tareapp.presenter.OnboardingPresenter;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.util.OnboardingFragment;
import com.chrisgalhur.tareapp.util.MyViewPagerAdapter;
import com.chrisgalhur.tareapp.view.OnboardingView;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends BaseActivity implements OnboardingView {

    //region INJECTION
    private OnboardingPresenter presenter;
    //endregion INJECTION

    //region UI
    private ViewPager2 viewPager;
    private ImageView ivDiamond;
    private ImageView ivDiamond2;
    private ImageView ivDiamond3;
    private ImageView ivDiamond4;
    private ImageView ivDiamond5;
    private TextView tvSkipTop;
    private TextView tvSkipBack;
    private TextView tvNextFinish;
    //endregion UI

    //region ON_CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayoutOnboarding), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenter = new OnBoardingPresenterImpl(this);

        viewPager = findViewById(R.id.viewPagerOnboarding);
        ivDiamond = findViewById(R.id.ivDiamond1FragmentOnBoarding);
        ivDiamond2 = findViewById(R.id.ivDiamond2FragmentOnBoarding);
        ivDiamond3 = findViewById(R.id.ivDiamond3FragmentOnBoarding);
        ivDiamond4 = findViewById(R.id.ivDiamond4FragmentOnBoarding);
        ivDiamond5 = findViewById(R.id.ivDiamond5FragmentOnBoarding);
        tvSkipTop = findViewById(R.id.tvSkipTopOnBoarding);
        tvSkipBack = findViewById(R.id.tvSkipOnBoarding);
        tvNextFinish = findViewById(R.id.tvNextOnBoarding);

        presenter.loadFragments();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                presenter.updateView(position);
            }
        });
    }
    //endregion ON_CREATE

    //region SHOW_SCREEN
    @Override
    public void showScreen(int position) {
        viewPager.setCurrentItem(position);
    }
    //endregion SHOW_SCREEN

    //region SHOW_ERROR
    @Override
    public void showError() {
        //todo don't have thought of this yet
    }
    //endregion SHOW_ERROR

    //region NAVIGATE_TO_MAIN
    @Override
    public void navigateToMain() {
        startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
    }
    //endregion NAVIGATE_TO_MAIN

    //region SET_SKIP_BACK_ON_CLICK_LISTENER
    @Override
    public void setSkipBackOnClickListener(View.OnClickListener listener) {
        tvSkipBack.setOnClickListener(listener);
    }
    //endregion SET_SKIP_BACK_ON_CLICK_LISTENER

    //region SET_NEXT_FINISH_ON_CLICK_LISTENER
    @Override
    public void setNextFinishOnClickListener(View.OnClickListener listener) {
        tvNextFinish.setOnClickListener(listener);
    }
    //endregion SET_NEXT_FINISH_ON_CLICK_LISTENER

    //region SET_SKIP_BACK_TEXT
    @Override
    public void setSkipBackText(String text) {
        tvSkipBack.setText(text);
    }
    //endregion SET_SKIP_BACK_TEXT

    //region SET_NEXT_FINISH_TEXT
    @Override
    public void setNextFinishText(String text) {
        tvNextFinish.setText(text);
    }
    //endregion SET_NEXT_FINISH_TEXT

    //region SET_CURRENT_ITEM
    @Override
    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item);
    }
    //endregion SET_CURRENT_ITEM

    //region GET_CURRENT_ITEM
    @Override
    public int getCurrentItem() {
        return viewPager.getCurrentItem();
    }
    //endregion GET_CURRENT_ITEM

    //region SET_DIAMOND_IMAGE
    @Override
    public void setDiamondImage(int position) {
        switch (position){
            case 0:
                ivDiamond.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(View.INVISIBLE);
                break;
            case 1:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(View.VISIBLE);
                break;
            case 3:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(View.VISIBLE);
                break;
            case 4:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_filled);
                tvSkipTop.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }
    //endregion SET_DIAMOND_IMAGE

    //region SET_FRAGMENTS
    @Override
    public void setFragments(List<Fragment> fragments) {
        MyViewPagerAdapter myViewPagerAdapter;
        List<Fragment> fragmentList;
        fragmentList = fragments;
        myViewPagerAdapter = new MyViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(myViewPagerAdapter);
    }
    //endregion SET_FRAGMENTS
}
