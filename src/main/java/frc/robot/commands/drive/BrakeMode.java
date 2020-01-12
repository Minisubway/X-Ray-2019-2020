package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class BrakeMode extends CommandBase {
  private final Drivetrain m_Drivetrain;
  public BrakeMode(Drivetrain drivetrain) {
    m_Drivetrain = drivetrain;
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    //this.runsWhenDisabled(true);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_Drivetrain.brakeMode();
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
    m_Drivetrain.defaultIdleMode();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }
}
