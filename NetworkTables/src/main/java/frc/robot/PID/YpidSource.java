package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;

public class YpidSource implements PIDSource
{
    public YpidSource()
    {
        
    }

    public PIDSourceType getPIDSourceType()
    {
        return PIDSourceType.kDisplacement;
    }

    public void setPIDSourceType(PIDSourceType type)
    {

    }

    public double pidGet()
    {
        return Robot.vision.visionTable[2];
    }
}