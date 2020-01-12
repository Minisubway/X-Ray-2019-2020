package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.controllers.SpectrumXboxController;

/**
 * An example command.  You can replace me with your own command.
 */
public class RumbleController extends CommandBase {
  private SpectrumXboxController controller;
  private double value;

  public RumbleController(SpectrumXboxController controller, double value) {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);\
    this.controller = controller;
    this.value = value;
  }

  // Called just before this Command runs the first time
  public void initialize() {
    controller.setRumble(value, value);
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
    controller.setRumble(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
  }
}
