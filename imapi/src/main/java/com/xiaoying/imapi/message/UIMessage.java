package com.xiaoying.imapi.message;

public class UIMessage {
    private int progress;
    private boolean playing;
    private XYMessage message;

    public UIMessage(XYMessage message) {
        this.message = message;
        this.progress = 100;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public XYMessage getMessage() {
        return message;
    }

    public static UIMessage obtain(XYMessage message) {
        return new UIMessage(message);
    }
}
