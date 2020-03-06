/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private final WPI_TalonSRX m_intakeMotor = new WPI_TalonSRX(8);

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  public void setIntakeSpeedIn(double power) {
    m_intakeMotor.set(power);
  }

  public void setIntakeSpeedOut(double power) {
    m_intakeMotor.set(power*-1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
