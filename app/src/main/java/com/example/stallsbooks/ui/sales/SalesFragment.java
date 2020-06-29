package com.example.stallsbooks.ui.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.stallsbooks.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SalesFragment extends Fragment {

    private SalesAdapter salesAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        salesAdapter = new SalesAdapter(this);
        initView(view);
        viewPager.setAdapter(salesAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("OBJECT"+ (position + 1))).attach();

    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        viewPager = view.findViewById(R.id.salesViewPager);
        tabLayout = view.findViewById(R.id.salesTabLayout);

    }

    
}