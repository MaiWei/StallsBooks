package com.example.stallsbooks.ui.sales;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stallsbooks.ui.sales.PagerFragment.DateFragment;

public class SalesAdapter extends FragmentStateAdapter {

    public SalesAdapter(@NonNull SalesFragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new DateFragment();
        Bundle args = new Bundle();
        args.putInt(DateFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
