package com.example.stallsbooks.bean;

public class PieChartBean {

    private float count;
    private String name;

    public PieChartBean() {
    }

    public PieChartBean(float count, String name) {
        this.count = count;
        this.name = name;
    }

    public float getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }
}
