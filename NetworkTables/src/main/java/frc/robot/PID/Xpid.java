package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.Constants;
import frc.robot.Robot;

//sideways
public class Xpid implements PIDOutput
{
    public PIDController xController;
    public XpidSource source;

    public Xpid()
    {

    }

    public void set(double setpoint)
    {
        xController.setSetpoint(setpoint);
    }

    public void controllerInit()
    {
        source = new XpidSource();
        xController = new PIDController(Constants.kP, Constants.kI, Constants.kD, source, this);
        xController.setOutputRange(-0.7, 0.7);
        xController.setAbsoluteTolerance(0.3);
        xController.disable();
    }

    public void pidWrite(double output)
    {
        SmartDashboard.putNumber("pidOutput", output);
        Robot.autonomous.setX(output);
    }
}

