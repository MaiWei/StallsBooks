package com.example.stallsbooks.ui.sales;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stallsbooks.ui.sales.PagerFragment.DateFragment;
import com.example.stallsbooks.ui.sales.PagerFragment.FormFragment;

public class FormAdapter extends FragmentStateAdapter {

    public FormAdapter(@NonNull DateFragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putInt(FormFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
