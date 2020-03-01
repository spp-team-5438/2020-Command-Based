/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Turret extends SubsystemBase {

  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(7);

  /**
   * Creates a new Turret.
   */
  public Turret() {

  }

  public void aimTurret(double power) {
    turretMotor.set(power);
  }

  public boolean getRightLimitSwitch() {
    return true;
  }

  public boolean getLeftLimitSwitch() {
    return false;
  }

  public void stopTurret() {
    turretMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
