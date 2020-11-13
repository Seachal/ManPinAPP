package com.mp.android.apps.monke.readActivity.bean.packages;

import com.mp.android.apps.monke.readActivity.bean.BaseBean;
import com.mp.android.apps.monke.readActivity.bean.BookSortBean;

import java.util.List;

/**
 * Created by newbiechen on 17-4-23.
 */

public class BookSortPackage extends BaseBean {

    private List<BookSortBean> male;
    private List<BookSortBean> female;

    public List<BookSortBean> getMale() {
        return male;
    }

    public void setMale(List<BookSortBean> male) {
        this.male = male;
    }

    public List<BookSortBean> getFemale() {
        return female;
    }

    public void setFemale(List<BookSortBean> female) {
        this.female = female;
    }
}
