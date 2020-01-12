package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoMech;

/**
 * An example command.  You can replace me with your own command.
 */
public class RollerTopOn extends CommandBase {
  private final CargoMech m_CargoMech;
  private double s;
  public RollerTopOn(double speed, CargoMech cargoMech) {
    m_CargoMech = cargoMech;
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.cargoMech);
    s=speed;
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_CargoMech.logEvent("TOP ROLLER ON");
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
    m_CargoMech.setTop(s);
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_CargoMech.setTop(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
