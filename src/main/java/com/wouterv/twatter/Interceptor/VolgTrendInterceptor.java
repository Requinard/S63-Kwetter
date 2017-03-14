package com.wouterv.twatter.Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Wouter Vanmulken on 14-3-2017.
 */
@Interceptor
@Trends
public class VolgTrendInterceptor  {
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        Object[] objects = context.getParameters();
        objects[0]=((String)objects[0]).replace("vet","hard").replace("cool","smooth");
        context.setParameters(objects);

        return context.proceed();
    }

}

