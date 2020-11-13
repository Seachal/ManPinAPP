package com.mp.android.apps.monke.readActivity.bean.packages;


import com.mp.android.apps.monke.readActivity.bean.BaseBean;
import com.mp.android.apps.monke.readActivity.bean.BookHelpsBean;

import java.util.List;

/**
 * Created by newbiechen on 17-4-20.
 */

public class BookHelpsPackage extends BaseBean {

    private List<BookHelpsBean> helps;

    public List<BookHelpsBean> getHelps() {
        return helps;
    }

    public void setHelps(List<BookHelpsBean> helps) {
        this.helps = helps;
    }

}
