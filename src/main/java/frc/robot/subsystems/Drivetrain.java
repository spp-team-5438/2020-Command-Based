/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {

  //define Spark Maxes with IDs and as brushless controllers
  private final CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_SPARKMAX_ID, MotorType.kBrushless);
  private final CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_SPARKMAX_ID, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_SPARKMAX_ID, MotorType.kBrushless);
  private final CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_SPARKMAX_ID, MotorType.kBrushless);

  private final CANEncoder backLeftEncoder = new CANEncoder(backLeft);
  private final CANEncoder frontLeftEncoder = new CANEncoder(frontLeft);
  private final CANEncoder backRightEncoder = new CANEncoder(backRight);
  private final CANEncoder frontRightEncoder = new CANEncoder(frontRight);

  //define left and right side controller groups
  private final SpeedControllerGroup left = new SpeedControllerGroup(backLeft, frontLeft);
  private final SpeedControllerGroup right = new SpeedControllerGroup(backRight, frontRight);

  //define drive
  private final DifferentialDrive drive = new DifferentialDrive(left, right);

  public Drivetrain() {
    backLeft.setOpenLoopRampRate(.2);
    frontLeft.setOpenLoopRampRate(.2);
    backRight.setOpenLoopRampRate(.2);
    frontRight.setOpenLoopRampRate(.2);
  }

  public void arcadeDrive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  public void curvatureDrive(double fwd, double rot, boolean isQuickTurn) {
    drive.curvatureDrive(fwd, rot, isQuickTurn);
  }

  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  public double getBackLeftEncoder() {
    return backLeftEncoder.getPosition();
  }

  public double getBackRightEncoder() {
    return backRightEncoder.getPosition();
  }

  public double getFrontLeftEncoder() {
    return frontLeftEncoder.getPosition();
  }

  public double getFrontRightEncoder() {
    return frontRightEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
