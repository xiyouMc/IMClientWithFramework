package com.xiaoying.imapi.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TemplateTag {

    /**
     * 是否显示头像。
     *
     * @return 是否显示头像。
     */
    boolean showPortrait() default true;

    /**
     * 是否横向居中显示。
     *
     * @return 是否横向居中显示。
     */
    boolean centerInHorizontal() default false;

    /**
     * 是否隐藏消息。
     *
     * @return 是否隐藏消息。
     */
    boolean hide() default false;

    /**
     * 是否显示未发生成功警告。
     *
     * @return 是否显示未发生成功警告。
     */
    boolean showWarning() default true;

    /**
     * 是否现实发送进度。
     *
     * @return 是否现实发送进度。
     */
    boolean showProgress() default true;

    boolean showSummaryWithName() default true;

    Class messageContent();
}
