package com.github.line.currencyexchange.utils;

import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;

public class ByteArrayFactory {
    private ByteArrayFactory() {
        throw new AssertionError();
    }

    public static Optional<byte[]> getFromImage(BufferedImage image) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", byteArrayOutputStream);
            return Optional.of(IOUtils.readAllBytes(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
