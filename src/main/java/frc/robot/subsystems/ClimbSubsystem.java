package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;;

public class ClimbSubsystem extends SubsystemBase {
    
    //if made, change channel number
   private final PWMSparkMax climbMotor;
   
   public ClimbSubsystem(){

    climbMotor = new PWMSparkMax(3);
   }

   public void runClimb()
   {
        climbMotor.set(0.8);
   }

   public void revClimb(){
    climbMotor.set(-0.8);
   }

   public void stopClimb(){
    climbMotor.set(0);
   }

  public Command climbCommand (){
    return Commands.startEnd(
    () -> runClimb(),
    this :: stopClimb,
    this);
  }

    public Command lowerCommand (){
    return Commands.startEnd(
    () -> revClimb(),
    this :: stopClimb,
    this);
  }


}
