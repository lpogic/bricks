package bricks.image;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class LoadedImage {

    private final int glid;
    private final int width;
    private final int height;

    public LoadedImage(Image image) {
        this(image.getFilePath(), false, GL_CLAMP_TO_BORDER, GL_CLAMP_TO_BORDER, GL_NEAREST, GL_LINEAR, GL_RGBA);
    }

    public LoadedImage(Image image, boolean flip) {
        this(image.getFilePath(), flip, GL_CLAMP_TO_BORDER, GL_CLAMP_TO_BORDER, GL_NEAREST, GL_LINEAR, GL_RGBA);
    }

    public LoadedImage(String file, boolean flipImage, int textureWrapS, int textureWrapT, int textureMinFilter, int textureMaxFilter,
                       int format) {
        glid = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, glid);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, textureWrapS);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, textureWrapT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, textureMinFilter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, textureMaxFilter);
        int[] width = new int[1], height = new int[1], nrChannels = new int[1];
        stbi_set_flip_vertically_on_load(flipImage);
        ByteBuffer image = stbi_load(file, width, height, nrChannels, 0);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width[0], height[0], 0, format, GL_UNSIGNED_BYTE, image);
        glGenerateMipmap(GL_TEXTURE_2D);
        stbi_image_free(image);

        this.width = width[0];
        this.height = height[0];
    }

    public int getGlid() {
        return glid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
