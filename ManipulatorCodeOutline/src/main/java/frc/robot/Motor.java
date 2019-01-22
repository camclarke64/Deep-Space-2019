package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

public class Motor {

    WPI_TalonSRX talonOne, talonTwo;
    DigitalInput limitSwitch;
    
    public Motor() {
        talonOne = new WPI_TalonSRX(0); //this is subject to change
        talonTwo = new WPI_TalonSRX(1); //this is subject to change
        limitSwitch = new DigitalInput(2); //this is subject to change
    }

    public void limitSwitchCode() {
        while (limitSwitch.get()) {
            talonOne.set(0);
            talonTwo.set(0);
        }
    }
}