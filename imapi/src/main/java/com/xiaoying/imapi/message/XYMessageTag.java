package com.xiaoying.imapi.message;

/**
 * Created by xiyoumc on 16/7/27.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface XYMessageTag {
    int NONE = 0;
    int ISPERSISTED = 1;
    int ISCOUNTED = 3;
    int STATUS = 16;

    String value();

    int flag() default 0;

    Class<? extends XYMessageContent.MessageHandler> messageHandler() default XYMessageContent.MessageHandler.DefaultMessageHandler.class;
}
