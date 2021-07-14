package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import static com.jogamp.opengl.GL.*;

public class Houses {

    public Houses(GL2 gl, Texture roof, Texture prefab, Texture skyScraper) {
        for (int i = 0; i < 30; i++) {

            for (int j = 0; j < 15; j++) {

                if ((i == 4 || i == 6 || i == 14 || i == 16 || i == 24 || i == 26) && (j == 2 || j == 7 || j == 12)) {
                    roof.enable(gl);
                    roof.bind(gl);

                    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
                    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
                    gl.glBegin(GL2.GL_QUADS);

                    // vrchni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(8.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(8.0f, 8.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 8.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 45 + 200 * j - 1000);
                    gl.glEnd();
                    roof.disable(gl);

                    skyScraper.enable(gl);
                    skyScraper.bind(gl);
                    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
                    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
                    gl.glBegin(GL2.GL_QUADS);

                    // bocni stena - 1
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 8.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 8.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 45 + 200 * j - 1000);

                    // zadni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 8.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 8.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 45 + 200 * j - 1000);

                    // bocni stena - 2
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 8.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 8.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 5 + 200 * j - 1000);

                    // predni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(2.0f, 8.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 300, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 8.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 300, 5 + 200 * j - 1000);

                    gl.glEnd();
                    skyScraper.disable(gl);

                } else {
                    roof.enable(gl);
                    roof.bind(gl);
                    gl.glBegin(GL2.GL_QUADS);
                    // vrchni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 45 + 200 * j - 1000);

                    // bocni stena - 1
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 45 + 200 * j - 1000);

                    // bocni stena - 2
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 5 + 200 * j - 1000);

                    gl.glEnd();
                    roof.disable(gl);

                    //******************************************************************************

                    prefab.enable(gl);
                    prefab.bind(gl);
                    gl.glBegin(GL2.GL_QUADS);

                    // predni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 5 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 5 + 200 * j - 1000);

                    // zadni stena
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 0, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex3d(45 + 100 * i - 1000, 80, 45 + 200 * j - 1000);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex3d(5 + 100 * i - 1000, 80, 45 + 200 * j - 1000);

                    gl.glEnd();
                    prefab.disable(gl);
                }
            }

        }
    }
}