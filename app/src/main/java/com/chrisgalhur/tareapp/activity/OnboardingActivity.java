package com.chrisgalhur.tareapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.util.OnBoardingFragment;
import com.chrisgalhur.tareapp.util.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends BaseActivity {

    //region INJECTION
    private MyViewPagerAdapter myViewPagerAdapter;
    //endregion INJECTION

    //region VARIABLES
    private List<Fragment> fragmentList = new ArrayList<>();
    //endregion VARIABLES

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

        viewPager = findViewById(R.id.viewPagerOnboarding);
        ivDiamond = findViewById(R.id.ivDiamond1FragmentOnBoarding);
        ivDiamond2 = findViewById(R.id.ivDiamond2FragmentOnBoarding);
        ivDiamond3 = findViewById(R.id.ivDiamond3FragmentOnBoarding);
        ivDiamond4 = findViewById(R.id.ivDiamond4FragmentOnBoarding);
        ivDiamond5 = findViewById(R.id.ivDiamond5FragmentOnBoarding);
        tvSkipTop = findViewById(R.id.tvSkipTopOnBoarding);
        tvSkipBack = findViewById(R.id.tvSkipOnBoarding);
        tvNextFinish = findViewById(R.id.tvNextOnBoarding);

        fillFragmentList();
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(myViewPagerAdapter);

        myViewPagerAdapter = new MyViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                updateTextViewListeners(position);
                setDiamondPosition(position);
            }
        });
    }
    //endregion ON_CREATE

    //region UPDATE_TEXT_VIEW_LISTENERS
    private void updateTextViewListeners(int position) {
        if (position == 0){
            tvSkipBack.setOnClickListener(v -> launchMainActivity());
            tvNextFinish.setOnClickListener(v -> viewPager.setCurrentItem(+ 1));
            tvSkipBack.setText("Skip");
            tvNextFinish.setText("Next");
        } else if (position == 4){
            tvSkipBack.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
            tvNextFinish.setOnClickListener(v -> launchMainActivity());
            tvSkipBack.setText("Back");
            tvNextFinish.setText("Finish");
        } else {
            tvSkipBack.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1));
            tvNextFinish.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1));
            tvSkipBack.setText("Back");
            tvNextFinish.setText("Next");
        }
    }
    //endregion UPDATE_TEXT_VIEW_LISTENERS

    //region SET_DIAMOND_POSITION
    private void setDiamondPosition(int position) {
        switch (position){
            case 0:
                ivDiamond.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(TextView.INVISIBLE);
                break;
            case 1:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(TextView.VISIBLE);
                break;
            case 2:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(TextView.VISIBLE);
                break;
            case 3:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_filled);
                ivDiamond5.setImageResource(R.drawable.test_diamond_empty);
                tvSkipTop.setVisibility(TextView.VISIBLE);
                break;
            case 4:
                ivDiamond.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond2.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond3.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond4.setImageResource(R.drawable.test_diamond_empty);
                ivDiamond5.setImageResource(R.drawable.test_diamond_filled);
                tvSkipTop.setVisibility(TextView.INVISIBLE);
                break;
            default:
                break;
        }
    }
    //endregion SET_DIAMOND_POSITION

    //region FILL_FRAGMENT_LIST
    private void fillFragmentList() {
        OnBoardingFragment firstFragment = OnBoardingFragment.newInstance("0");
        OnBoardingFragment secondFragment = OnBoardingFragment.newInstance("1");
        OnBoardingFragment thirdFragment = OnBoardingFragment.newInstance("2");
        OnBoardingFragment fourthFragment = OnBoardingFragment.newInstance("3");
        OnBoardingFragment fifthFragment = OnBoardingFragment.newInstance("4");

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);
        fragmentList.add(fifthFragment);
    }
    //endregion FILL_FRAGMENT_LIST

    //region LAUNCH_MAIN_ACTIVITY
    private void launchMainActivity() {
        startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
    }
    //endregion LAUNCH_MAIN_ACTIVITY
}
