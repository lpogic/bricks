package app.model.font;

import app.model.graphic.IOUtil;
import org.lwjgl.stb.STBTTFontinfo;
import suite.suite.util.Cascade;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.stb.STBTruetype.*;

public class LoadedFont {

    private final Font font;
    private final STBTTFontinfo fontInfo;
    private final ByteBuffer trueType;
    private final int bitmapWidth;
    private final int bitmapHeight;
    private final float ascend;
    private final float descent;
    private final float lineGap;

    public LoadedFont(Font font) {

        this.font = font;
        this.bitmapWidth = 1024;
        this.bitmapHeight = 512;

        try {
            trueType = IOUtil.ioResourceToByteBuffer(font.getLocation(), bitmapWidth * bitmapHeight);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fontInfo = STBTTFontinfo.create();
        if (!stbtt_InitFont(fontInfo, trueType)) {
            throw new IllegalStateException("Failed to initialize font information.");
        }

        int[] ascend = new int[1], descent = new int[1], lineGap = new int[1];

        stbtt_GetFontVMetrics(fontInfo, ascend, descent, lineGap);

        this.ascend = ascend[0];
        this.descent = descent[0];
        this.lineGap = lineGap[0];
    }

    public float getAscend() {
        return ascend;
    }

    public float getDescent() {
        return descent;
    }

    public float getLineGap() {
        return lineGap;
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public ByteBuffer getTrueType() {
        return trueType;
    }

    public float getStringWidth(String text, float fontHeight) {
        int width = 0;
//        int lastCp = 0;
        int[] advancedWidth = new int[1];
        int[] leftSideBearing = new int[1];
        Cascade<Integer> ci = new Cascade<>(text.chars().iterator());
        for(int cp : ci) {
            stbtt_GetCodepointHMetrics(fontInfo, cp, advancedWidth, leftSideBearing);
            width += advancedWidth[0];
//            if(isKerningEnabled()) {
//                if(lastCp != 0) {
//                    width += stbtt_GetCodepointKernAdvance(info, lastCp, cp);
//                }
//                lastCp = cp;
//            }
        }

        return width * stbtt_ScaleForPixelHeight(fontInfo, fontHeight);
    }
}
