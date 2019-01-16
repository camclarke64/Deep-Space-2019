package frc.robot.drive;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class Autonomous
{

    private double x;
    private double y;
    private double r;

    public boolean disable = false;

    public Autonomous()
    {

    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setR(double r)
    {
        this.r = r;
    }

/*    public void chooser()
    {
        if(!disable)
        {
            if(Robot.oi.buttonX.get())
            {
                disable = true;
                driveX();
            }
            if(Robot.oi.buttonY.get())
            {

            }
        }   
    }
*/

    public void chooser()
    {
        driveX();
    }

    public void driveX()
    {
        Robot.xpid.xController.enable();
        Robot.xpid.set(0);
    }

    public void driveY()
    {
        Robot.ypid.yController.enable();
        Robot.ypid.set(0);
    }

    public void driveAuto()
    {
        
        RobotMap.drive.driveCartesian(-y,x,0); //-x=right -y=forward
    }
}