package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command.  You can replace me with your own command.
 */
public class MMElevator extends CommandBase {
  private final Elevator m_Elevator;
  int t = 0;
  boolean holdPos = false;
  public MMElevator(int target, Elevator elevator) {
    m_Elevator = elevator;
    // Use requires() here to declare subsystem dependencies
    addRequirements(elevator);
    t = target;
  }

  public MMElevator(Elevator elevator) {
    m_Elevator = elevator;
    // Use requires() here to declare subsystem dependencies
    addRequirements(elevator);
    holdPos = true;
  }

  // Called just before this Command runs the first time
  public void initialize() {
    if (holdPos){
      t = (int)m_Elevator.getPosition();
      if (t < 0){
        t = 0;
      }
    }
    m_Elevator.setTargetPosition(t);
    m_Elevator.logEvent("Postion MM: " + t);
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
    m_Elevator.MotionMagicControl();
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_Elevator.setOpenLoop(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
