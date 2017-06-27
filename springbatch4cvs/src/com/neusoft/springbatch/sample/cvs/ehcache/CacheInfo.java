package com.neusoft.springbatch.sample.cvs.ehcache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DUJILI on 14-10-28.
 */
public class CacheInfo implements Serializable {
    private static final long serialVersionUID = 3340583609343008716L;
    private List<CacheKeyInfo> keys;
    private String cacheName;

    public List<CacheKeyInfo> getKeys() {
        return keys;
    }

    public void setKeys(List<CacheKeyInfo> keys) {
        this.keys = keys;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public CacheInfo(){

    }

    public CacheInfo(String cacheName, List<CacheKeyInfo> keys) {
        this.cacheName = cacheName;
        this.keys = keys;
    }
}
