package app.model.font;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTBakedChar;
import suite.suite.Subject;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.stb.STBTruetype.stbtt_BakeFontBitmap;
import static suite.suite.$uite.$;

public class BackedFont {

    private final LoadedFont loadedFont;
    private final Subject $chars = $();
    private final int size;

    public BackedFont(LoadedFont loadedFont, int size) {
        this.loadedFont = loadedFont;
        this.size = size;

        // ascii + polskie krzaki
        bake(' ', '~');
        bake(211, 211);
        bake(243, 243);
        bake(260, 263);
        bake(280, 281);
        bake(321, 324);
        bake(346, 347);
        bake(377, 380);
    }

    public void bake(int firstCodePoint, int lastCodepoint) {

        int bitmapWidth = loadedFont.getBitmapWidth();
        int bitmapHeight = loadedFont.getBitmapHeight();
        int textureID = glGenTextures();
        STBTTBakedChar.Buffer buffer = STBTTBakedChar.malloc(1 + lastCodepoint - firstCodePoint);

        ByteBuffer bitmap = BufferUtils.createByteBuffer(bitmapWidth * bitmapHeight);
        stbtt_BakeFontBitmap(loadedFont.getTrueType(), size, bitmap, bitmapWidth, bitmapHeight, firstCodePoint, buffer);

        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RED, bitmapWidth, bitmapHeight, 0, GL_RED, GL_UNSIGNED_BYTE, bitmap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        for(int i = firstCodePoint;i <= lastCodepoint; ++i) {
            $chars.put(i, new CharacterTexture(textureID, buffer, i - firstCodePoint));
        }

        glBindTexture(GL_TEXTURE_2D, 0);

    }

    public float getSize() {
        return size;
    }

    public LoadedFont getLoadedFont() {
        return loadedFont;
    }

    public CharacterTexture getCharacterTexture(int codePoint) {
        var $char = $chars.in(codePoint).get();
        if($char.absent()) bake(codePoint, codePoint);
        return $chars.in(codePoint).asExpected();
    }
}
