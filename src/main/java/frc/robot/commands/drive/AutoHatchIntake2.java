/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.controllers.SpectrumXboxController;
import frc.robot.commands.RumbleController;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoHatchIntake2 extends ParallelCommandGroup {
  /**
   * Creates a new AutoHatchIntake2.
   */
  public AutoHatchIntake2(SpectrumXboxController controller) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(
      new RumbleController(controller, 0.75),
      new WaitCommand(0.2)
    );
  }
}
