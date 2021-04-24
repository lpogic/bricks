package bricks.input;

import static org.lwjgl.glfw.GLFW.*;

public class Cursor {

    public enum Face {
        ARROW(GLFW_ARROW_CURSOR),
//        CENTER(GLFW_CENTER_CURSOR),
        CROSSHAIR(GLFW_CROSSHAIR_CURSOR),
        HAND(GLFW_HAND_CURSOR),
        VRESIZE(GLFW_VRESIZE_CURSOR),
        IBEAM(GLFW_IBEAM_CURSOR),
        HRESIZE(GLFW_HRESIZE_CURSOR);

        private final int glid;

        Face(int glid) {
            this.glid = glid;
        }

        public int getGLFWCode() {
            return glid;
        }
    }

    public enum Mode {
        VISIBLE(GLFW_CURSOR_NORMAL),
        HIDDEN(GLFW_CURSOR_HIDDEN),
        HIDDEN_UNLIMITED(GLFW_CURSOR_DISABLED);

        private final int glid;

        Mode(int glid) {
            this.glid = glid;
        }

        public int getGLFWCode() {
            return glid;
        }
    }
}
