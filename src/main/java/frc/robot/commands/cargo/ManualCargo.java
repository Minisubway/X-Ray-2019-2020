package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CargoMech;

/**
 * An example command.  You can replace me with your own command.
 */
public class ManualCargo extends CommandBase {

  private final CargoMech m_CargoMech;

  public ManualCargo(CargoMech cargoMech) {
    m_CargoMech = cargoMech;
    // Use requires() here to declare subsystem dependencies
    addRequirements(cargoMech);
  }

  // Called just before this Command runs the first time
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {
    m_CargoMech.setBottom(RobotContainer.opController.rightStick.getY());
    m_CargoMech.setTop(RobotContainer.opController.rightStick.getX());
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  public void end() {
    m_CargoMech.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public void interrupted() {
    end();
  }
}
