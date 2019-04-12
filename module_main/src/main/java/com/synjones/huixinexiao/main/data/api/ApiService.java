package com.synjones.huixinexiao.main.data.api;



import com.synjones.huixinexiao.common_base.net.BaseHttpResult;
import com.synjones.huixinexiao.main.data.entity.HotWord;
import com.synjones.huixinexiao.main.data.entity.TestNews;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public interface ApiService {

    @GET("api/data/Android/10/1")
    Observable<BaseHttpResult<List<TestNews>>> getGankData();

    @GET("/hotkey/json")
    Observable<BaseHttpResult<List<HotWord>>> getHotWord();

}

