/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Vision;
import frc.robot.drive.Autonomous;
import frc.robot.drive.TeleopDrive;
import frc.robot.PID.Xpid;
import frc.robot.PID.Ypid;
import frc.robot.RobotMap;
import com.kauailabs.navx.frc.AHRS;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Vision vision = new Vision();
  public static OI oi = new OI();
  public static TeleopDrive teleopDrive = new TeleopDrive();
  public static Autonomous autonomous = new Autonomous();
  public static Xpid xpid = new Xpid();
  public static Ypid ypid = new Ypid();

  public static boolean autoOp = false;

  public double[] table = new double[5];

  AHRS ahrs;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    xpid.controllerInit();
    ypid.controllerInit();
    xpid.xController.disable();
    ypid.yController.disable();
    ahrs = new AHRS(SPI.Port.kMXP);

    UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(); //in the best case, we want everything about the camera in one thread - easier processing
		camera1.setResolution(640, 480);
    new Thread(() -> {
			camera1.setFPS(30);
			camera1.setExposureAuto();
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Camera1", 640, 480); 
			//set up a new camera with this name in SmartDashboard (Veiw->Add->CameraServer Stream Viewer)
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted())
			{
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2RGB);
				outputStream.putFrame(output);
			}					
		}).start(); //camera init + execution

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("ahrs_pid", ahrs.pidGet());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
//    xpid.xController.enable();
    ypid.yController.enable();
    autonomous.chooser();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    vision.getTable();
    vision.displayTable();
    autonomous.driveAuto();
  }

  @Override
  public void teleopInit() {
    xpid.xController.disable();
    ypid.yController.disable();
    RobotMap.TalonSet();
  }
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    vision.getTable();
    vision.displayTable();
    autonomous.chooser();
    teleopDrive.execute();
    teleopDrive.turnOnAxis.automatedTurnToAngle();
    teleopDrive.getDiagnostics();
    autonomous.driveAuto();
    if(oi.bumperL.get())
    {
      xpid.xController.disable();
      ypid.yController.disable();
      teleopDrive.turnOnAxis.turnAngleController.disable();
      teleopDrive.turnOnAxis.autoTurn = false;
      autoOp = false;
    }
  }

  @Override
  public void testInit() {
    teleopDrive.turnOnAxis.turnAngleController.disable();
    xpid.xController.disable();
    ypid.yController.disable();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    vision.getTable();
    vision.displayTable();
    autonomous.chooser();
    teleopDrive.execute();
    teleopDrive.turnOnAxis.automatedTurnToAngle();
    teleopDrive.getDiagnostics();
    autonomous.driveAuto();
    if(oi.bumperL.get())
    {
      xpid.xController.disable();
      ypid.yController.disable();
      teleopDrive.turnOnAxis.turnAngleController.disable();
      teleopDrive.turnOnAxis.autoTurn = false;
      autoOp = false;
    }
  }

}
