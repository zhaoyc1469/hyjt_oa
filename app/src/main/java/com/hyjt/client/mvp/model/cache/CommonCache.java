package com.hyjt.client.mvp.model.cache;

import com.hyjt.client.mvp.model.entity.LoginResp;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 *
 */
public interface CommonCache {



    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<LoginResp>> getUsers(Observable<LoginResp> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

}
