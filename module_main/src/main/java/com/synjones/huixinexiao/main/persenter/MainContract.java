package com.synjones.huixinexiao.main.persenter;


import com.synjones.huixinexiao.common_base.mvp.IModel;
import com.synjones.huixinexiao.common_base.mvp.IView;
import com.synjones.huixinexiao.common_base.net.BaseHttpResult;
import com.synjones.huixinexiao.main.data.entity.TestNews;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public interface MainContract {

    /**
     * 对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
     */
    interface View extends IView {

        void showData(List<TestNews> testNews);
    }

    interface Model extends IModel {

        Observable<BaseHttpResult<List<TestNews>>> getGankData();
    }
}
