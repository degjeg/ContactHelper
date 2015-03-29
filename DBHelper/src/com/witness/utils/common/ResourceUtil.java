/*
 * Copyright (C) 2013  WhiteCat Danger (www.thinkandroid.cn)
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

import android.content.Context;
import android.content.res.Resources;

public class ResourceUtil {
    public static void setCtx(Context ctx) {
        ResourceUtil.ctx = ctx;
    }

    private static  Context ctx;

    public static String getString(String name) {
        Resources resources = ctx.getResources();
        int id = resources.getIdentifier(name, "string", ctx.getPackageName());

        return resources.getString(id);
    }

    public static int getDrawable(String name) {
        Resources resources = ctx.getResources();
        int id = resources.getIdentifier(name, "drawable", ctx.getPackageName());

        return id;
    }
}
