// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



package frc.robot.commands;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/** An example command that uses an example subsystem. */
public class DefaultDrive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final SwerveSubsystem m_SwerveSubsystem;
  private CommandXboxController m_driverController =
        new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
  
   //public DefaultDrive(ExampleSubsystem subsystem) {
      // //Use addRequirements() here to declare subsystem dependencies.
     // addRequirements(m_subsystem);}
  
    public DefaultDrive(SwerveSubsystem swerveSubsystem) {
      m_SwerveSubsystem = swerveSubsystem;
      m_driverController =
    new CommandXboxController(OperatorConstants.kDriverControllerPort);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_SwerveSubsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {} 

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = -m_driverController.getLeftY();
    double strafe = m_driverController.getLeftX();
    double rotation = m_driverController.getRightX();

    Trigger slowMode = m_driverController.leftBumper();

    if (slowMode != null) {
      forward  *= 0.5;
      strafe *= 0.5;
      rotation *= 0.5;
    }

    m_SwerveSubsystem.allowSwervedrive(forward, strafe, rotation, true);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
