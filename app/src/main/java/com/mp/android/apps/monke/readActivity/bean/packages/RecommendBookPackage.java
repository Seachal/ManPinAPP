package com.mp.android.apps.monke.readActivity.bean.packages;


import com.mp.android.apps.monke.readActivity.bean.BaseBean;
import com.mp.android.apps.monke.readActivity.bean.CollBookBean;

import java.util.List;

/**
 * Created by newbiechen on 17-5-8.
 */

public class RecommendBookPackage extends BaseBean {

    private List<CollBookBean> books;

    public List<CollBookBean> getBooks() {
        return books;
    }

    public void setBooks(List<CollBookBean> books) {
        this.books = books;
    }
}
