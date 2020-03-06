/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Turret extends SubsystemBase {

  private final WPI_TalonSRX turretMotor = new WPI_TalonSRX(Constants.TURRET_TALONSRX_ID);
  private final DigitalInput leftLimit = new DigitalInput(Constants.LEFT_LIMIT_ID);
  private final DigitalInput rightLimit = new DigitalInput(Constants.RIGHT_LIMIT_ID);

  /**
   * Creates a new Turret.
   */
  public Turret() {

  }

  public void aimTurret(double power) {
    turretMotor.set(power);
  }

  public boolean getRightLimitSwitch() {
    return rightLimit.get();
  }

  public boolean getLeftLimitSwitch() {
    return leftLimit.get();
  }

  public void stopTurret() {
    turretMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
