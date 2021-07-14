package main;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {

    private static final int FPS = 60;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().start());
    }

    public void start() {
        try {
            Frame frame = new Frame("PGRF2 2019 - Task2 - David Pikal");
            frame.setSize(800, 620);

            GLProfile profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            capabilities.setRedBits(8);
            capabilities.setBlueBits(8);
            capabilities.setGreenBits(8);
            capabilities.setAlphaBits(8);
            capabilities.setDepthBits(24);

            GLCanvas canvas = new GLCanvas(capabilities);
            renderer.Renderer ren = new Renderer();
            canvas.addGLEventListener(ren);
            canvas.addMouseListener(ren);
            canvas.addMouseMotionListener(ren);
            canvas.addKeyListener(ren);
            canvas.setSize(800, 600);

            frame.add(canvas);

            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Thread() {
                        @Override
                        public void run() {
                            if (animator.isStarted()) animator.stop();
                            System.exit(0);
                        }
                    }.start();
                }
            });

            JPanel pnlDescription = new JPanel();
            pnlDescription.add(new JLabel("EN klavesnice W - akcelerace, S - zpomaleni, A - zatoceni vlevo, D" +
                    " - zatoceni vpravo, Q - " +
                    "zvyseni vysky, Z - snizeni vysky"));
            frame.add(pnlDescription, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            animator.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
