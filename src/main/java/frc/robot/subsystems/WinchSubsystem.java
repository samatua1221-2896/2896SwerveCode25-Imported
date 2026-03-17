// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/* This is the code that controls the winch on the robot system. 
 * It uses a VictorSP motor controller
 */



package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WinchSubsystem extends SubsystemBase {


  private final VictorSP winchMotor;

  /** Creates a new ExampleSubsystem. */
  public WinchSubsystem() {

    winchMotor = new VictorSP(2);
  }

 
  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void goForward(){
    winchMotor.setVoltage(12);
  }

  public void goBackward(){
    winchMotor.setVoltage(-12);
  }

  public void stop(){
    winchMotor.stopMotor();
  }


public Command liftRobot (){
  return Commands.startEnd(
    () -> goForward(),
    this :: stop,
    this
  );
}

public Command dropRobot (){
  return Commands.startEnd(
    () -> goBackward(),
    this :: stop,
    this
  );
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
