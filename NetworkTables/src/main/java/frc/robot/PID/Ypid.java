package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.Constants;
import frc.robot.Robot;

//forward
public class Ypid implements PIDOutput
{
    public PIDController yController;
    public YpidSource source;

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
        yController = new PIDController(Constants.kPy, Constants.kI, Constants.kD, source, this);
        yController.setOutputRange(-0.7, 0.7);
        yController.setAbsoluteTolerance(0.3);
        yController.disable();
    }

    public void pidWrite(double output)
    {
        Robot.autonomous.setY(output);
    }
}