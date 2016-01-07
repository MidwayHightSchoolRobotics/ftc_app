package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by BraxtonL_Williams on 1/4/2016.
 */
public class R2AutoPrimary extends R2Telemetry{

    public R2AutoPrimary ()

    {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    } // PushBotAuto


    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_drive_encoders ();

    } // start


    @Override public void loop ()

    {
        run_using_encoders();


        set_drive_power(1.0f, 1.0f);

        if (have_drive_encoders_reached (2880, 2880))
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

        }

        set_drive_power(1.0f, 0.4f);

        if (have_drive_encoders_reached (2880, 2880))
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

        }


    }


}
