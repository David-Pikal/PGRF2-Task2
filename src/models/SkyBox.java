package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class SkyBox {
    private double d = 1000;

    public SkyBox(GL2 gl, Texture skyBox) {
        //gl.glColor3d(0.5, 0.5, 0.5);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
        skyBox.enable(gl);
        skyBox.bind(gl);

        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, -d, -d);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, -d);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, d);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, -d, d);


        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, -d, -d);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, -d);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, d);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, -d, d);


        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, -d);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, -d);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, d);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, d);

        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, -d);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, -d, -d);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, -d, -d);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, -d);

        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, d, d);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-d, -d, d);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, -d, d);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glColor3d(0.5, 0.5, 0.5);
        gl.glVertex3d(d, d, d);

        gl.glEnd();

        gl.glDisable(GL2.GL_TEXTURE_2D);
    }
}