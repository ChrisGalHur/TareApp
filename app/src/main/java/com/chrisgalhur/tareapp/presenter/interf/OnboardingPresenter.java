package com.chrisgalhur.tareapp.presenter.interf;

public interface OnboardingPresenter {
    void onNextClicked(int position);
    void onBackClicked(int position);
    void onSkipClicked();
    void updateView(int position);
    void loadFragments();
}
