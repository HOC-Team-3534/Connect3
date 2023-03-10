// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import java.util.Arrays;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.sequences.SequenceProcessor;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.LightsState;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Carriage;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveDrive;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.ISubsystem;
import frc.pathplanner.PathPlannerFollower;
import frc.robot.Constants.*;
import frc.robot.RobotContainer.Buttons;
import frc.robot.autons.Auton;
import frc.robot.autons.Path;
import frc.robot.extras.Limelight;

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
  public static BaseAutonSequence<? extends ISequencePhase> chosenAuton;
  public static double designatedLoopPeriod = 20;
  private static long autonStartTime;
  private final SendableChooser<Auton> sendableChooser = new SendableChooser<>();
  public static PathPlannerFollower farLeftPlace2;
  public static PathPlannerFollower farRightPlace2;
  public static SwerveDrive swerveDrive;
  public static Intake intake;
  public static Carriage carriage;
  public static Elevator elevator;
  public static Lights lights;
  public static Limelight limelight;
  private int loopCnt, loopPeriod, logCounter;
  private long prevLoopTime = 0;
  public static boolean isAutonomous;
  private final Field2d m_field = new Field2d();
  private final Field2d LLPose_Field = new Field2d();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    limelight = new Limelight(null, null, null);
    swerveDrive = new SwerveDrive();
    intake = new Intake();
    lights = new Lights();
    sequenceProcessor = new SequenceProcessor();
    Arrays.asList(Path.values()).stream().forEach(path -> path.loadPath());
    SmartDashboard.putNumber("Auton Time Delay(ms)", 0.0);
    sendableChooser.setDefaultOption("Drive the Robot Forward",
                                     Auton.Drive_Forward);// TODO change this an
                                                          // official auton
    sendableChooser.addOption("Far Left Place Cube then Cone",
                              Auton.Far_Left_Place2);
    SmartDashboard.putData(sendableChooser);
    SmartDashboard.putData("Field", m_field);
    SmartDashboard.putData("LL Pose Field", LLPose_Field);
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
    isAutonomous = this.isAutonomous();
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
    lights.forceRelease();
    chosenAuton = sendableChooser.getSelected().getAuton();
    chosenAuton.start();
    autonStartTime = System.currentTimeMillis();
    elevator.forceRelease();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    log();
    long currentTime = System.currentTimeMillis();
    if (currentTime - prevLoopTime >= designatedLoopPeriod) {
      loopPeriod = (int) (currentTime - prevLoopTime);
      prevLoopTime = currentTime;
      loopCnt++;
      if (currentTime - autonStartTime > SmartDashboard.getNumber("Auton Time Delay(ms)",
                                                                  0.0)) {
        chosenAuton.process();
      }
      // run processes
      /** Run subsystem process methods here */
      swerveDrive.process();
    }
    Timer.delay(0.001);
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    swerveDrive.forceRelease();
    intake.forceRelease();
    lights.forceRelease();
    elevator.forceRelease();
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
      lights.process();
      swerveDrive.process();
      intake.process();
      elevator.process();
    }
    Timer.delay(0.001);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    swerveDrive.forceRelease();
    intake.forceRelease();
    lights.forceRelease();
    elevator.forceRelease();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    log();
    swerveDrive.process();
    lights.process();
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
      m_field.setRobotPose(swerveDrive.getPose());
      var pose = limelight.getGridPose(swerveDrive.getGridPositionRequest());
      if (pose != null)
        LLPose_Field.setRobotPose(pose);
      // if (swerveDrive.getPathPlannerFollower() != null) {
      // if (swerveDrive.getPathPlannerFollower().getRemainingTimeSeconds() <=
      // 0)
      // swerveDrive.getPathPlannerFollower().resetStart();
      // var currState = swerveDrive.getPathPlannerFollower().getCurrentState();
      // LLPose_Field.setRobotPose(new
      // Pose2d(currState.poseMeters.getTranslation(),
      // currState.holonomicRotation));
      // }
      logCounter = 0;
    }
  }
}
