package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Motor {

    XboxController xBox;
    CANSparkMax motor;
    CANEncoder encoder;

    public static final int deviceID = 1; //subject to change

    public void robotInit() {
        motor = new CANSparkMax(deviceID, MotorType.kBrushless);
        encoder = motor.getEncoder();
        xBox = new XboxController(Consts.xBoxPort);
    }

    public void teleopPeriodic() {
        motor.set(xBox.getY());
        SmartDashboard.putNumber("Encoder Position", encoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity", encoder.getVelocity());
    }
}