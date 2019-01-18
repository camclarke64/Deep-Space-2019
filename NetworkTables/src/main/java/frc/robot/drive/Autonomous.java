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

    public void chooser()
    {
        if(Robot.oi.buttonX.get())
        {
            driveX();
            driveY();
            Robot.teleopDrive.turnOnAxis.turnAngleController.enable();
        }
    }

    public void driveX()
    {
        Robot.xpid.xController.enable();
        Robot.xpid.set(0);
        Robot.autoOp = true;
    }

    public void driveY()
    {
        Robot.ypid.yController.enable();
        Robot.ypid.set(0);
        Robot.autoOp = true;
    }

    public void driveAuto()
    {
        if(Robot.autoOp)
        {
            RobotMap.drive.driveCartesian(-y,x,0.7*Robot.teleopDrive.turnOnAxis.pidAngleOutput.getSpeed()); //x=right -y=forward
        }
    }
}