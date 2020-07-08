package com.example.stallsbooks.ui.sales;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.stallsbooks.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SalesFragment extends Fragment implements View.OnClickListener {

    private SalesAdapter salesAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private TextView selectTime;
    private TextView inputSales;

    private String[] tabNames = new String[3];

    private int year;
    private int month;
    private int week;
    private int day;

    private Date date;

    public static int position;     // 对应”日“、”周“、”月“的位置

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        salesAdapter = new SalesAdapter(this);
        viewPager.setAdapter(salesAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabNames[position])).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                switch (tab.getPosition()) {
                    case 0:
                        selectTime.setText(getDay(date));
                        break;
                    case 1:
                        selectTime.setText(year + "-" + getWeek(date) + "周");
                        break;
                    case 2:
                        selectTime.setText(getMonth(date));
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

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {
        viewPager = view.findViewById(R.id.salesViewPager);
        tabLayout = view.findViewById(R.id.salesTabLayout);
        selectTime = view.findViewById(R.id.selectTime);
        inputSales = view.findViewById(R.id.inputSales);

        tabLayout.setSelectedTabIndicatorHeight(0);
        tabNames = new String[]{"日", "周", "月"};

        // 初始化时间
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        week = calendar.get(Calendar.WEEK_OF_YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        date = new Date();
        selectTime.setText(getDay(date));

        selectTime.setOnClickListener(this);
        inputSales.setOnClickListener(this);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectTime:
                getTimePickerView(position);
                break;
            case R.id.inputSales:
                // TODO 用户手动输入
                break;
            default:
                break;
        }
    }

    /**
     * 调用时间选择器
     */
    private void getTimePickerView(int pos) {
        Calendar selectDate = Calendar.getInstance();
        selectDate.set(year, month, day);

        Calendar startDate = Calendar.getInstance();
        //TODO 后期读取数据库中的记录的最初日期
        startDate.set(2000, 1, 1);
        Calendar endDate = Calendar.getInstance();

        TimePickerView mTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                selectTime.setText(getDay(date));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatDay = new SimpleDateFormat("dd");

                year = Integer.parseInt(formatYear.format(date));
                month = Integer.parseInt(formatMonth.format(date)) - 1;     // 因为月份对应的值的范围为0-11
                week = getWeek(date);
                day = Integer.parseInt(formatDay.format(date));
            }
        })
                .setDate(selectDate)
                .setRangDate(startDate, endDate)
                .build();

        mTimePickerView.show();

    }

    /**
     * 获取当前年月日
     * @param date
     * @return
     */
    private String getDay(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获取本年第几周
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前月份
     * @param date
     * @return
     */
    private String getMonth(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

}