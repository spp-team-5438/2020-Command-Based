/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {

  private final WPI_TalonSRX rightClimb = new WPI_TalonSRX(Constants.RIGHT_CLIMB_TALONSRX_ID);
  private final WPI_TalonSRX leftClimb = new WPI_TalonSRX(Constants.LEFT_CLIMB_TALONSRX_ID);
  private final Solenoid climbBrake = new Solenoid(Constants.CLIMB_BRAKE_SOLENOID_ID);

  /**
   * Creates a new Climb.
   */
  public Climb() {
    leftClimb.setNeutralMode(NeutralMode.Brake);
    rightClimb.setNeutralMode(NeutralMode.Brake);
    rightClimb.follow(leftClimb);
    rightClimb.setInverted(true);
  }

  public void setClimbMotorSpeed(double power) {
    leftClimb.set(power);
  }

  public void setClimbBrake(boolean state) {
    climbBrake.set(state);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
