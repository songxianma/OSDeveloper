/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liteng.dev.utils;

import android.text.TextUtils;
import android.util.Log;

import com.liteng.dev.base.App;

//来自于 xutils --> afinal
public class LogUtils {

    public static String customTagPrefix = "";

    private LogUtils() {
    }

    public static boolean DEBUG = App.DEBUG;


    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }


    public static void d(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, content);
    }

    public static void d(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, content, tr);
    }

    public static void e(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.e(tag, content);

    }

    public static void e(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.e(tag, content, tr);

    }

    public static void i(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        Log.i(tag, content);

    }

    public static void i(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, content, tr);

    }

    public static void v(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        Log.v(tag, content);

    }

    public static void v(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, content, tr);

    }

    public static void w(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        Log.w(tag, content);

    }

    public static void w(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, content, tr);
    }

    public static void w(Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, tr);
    }


    public static void wtf(String content) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, content);
    }

    public static void wtf(String content, Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.wtf(tag, content, tr);

    }

    public static void wtf(Throwable tr) {
        if (!DEBUG) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.wtf(tag, tr);
    }

    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

}
