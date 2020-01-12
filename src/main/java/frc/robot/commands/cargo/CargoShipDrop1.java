/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CargoMech;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class CargoShipDrop1 extends ParallelCommandGroup {
  /**
   * Creates a new CargoShipDrop1.
   */
  public CargoShipDrop1(CargoMech cargoMech) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();

    addCommands(
      new RollerBottomOn(0.5 , cargoMech),
      new IntakeDown(cargoMech),
      new TiltUp(cargoMech),
      new WaitCommand(0.5)
    );
  }
}
