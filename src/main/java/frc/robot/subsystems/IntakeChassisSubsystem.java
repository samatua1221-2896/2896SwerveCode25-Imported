package frc.robot.subsystems;

//import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeChassisSubsystem extends SubsystemBase {

   private final PWMSparkMax chassisMotor = new PWMSparkMax(2);
    
   public IntakeChassisSubsystem(){}

   public void dropChassis()
   {
        // if switched to smaller motor, change to new voltage
        chassisMotor.setVoltage(12);
   }

  public void liftChassis()
   {
        // if switched to smaller motor, change to new voltage
        chassisMotor.setVoltage(-12);
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

}
