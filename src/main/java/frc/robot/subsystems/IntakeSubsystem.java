package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class IntakeSubsystem extends SubsystemBase 
{
    private final PWMSparkMax intakeMotor = new PWMSparkMax(1);
    
    public IntakeSubsystem(){} 

    public void runIntake()
    {
        intakeMotor.set(0.8);
    }

    public void reverseIntake()
    {
        intakeMotor.set(-0.8);
    }

    public void stopIntake()
    {
        intakeMotor.set(0);
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run

}

    public Command intakeCommand (){
    return Commands.startEnd(
    () -> runIntake(),
    this :: stopIntake,
    this);
  }

  public Command reverseIntakeCommand (){
    return Commands.startEnd(
    () -> reverseIntake(),
    this :: stopIntake,
    this);
  }

  public Command intakeAuto() {
    return Commands.startEnd(
        () -> runIntake(),
        this::stopIntake,
        this
    ).withTimeout(18.0);
}

   @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


}
