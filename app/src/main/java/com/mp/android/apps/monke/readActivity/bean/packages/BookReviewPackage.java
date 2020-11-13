package com.mp.android.apps.monke.readActivity.bean.packages;


import com.mp.android.apps.monke.readActivity.bean.BaseBean;
import com.mp.android.apps.monke.readActivity.bean.BookReviewBean;

import java.util.List;

/**
 * Created by newbiechen on 17-4-21.
 */

public class BookReviewPackage extends BaseBean {

    private List<BookReviewBean> reviews;

    public List<BookReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<BookReviewBean> reviews) {
        this.reviews = reviews;
    }
}
