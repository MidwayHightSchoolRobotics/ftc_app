package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;

/**
 * Created by Braxton on 10/2/2015.
 */
public class R2Hardware extends OpMode
{


    public ArrayList<String> warnings = new ArrayList<String>(20);

    private DcMotor motorDriveLeft;
    private DcMotor motorDriveRight;

    private DcMotor motorString;
    private DcMotor motorLift;
    private DcMotor motorPull;
    private DcMotor motorHookPull;

    public Servo servoFlip;
    public Servo servoGrab;
    public Servo servoHook;

    public Servo servoMen;


    public R2Hardware()
    {

    }

    @Override
    public void init()
    {

        try
        {
            motorDriveLeft = hardwareMap.dcMotor.get("driveLeft");

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'driveLeft'");
            DbgLog.msg(e.getLocalizedMessage());
            motorDriveLeft = null;
        }

        try
        {
            motorDriveRight = hardwareMap.dcMotor.get("driveRight");
            motorDriveRight.setDirection(DcMotor.Direction.REVERSE);

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'driveRight'");
            DbgLog.msg(e.getLocalizedMessage());
            motorDriveRight = null;
        }


        try
        {
            motorString = hardwareMap.dcMotor.get("string");

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'string'");
            DbgLog.msg(e.getLocalizedMessage());
            motorString = null;
        }

        try
        {
            motorLift = hardwareMap.dcMotor.get("lift");

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'lift'");
            DbgLog.msg(e.getLocalizedMessage());
            motorLift = null;
        }

        try
        {
            motorPull = hardwareMap.dcMotor.get("pull");

        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'pull'");
            DbgLog.msg(e.getLocalizedMessage());
            motorPull = null;
        }

        try
        {
            motorHookPull = hardwareMap.dcMotor.get("hookpull");
        }
        catch (Exception e)
        {
            warnings.add("Failed to map 'pull'");
            DbgLog.msg(e.getLocalizedMessage());
            motorHookPull = null;
        }

        try
        {
            servoFlip = hardwareMap.servo.get("flipper");
        }
        catch (Exception e)
        {
            warnings.add("Failed to map 'flipper'");
            DbgLog.msg(e.getLocalizedMessage());
            servoFlip = null;
        }

        try
        {
            servoHook = hardwareMap.servo.get("hook");
        }
        catch(Exception e)
        {
            warnings.add("Failed to map 'hook'");
            DbgLog.msg(e.getLocalizedMessage());
            servoHook = null;
        }

        try
        {
            servoMen = hardwareMap.servo.get("men");
        }
        catch (Exception e)
        {
            warnings.add("failed to map 'men'");
            DbgLog.msg(e.getLocalizedMessage());
            servoMen = null;
        }

        try {
            servoFlip.setPosition(0.1);
        }
        catch (Exception e)
        {
            DbgLog.msg(e.getLocalizedMessage());
        }

        try {

            servoMen.setPosition(0.66);
        }
        catch (Exception e)
        {
            DbgLog.msg(e.getLocalizedMessage());
        }





    }

    @Override
    public void loop()
    {

    }

    @Override
    public void stop()
    {

    }

    public void updateMotorPower(int moto, float power)
    {
        DcMotor motorToChange;
        switch(moto)
        {
            case(1):
                motorToChange = motorLift;
                break;
            case(2):
                motorToChange = motorPull;
                break;
            case(3):
                motorToChange = motorString;
                break;
            case(4):
                motorToChange = motorHookPull;
                break;
            default:
                motorToChange = null;
                break;
        }
        if(motorToChange != null)
        {
            motorToChange.setPower(power);
        }
    }

    //--------------------------------------------------------------------------
    //
    // scale_motor_power
    //
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    float scale_motor_power (float p_power)
    {
        //
        // Assume no scaling.
        //
        float l_scale = 0.0f;

        //
        // Ensure the values are legal.
        //
        float l_power = Range.clip(p_power, -1, 1);

        float[] l_array =
                { 0.00f, 0.05f, 0.09f, 0.10f, 0.12f
                        , 0.15f, 0.18f, 0.24f, 0.30f, 0.36f
                        , 0.43f, 0.50f, 0.60f, 0.72f, 0.85f
                        , 1.00f, 1.00f
                };

        //
        // Get the corresponding index for the specified argument/parameter.
        //
        int l_index = (int)(l_power * 16.0);
        if (l_index < 0)
        {
            l_index = -l_index;
        }
        else if (l_index > 16)
        {
            l_index = 16;
        }

        if (l_power < 0)
        {
            l_scale = -l_array[l_index];
        }
        else
        {
            l_scale = l_array[l_index];
        }

        return l_scale;

    } // scale_motor_power

