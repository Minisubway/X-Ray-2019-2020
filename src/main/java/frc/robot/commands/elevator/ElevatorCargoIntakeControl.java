package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.cargo.IntakeUp;
import frc.robot.subsystems.Elevator;

public class ElevatorCargoIntakeControl extends CommandBase {

  private final Elevator m_Elevator;
  
  /**
   * Add your docs here.
   */
  public ElevatorCargoIntakeControl(Elevator elevator) {
    m_Elevator = elevator;

  }
  public void intialize(){
    RobotContainer.cargoMech.logEvent("Waiting to raise elevator for intaking");
    Robot.printDebug("Waiting to raise elevator for intake");
    
  }
  public boolean isFinished() {
    /*if (RobotContainer.cargoMech.cargoTopMAX.getOutputCurrent() > RobotContainer.prefs.getNumber("C: InAmpsThreshold", 29) &&
      CommandScheduler.getInstance().timeSinceScheduled(this) > 2){
      new MMElevator((int)RobotContainer.prefs.getNumber("C: ElevatorHeight", 2000), m_Elevator).schedule();
      
      return true;
    }else{
      return false;
    } */
    return true;
  }

  public void end(){
  }

  protected void interrupted(){
  }
}
