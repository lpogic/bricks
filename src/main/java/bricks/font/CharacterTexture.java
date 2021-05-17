package bricks.font;

import org.lwjgl.stb.STBTTBakedChar;

public record CharacterTexture(int textureGlid, STBTTBakedChar.Buffer buffer, int bufferOffset) {

    public int getTextureGlid() {
        return textureGlid;
    }

    public STBTTBakedChar.Buffer getBuffer() {
        return buffer;
    }

    public int getBufferOffset() {
        return bufferOffset;
    }
}
