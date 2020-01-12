package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hatch;

/**
 * An example command.  You can replace me with your own command.
 */
public class HatchRelease extends CommandBase {

  private final Hatch m_Hatch;

  public HatchRelease(Hatch hatch) {
    m_Hatch = hatch;
    // Use requires() here to declare subsystem dependencies
    addRequirements(hatch);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_Hatch.hatchRelease();
    m_Hatch.logEvent("HATCH RELEASE");
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
    m_Hatch.hatchHold();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
