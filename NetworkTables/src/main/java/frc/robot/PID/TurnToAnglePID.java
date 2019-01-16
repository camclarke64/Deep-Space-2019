/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PID;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import frc.robot.PID.PIDAngleWrite;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class TurnToAnglePID {
    
    PIDController turnAngleController;
    AHRS aHRS;
    PIDAngleWrite pidAngleOutput;


    public TurnToAnglePID () {
        pidAngleOutput = new PIDAngleWrite();
        aHRS = new AHRS(SPI.Port.kMXP);
        turnAngleController = new PIDController(Constants.kP, Constants.kI, Constants.kD, aHRS, pidAngleOutput);
    }

    public double TurnToAbsolute (double angle) {
        turnAngleController.setPID(Constants.kP, Constants.kI, Constants.kD);
        turnAngleController.setInputRange(-180, 180);
        turnAngleController.enable();
        turnAngleController.setSetpoint(angle);
        return pidAngleOutput.getSpeed();
    }

}
