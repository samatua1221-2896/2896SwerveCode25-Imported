package frc.robot.subsystems;

//import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeChassisSubsystem extends SubsystemBase {

   private final PWMSparkMax chassisMotor;
    
   public IntakeChassisSubsystem(){

    chassisMotor = new PWMSparkMax(2);
   }

   public void dropChassis()
   {
        // double check the voltage of this, don't want to ruin the intake when testing the motor
        chassisMotor.setVoltage(-4);
   }

  public void liftChassis()
   {
        // double check the voltage of this, don't want to ruin the intake when testing the motor
        chassisMotor.setVoltage(4);
   }

   public void stopChassis()
   {
        chassisMotor.setVoltage(0);
   }

public Command drop (){
  return Commands.startEnd(
    () -> dropChassis(),
    this :: stopChassis,
    this
  );
}

public Command lift (){
  return Commands.startEnd(
    () -> liftChassis(),
    this :: stopChassis,
    this
  );
}

public Command dropAuto() {
  return Commands.startEnd(
    () -> dropChassis(),
    this::stopChassis,
    this
  ).withTimeout(0.5);
}

}
