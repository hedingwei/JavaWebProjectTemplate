package com.ambimmort.nisp3.model.ui.f.pub;

/**
 * Created by qinxiaoyao on 2015/6/29.
 */
public class PaginationBean {
    int total = 0;
    int start = 0;
    int interval = 10;
    int end = 10;
    int isClickPagination = 0;

    public int getIsClickPagination() {
        return isClickPagination;
    }

    public void setIsClickPagination(int isClickPagination) {
        this.isClickPagination = isClickPagination;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        if(isClickPagination == 0){
            this.start = 0;
            this.end = 10;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
