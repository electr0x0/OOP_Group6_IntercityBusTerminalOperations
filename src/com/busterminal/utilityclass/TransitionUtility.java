/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.utilityclass;

import javafx.animation.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author electr0
 */
public class TransitionUtility {
    
    public static void materialFade(Pane pane) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), pane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.setAutoReverse(false);
        fade.play();
    }
    
    public static void materialScale(Pane pane) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(800), pane);
        scale.setFromX(0.6);
        scale.setFromY(0.6);
        scale.setToX(1);
        scale.setToY(1);
        scale.setInterpolator(Interpolator.EASE_OUT);
        scale.play();
    }
    
    public static void materialScaleOpposite(Pane pane) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(800), pane);
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setToX(0);
        scale.setToY(0);
        scale.setInterpolator(Interpolator.EASE_OUT);
        scale.play();
    }
}
