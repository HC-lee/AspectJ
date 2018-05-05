package com.supcoder.library.aspectj;

import android.util.Log;

import com.supcoder.library.annotation.CalculateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class MethodAspect {
    private final static String TAG = "MethodAspect";

    private static final String POINTCUT_METHOD =
            "execution(@com.supcoder.library.annotation.CalculateTime * *(..))";
    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.supcoder.library.annotation.CalculateTime *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void withinAnnotatedMethod() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void withinAnnotatedConstructor() {
    }

    @Around("withinAnnotatedMethod() || withinAnnotatedConstructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature methodSignature = null;
        if (joinPoint.getSignature() instanceof MethodSignature) {
            methodSignature = (MethodSignature) joinPoint.getSignature();
            CalculateTime behaviorTrace = ((MethodSignature) methodSignature).getMethod().getAnnotation(CalculateTime.class);
            String contentType = behaviorTrace.value();
            int type = behaviorTrace.type();
        } else if (joinPoint.getSignature() instanceof ConstructorSignature) {
            methodSignature = (ConstructorSignature) joinPoint.getSignature();
        }

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

        Log.e(TAG, buildLogMessage(className, methodName, lengthMillis));


        return result;
    }


    /**
     * Create a log message.
     *
     * @param methodName     A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String className, String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append(className);
        message.append(" --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }
}
