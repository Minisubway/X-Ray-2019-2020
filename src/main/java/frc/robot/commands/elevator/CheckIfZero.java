package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

/**
 * Zero the Extension
 */
public class CheckIfZero extends CommandBase {
	private final Elevator m_Elevator;
	double s;
	boolean alreadyDown = false;
	public CheckIfZero(Elevator elevator) {
		m_Elevator = elevator;
		// Use requires() here to declare subsystem dependencies
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		if (m_Elevator.getBottomLimitSW()){
			m_Elevator.zeroPosition();
		  }
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	public void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end();
	}
}
