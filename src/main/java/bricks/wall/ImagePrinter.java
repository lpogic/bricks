package bricks.wall;

import brackettree.reader.BracketTree;
import bricks.graphic.ImageBrick;
import bricks.graphic.Shader;
import bricks.image.ImageManager;
import bricks.image.LoadedImage;
import bricks.trade.Guest;
import bricks.trade.Host;

import static org.lwjgl.opengl.GL30.*;

public class ImagePrinter extends Guest<Host> {

    int glid;
    Shader shader;

    private final int vertexGlid;

    public ImagePrinter(Host host, Shader shader) {
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

    public void setWallSize(float width, float height) {
        shader.use();
        shader.set("wallSize", width, height);
    }

    public void print(ImageBrick rectangle) {
        shader.use();

        float width = rectangle.width().getFloat();
        float height = rectangle.height().getFloat();
        float x = rectangle.x().getFloat();
        float y = rectangle.y().getFloat();

        LoadedImage image = order(ImageManager.class).getLoaded(rectangle.image().get());
        int texGlid = image.getGlid();

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
