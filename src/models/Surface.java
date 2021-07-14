package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import static com.jogamp.opengl.GL.*;

public class Surface {

    public Surface(GL2 gl, Texture ground) {
        ground.enable(gl);
        ground.bind(gl);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        gl.glBegin(GL2.GL_QUADS);

        //gl.glColor3f(0, .8f, 0);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(-1000, 0, -1000);

        gl.glTexCoord2f(0, 200);
        //gl.glColor3f(0, .8f, 0);
        gl.glVertex3f(-1000, 0, 2000);

        gl.glTexCoord2f(200, 200);
        //gl.glColor3f(0, .8f, 0);
        gl.glVertex3f(2000, 0, 2000);

        gl.glTexCoord2f(200, 0);
        //gl.glColor3f(0, .8f, 0);
        gl.glVertex3f(2000, 0, -1000);
        gl.glEnd();

        ground.disable(gl);
    }
}

