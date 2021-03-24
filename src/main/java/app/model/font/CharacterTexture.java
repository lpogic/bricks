package app.model.font;

import org.lwjgl.stb.STBTTBakedChar;

public class CharacterTexture {
    private final int textureGlid;
    private final STBTTBakedChar.Buffer buffer;
    private final int bufferOffset;

    public CharacterTexture(int textureGlid, STBTTBakedChar.Buffer buffer, int bufferOffset) {
        this.textureGlid = textureGlid;
        this.buffer = buffer;
        this.bufferOffset = bufferOffset;
    }

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
