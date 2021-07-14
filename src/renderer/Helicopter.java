package renderer;

import transforms.Vec2D;

/**
 *
 */
public class Helicopter {

    private final int maxSpeed = 10;
    private final int maxReverseSpeed = -5;
    private int actualSpeed = 5;

    private double eyeX, eyeY, eyeZ, upX, upY, upZ, plannedPx, plannedPy, plannedPz, azimutRad, zenitRad;
    private double positionX = 500;
    private double positionY = 110;
    private double positionZ = 500;
    private double zenit = 0;
    private double azimut = 0;
    private Vec2D forward, right, left, directionForward, directionRight, directionLeft, prepared;

    public Helicopter() {
        calculate();
    }

    /**
     * zrychleni
     */
    public void accelerate() {
        if (actualSpeed < maxSpeed) {
            actualSpeed++;
            System.out.println(actualSpeed);
        } else {
            System.out.println("Maximal forward speed reached!");
        }
    }

    /**
     * zpomaleni, pripadne zpatecka
     */
    public void slowDown() {
        if (actualSpeed > maxReverseSpeed) {
            actualSpeed--;
            System.out.println(actualSpeed);
        } else {
            System.out.println("Maximal reverse speed reached!");
        }
    }

    /**
     * @param dx
     * @param dy
     * metoda pro rozhlizeni ve scene, v rendereru nikdy nevolana
     * uvazovana pro eventualni rozsireni
     */
    public void changeViewDirection(int dx, int dy) {
        zenit -= dy;
        if (zenit > 90)
            zenit = 90;
        if (zenit < -90)
            zenit = -90;
        azimut += dx;
        azimut = azimut % 360;
        calculate();
    }

    /**
     * metoda pro vypocet uP-vektoru a smeru pohledu
     */
    private void calculate() {
        azimutRad = Math.toRadians(azimut);
        zenitRad = Math.toRadians(zenit);
        eyeX = Math.sin(azimutRad) * Math.cos(zenitRad);
        eyeY = Math.sin(zenitRad);
        eyeZ = -Math.cos(azimutRad) * Math.cos(zenitRad);
        upX = Math.sin(azimutRad) * Math.cos(zenitRad + Math.PI / 2);
        upY = Math.sin(zenitRad + Math.PI / 2);
        upZ = -Math.cos(azimutRad) * Math.cos(zenitRad + Math.PI / 2);
    }

    public void forward() {
        positionX = (positionX + eyeX * actualSpeed) % 1000;
        positionY = positionY + eyeY * actualSpeed;
        positionZ = (positionZ + eyeZ * actualSpeed) % 1000;

        if (positionX < 0) {
            positionX = positionX + 1000;
        }

        if (positionZ < 0) {
            positionZ = positionZ + 1000;
        }
    }


    public void plannedTurnRight() {
        streightDirection();
        forward = new Vec2D(plannedPx, plannedPz);

        rightDirection();
        right = new Vec2D(plannedPx, plannedPz);

        directionRight = new Vec2D(right.getX() - positionX, right.getY() - positionZ);
        directionForward = new Vec2D(forward.getX() - positionX, forward.getY() - positionZ);
        prepared = directionForward.add(directionRight);

        prepared = prepared.mul(0.9);

        plannedPx = (positionX + prepared.getX()) % 1000;
        plannedPz = (positionZ + prepared.getY()) % 1000;

        if (plannedPx < 0) {
            plannedPx = plannedPx + 1000;
        }

        if (plannedPz < 0) {
            plannedPz = plannedPz + 1000;
        }
    }

    public void turnRight() {
        double right = directionRight.length();
        double forward = directionForward.length();

        double alfa = right / forward;
        double changeViewDirection = Math.atan(alfa);

        azimut = (azimut + changeViewDirection) % 360;
        calculate();

        positionX = (positionX + prepared.getX()) % 1000;
        positionZ = (positionZ + prepared.getY()) % 1000;

        coordinatesControl();
    }

    public void plannedTurnLeft() {
        streightDirection();
        forward = new Vec2D(plannedPx, plannedPz);

        leftDirection();
        left = new Vec2D(plannedPx, plannedPz);

        directionLeft = new Vec2D(left.getX() - positionX, left.getY() - positionZ);
        directionForward = new Vec2D(forward.getX() - positionX, forward.getY() - positionZ);
        prepared = directionForward.add(directionLeft);

        prepared = prepared.mul(0.9);

        plannedPx = (positionX + prepared.getX()) % 1000;
        plannedPz = (positionZ + prepared.getY()) % 1000;

        plannedCoordianteControl();
    }

    public void turnLeft() {
        double left = directionLeft.length();
        double forward = directionForward.length();

        double alfa = left / forward;
        double changeViewDirection = Math.atan(alfa);

        azimut = (azimut - changeViewDirection) % 360;
        calculate();

        positionX = (positionX + prepared.getX()) % 1000;
        positionZ = (positionZ + prepared.getY()) % 1000;

        coordinatesControl();
    }


    /**
     *
     */
    public void plannedStreightForward() {
        plannedPx = (positionX + eyeX * actualSpeed) % 1000;
        plannedPy = (positionY + eyeY * actualSpeed) % 1000;
        plannedPz = (positionZ + eyeZ * actualSpeed) % 1000;
        plannedCoordianteControl();
    }

    private void streightDirection() {
        plannedPx = positionX + eyeX * actualSpeed;
        plannedPy = positionY + eyeY * actualSpeed;
        plannedPz = positionZ + eyeZ * actualSpeed;
    }

    private void leftDirection() {
        plannedPz = positionZ - Math.cos(azimutRad - Math.PI / 2) * 1.5;
        plannedPx = positionX + Math.sin(azimutRad - Math.PI / 2) * 1.5;
    }

    private void rightDirection() {
        plannedPz = positionZ + Math.cos(azimutRad - Math.PI / 2) * 1.5;
        plannedPx = positionX - Math.sin(azimutRad - Math.PI / 2) * 1.5;
    }

    /**
     * pripadny prepocet souradnic
     */
    private void plannedCoordianteControl() {
        if (plannedPx < 0) {
            plannedPx = plannedPx + 1000;
        }

        if (plannedPz < 0) {
            plannedPz = plannedPz + 1000;
        }
    }

    /**
     * pripadny prepocet souradni
     */
    private void coordinatesControl() {
        if (positionX < 0) {
            positionX = positionX + 1000;
        }

        if (positionZ < 0) {
            positionZ = positionZ + 1000;
        }
    }

    public double getEyeX() {
        return eyeX;
    }

    public double getEyeY() {
        return eyeY;
    }

    public double getEyeZ() {
        return eyeZ;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionZ() {
        return positionZ;
    }

    public double getUpX() {
        return upX;
    }

    public double getUpY() {
        return upY;
    }

    public double getUpZ() {
        return upZ;
    }

    public double getPlannedPx() {
        return plannedPx;
    }

    public double getPlannedPy() {
        return plannedPy;
    }

    public double getPlannedPz() {
        return plannedPz;
    }

    public double getZenit() {
        return zenit;
    }

    public double getAzimut() {
        return azimut;
    }

    public void setActualSpeed(int actualSpeed) {
        this.actualSpeed = actualSpeed;
    }
}