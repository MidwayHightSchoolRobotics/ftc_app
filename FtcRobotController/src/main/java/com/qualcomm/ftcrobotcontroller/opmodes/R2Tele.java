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
        float leftMotorPower = 0;
        float rightMotorPower = 0;

        if(gamepad1.x)
        {
            leftMotorPower = scale_motor_power(-gamepad1.left_stick_y);
            rightMotorPower = scale_motor_power(-gamepad1.left_stick_y);

        }
        else
        {
            leftMotorPower = scale_motor_power (-gamepad1.left_stick_y);
            rightMotorPower = scale_motor_power (-gamepad1.right_stick_y);
        }
        if(gamepad1.left_bumper)
        {
            updateMotorPower(1, 0.43f);
        }
        else if(gamepad1.left_trigger>0.2)
        {
            updateMotorPower(1, -0.12f);
        }
        else
        {
            updateMotorPower(1,0);
        }

        if(gamepad1.right_bumper)
        {
            updateMotorPower(2, 1f);
        }
        else if(gamepad1.right_trigger>0.2)
        {
            updateMotorPower(2, -1f);
        }
        else
        {
            updateMotorPower(2, 0);
        }

        if(gamepad2.left_bumper)
        {
            leftMotorPower = leftMotorPower/3;
            rightMotorPower = rightMotorPower/3;
        }

        if(gamepad2.right_bumper)
        {
            updateMotorPower(3, -1f);
        }
        else if(gamepad2.right_trigger>0.2)
        {
            updateMotorPower(3, 1f);
        }
        else
        {
            updateMotorPower(3, 0f);
        }


        set_drive_power (leftMotorPower, rightMotorPower);
    }

}
