package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class ShooterSubsystem extends SubsystemBase
{
    private final VictorSP shooterMotor = new VictorSP(0);

    public ShooterSubsystem(){} 

    public void runShooter()
    {
        shooterMotor.set(0.8);
    }

    public void reverseShooter()
    {
        shooterMotor.set(-0.8);
    }

    public void stopShooter()
    {
        shooterMotor.set(0);
    }

     @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  /*
   *   public Command goUp (){
    return Commands.startEnd(
    () -> goForward(),
    this :: stop,
    this);
  }
   */


  public Command shoot (){
    return Commands.startEnd(
    () -> runShooter(),
    this :: stopShooter,
    this);
  }

  public Command reverseShoot (){
    return Commands.startEnd(
    () -> reverseShooter(),
    this :: stopShooter,
    this);
  }

  public Command shootAuto() {
    return Commands.startEnd(
        () -> runShooter(),
        this::stopShooter,
        this
    ).withTimeout(3.0);
}  


   @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
