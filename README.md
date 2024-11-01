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

/**
 * 간단한 계산기 애플리케이션을 구현한 클래스입니다. 
 * 기본 사칙연산과 퍼센트, 제곱, 제곱근, 역수 등의 기능을 제공합니다. 
 * GUI는 Swing을 사용하여 구현되며, 버튼 클릭 이벤트를 통해 연산을 수행합니다.
 * 
 * <p>Calculator 클래스는 ActionListener를 구현하여 버튼 클릭 시 동작을 처리합니다.
 * 메모리 기능, 다양한 연산 기능 등을 포함하며, 각 버튼의 레이블과
 * 초기화는 클래스 생성자에서 설정됩니다.
 * </p>
 * 
 * <p> 변경 내역:
 * <ul>
 *   <li>10/23: 계산기 UI 생성</li>
 *   <li>10/24: 패널에 여백 최소화</li>
 *   <li>10/26: 버튼 개수, 사이즈 수정</li>
 *   <li>10/29: 버튼 영역 증가</li>
 *   <li>10/30: 제곱, 제곱근 추가</li>
 *   <li>10/31: 계산 시스템 추가</li>
 *   <li>11/01: 연산자 오류 수정</li>
 * </ul>
 * </p>
 * 
 * @author Your Name
 * @version 1.0
 * @since 1.0
 * 
 * @created 2024-11-01
 */
public class Calculator implements ActionListener {

    /** 메인 애플리케이션 프레임입니다. 계산기의 창을 나타냅니다. */
    JFrame frame;

    /** 계산식과 결과가 표시되는 텍스트 필드입니다. */
    JTextField textField;

    /** 메모리 기능(MC, MR, M+, M-, MS, Mv) 버튼을 위한 레이블 배열입니다. */
    JLabel[] memLabels = new JLabel[6];
    
    /** 숫자 버튼 (0-9)을 담은 배열입니다. */
    JButton[] numberButtons = new JButton[10];

    /** 기능 버튼 (+, -, *, / 등)을 담은 배열입니다. */
    JButton[] functionButtons = new JButton[14];

    // 연산자 및 기능 버튼
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JButton percButton, sqrButton, sqrtButton, fracButton, backButton;

    /** 숫자 및 기능 버튼을 배치하는 패널입니다. */
    JPanel panel, memPanel;

    /** 버튼과 텍스트 필드의 폰트 설정입니다. */
    Font font = new Font("Arial", Font.PLAIN, 28);

    /** 첫 번째 연산 피연산자입니다. */
    double num1 = 0;

    /** 두 번째 연산 피연산자입니다. */
    double num2 = 0;

    /** 연산 결과를 저장하는 변수입니다. */
    double result = 0;

    /** 선택된 연산자를 저장하는 변수입니다. */
    char operator;

    /** 연산자가 클릭되었는지 여부를 저장하는 플래그입니다. */
    boolean isOperatorClicked = false;

    /**
     * Calculator 클래스의 생성자입니다. 
     * 프레임, 텍스트 필드 및 버튼을 초기화하고 GUI 레이아웃을 설정합니다.
     * 각 버튼에는 ActionListener가 추가되어 버튼 클릭 시 actionPerformed 메서드가 호출됩니다.
     * 
     * <p>프레임 크기, 버튼 레이아웃, 메모리 기능 표시 패널 등의 GUI 요소가 설정됩니다.
     * 메모리 버튼, 숫자 버튼, 기능 버튼 등이 추가되며, 이벤트 리스너를 통해 각 버튼의 기능이 연결됩니다.
     * </p>
     * 
     * @created 2024-11-01
     */
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
        memPanel.setLayout(new GridLayout(1, 6));
        memPanel.setBackground(frame.getBackground());

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

    /**
     * 계산기 애플리케이션의 시작점입니다. Calculator 객체를 생성하여 GUI를 실행합니다.
     * 
     * @param args 커맨드 라인 인수 (사용되지 않음)
     * 
     * @created 2024-11-01
     */
    public static void main(String[] args) {
        new Calculator();
    }

    /**
     * 버튼 클릭 시의 액션을 처리하는 메서드입니다. 숫자, 연산자, 기능 버튼 등
     * 각 버튼의 기능에 맞는 동작을 수행하여 연산 및 결과 표시를 처리합니다.
     * 
     * <p>버튼의 종류에 따라 다른 동작을 수행합니다. 숫자 버튼 클릭 시
     * 입력 값을 텍스트 필드에 추가하고, 연산자 클릭 시 num1에 값을 저장하고 
     * 연산자를 설정합니다. </p>
     * 
     * <p>기능 버튼은 다음과 같은 동작을 수행합니다:
     * <ul>
     *   <li>소수점 추가 (decButton)</li>
     *   <li>음수/양수 변환 (negButton)</li>
     *   <li>백분율 계산 (percButton)</li>
     *   <li>제곱 계산 (sqrButton)</li>
     *   <li>제곱근 계산 (sqrtButton)</li>
     *   <li>역수 계산 (fracButton)</li>
     *   <li>계산 초기화 및 삭제 (clrButton, delButton, backButton)</li>
     * </ul>
     * </p>
     * 
     * @param e 발생한 액션 이벤트
     * 
     * @created 2024-11-01
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 구현 코드
    }
}