    //--------------------------------------------------------------------------
    //
    // a_left_drive_power
    //
    /**
     * Access the left drive motor's power level.
     */
    double a_left_drive_power ()
    {
        double l_return = 0.0;

        if (motorDriveLeft != null)
        {
            l_return = motorDriveLeft.getPower ();
        }

        return l_return;

    } // a_left_drive_power

    //--------------------------------------------------------------------------
    //
    // a_right_drive_power
    //
    /**
     * Access the right drive motor's power level.
     */
    double a_right_drive_power ()
    {
        double l_return = 0.0;

        if (motorDriveRight != null)
        {
            l_return = motorDriveRight.getPower ();
        }

        return l_return;

    } // a_right_drive_power

    //--------------------------------------------------------------------------
    //
    // set_drive_power
    //
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_drive_power (double p_left_power, double p_right_power)

    {
        if (motorDriveLeft != null)
        {
            motorDriveLeft.setPower(p_left_power);
        }
        if (motorDriveRight != null)
        {
            motorDriveRight.setPower(p_right_power);
        }

    } // set_drive_power

    //--------------------------------------------------------------------------
    //
    // run_using_left_drive_encoder
    //
    /**
     * Set the left drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_left_drive_encoder ()

    {
        if (motorDriveLeft != null)
        {
            motorDriveLeft.setChannelMode
                    ( DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

    } // run_using_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_using_right_drive_encoder
    //
    /**
     * Set the right drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_right_drive_encoder ()

    {
        if (motorDriveRight != null)
        {
            motorDriveRight.setChannelMode
                    ( DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

    } // run_using_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_using_encoders
    //
    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_using_encoders ()

    {
        //
        // Call other members to perform the action on both motors.
        //
        run_using_left_drive_encoder ();
        run_using_right_drive_encoder ();

    } // run_using_encoders

    //--------------------------------------------------------------------------
    //
    // run_without_left_drive_encoder
    //
    /**
     * Set the left drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_without_left_drive_encoder ()

    {
        if (motorDriveLeft != null)
        {
            if (motorDriveLeft.getChannelMode () ==
                    DcMotorController.RunMode.RESET_ENCODERS)
            {
                motorDriveLeft.setChannelMode
                        ( DcMotorController.RunMode.RUN_WITHOUT_ENCODERS
                        );
            }
        }

    } // run_without_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_without_right_drive_encoder
    //
    /**
     * Set the right drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_without_right_drive_encoder ()

    {
        if (motorDriveRight != null)
        {
            if (motorDriveRight.getChannelMode () ==
                    DcMotorController.RunMode.RESET_ENCODERS)
            {
                motorDriveRight.setChannelMode
                        ( DcMotorController.RunMode.RUN_WITHOUT_ENCODERS
                        );
            }
        }

    } // run_without_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_without_drive_encoders
    //
    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_without_drive_encoders ()

    {
        //
        // Call other members to perform the action on both motors.
        //
        run_without_left_drive_encoder ();
        run_without_right_drive_encoder ();

    } // run_without_drive_encoders

    //--------------------------------------------------------------------------
    //
    // reset_left_drive_encoder
    //
    /**
     * Reset the left drive wheel encoder.
     */
    public void reset_left_drive_encoder ()

    {
        if (motorDriveLeft != null)
        {
            motorDriveLeft.setChannelMode
                    ( DcMotorController.RunMode.RESET_ENCODERS
                    );
        }

    } // reset_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // reset_right_drive_encoder
    //
    /**
     * Reset the right drive wheel encoder.
     */
    public void reset_right_drive_encoder ()

