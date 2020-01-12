package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * An example command.  You can replace me with your own command.
 */
public class SetElevator extends CommandBase {
  private final Elevator m_Elevator;
  double s;
  
  public SetElevator(double speed, Elevator elevator) {
    m_Elevator = elevator;
    // Use requires() here to declare subsystem dependencies
    addRequirements(m_Elevator);
    s = speed;
  }

  // Called just before this Command runs the first time
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
      m_Elevator.setOpenLoop(s);
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_Elevator.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
