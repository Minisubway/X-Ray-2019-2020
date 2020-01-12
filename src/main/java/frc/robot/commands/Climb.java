package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class Climb extends CommandBase {
  private final Drivetrain m_Drivetrain;
  private final Climber m_Climber;
  public Climb(Drivetrain drivetrain, Climber climber) {
    // Use requires() here to declare subsystem dependencies
    m_Drivetrain = drivetrain;
    m_Climber = climber;
    addRequirements(climber);
    addRequirements(drivetrain);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_Climber.logEvent("CLIMB");
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
      //Control the climber with the right stick, set a deadband value of 30%
      m_Drivetrain.tankDrive(1.0, 1.0);
      if (!m_Climber.getLimit()){
        m_Climber.set(1.0);
      } else {
        m_Climber.set(0.0);
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  //finish when we hit the limit switch of the climber current is too high.
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_Climber.set(0.0);
    m_Drivetrain.stop();;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
