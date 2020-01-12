package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.controllers.SpectrumXboxController;
import frc.robot.RobotContainer;

/**
 * An example command.  You can replace me with your own command.
 */
public class RumbleOff extends CommandBase {

  private final SpectrumXboxController m_driverController;
  private final SpectrumXboxController m_opController;

  public RumbleOff(SpectrumXboxController driverController, SpectrumXboxController opController) {
  m_driverController = driverController;
  m_opController = opController;
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    //this.setRunWhenDisabled(true);
    runsWhenDisabled();
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_driverController.setRumble(0, 0);
    m_opController.setRumble(0, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  public void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
  }
}
