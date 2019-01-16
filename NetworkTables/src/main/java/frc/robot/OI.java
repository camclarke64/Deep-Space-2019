/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {
	
	XboxController controller = new XboxController(0);
	
	final static int aButton = 1;
	final static int bButton = 2;
	final static int xButton = 3;
	final static int yButton = 4;
	final static int leftBumper = 5;
	final static int rightBumper = 6;
	final static int backButton = 7;
	final static int startButton = 8;
	final static int lStickButton = 9;
	final static int rStickButton = 10;
	final static int lStickXAxis = 0;
	final static int lStickYAxis = 1;
	final static int lTriggerAxis = 2;
	final static int rTriggerAxis = 3;
	final static int rStickXAxis = 4;
	final static int rStickYAxis = 5;
	final static double deadzone = 0.2;
	
	public final JoystickButton buttonA = new JoystickButton(controller, aButton);
	public final JoystickButton buttonB = new JoystickButton(controller, bButton);
	public final JoystickButton buttonX = new JoystickButton(controller, xButton);
	public final JoystickButton buttonY = new JoystickButton(controller, yButton);
	public final JoystickButton bumperL = new JoystickButton(controller, leftBumper);
	public final JoystickButton bumperR = new JoystickButton(controller, rightBumper);
	public final JoystickButton backB = new JoystickButton(controller, backButton);	
	public final JoystickButton startB = new JoystickButton(controller, startButton);
	public final JoystickButton leftStickB = new JoystickButton(controller, lStickButton);
	public final JoystickButton rightStickB = new JoystickButton(controller, rStickButton);
	
	public OI()
	{
		
	}
	
	public double deadzone(double input)
	{
		if(Math.abs(input)>deadzone)
		{
			return input;
		}
		else
		{
			return 0;
		}
	}
	
	public double getLeftY()
	{
		return deadzone(controller.getY(Hand.kLeft));
	}
	
	public double getLeftX()
	{
		return -deadzone(controller.getX(Hand.kLeft));
	}
	
	public double getRightY()
	{
		return -deadzone(controller.getY(Hand.kRight));
	}
	
	public double getRightX()
	{
		return deadzone(controller.getX(Hand.kRight));
	}
	
	public double getTrigger()
	{
		if(controller.getTriggerAxis(Hand.kRight)>0)
		{
			return deadzone(controller.getTriggerAxis(Hand.kRight));
		}
		else if(controller.getTriggerAxis(Hand.kLeft)>0)
		{
			return -deadzone(controller.getTriggerAxis(Hand.kLeft));
		}
		else
		{
			return 0.0;
		}
		//left trigger has negative value, right trigger has positive value	
	}

	
}
