/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.           
                                                    */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

public class Mech {
    private static Mech instance_;
	public static Mech getInstance() {
		if(instance_ == null) {
			instance_ = new Mech();
		}
		return instance_;
    }

    TalonSRX mechOne;
    TalonSRX mechTwo;
    TalonSRX mechThree;
    TalonSRX mechFour;

    public Mech(){
        mechOne = new TalonSRX(0);
        mechTwo = new TalonSRX(1);
        mechThree = new TalonSRX(2);
        mechFour = new TalonSRX(3);
    }

    public void motionMagic(){
        int kTimeoutMs=10;
        mechOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
		mechOne.setSensorPhase(true);
		mechOne.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		mechOne.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		mechOne.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* set the peak and nominal outputs */
		mechOne.configNominalOutputForward(0, kTimeoutMs);
		mechOne.configNominalOutputReverse(0, kTimeoutMs);
		mechOne.configPeakOutputForward(1, kTimeoutMs);
		mechOne.configPeakOutputReverse(-1, kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		mechOne.selectProfileSlot(0, 0);
		mechOne.config_kF(0, 0, kTimeoutMs);
		mechOne.config_kP(0, 0, kTimeoutMs);
		mechOne.config_kI(0, 0, kTimeoutMs);
        mechOne.config_kD(0, 0, kTimeoutMs);
        
		/* set acceleration and cruise velocity - see documentation */
		mechOne.configMotionCruiseVelocity(5000, kTimeoutMs);
        mechOne.configMotionAcceleration(3500, kTimeoutMs);
        
		/* zero the sensor */
        mechOne.setSelectedSensorPosition(0, 0, kTimeoutMs);


        /* Set relevant frame periods to be at least as fast as periodic rate */
		mechTwo.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		mechTwo.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* set the peak and nominal outputs */
		mechTwo.configNominalOutputForward(0, kTimeoutMs);
		mechTwo.configNominalOutputReverse(0, kTimeoutMs);
		mechTwo.configPeakOutputForward(1, kTimeoutMs);
		mechTwo.configPeakOutputReverse(-1, kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		mechTwo.selectProfileSlot(0, 0);
		mechTwo.config_kF(0, 0.2, kTimeoutMs);
		mechTwo.config_kP(0, 0.2, kTimeoutMs);
		mechTwo.config_kI(0, 0, kTimeoutMs);
        mechTwo.config_kD(0, 0, kTimeoutMs);
        
		/* set acceleration and cruise velocity - see documentation */
		mechTwo.configMotionCruiseVelocity(5000, kTimeoutMs);
        mechTwo.configMotionAcceleration(3500, kTimeoutMs);
        
		/* zero the sensor */
        mechTwo.setSelectedSensorPosition(0, 0, kTimeoutMs);
    }
    public void setmechOnePosition(int value){
        mechOne.set(ControlMode.MotionMagic, value);
    }

    public void setmechTwoPosition(int value){
        mechTwo.set(ControlMode.MotionMagic, value);
    }

    public void setmechThreePosition(int value){
        mechThree.set(ControlMode.MotionMagic, value);
    }

    public void setmechFourPosition(int value){
        mechFour.set(ControlMode.MotionMagic, value);
    }

    public void mechOneControl(double input){
        mechOne.set(ControlMode.PercentOutput, input);
    }

    public double getMechOnePosition(){
        return mechOne.getSelectedSensorPosition(0);
    }

    public void zero(){
        mechOne.setSelectedSensorPosition(0, 0, 10);
        mechTwo.setSelectedSensorPosition(0, 0, 10);
        mechThree.setSelectedSensorPosition(0, 0, 10);
        mechFour.setSelectedSensorPosition(0, 0, 10);
    }
}