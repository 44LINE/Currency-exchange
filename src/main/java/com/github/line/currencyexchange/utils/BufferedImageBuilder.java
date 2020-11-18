package com.github.line.currencyexchange.utils;

import java.awt.image.BufferedImage;

public abstract class BufferedImageBuilder {
    protected final int DEFAULT_WIDTH = 350;
    protected final int DEFAULT_HEIGHT = 300;

    protected int width = 0, height = 0;

    public BufferedImageBuilder setWidth(int width) {
        if (width>0) {
            this.width = width;
        }
        return this;
    }

    public BufferedImageBuilder setHeight(int height) {
        if (height>0) {
            this.height = height;
        }
        return this;
    }

    public BufferedImage build() {
        throw new UnsupportedOperationException();
    }
}
