package gui;

import javax.swing.*;

public class CustomFrame extends JFrame {
    public static final int W_FRAME = 496;
    public static final int H_FRAME = 521;
    public CustomFrame(){
        //Gán title cho frame
        setTitle("Tank 1990");
        //Gán kích thước cho frame
        setSize(W_FRAME,H_FRAME);
        //Gán frame hiển thị ra giữa màn hình
        setLocationRelativeTo(null);
        //Tắt chức năng thay đổi kích thước trong frame
        setResizable(false);
        //Gán chế đọ tắt chương trình
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        CustomPanel panel = new CustomPanel();
        add(panel);
        setVisible(true);
    }
}
