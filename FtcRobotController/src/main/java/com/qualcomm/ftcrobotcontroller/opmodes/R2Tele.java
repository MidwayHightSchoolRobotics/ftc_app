package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by BraxtonL_Williams on 10/12/2015.
 */
public class R2Tele extends R2Telemetry {

    public R2Tele()
    {

    }

    public void loop()
    {

        float leftMotorPower = scale_motor_power (-gamepad1.left_stick_y);
        float rightMotorPower = scale_motor_power (-gamepad1.right_stick_y);

        set_drive_power (leftMotorPower, rightMotorPower);
    }

}
