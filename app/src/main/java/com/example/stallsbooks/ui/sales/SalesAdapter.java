package com.example.stallsbooks.ui.sales;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stallsbooks.ui.sales.PagerFragment.DayFragment;

public class SalesAdapter extends FragmentStateAdapter {

    public SalesAdapter(@NonNull SalesFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new SalesFragment();
        Bundle args = new Bundle();
        args.putInt(DayFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
