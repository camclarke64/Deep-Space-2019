package frc.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class RobotMap
{
    public static Talon frontL = new Talon(0);
    public static Talon frontR = new Talon(1);
    public static Talon backL = new Talon(2);
    public static Talon backR = new Talon(3);

    public static MecanumDrive drive = new MecanumDrive(frontL, backL, frontR, backR);

    public RobotMap()
    {

    }


}