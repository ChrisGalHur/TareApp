package com.chrisgalhur.tareapp.presenter;

import androidx.fragment.app.Fragment;

import com.chrisgalhur.tareapp.util.OnboardingFragment;
import com.chrisgalhur.tareapp.view.OnboardingView;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingPresenterImpl implements OnboardingPresenter{

    //region INJECTION
    private final OnboardingView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public OnBoardingPresenterImpl(OnboardingView view){
        this.view = view;
    }
    //endregion CONSTRUCTOR

    //region ON_NEXT_CLICKED
    @Override
    public void onNextClicked(int position){
        int nextPosition = view.getCurrentItem() + 1;
        view.setCurrentItem(nextPosition);
    }
    //endregion ON_NEXT_CLICKED

    //region ON_BACK_CLICKED
    @Override
    public void onBackClicked(int position) {
        int backPosition = view.getCurrentItem() - 1;
        view.setCurrentItem(backPosition);
    }
    //endregion ON_BACK_CLICKED

    //region ON_SKIP_CLICKED
    @Override
    public void onSkipClicked() {
        view.navigateToMain();
    }
    //endregion ON_SKIP_CLICKED

    //region UPDATE_VIEW
    @Override
    public void updateView(int position) {
        if (position == 0){
            view.setSkipBackOnClickListener(v -> onSkipClicked());
            view.setNextFinishOnClickListener(v -> onNextClicked(position + 1));
            view.setSkipBackText("Skip");
            view.setNextFinishText("Next");
        } else if (position == 4){
            view.setSkipBackOnClickListener(v -> onBackClicked(position - 1));
            view.setNextFinishOnClickListener(v -> view.navigateToMain());
            view.setSkipBackText("Back");
            view.setNextFinishText("Finish");
        } else {
            view.setSkipBackOnClickListener(v -> onBackClicked(position - 1));
            view.setNextFinishOnClickListener(v -> onNextClicked(position + 1));
            view.setSkipBackText("Back");
            view.setNextFinishText("Next");
        }

        view.setDiamondImage(position);
    }
    //endregion UPDATE_VIEW

    //region LOAD_FRAGMENTS
    @Override
    public void loadFragments() {
        OnboardingFragment firstFragment = OnboardingFragment.newInstance("0");
        OnboardingFragment secondFragment = OnboardingFragment.newInstance("1");
        OnboardingFragment thirdFragment = OnboardingFragment.newInstance("2");
        OnboardingFragment fourthFragment = OnboardingFragment.newInstance("3");
        OnboardingFragment fifthFragment = OnboardingFragment.newInstance("4");

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);
        fragmentList.add(fifthFragment);

        view.setFragments(fragmentList);
    }
    //endregion LOAD_FRAGMENTS
}
