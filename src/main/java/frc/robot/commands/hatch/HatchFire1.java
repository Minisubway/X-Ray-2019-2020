/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import frc.robot.commands.cargo.*;
import frc.robot.subsystems.CargoMech;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class HatchFire1 extends ParallelCommandGroup {
  /**
   * Creates a new HatchFire1.
   */
  public HatchFire1(CargoMech cargoMech) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(
      new TiltDown(cargoMech),
      new CargoCoastMode(cargoMech),
      new WaitCommand(0.01)
    );
  }
}
