package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import models.Houses;
import models.SkyBox;
import models.Surface;
import services.Map;

import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class Renderer implements GLEventListener, MouseListener,
        MouseMotionListener, KeyListener {

    private int oldX, oldY, width, height, state;
    private Texture skyBox, surface, prefab, roof, skyScraper;
    private Helicopter helicopter;
    private Map map;
    private GLU glu;


    @Override
    public void init(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        glu = new GLU();
        helicopter = new Helicopter();
        map = new Map();
        state = 0;

        InputStream is = getClass().getResourceAsStream("/index.jpg"); // vzhledem k adresari res
        skyBox = findTexture(is);

        is = getClass().getResourceAsStream("/surface.jpg");
        surface = findTexture(is);

        is = getClass().getResourceAsStream("/prefab.jpg");
        prefab = findTexture(is);

        is = getClass().getResourceAsStream("/roof.jpg");
        roof = findTexture(is);

        is = getClass().getResourceAsStream("/sky-scraper.jpg");
        skyScraper = findTexture(is);
    }

    @Override
    public void display(GLAutoDrawable glDrawable) {

        GL2 gl = glDrawable.getGL().getGL2();

        // mazani img-bufferu a z-bufferu
        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // zapnuti z-testu
        gl.glEnable(GL2.GL_DEPTH_TEST);

        // projekcni transformace
        glu.gluPerspective(45, width / (float) height, 0.1f, 5000);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        // transformace pro skybox
        gl.glRotatef(-(float) helicopter.getZenit(), 1.0f, 0, 0);
        gl.glRotatef((float) helicopter.getAzimut(), 0, 1.0f, 0);
        new SkyBox(gl, skyBox);

        gl.glPopMatrix();
        gl.glLoadIdentity();

        if (state == 0) {
            helicopter.plannedStreightForward();
            if (map.canEnter(helicopter.getPlannedPx(), helicopter.getPlannedPy(), helicopter.getPlannedPz())) {
                helicopter.forward();
            } else{
                // v pripade kolize nastaveni rychlosti na 0
                helicopter.setActualSpeed(0);
            }
        } else if (state < 0) {
            helicopter.plannedTurnLeft();
            if (map.canEnter(helicopter.getPlannedPx(), helicopter.getPlannedPy(), helicopter.getPlannedPz())) {
                helicopter.turnLeft();
            } else{
                helicopter.setActualSpeed(0);
            }
        } else if (state > 0) {
            helicopter.plannedTurnRight();
            if (map.canEnter(helicopter.getPlannedPx(), helicopter.getPlannedPy(), helicopter.getPlannedPz())) {
                helicopter.turnRight();
            } else{
                helicopter.setActualSpeed(0);
            }
        }

        // pohledova transformace
        glu.gluLookAt(helicopter.getPositionX(), helicopter.getPositionY(), helicopter.getPositionZ(),
                helicopter.getEyeX() + helicopter.getPositionX(), helicopter.getEyeY() + helicopter.getPositionY(), helicopter.getEyeZ() + helicopter.getPositionZ(),
                helicopter.getUpX(), helicopter.getUpY(), helicopter.getUpZ());


        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();

        new Surface(gl, surface);
        new Houses(gl, roof, prefab, skyScraper);
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        glDrawable.getGL().getGL2().glViewport(0, 0, width, height);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX();
        oldY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        /*
        int dx = e.getX() - oldX;
        int dy = e.getY() - oldY;
        oldX = e.getX();
        oldY = e.getY();

        helicopter.changeViewDirection(dx, dy);
         */
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            helicopter.accelerate();
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            helicopter.slowDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            if(helicopter.getPositionY() <  800){
                helicopter.setPositionY(helicopter.getPositionY() + 1);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            if(helicopter.getPositionY() >  10){
                helicopter.setPositionY(helicopter.getPositionY() - 1);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            state--;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            state++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            state = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void dispose(GLAutoDrawable glDrawable) {
    }

    private Texture findTexture(InputStream is) {
        if (is == null) {
            System.out.println("File not found");
        } else {
            try {
                return TextureIO.newTexture(is, true, "jpg");
            } catch (IOException e) {
                System.err.println("Error while loading texture");
            }
        }
        return null;
    }
}