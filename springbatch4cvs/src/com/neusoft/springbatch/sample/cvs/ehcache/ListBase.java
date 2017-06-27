package com.neusoft.springbatch.sample.cvs.ehcache;


import java.util.ArrayList;
import java.util.List;

import com.neusoft.springbatch.sample.cvs.util.StringUtil;

/**
 * Created by DUJILI on 14-10-28.
 */
public class ListBase<T> extends ArrayList<T> {
    private static final long serialVersionUID = 1L;
    //region Other Functions

    public ListBase<T> GetPaging(int pageSize, int pageIndex) {
        return GetPaging(pageSize, pageIndex, "", true);
    }

    public ListBase<T> GetPaging(int pageSize, int pageIndex, Object sortColumn, boolean isAsc) {
        return GetPaging(pageSize, pageIndex, sortColumn.toString(), true);
    }

    public ListBase<T> GetPaging(int pageSize, int pageIndex, String sortColumn, boolean isAsc) {
        ListBase<T> ret = new ListBase<T>();
        if (!StringUtil.isBlank(sortColumn)) {
            //this.sortBy(sortColumn, isAsc);
        }
        int index;
        if (this.size() > pageSize) {
            for (index = (pageIndex - 1) * pageSize; index < pageSize * pageIndex && index < this.size(); index++) {
                ret.add(get(index));
            }
            return ret;
        } else
            return this;
    }

    //endregion
}
