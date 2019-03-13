package com.synjones.module_main.data.api;



import com.synjones.common_base.net.BaseHttpResult;
import com.synjones.module_main.data.entity.HotWord;
import com.synjones.module_main.data.entity.TestNews;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author xuhao
 * @date 2018/6/11 23:04
 * @desc
 */
public interface ApiService {

    @GET("api/data/Android/10/1")
    Observable<BaseHttpResult<List<TestNews>>> getGankData();

    @GET("/hotkey/json")
    Observable<BaseHttpResult<List<HotWord>>> getHotWord();

}
