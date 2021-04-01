package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.Point;
import bricks.graphic.ImageRectangle;
import bricks.graphic.Shader;
import bricks.image.ImageManager;
import bricks.image.LoadedImage;
import bricks.trade.Guest;
import bricks.trade.Host;

import static org.lwjgl.opengl.GL30.*;

public class ImageRectanglePainter extends Guest<Host> {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public ImageRectanglePainter(Host host, Shader shader) {
        super(host);
        glid = glGenVertexArrays();

        this.shader = shader != null ? shader : BracketTree.read(Shader.class.getClassLoader().
                getResourceAsStream("forest/textureShader.tree")).as(Shader.class);

        vertexGlid = glGenBuffers();
        glBindVertexArray(glid);

        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferData(GL_ARRAY_BUFFER, 16, GL_DYNAMIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 16, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 16, 8);
        glEnableVertexAttribArray(1);
        glBindVertexArray(0);

    }

    public void setWallSize(int width, int height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void paint(ImageRectangle rectangle) {
        shader.use();

        LoadedImage image = order(ImageManager.class).getImage(rectangle.getImage());
        int texGlid = image.getGlid();
        float width = rectangle.getWidth();
        float height = rectangle.getHeight();
        Point position = rectangle.getPosition();
        float x = switch (rectangle.getXOrigin()) {
            case LEFT -> position.getX() + width / 2;
            case CENTER -> position.getX();
            case RIGHT -> position.getX() - width / 2;
        };
        float y = switch (rectangle.getYOrigin()) {
            case BOTTOM -> position.getY() - height / 2;
            case CENTER -> position.getY();
            case TOP -> position.getY() + height / 2;
        };

        float[] vertex = new float[]{
                x, y, width, height
        };

        glBindVertexArray(glid);
        glBindTexture(GL_TEXTURE_2D, texGlid);
        glBindBuffer(GL_ARRAY_BUFFER, vertexGlid);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertex);
        glDrawArrays(GL_POINTS, 0, 1);
    }
}
