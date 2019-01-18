package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.livewindow.*;

//sideways
public class Xpid implements PIDOutput
{
    public PIDController xController;
    public XpidSource source;
    public double xOutput;

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
        xController = new PIDController(Constants.kPx, Constants.kIx, Constants.kDx, source, this);
        xController.setOutputRange(-0.5, 0.5);
        xController.setAbsoluteTolerance(0.3);
        xController.disable();
        LiveWindow.addActuator("DriveSystem", "xController", xController);
    }

    public void pidWrite(double output)
    {
        xOutput = output;
        Robot.autonomous.setX(output);
    }
}

