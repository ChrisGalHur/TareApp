package com.chrisgalhur.tareapp.ui.activity.view;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface OnboardingView {
    void showScreen(int position);
    void showError();
    void navigateToMain();
    void setSkipBackOnClickListener(View.OnClickListener listener);
    void setNextFinishOnClickListener(View.OnClickListener listener);
    void setSkipBackText(String text);
    void setNextFinishText(String text);
    void setCurrentItem(int item);
    int getCurrentItem();
    void setDiamondImage(int position);
    void setFragments(List<Fragment> fragments);
}