    {
        if (motorDriveRight != null)
        {
            motorDriveRight.setChannelMode
                    ( DcMotorController.RunMode.RESET_ENCODERS
                    );
        }

    } // reset_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // reset_drive_encoders
    //
    /**
     * Reset both drive wheel encoders.
     */
    public void reset_drive_encoders ()

    {
        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_left_drive_encoder ();
        reset_right_drive_encoder ();

    } // reset_drive_encoders

    //--------------------------------------------------------------------------
    //
    // a_left_encoder_count
    //
    /**
     * Access the left encoder's count.
     */
    int a_left_encoder_count ()
    {
        int l_return = 0;

        if (motorDriveLeft != null)
        {
            l_return = motorDriveLeft.getCurrentPosition ();
        }

        return l_return;

    } // a_left_encoder_count

    //--------------------------------------------------------------------------
    //
    // a_right_encoder_count
    //
    /**
     * Access the right encoder's count.
     */
    int a_right_encoder_count ()

    {
        int l_return = 0;

        if (motorDriveRight != null)
        {
            l_return = motorDriveRight.getCurrentPosition ();
        }

        return l_return;

    } // a_right_encoder_count

    //--------------------------------------------------------------------------
    //
    // has_left_drive_encoder_reached
    //
    /**
     * Indicate whether the left drive motor's encoder has reached a value.
     */
    boolean has_left_drive_encoder_reached (double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (motorDriveLeft != null)
        {
            //
            // Has the encoder reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs (motorDriveLeft.getCurrentPosition ()) > p_count)
            {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_left_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // has_right_drive_encoder_reached
    //
    /**
     * Indicate whether the right drive motor's encoder has reached a value.
     */
    boolean has_right_drive_encoder_reached (double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (motorDriveRight != null)
        {
            //
            // Have the encoders reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs (motorDriveRight.getCurrentPosition ()) > p_count)
            {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_right_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // have_drive_encoders_reached
    //
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean have_drive_encoders_reached
    ( double p_left_count
            , double p_right_count
    )

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Have the encoders reached the specified values?
        //
        if (has_left_drive_encoder_reached (p_left_count) &&
                has_right_drive_encoder_reached (p_right_count))
        {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // have_encoders_reached

    //--------------------------------------------------------------------------
    //
    // drive_using_encoders
    //
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean drive_using_encoders
    ( double p_left_power
            , double p_right_power
            , double p_left_count
            , double p_right_count
    )

    {
        //
        // Assume the encoders have not reached the limit.
        //
        boolean l_return = false;

        //
        // Tell the system that motor encoders will be used.
        //
        run_using_encoders ();

        //
        // Start the drive wheel motors at full power.
        //
        set_drive_power (p_left_power, p_right_power);

        //
        // Have the motor shafts turned the required amount?
        //
        // If they haven't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        //
        if (have_drive_encoders_reached (p_left_count, p_right_count))
        {
            //
            // Reset the encoders to ensure they are at a known good value.
            //
            reset_drive_encoders ();

            //
            // Stop the motors.
            //
            set_drive_power (0.0f, 0.0f);

            //
            // Transition to the next state when this method is called
            // again.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // drive_using_encoders

    //--------------------------------------------------------------------------
    //
    // has_left_drive_encoder_reset
    //
    /**
     * Indicate whether the left drive encoder has been completely reset.
     */
    boolean has_left_drive_encoder_reset ()
    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Has the left encoder reached zero?
        //
        if (a_left_encoder_count () == 0)
        {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_left_drive_encoder_reset

    //--------------------------------------------------------------------------
    //
    // has_right_drive_encoder_reset
    //
    /**
     * Indicate whether the left drive encoder has been completely reset.
     */
    boolean has_right_drive_encoder_reset ()
    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Has the right encoder reached zero?
        //
        if (a_right_encoder_count () == 0)
        {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_right_drive_encoder_reset

    //--------------------------------------------------------------------------
    //
    // have_drive_encoders_reset
    //
    /**
     * Indicate whether the encoders have been completely reset.
     */
    boolean have_drive_encoders_reset ()
    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Have the encoders reached zero?
        //
        if (has_left_drive_encoder_reset () && has_right_drive_encoder_reset ())
        {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // have_drive_encoders_reset


}
