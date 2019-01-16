package frc.robot.drive;

import frc.robot.RobotMap;
import frc.robot.PID.TurnToAnglePID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot;

public class TeleopDrive
{
    TurnToAnglePID turnOnAxis; 
    XboxController _xBox;

    public TeleopDrive()
    {
        turnOnAxis = new TurnToAnglePID();
        _xBox = new XboxController(0);
    }

    public void execute()
    {
        RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*Robot.oi.getRightX()); 
        //first parameter is forward/backwards second is left/right
    }

    public void automatedTurnToAngle (){
        if((_xBox.getPOV()) == 0) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(0)); 
        }
        else if((_xBox.getPOV()) == 90) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(90)); 
        }
        else if ((_xBox.getPOV()) == 180) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(180)); 
        }
        else if((_xBox.getPOV()) == 270) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(-90)); 
        }
        else if(Robot.oi.backB.get()) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(-61)); 
        }
        else if(Robot.oi.startB.get()) { 
            RobotMap.drive.driveCartesian(0.7*Robot.oi.getLeftY(), 0.7*Robot.oi.getLeftX(), 0.7*turnOnAxis.TurnToAbsolute(61)); 
        }

    }

}