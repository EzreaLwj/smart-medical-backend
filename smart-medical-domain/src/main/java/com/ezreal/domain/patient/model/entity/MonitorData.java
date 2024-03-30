package com.ezreal.domain.patient.model.entity;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ezreal
 * @Date 2024/3/30
 */


public class MonitorData implements Serializable {

    private XAxis xAxis;

    private YAxis yAxis;

    private Series series;

    public MonitorData(List<Double> data, String name, List<String> time) {
        this.series = new Series();
        this.xAxis = new XAxis(time);
        this.yAxis = new YAxis();

        series.setData(data);
        series.setName(name);
        series.setType("line");
    }

    @Data
    private static class Series implements Serializable {
        private String name;

        private List<Double> data;

        private String type = "line";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Double> getData() {
            return data;
        }

        public void setData(List<Double> data) {
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @Data
    private static class YAxis implements Serializable {
        private String type = "value";

    }

    @Data
    private static class XAxis implements Serializable {

        private String type;

        private List<String> data;

        public XAxis(List<String> time) {
            this.type = "category";
            this.data = time;

        }

    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public YAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
