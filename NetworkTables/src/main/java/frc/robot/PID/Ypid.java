package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.livewindow.*;

//forward
public class Ypid implements PIDOutput
{
    public PIDController yController;
    public YpidSource source;
    public double yOutput;

    public Ypid()
    {

    }

    public void set(double setpoint)
    {
        yController.setSetpoint(setpoint);
    }

    public void controllerInit()
    {
        source = new YpidSource();
        yController = new PIDController(Constants.kPy, Constants.kIy, Constants.kDy, source, this);
        yController.setOutputRange(-0.5, 0.5);
        yController.setAbsoluteTolerance(0.3);
        yController.disable();
        LiveWindow.addActuator("DriveSystem", "yController", yController);
    }

    public void pidWrite(double output)
    {
        yOutput = output;
        Robot.autonomous.setY(output);
    }
}