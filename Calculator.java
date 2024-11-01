package chap01;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JLabel[] memLabels = new JLabel[6]; // 메모리 기능 레이블 배열
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[14];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JButton percButton, sqrButton, sqrtButton, fracButton, backButton;
    JPanel panel, memPanel;

    Font font = new Font("Arial", Font.PLAIN, 28);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean isOperatorClicked = false;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 750);
        frame.setLayout(null);

        // 텍스트 필드 생성 및 설정
        textField = new JTextField();
        textField.setBounds(25, 25, 350, 75);
        textField.setFont(new Font("Arial", Font.PLAIN, 40));
        textField.setEditable(false);
        frame.add(textField);

        // 메모리 기능 레이블 패널 생성 및 설정
        String[] memTexts = { "MC", "MR", "M+", "M-", "MS", "Mv" };
        memPanel = new JPanel();
        memPanel.setBounds(25, 110, 350, 30);
        memPanel.setLayout(new GridLayout(1, 6)); // 동일한 간격으로 6개 레이블 배치
        memPanel.setBackground(frame.getBackground()); // 패널 배경색 프레임과 동일하게 설정

        for (int i = 0; i < 6; i++) {
            memLabels[i] = new JLabel(memTexts[i], JLabel.CENTER);
            memLabels[i].setFont(new Font("Arial", Font.PLAIN, 12));
            memPanel.add(memLabels[i]);
        }
        frame.add(memPanel);

        // 연산 및 기능 버튼 생성
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("C");
        clrButton = new JButton("CE");
        negButton = new JButton("+/-");
        percButton = new JButton("%");
        sqrButton = new JButton("x²");
        sqrtButton = new JButton("<html><sup>2</sup>&#8730;x</html>");
        fracButton = new JButton("1/x");
        backButton = new JButton("←");

        // 기능 버튼 배열에 추가 (중복 없이)
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = percButton;
        functionButtons[10] = sqrButton;
        functionButtons[11] = sqrtButton;
        functionButtons[12] = fracButton;
        functionButtons[13] = backButton;

        // 모든 버튼에 폰트와 색상 설정 및 내부 여백 추가
        for (int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(font);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(Color.WHITE);
            functionButtons[i].setMargin(new Insets(5, 5, 5, 5));
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setMargin(new Insets(5, 5, 5, 5));
        }

        // 패널 설정 및 GridLayout 배치로 버튼을 꽉 채움
        panel = new JPanel();
        panel.setBounds(25, 150, 350, 480);
        panel.setLayout(new GridLayout(6, 4, 5, 8));

        // 버튼을 그리드 레이아웃에 추가하여 빈 공간 없이 채움
        panel.add(percButton);
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(backButton);

        panel.add(fracButton);
        panel.add(sqrButton);
        panel.add(sqrtButton);
        panel.add(divButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(negButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (isOperatorClicked) {
                    textField.setText("");  // 다음 숫자 입력 시에만 초기화
                    isOperatorClicked = false;
                }
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = e.getActionCommand().charAt(0);
            isOperatorClicked = true;
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '×':
                    result = num1 * num2;
                    break;
                case '÷':
                    result = num2 != 0 ? num1 / num2 : 0;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
            isOperatorClicked = true;
        }
        if (e.getSource() == delButton) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }
        if (e.getSource() == percButton) {
            num2 = Double.parseDouble(textField.getText());
            if (operator != '\0') {
                result = num1 * (num2 / 100); // 현재 연산자와 함께 퍼센트 계산
                textField.setText(String.valueOf(result));
                num1 = result;
            }
            isOperatorClicked = true;
        }
        if (e.getSource() == sqrButton) {
            double temp = Double.parseDouble(textField.getText());
            temp = Math.pow(temp, 2);
            textField.setText(String.valueOf(temp));
        }
        if (e.getSource() == sqrtButton) {
            double temp = Double.parseDouble(textField.getText());
            temp = Math.sqrt(temp);
            textField.setText(String.valueOf(temp));
        }
        if (e.getSource() == fracButton) {
            double temp = Double.parseDouble(textField.getText());
            temp = 1 / temp;
            textField.setText(String.valueOf(temp));
        }
    }
}
