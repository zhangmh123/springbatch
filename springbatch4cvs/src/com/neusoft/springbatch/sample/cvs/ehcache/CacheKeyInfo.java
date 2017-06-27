package com.neusoft.springbatch.sample.cvs.ehcache;

import java.io.Serializable;

/**
 * Created by DUJILI on 14-10-28.
 */
public class CacheKeyInfo  implements Serializable {
    private static final long serialVersionUID = 3340583609343008716L;

    public  enum CacheKeyColumn{
        Name,
        Type
    }

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CacheKeyInfo(){

    }

    public CacheKeyInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
