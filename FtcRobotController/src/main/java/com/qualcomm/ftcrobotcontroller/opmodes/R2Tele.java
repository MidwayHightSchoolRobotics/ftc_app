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
        if(gamepad2.right_bumper)
        {
            updateMotorPower(1, 0.43f);
        }
        else if(gamepad2.right_trigger>0.2)
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

        if(gamepad2.x)
        {
            leftMotorPower = leftMotorPower/3;
            rightMotorPower = rightMotorPower/3;
        }

        if(gamepad2.left_bumper)
        {
            updateMotorPower(3, -0.5f);
        }
        else if(gamepad2.left_trigger>0.2)
        {
            updateMotorPower(3, 0.5f);
        }
        else
        {
            updateMotorPower(3, 0f);
        }

        if(gamepad2.b)
        {
            servoFlip.setPosition(0.1);
        }
        else if(gamepad2.a)
        {
            servoFlip.setPosition(0.93);
        }

        if(gamepad2.dpad_down)
        {
            servoGrab.setPosition(0.2);
        }
        else if(gamepad2.dpad_up)
        {
            servoGrab.setPosition(0.6);
        }


        set_drive_power (leftMotorPower, rightMotorPower);
    }

}
