package com.example.stallsbooks.ui.sales.PagerFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.stallsbooks.R;
import com.example.stallsbooks.bean.PieChartBean;
import com.example.stallsbooks.ui.sales.FormAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.openxu.cview.chart.bean.ChartLable;
import com.openxu.cview.chart.piechart.PieChartLayout;
import com.openxu.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


public class DateFragment extends Fragment {

    public static final String ARG_OBJECT = "object";

    private PieChartLayout salesPieChart;

    private FormAdapter formAdapter;
    private TabLayout dateTabLayout;
    private ViewPager2 dateViewpager;

    private String[] dateTabNames = new String[2];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);

        formAdapter = new FormAdapter(this);
        dateViewpager.setAdapter(formAdapter);
        new TabLayoutMediator(dateTabLayout, dateViewpager, (tab, position) -> tab.setText(dateTabNames[position])).attach();

        dateTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(View view) {
        salesPieChart = view.findViewById(R.id.salesPieChart);
        dateTabLayout = view.findViewById(R.id.dateTabLayout);
        dateViewpager = view.findViewById(R.id.dateViewPager);

        dateTabLayout.setSelectedTabIndicatorHeight(0);
        dateTabNames = new String[]{"柱状图", "统计表"};

        /*
         * 圆环宽度
         * ringWidth > 0 :空心圆环，内环为白色，可以在内环中绘制字
         * ringWidth <=0 :实心
         */
        salesPieChart.setRingWidth(DensityUtil.dip2px(getContext(), 15));
        salesPieChart.setLineLenth(DensityUtil.dip2px(getContext(), 8)); // //指示线长度
        salesPieChart.setTagModul(PieChartLayout.TAG_MODUL.MODUL_CHART);       //在扇形图上显示tag
        salesPieChart.setDebug(false);
        salesPieChart.setLoading(true);
//请求数据
        List<PieChartBean> datalist = new ArrayList<>();
        datalist.add(new PieChartBean(20, "理发屋"));
        datalist.add(new PieChartBean(20, "KTV"));
//显示在中间的lable
        List<ChartLable> tableList = new ArrayList<>();
        tableList.add(new ChartLable("建筑", DensityUtil.sp2px(getContext(), 12), getResources().getColor(R.color.text_color_light_gray)));
        tableList.add(new ChartLable("性质", DensityUtil.sp2px(getContext(), 12), getResources().getColor(R.color.text_color_light_gray)));
        salesPieChart.setLoading(false);
//参数1：数据类型   参数2：数量字段名称   参数3：名称字段   参数4：数据集合   参数5:lable集合
        salesPieChart.setChartData(PieChartBean.class, "Numner", "Name",datalist ,tableList);
    }

}