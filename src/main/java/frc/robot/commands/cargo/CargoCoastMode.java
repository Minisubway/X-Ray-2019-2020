package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CargoMech;

/**
 * An example command.  You can replace me with your own command.
 */
public class CargoCoastMode extends CommandBase {

  private final CargoMech m_CargoMech;

  public CargoCoastMode(CargoMech cargoMech) {
    m_CargoMech = cargoMech;
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.cargoMech);
  }

  // Called just before this Command runs the first time
  public void initialize() {
    m_CargoMech.coastMode();
    m_CargoMech.logEvent("Cargo Coast");
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
    m_CargoMech.brakeMode();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
  end();
  }
}
