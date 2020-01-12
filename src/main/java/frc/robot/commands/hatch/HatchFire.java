package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.drivers.Photon.Animation;
import frc.lib.drivers.Photon.Color;
import frc.robot.Robot;
import frc.robot.commands.cargo.*;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Hatch;

public class HatchFire extends SequentialCommandGroup {

  private final Hatch m_Hatch;
  /**
   * Add your docs here.
   */
  public HatchFire(Hatch hatch, CargoMech cargoMech) {
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

    m_Hatch = hatch;

    addCommands(
      new HatchFire1(cargoMech),
      new HatchFire2(hatch),
      new HatchFire3(hatch, cargoMech)
    );

    /*addParallel(new TiltDown());
    addParallel(new CargoCoastMode());
    addSequential(new WaitCommand(.01));
    addParallel(new HatchRelease());
    addSequential(new WaitCommand(.02));
    addParallel(new HatchEject());
    addParallel(new IntakeDown()); */
  }

  public void initialize(){
    m_Hatch.logEvent("HATCH FIRE");
    //Robot.photon.addAnimation("FireHatch", Animation.SOLID, Color.YELLOW, Color.WHITE, 100, 10);
  }
}
