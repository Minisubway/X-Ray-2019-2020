package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;

/**
 * An example command.  You can replace me with your own command.
 */
public class SimpleElevatorGoToPos extends CommandBase {
  private final Elevator m_Elevator;
  int pos;
  double upSpeed;
  double downSpeed;
  SetElevator c;
  
  public SimpleElevatorGoToPos(int position, Elevator elevator) {
    m_Elevator = elevator;
    // Use requires() here to declare subsystem dependencies
    addRequirements(elevator);
    pos = position;
    
    //Command used at the end to set the elevator in place
    c = new SetElevator(0.05, m_Elevator);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    if (m_Elevator.isNearPosition(pos)){
      upSpeed = 0;
      downSpeed = 0;
    } else{
      upSpeed = RobotContainer.prefs.getNumber("ElevatorUpSpeed", 0.8);
      downSpeed = RobotContainer.prefs.getNumber("ElevatorDownSpeed", -0.4);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
    if (pos > m_Elevator.getPosition()){
      m_Elevator.setOpenLoop(upSpeed);
    } else{
      m_Elevator.setOpenLoop(downSpeed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return m_Elevator.isNearPosition(pos);
  }

  // Called once after isFinished returns true
  public void end() {
    //start a new command that gives a little bit of voltage to hold elevator in place
    c.schedule();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
