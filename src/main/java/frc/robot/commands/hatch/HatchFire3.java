/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.cargo.IntakeDown;
import frc.robot.subsystems.CargoMech;
import frc.robot.subsystems.Hatch;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class HatchFire3 extends ParallelCommandGroup {
  /**
   * Creates a new HatchFire3.
   */
  public HatchFire3(Hatch hatch, CargoMech cargoMech) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(
      new HatchEject(hatch),
      new IntakeDown(cargoMech)
    );
  }
}
