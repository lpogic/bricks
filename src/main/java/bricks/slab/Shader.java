package bricks.slab;

import brackettree.Interpreted;
import bricks.image.LoadedImage;
import org.joml.Matrix2f;
import org.joml.Matrix4f;
import suite.suite.Subject;

import static org.lwjgl.opengl.GL45.*;
import static suite.suite.$uite.$;

public class Shader implements Interpreted {

    public static Subject compose(Subject $) {
        String vertex = $.in("vertex").asString();
        String fragment = $.in("fragment").asString();
        if($.absent("geometry")) {
            return $(new Shader(vertex, fragment));
        }
        String geometry = $.in("geometry").asString();

        return $(new Shader(vertex, geometry, fragment));
    }

    int glid;

    public Shader(String vertex, String fragment) {
        glid = glCreateProgram();

        int vertexGlid = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexGlid, vertex);
        glCompileShader(vertexGlid);

        int fragmentGlid = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentGlid, fragment);
        glCompileShader(fragmentGlid);

        glAttachShader(glid, vertexGlid);
        glAttachShader(glid, fragmentGlid);
        glLinkProgram(glid);

        glDeleteShader(vertexGlid);
        glDeleteShader(fragmentGlid);
    }

    public Shader(String vertex, String geometry, String fragment) {
        glid = glCreateProgram();

        int vertexGlid = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexGlid, vertex);
        glCompileShader(vertexGlid);

        int geometryGlid = glCreateShader(GL_GEOMETRY_SHADER);
        glShaderSource(geometryGlid, geometry);
        glCompileShader(geometryGlid);

        int fragmentGlid = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentGlid, fragment);
        glCompileShader(fragmentGlid);

        glAttachShader(glid, vertexGlid);
        glAttachShader(glid, geometryGlid);
        glAttachShader(glid, fragmentGlid);
        glLinkProgram(glid);

        glDeleteShader(vertexGlid);
        glDeleteShader(geometryGlid);
        glDeleteShader(fragmentGlid);
    }

    public void use() {
        glUseProgram(glid);
    }

    public void set(String name, int value) {
        glUniform1i(glGetUniformLocation(glid, name), value);
    }

    public void set(String name, float value) {
        glUniform1f(glGetUniformLocation(glid, name), value);
    }

    public void set(String name, double value) {
        glUniform1d(glGetUniformLocation(glid, name), value);
    }

    public void set(String name, float v1, float v2) {
        glUniform2f(glGetUniformLocation(glid, name), v1, v2);
    }

    public void set(String name, float v1, float v2, float v3) {
        glUniform3f(glGetUniformLocation(glid, name), v1, v2, v3);
    }

    public void set(String name, float v1, float v2, float v3, float v4) {
        glUniform4f(glGetUniformLocation(glid, name), v1, v2, v3, v4);
    }

    public void set(String name, Matrix2f matrix) {
        glUniformMatrix2fv(glGetUniformLocation(glid, name), false, matrix.get(new float[4]));
    }

    public void set(String name, Matrix4f matrix) {
        glUniformMatrix4fv(glGetUniformLocation(glid, name), false, matrix.get(new float[16]));
    }

    public void setTexture(String name, int index, LoadedImage image) {
        use();
        set(name, index);
        glActiveTexture(GL_TEXTURE0 + index);
        glBindTexture(GL_TEXTURE_2D, image.getGlid());
    }
}
