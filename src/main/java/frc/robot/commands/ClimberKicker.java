package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * An example command.  You can replace me with your own command.
 */
public class ClimberKicker extends CommandBase {
  private final Climber m_Climber;
  public ClimberKicker(Climber climber) {
    m_Climber = climber;
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_Climber.setKicker(true);
    m_Climber.logEvent("CLIMBER KICKER");
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_Climber.setKicker(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
