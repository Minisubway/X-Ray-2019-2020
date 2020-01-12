package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.cargo.*;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Hatch;

public class HatchReady extends ParallelCommandGroup {

  private final Hatch m_Hatch;
  /**
   * Add your docs here.
   */
  public HatchReady(Hatch hatch, CargoMech cargoMech) {

    m_Hatch = hatch;
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

    addCommands(
      new TiltDown(cargoMech),
      new HatchRelease(hatch)
    );
  }

  public void initialize(){
    m_Hatch.logEvent("HATCH READY");
  }
}
