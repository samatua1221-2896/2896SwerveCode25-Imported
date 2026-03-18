// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

//import org.ejml.data.FScalar;
//import org.ejml.dense.block.linsol.chol.CholeskyOuterSolver_DDRB;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

//import edu.wpi.first.wpilibj.RobotBase;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  
  // The robot's subsystems and commands are defined here...

  //Example subsystem is the example already given in YAGSL code; .java file inside of subsystem folder
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //The entire swerve subsystem; all math and inverse kinematics are run through here
  private final SwerveSubsystem drivebase = new SwerveSubsystem();

  //a small motor and VistorSP to control the wheels
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();

  // Shooter system to control the shooter motors
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();

  //Controls the climbing motors
  private final ClimbSubsystem m_ClimbSubsystem = new ClimbSubsystem();

  //Controls the Chassis for the intake
  private final IntakeChassisSubsystem m_IntakeChassisSubsystem = new IntakeChassisSubsystem();
 

  // Replace with CommandPS4Controller or CommandJoystick if needed

  //Xbox controls the swerve drive base
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  //Joystick controls the m_WinchSubsystem object
  private final CommandJoystick m_operatorJoystick = 
      new CommandJoystick(OperatorConstants.kOperatorJoystickPort);


public RobotContainer(){

  NamedCommands.registerCommand("exampleCommand", m_ShooterSubsystem.shoot());

  // Do all other initialization
  configureButtonBindings();
  }
  
  public void configureButtonBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new DefaultDrive(drivebase));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    //This controls the motor that is attached to the compression wheels
    m_operatorJoystick.button(2).whileTrue(m_IntakeSubsystem.intakeCommand());
    m_operatorJoystick.button(8).whileTrue(m_IntakeSubsystem.reverseIntakeCommand());

    //This controls the motor that shoots the ball
    m_operatorJoystick.button(1).whileTrue(m_ShooterSubsystem.shoot());
    m_operatorJoystick.button(7).whileTrue(m_ShooterSubsystem.reverseShoot());

    //Make the buttons to lower and lift the intakeChassis
    m_operatorJoystick.button(3).whileTrue(m_IntakeChassisSubsystem.drop());
    m_operatorJoystick.button(5).whileTrue(m_IntakeChassisSubsystem.lift());

    //Controls for the hypothetical climb
    m_operatorJoystick.button(12).whileTrue(m_ClimbSubsystem.climbCommand());
    m_operatorJoystick.button(10).whileTrue(m_ClimbSubsystem.lowerCommand());
   
  }
  

  
  
  SwerveInputStream driveAngularvelocity = SwerveInputStream.of(drivebase.getSwervedrive(),
                                                           () -> m_driverController.getLeftY() * -1,
                                                           () -> m_driverController.getLeftX() * -1)
                                                          .withControllerRotationAxis(m_driverController::getRightX)
                                                          .deadband(OperatorConstants.DEADBAND)
                                                          .scaleTranslation(0.8)
                                                          .allianceRelativeControl(true);

SwerveInputStream driveDirectAngle = driveAngularvelocity.copy().withControllerHeadingAxis(m_driverController::getRightX, 
                                                                                           m_driverController::getRightY)
                                                                                          .headingWhile(true); 
                                                              
Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);

Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularvelocity);

/*

//Remove comment code in order to install simulation drive, as of 10/16/25 does not work, is supposed to be sim versions of all the above block of code

SwerveInputStream driveAngularvelocitySim = SwerveInputStream.of(drivebase.getSwervedrive(),
                                                           () -> -m_driverController.getLeftY(),
                                                           () -> -m_driverController.getLeftX())
                                                          .withControllerRotationAxis(() -> m_driverController.getRawAxis(2))
                                                          .deadband(OperatorConstants.DEADBAND)
                                                          .scaleTranslation(0.8)
                                                          .allianceRelativeControl(true);
                                                                                          
SwerveInputStream driveDirectAngleSim = driveAngularvelocitySim.copy() 
                                                                .withControllerHeadingAxis(() -> Math.sin(
                                                                                              m_driverController.getRawAxis(
                                                                                                    2) * Math.PI) * (Math.PI * 2),
                                                                                            () -> Math.cos(
                                                                                              m_driverController.getRawAxis(
                                                                                                    2) * Math.PI) *
                                                                                                      (Math.PI * 2))
                                                                .headingWhile(true);

Command driveFieldOrientedDirectAngleSim = drivebase.driveFieldOriented(driveDirectAngleSim);
*/


  /**                                                         
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  

  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(String pathName) {
    // An example command will be run in autonomous

    //Autos are run using PathPlanner, code redirects to the application
    return new PathPlannerAuto(pathName);
  }


}
