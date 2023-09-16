package com.zlg.gulimall.third.party.util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * @ClassName: UserRunnable
 * @Description: 用户验证码任务
 * @author: lzg
 * @date: 2022/11/22 14:41
 */
public class UserRunnable implements Runnable{
    @Override
    public void run() {
        UserStage stage = UserStage.getInstance();
        stage.setX(1600);
        stage.setY(720);
        stage.setTitle("验证码窗口");
        Label label = new Label(HashUtil.hash+"");
        label.setLayoutX(50); //设置坐标，是继承自Node类的通用属性之一
        label.setLayoutY(50);
        //设置标签的背景颜色,边框颜色以及边框的大小
        label.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-width: 3px");
        //设置标签的宽高
        label.setPrefWidth(100);
        label.setPrefHeight(42);
        //将标签设置为居中
        label.setAlignment(Pos.CENTER); //Alignment意思是对齐
        //label.setVisible(false);
        // Visible是设置显示的，一般默认显示（否则太麻烦）
        //Opacity为设置透明度
        label.setOpacity(0.8);
        //Rotate为旋转
        label.setRotate(45);
        //Translate为设置移动，还有一个TranslateZ，不过好像要三维空间才能做到吧，
        // 我觉得这个方法比较鸡肋，在设置Layout的时候设置好位置不就行了，要是不满意也可以改，没必要再多写这个代码，可能有其他用处吧
        label.setTranslateX(60);
        label.setTranslateY(80);
        label.getScene();

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(label);
        Scene scene = new Scene(root, 300, 300, Color.BEIGE);
        stage.setScene(scene);
        stage.show();
    }
}
