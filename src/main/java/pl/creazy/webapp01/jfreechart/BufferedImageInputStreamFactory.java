package pl.creazy.webapp01.jfreechart;

import com.vaadin.flow.server.InputStreamFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents implementation of vaadin interface {@link com.vaadin.flow.server.InputStreamFactory}.
 * Takes {@link java.awt.image.BufferedImage} as constructor's param while instantiation and by only method
 * returns it as {@link java.io.ByteArrayInputStream} which extends {@link java.io.InputStream}
 * @author creazy
 * @version %I
 */

public final class BufferedImageInputStreamFactory implements InputStreamFactory {

    private BufferedImage object;

    private BufferedImageInputStreamFactory() {}

    /**
     * Instantiates a new {@link pl.creazy.webapp01.jfreechart.BufferedImageInputStreamFactory}.
     *
     * @param bufferedImage {@link java.awt.image.BufferedImage} to be serialized
     */
    public BufferedImageInputStreamFactory(BufferedImage bufferedImage) {
        this.object = bufferedImage;
    }

    /**
     * The only method returns serialized {@link java.awt.image.BufferedImage}.
     *
     * @return {@link java.io.ByteArrayInputStream} extending {@link java.io.InputStream}
     */
    @Override
    public InputStream createInputStream() {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){
                ImageIO.write(object, "png", bos);
                return new ByteArrayInputStream(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public boolean requiresLock() {
        return false;
    }
}
