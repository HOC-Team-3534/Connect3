// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.sequences.SequenceProcessor;
import frc.robot.subsystems.Blinkin;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Flipper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Susan;
import frc.robot.subsystems.SwerveDrive;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.ISubsystem;
import frc.robot.Constants.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  public static SequenceProcessor sequenceProcessor;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static SwerveDrive swerveDrive;
  public static Intake intake;
  public static Susan susan;
  public static Flipper flipper;
  public static Elevator elevator;
  public static Blinkin state;
  private int loopCnt, loopPeriod, logCounter;
  private long prevLoopTime = 0;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    swerveDrive = new SwerveDrive();
    intake = new Intake();
    sequenceProcessor = new SequenceProcessor();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
   * Dashboard, remove all of the chooser code and uncomment the getString line
   * to get the auto name from the text box below the Gyro
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    swerveDrive.forceRelease();
    intake.forceRelease();
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;

      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    swerveDrive.forceRelease();
    intake.forceRelease();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    log();
    long currentTime = System.currentTimeMillis();
    if (currentTime - prevLoopTime >= Constants.LOOP_PERIOD_MILLIS) {
      loopPeriod = (int) (currentTime - prevLoopTime);
      prevLoopTime = currentTime;
      loopCnt++;
      sequenceProcessor.process();
      // run processes
      /** Run subsystem process methods here */
      swerveDrive.process();
      // intake.process();
    }
    Timer.delay(0.001);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    swerveDrive.forceRelease();
    intake.forceRelease();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    log();
    swerveDrive.process();
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }

  private void log() {
    logCounter++;
    if (logCounter > 5) {
      SmartDashboard.putNumber("Gyro Heading from Drivetrain Model",
                               swerveDrive.getGyroRotation().getDegrees());
      SmartDashboard.putNumber("CANCoder FL Rotation",
                               swerveDrive.getDrivetrainModel().getModulePositions()[0].angle.getDegrees());
      SmartDashboard.putNumber("CANCoder FL Absolute Position",
                               swerveDrive.getFLAbsoluteRotation2d().getDegrees());
      logCounter = 0;
    }
  }
}
