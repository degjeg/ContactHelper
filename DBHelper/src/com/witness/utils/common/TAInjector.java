/*
 * Copyright (C) 2013  WhiteCat 白猫 (www.thinkandroid.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.witness.utils.common;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;

import com.witness.utils.annotation.Inject;
import com.witness.utils.annotation.InjectLayout;
import com.witness.utils.annotation.InjectResource;
import com.witness.utils.annotation.InjectView;

import java.lang.reflect.Field;

public class TAInjector {
    private static TAInjector instance;

    private TAInjector() {

    }

    public static TAInjector getInstance() {
        if (instance == null) {
            instance = new TAInjector();
        }
        return instance;
    }

    public void injectAll(Activity activity) {

        Field[] fields = activity.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectView.class)) {
                    injectView(activity, field);
                } else if (field.isAnnotationPresent(InjectResource.class)) {
                    injectResource(activity, field);
                } else if (field.isAnnotationPresent(Inject.class)) {
                    inject(activity, field);
                }
            }
        }
    }

    private void inject(Activity activity, Field field) {

        try {
            field.setAccessible(true);
            field.set(activity, field.getType().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void injectView(Activity activity, Field field) {

        if (field.isAnnotationPresent(InjectView.class)) {
            InjectView viewInject = field.getAnnotation(InjectView.class);
            int viewId = viewInject.id();
            try {
                field.setAccessible(true);
                View v = activity.findViewById(viewId);

                field.set(activity, v);

                if (viewInject.click()) {
                    v.setOnClickListener((View.OnClickListener) activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void injectView(Object fragment, Field field, View rootView, boolean isFragment) {

        if (field.isAnnotationPresent(InjectView.class)) {
            InjectView viewInject = field.getAnnotation(InjectView.class);
            int viewId = viewInject.id();
            try {
                field.setAccessible(true);

                View v = rootView.findViewById(viewId);
                field.set(fragment, v);

                if (viewInject.click()) {
                    v.setOnClickListener((View.OnClickListener) fragment);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void injectResource(Activity activity, Field field) {

        if (field.isAnnotationPresent(InjectResource.class)) {
            InjectResource resourceJect = field
                    .getAnnotation(InjectResource.class);
            int resourceID = resourceJect.id();
            try {
                field.setAccessible(true);
                Resources resources = activity.getResources();
                String type = resources.getResourceTypeName(resourceID);
                if (type.equalsIgnoreCase("string")) {
                    field.set(activity,
                            activity.getResources().getString(resourceID));
                } else if (type.equalsIgnoreCase("drawable")) {
                    field.set(activity,
                            activity.getResources().getDrawable(resourceID));
                } else if (type.equalsIgnoreCase("layout")) {
                    field.set(activity,
                            activity.getResources().getLayout(resourceID));
                } else if (type.equalsIgnoreCase("array")) {
                    if (field.getType().equals(int[].class)) {
                        field.set(activity, activity.getResources()
                                .getIntArray(resourceID));
                    } else if (field.getType().equals(String[].class)) {
                        field.set(activity, activity.getResources()
                                .getStringArray(resourceID));
                    } else {
                        field.set(activity, activity.getResources()
                                .getStringArray(resourceID));
                    }

                } else if (type.equalsIgnoreCase("color")) {
                    if (field.getType().equals(Integer.TYPE)) {
                        field.set(activity,
                                activity.getResources().getColor(resourceID));
                    } else {
                        field.set(activity, activity.getResources()
                                .getColorStateList(resourceID));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inject(Activity activity) {

        Field[] fields = activity.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    inject(activity, field);
                }
            }
        }
    }

    public void injectView(Activity activity) {

        String activityName1 = "BaseActivity";
        String activityName2 = "BaseFragmentActivity";

        Class<?> cls = activity.getClass();
        String clsName = cls.getSimpleName();
        while (true) {
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(InjectView.class)) {
                        injectView(activity, field);
                    }
                }
            }
            cls = cls.getSuperclass();
            if (cls == null
                    || TextUtils.equals(clsName, activityName1)
                    || TextUtils.equals(clsName, activityName2)
                    || TextUtils.equals(clsName, "Activity")
                    || TextUtils.equals(clsName, "FragmentActivity")) {
                break;
            }

            clsName = cls.getSimpleName();
        }
    }

    public void injectView(Object fragment, View rootView, boolean isFragment) {

        Class<?> cls = ((Object) fragment).getClass();
        Field[] fields = cls.getDeclaredFields();

        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectView.class)) {
                    injectView(fragment, field, rootView, true);
                }
            }
        }
    }


    public void injectResource(Activity activity) {

        Field[] fields = activity.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectResource.class)) {
                    injectResource(activity, field);
                }
            }
        }
    }

    public void injectLayout(Activity activity) {

        String activityName1 = "BaseActivity";
        String activityName2 = "BaseFragmentActivity";

        Class<?> cls = activity.getClass();

        String clsName = cls.getSimpleName();
        while (!clsName.equals(activityName1) && !clsName.equals(activityName2)) {
            InjectLayout layoutInject = cls.getAnnotation(InjectLayout.class);
            if (layoutInject != null && layoutInject.id() != 0) {
                activity.setContentView(layoutInject.id());
                break;
            }
            cls = cls.getSuperclass();

            if (cls == null || TextUtils.equals(clsName, "Activity") || TextUtils.equals(clsName, "FragmentActivity")) {
                return;
            }
            clsName = cls.getSimpleName();
        }
    }

}
