package frc.robot;

public class ManipCodeOutline {

    /**
     * @param theta angle in radians -- this is what Java likes to use
     * @return angle in degrees -- this is what we like to use
     */
    public double r2d(double theta) {
        return theta * (180 / Math.PI);
    }

    /**
     * @param degrees the set degree measurement we want to turn to
     */
    public void turnToAngle(double degrees) {
        
    }

    /**
     * We add code here for drive forward with vision
     * Add a character to SmartDashboard whether or not we're following the straight line
     */
    public void centerDriveVision() {

    }

    /**
     * We add code here (maybe Cam's?) for pnuematics push out
     * Add a boolean return statement for whether or not the hatch kicked out
     */
    public void extendHatch() {

    }
}