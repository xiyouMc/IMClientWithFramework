package com.xiaoying.imapi.message;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ReplacementSpan;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.toolkit.R;

/**
 * Created by xiyoumc on 16/7/27.
 */
public class XYEmoji {
    private static final String TAG = "EmojiManager";
    private static HashMap<Integer, XYEmoji.EmojiInfo> emojiMap;
    private static List<XYEmoji.EmojiInfo> emojiList;

    public XYEmoji() {
    }

    public static void init(Context context) {
        context = context.getApplicationContext();
        Resources sResources = context.getResources();
        int[] codes = sResources.getIntArray(R.array.rc_emoji_code);
        TypedArray array = sResources.obtainTypedArray(R.array.rc_emoji_res);
        if (codes.length == array.length()) {
            emojiMap = new HashMap();
            emojiList = new ArrayList();

            for (int i = 0; i < codes.length; ++i) {
                XYEmoji.EmojiInfo emojiInfo = new XYEmoji.EmojiInfo(codes[i], array.getResourceId(i, -1));
                emojiMap.put(Integer.valueOf(codes[i]), emojiInfo);
                emojiList.add(emojiInfo);
            }
        } else {
            Log.e("EmojiManager", "XYEmoji init fail because of length error.");
        }

        array.recycle();
    }

    public static Drawable getEmojiDrawable(Context context, int index) {
        Drawable drawable = null;
        if (index >= 0 && index < emojiList.size()) {
            XYEmoji.EmojiInfo XYEmoji = (XYEmoji.EmojiInfo) emojiList.get(index);
            drawable = context.getResources().getDrawable(XYEmoji.resId);
        }

        return drawable;
    }

    public static int getEmojiSize() {
        return emojiMap.size();
    }

    public static int getEmojiCode(int index) {
        XYEmoji.EmojiInfo info = (XYEmoji.EmojiInfo) emojiList.get(index);
        return info.code;
    }

    public static CharSequence ensure(Context context, String input) {
        if (input == null) {
            return input;
        } else {
            char[] chars = input.toCharArray();
            SpannableStringBuilder ssb = new SpannableStringBuilder(input);

            for (int i = 0; i < chars.length; ++i) {
                if (!Character.isHighSurrogate(chars[i])) {
                    int codePoint;
                    boolean isSurrogatePair;
                    if (Character.isLowSurrogate(chars[i])) {
                        if (i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                            continue;
                        }

                        codePoint = Character.toCodePoint(chars[i - 1], chars[i]);
                        isSurrogatePair = true;
                    } else {
                        codePoint = chars[i];
                        isSurrogatePair = false;
                    }

                    if (emojiMap.containsKey(Integer.valueOf(codePoint))) {
                        ssb.setSpan(new XYEmoji.EmojiImageSpan(context, codePoint), isSurrogatePair ? i - 1 : i, i + 1, 33);
                    }
                }
            }

            return ssb;
        }
    }

    public static boolean isEmoji(String input) {
        if (input == null) {
            return false;
        } else {
            char[] chars = input.toCharArray();
            boolean codePoint = false;
            int length = chars.length;

            for (int i = 0; i < length; ++i) {
                if (!Character.isHighSurrogate(chars[i])) {
                    int var5;
                    if (Character.isLowSurrogate(chars[i])) {
                        if (i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                            continue;
                        }

                        var5 = Character.toCodePoint(chars[i - 1], chars[i]);
                    } else {
                        var5 = chars[i];
                    }

                    if (emojiMap.containsKey(Integer.valueOf(var5))) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void ensure(Context context, Spannable spannable) {
        char[] chars = spannable.toString().toCharArray();

        for (int i = 0; i < chars.length; ++i) {
            if (!Character.isHighSurrogate(chars[i])) {
                int codePoint;
                boolean isSurrogatePair;
                if (Character.isLowSurrogate(chars[i])) {
                    if (i <= 0 || !Character.isSurrogatePair(chars[i - 1], chars[i])) {
                        continue;
                    }

                    codePoint = Character.toCodePoint(chars[i - 1], chars[i]);
                    isSurrogatePair = true;
                } else {
                    codePoint = chars[i];
                    isSurrogatePair = false;
                }

                if (emojiMap.containsKey(Integer.valueOf(codePoint))) {
                    spannable.setSpan(new XYEmoji.EmojiImageSpan(context, codePoint), isSurrogatePair ? i - 1 : i, i + 1, 34);
                }
            }
        }

    }

    private static class EmojiInfo {
        int code;
        int resId;

        public EmojiInfo(int code, int resId) {
            this.code = code;
            this.resId = resId;
        }
    }

    private static class EmojiImageSpan extends ReplacementSpan {
        Drawable mDrawable;
        float density;
        private WeakReference<Drawable> mDrawableRef;

        private EmojiImageSpan(Context context, int code) {
            XYEmoji.EmojiInfo XYEmoji = (XYEmoji.EmojiInfo) emojiMap.get(Integer.valueOf(code));
            if (XYEmoji != null) {
                DisplayMetrics dm = context.getResources().getDisplayMetrics();
                this.density = dm.density;
                this.mDrawable = context.getResources().getDrawable(XYEmoji.resId);
                if (this.mDrawable != null) {
                    int width = this.mDrawable.getIntrinsicWidth() * 4 / 5;
                    int height = this.mDrawable.getIntrinsicHeight() * 4 / 5;
                    this.mDrawable.setBounds(0, 0, width > 0 ? width : 0, height > 0 ? height : 0);
                } else {
                    Log.e("EmojiManager", "Can not get drawable from code :" + code);
                }
            }

        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            Drawable d = this.getCachedDrawable();
            Rect rect = d.getBounds();
            if (fm != null) {
                fm.ascent = -rect.bottom;
                fm.descent = 0;
                fm.top = fm.ascent;
                fm.bottom = 0;
            }

            return rect.right;
        }

        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            Drawable b = this.getCachedDrawable();
            canvas.save();
            int transY = bottom - b.getBounds().bottom;
            transY = (int) ((float) transY - this.density);
            canvas.translate(x, (float) transY);
            b.draw(canvas);
            canvas.restore();
        }

        private Drawable getCachedDrawable() {
            WeakReference wr = this.mDrawableRef;
            Drawable d = null;
            if (wr != null) {
                d = (Drawable) wr.get();
            }

            if (d == null) {
                d = this.getDrawable();
                this.mDrawableRef = new WeakReference(d);
            }

            return d;
        }
    }
}

