
package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.CargoMech;

public class CargoShipDrop extends SequentialCommandGroup {

  /**
   * Add your docs here.
   */
  public CargoShipDrop(CargoMech cargoMech) {

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    addRequirements(cargoMech);

    addCommands(
        new CargoShipDrop1(cargoMech),
        new CargoShipDrop2(cargoMech),
        new RollerBottomOn(-1.0, cargoMech)
    );
  }
}
