package com.neusoft.springbatch.sample.cvs.ehcache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.springbatch.sample.cvs.util.StringUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * Created by DUJILI on 14-10-28.
 * Encache缓存管理
 */
public class CacheHelper {

    static CacheManager cacheManager = CacheManager.getInstance();

    public static boolean addToCache(String cacheName, String key, Object item) {
        if (item == null)
            return false;
        removeFromCache(cacheName, key);
        setCache(cacheName, key, item);
        return true;
    }

    /**
     * 获取Cache对象
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Element getCache(String cacheName, String key) {
        if (!StringUtil.isBlank(cacheName)) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                return cache.get(key);
            } else {
                return null;
            }
        } else {
            for (String name : cacheManager.getCacheNames()) {
                Cache cache = cacheManager.getCache(name);
                if (cache.get(key) != null) {
                    return cache.get(key);
                }
            }
        }
        return null;
    }

    /**
     * 获取实际保存的�?
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object getCacheValue(String cacheName, String key) {
        Element cache = getCache(cacheName, key);
        return cache != null ? cache.getObjectValue() : null;
    }

    public static boolean removeFromCache(String cacheName, String key) {
        if (!StringUtil.isBlank(cacheName)) {
            if (StringUtil.isBlank(key)) {
                removeAllCache(cacheName);
            } else {
                if (getCache(cacheName, key) != null) {
                    Cache cache = cacheManager.getCache(cacheName);
                    if (cache != null) {
                        if (cache.get(key) != null) {
                            cache.remove(key);
                        }
                    }
                }
            }
        } else {
            for (String name : cacheManager.getCacheNames()) {
                Cache cache = cacheManager.getCache(name);
                if (cache.get(key) != null) {
                    cache.remove(key);
                }
            }
        }
        return true;
    }

    public static void setCache(String cacheName, String key, Object obj) {
        if (obj == null)
            return;
        Cache cache = cacheManager.getCache(cacheName);
        cache.put(new Element(key, obj));
    }

    public static void setCache(String cacheName, String key, Object obj, int expireSeconds) {
        if (obj == null)
            return;
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.putIfAbsent(new Element(key, obj, false, expireSeconds, expireSeconds));
        }
    }

    public static void removeAllCache() {
        cacheManager.clearAll();
    }

    public static void removeAllCache(String cacheName) {
        if (!StringUtil.isBlank(cacheName)) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                for (Object cacheKey : cache.getKeys()) {
                    cache.remove(cacheKey);
                }
            }
        } else {
            removeAllCache();
        }
    }

    public enum RemoveCacheType {
        StartWith,
        EndWith,
        Like,
        Equal,
        All,
    }

    public static void removeCaches(String cacheName, String key, RemoveCacheType type) {
        switch (type) {
            case Equal:
                removeFromCache(cacheName, key);
                return;
            case All:
                removeAllCache(cacheName);
                return;
            default:
                break;
        }
        for (String name : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(name);
            if (!StringUtil.isBlank(cacheName)) {
                if (!cacheName.equals(name)) {
                    continue;
                }
            }
            for (Object cacheKey : cache.getKeys()) {

                switch (type) {
                    case StartWith:
                        if (cacheKey.toString().startsWith(key))
                            cache.remove(cacheKey);
                        break;
                    case EndWith:
                        if (cacheKey.toString().endsWith(key))
                            cache.remove(cacheKey);
                        break;
                    case Like:
                        if (cacheKey.toString().indexOf(key) > -1)
                            cache.remove(cacheKey);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static String[] getAllCacheNames() {
        return cacheManager.getCacheNames();
    }

    public static Cache getCacheByName(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    public static Map<String, Map<Object, Element>> getAllCache() {
        Map<String, Map<Object, Element>> allCaches = new HashMap<String, Map<Object, Element>>();

        for (String name : cacheManager.getCacheNames()) {
            Map<Object, Element> singCache = new HashMap<Object, Element>();
            Cache cache = cacheManager.getCache(name);
            for (Object cacheKey : cache.getKeys()) {
                singCache.put(cacheKey, cache.get(cacheKey));
            }
            allCaches.put(name, singCache);
        }
        return allCaches;
    }

    public static CacheInfos getAllCacheInfos() {
        CacheInfos allCaches = new CacheInfos();
        for (String name : cacheManager.getCacheNames()) {
            CacheInfo singCache = new CacheInfo();
            singCache.setCacheName(name);
            List<CacheKeyInfo> keys = new ArrayList<CacheKeyInfo>();
            Cache cache = cacheManager.getCache(name);
            for (Object cacheKey : cache.getKeys()) {
                Object val = cache.get(cacheKey).getObjectValue();
                if (val != null) {
                    keys.add(new CacheKeyInfo(cacheKey.toString(), val.getClass().getName()));
                } else {
                    keys.add(new CacheKeyInfo(cacheKey.toString(), "null"));
                }
            }
            singCache.setKeys(keys);
            allCaches.add(singCache);
        }
        return allCaches;
    }
}
