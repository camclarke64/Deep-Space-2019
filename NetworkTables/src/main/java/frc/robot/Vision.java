package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision
{
    public double[] visionTable;

    public Vision()
    {
        visionTable = new double[5];
    }

    public void getTable()
    {
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        double tl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);
        visionTable[0]=tv;
        visionTable[1]=tx;
        visionTable[2]=ty;
        visionTable[3]=ta;
        visionTable[4]=tl;
    }

    public void displayTable()
    {
        SmartDashboard.putNumber("tv", visionTable[0]);
        SmartDashboard.putNumber("tx", visionTable[1]);
        SmartDashboard.putNumber("ty", visionTable[2]);
        SmartDashboard.putNumber("ta", visionTable[3]);
        SmartDashboard.putNumber("tl", visionTable[4]);

        if(Robot.autoOp)
        {
            System.out.println(visionTable[1]+";"+visionTable[2]+";"+visionTable[3]);
        }
    }
}