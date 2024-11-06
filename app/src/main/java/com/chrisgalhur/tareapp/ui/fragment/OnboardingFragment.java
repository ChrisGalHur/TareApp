package com.chrisgalhur.tareapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.chrisgalhur.tareapp.R;

public class OnboardingFragment extends Fragment {

    //region CONSTANTS
    private static final String ARG_POSITION = "position";
    //endregion CONSTANTS

    //region CONSTRUCTOR
    public OnboardingFragment() {
        // Required empty public constructor to create a new instance
    }
    //endregion CONSTRUCTOR

    //region ON_BOARDING_FRAGMENT
    public static OnboardingFragment newInstance(String position) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }
    //endregion ON_BOARDING_FRAGMENT

    //region ON_CREATE_VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_boarding, container, false);

        return view;
    }
    //endregion ON_CREATE_VIEW

    //region ON_VIEW_CREATED
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = Integer.parseInt(getArguments().getString(ARG_POSITION));

        fillFragment(position);
    }
    //endregion ON_VIEW_CREATED

    //region FILL_FRAGMENT
    private void fillFragment(int position) {
        //Get references to UI elements
        ImageView ivImage = getView().findViewById(R.id.ivFragmentOnBoarding);
        TextView tvDescription = getView().findViewById(R.id.tvDescriptionFragmentOnBoarding);
        ConstraintLayout constraintLayout = getView().findViewById(R.id.constLayFragmentonBoarding);

        //Set all elements depending on position todo: change this to real descriptions and images
        switch (position) {
            case 0:
                ivImage.setImageResource(R.drawable.test_image);
                tvDescription.setText("First description");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.light_blue));
                break;
            case 1:
                ivImage.setImageResource(R.drawable.test_image2);
                tvDescription.setText("Second description");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.medium_blue));
                break;
            case 2:
                ivImage.setImageResource(R.drawable.test_image3);
                tvDescription.setText("Third description");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                break;
            case 3:
                ivImage.setImageResource(R.drawable.test_image4);
                tvDescription.setText("Fourth description");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.medium_blue));
                break;
            case 4:
                ivImage.setImageResource(R.drawable.test_image5);
                tvDescription.setText("Fifth description");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.light_blue));
                break;
            default:
                break;
        }
    }
    //endregion FILL_FRAGMENT
}