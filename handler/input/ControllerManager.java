package handler.input;

import com.studiohartman.jamepad.ControllerState;

public class ControllerManager extends com.studiohartman.jamepad.ControllerManager {

    com.studiohartman.jamepad.ControllerManager controllers;
    private float[] joystick1, joystick2;
    public float xMovement, yMovement, xAttack, yAttack;

    private boolean[] actionButtons, dpad, joystickPress;

    public boolean square, triangle, circle, X, lJoystick, rJoystick, share, options, connected, dpadUp, dpadDown, dpadLeft, dpadRight;
    public ControllerManager()
    {
        controllers = new com.studiohartman.jamepad.ControllerManager();
        controllers.initSDLGamepad();
        joystick1 = new float[2];
        joystick2 = new float[2];
        actionButtons = new boolean[4];
        joystickPress = new boolean[2];
        dpad = new boolean[4];
    }

    public void tick()
    {
            ControllerState currState = controllers.getState(0);
        connected = currState.isConnected;
            if(!currState.isConnected) {
                return;
            }
            xMovement = currState.leftStickX;
            yMovement = currState.leftStickY;
            xAttack = currState.rightStickX;
            yAttack = currState.rightStickY;
            square = currState.xJustPressed;
            triangle = currState.yJustPressed;
            circle = currState.bJustPressed;
            X = currState.aJustPressed;
            lJoystick = currState.leftStickClick;
            rJoystick = currState.rightStickClick;
            share = currState.backJustPressed;
            options = currState.startJustPressed;

//            System.out.println(xMovement + ", " + yMovement);
    }

    public void vibrate()
    {
        controllers.doVibration(0,0.5f,0.5f, 1000);
    }

}