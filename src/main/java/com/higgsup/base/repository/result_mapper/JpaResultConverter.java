package com.higgsup.base.repository.result_mapper;

import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiep on 5/5/2017.
 */
public class JpaResultConverter extends ResultMapper {
    @SuppressWarnings("unchecked")
    public <T> List<T> list(Query q, Class<T> clazz) throws IllegalArgumentException {
        List<T> result = new ArrayList<T>();
        Constructor<?> ctor = clazz.getDeclaredConstructors()[0];
        List<Object[]> list = q.getResultList();
        for (Object obj : list) {
            if (ctor.getParameterTypes().length == 1) {
                obj = new Object[]{obj};
            }
            result.add((T) createInstance(ctor, (Object[]) obj));
        }
        return result;
    }

    public <T> T uniqueResult(Query q, Class<T> clazz) {
        Object rec = q.getSingleResult();
        Constructor<?> ctor = clazz.getDeclaredConstructors()[0];
        if (ctor.getParameterTypes().length == 1) {
            rec = new Object[]{rec};
        }
        return createInstance(ctor, (Object[]) rec);
    }
}
