# π TodoList-project π
μ²μνλ κ°λ° νλ‘μ νΈλ‘, JDBCμ ```CRUD``` νμ©μ μ΅μν΄μ§κΈ° μν΄ μ§νν νλ‘μ νΈ

### μ¬μ©λ κΈ°μ 
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
</div>
<img src="https://img.shields.io/badge/windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">

### νλ‘μ νΈ κΈ°κ°
μ μ²΄ κΈ°κ° : 2022-04-11 ~ 2022-04-29
|κΈ°κ°|μ§ν|
|-|-|
|2022-04-11 ~ 2022-04-15|νλ‘μ νΈ κ΅¬μ|
|2022-04-16 ~ 2022-04-20|λ©λ΄κ΅¬μ & DBμ€κ³|
|2022-04-21 ~ 2022-04-29|κΈ°λ₯ κ΅¬ν|
<br>

## π λ©λ΄ κ΅¬μ±
<img width="842" alt="αα³αα³αα΅α«αα£αΊ 2022-05-08 15 34 25" src="https://user-images.githubusercontent.com/88151484/167284861-2d2e927b-a297-4e81-a1d9-e9faf5bb6802.png">

- λ‘κ·ΈμΈ νμ΄μ§μμ ```νμκ°μ```κ³Ό λ‘κ·ΈμΈ μ±κ³΅ μ νμ΄μ§λ‘ ```λ©μΈνλ©΄```μΌλ‘ λμ΄κ° μ μκ² κ΅¬ν
- λ©μΈνλ©΄μμλ ```CardLayout```μ μ¬μ©νμ¬ ```μΊλ¦°λ``` ```μ€λ ν  μΌ``` ```λͺ¨λ  ν  μΌ``` ```νλ‘μ νΈ``` ν­μΌλ‘ μ΄ 4κ°μ ν­ κ΅¬ν
- μμ±ν ν  μΌ, νλ‘μ νΈλ λ€λ₯Έ μ¬μ©μμκ² κ³΅μ ν  μ μλλ‘ κ΅¬ν (μμ X, ν  μΌ μλ£ λ° κ³΅μ λ μμ μκ²λ§ μ­μ ν  μ μλ κΆν λΆμ¬)
<br>

## π½ DB μ€κ³
<div>
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167290357-b74ef54c-d6d9-4353-bc45-32e6d134cb3b.png">
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167290359-109771ad-6eb8-401f-9667-25bfe12e5e17.png">
</div>

νμ΄λΈ λΆλΆμ <a href="https://docs.google.com/presentation/d/1W-vd92Hp-BsFgzraK4iwAI2g-tZcWvAVcTg9GBg3WU8/edit?usp=sharing" target="_blank">λ§ν¬</a> μ°Έμ‘°

<br>

## π¨ νλ‘κ·Έλ¨ GUI
### λ‘κ·ΈμΈ/νμκ°μ
<div>
<img width="400px" src="https://user-images.githubusercontent.com/88151484/167284322-4d061638-d17e-47a2-94a0-f86a65c79c89.png">
<img width="300px" src="https://user-images.githubusercontent.com/88151484/167284327-436e35c1-078b-4f3c-bbf3-fd692b22155b.png">
</div>
<br>

- λ‘κ·ΈμΈ νλ©΄μμ ID, PWD μλ ₯ ν ```Sign up``` ν΄λ¦­ μ, Fieldμ μλ ₯ν κ°λ€μ΄ νμκ°μ ν­μ μ λ¬λλλ‘ κ΅¬ν
```java
//LoginGUI===========
    //Button ν΄λ¦­ μ EventHandler
    private class ButtonEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == btnLogin) {
                    System.out.println("λ‘κ·ΈμΈ λ²νΌ ν΄λ¦­");
                    login();
                } else if (e.getSource() == btnJoin) {  //μ΄λ²€νΈ λ°μ μμ€κ° btnJoin κ°μ²΄μΌ κ²½μ°
                    System.out.println("νμκ°μ λ²νΌ ν΄λ¦­");
                    join(); //λ―Έλ¦¬ μ μν join() νΈμΆ
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //join()
    private void join() {
        String userId = tfUserId.getText();
        String pwd = tfPwd.getText();

        JoinGUI f = new JoinGUI(userId, pwd, this); //JoinGUIμκ² μμ±μλ‘ userId, pwdμ κ°κ³Ό, μμ μ κ°μ²΄ μ£Όμλ₯Ό λκΈ΄λ€.
                                                    //μ¬μ€ μκ°ν΄λ³΄λκΉ κ°μ²΄ μ£Όμλ§ λκ²¨λ λμ κ±° κ°λ€.
        f.setVisible(true);
    }
``` 
```java
//JoinGUI===========
  public class JoinGUI extends javax.swing.JFrame {
    private JobDAO jDao;
    private UserListDAO uDao;
    private LoginGUI loginGUI;

    /**
     * Creates new form JoinGUI
     */
    public JoinGUI() {
        initComponents();

        init();
        addEvent();
    }

    public JoinGUI(String userId, String pwd) {
        this(); //μμ μ μμ±μ JoinGUI()μ νΈμΆ

        tfUserId.setText(userId);
        tfPwd.setText(pwd);
    }

    public JoinGUI(String userId, String pwd, LoginGUI loginGUI) {
        this(userId, pwd);  //μμ μ μμ±μ JoinGUI(String userId, String pwd)μ νΈμΆ
        this.loginGUI = loginGUI; //λκ²¨λ°μ κ°μ²΄ μ£Όμλ₯Ό μ μ₯
    }
    
    ...
    ...
  }
```
<br>

- νμκ°μ μλ£ ν, νμκ°μ μ μλ ₯νμλ ID, PWDλ₯Ό λ‘κ·ΈμΈνλ©΄μ λ΄μμ€λ€.   
```java
//JoinGUI===========
  public class JoinGUI extends javax.swing.JFrame {
    //μμ΄λ μμ± λ©μλ
    private void createUser() throws SQLException {
        ...
        ...
        ...
        
        
        if(cnt > 0) {
            JOptionPane.showMessageDialog(this, "κ³μ λ±λ‘ μ±κ³΅");
            loginGUI.tfUserId.setText(userId);  //μμ±μλ‘ λ°μμ¨ λ‘κ·ΈμΈνλ©΄μ κ°μ²΄μ λ³΄μ λ±λ‘ν μμ΄λλ₯Ό λκ²¨μ€λ€
            loginGUI.tfPwd.setText(pwd);
            
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(this, "κ³μ λ±λ‘ μ€ν¨");
        }
    }
  }
```
<br>

### λ©μΈνλ©΄
#### μΊλ¦°λ
<img width="600px" src="https://user-images.githubusercontent.com/88151484/167284320-4eb132d6-ecb8-43f0-b93d-539d160bcc2c.png">

- μΊλ¦°λ κ΅¬ν
```java
public class MainGUI2 extends javax.swing.JFrame {
    private int[][] dayArr;
    private JLabel[][] dayList;
    private JPanel[][] dayPanel;

    private Date currentDate; //μΊλ¦°λ κ΄λ ¨ λ μ§, μ΅μ΄  λ° μΊλ¦°λ μλ¨ λ²νΌ ν΄λ¦­ μ μ΄κΈ°ν

    //μΊλ¦°λ μ΄κΈ°ν λ©μλ
    private void calenderStart() {
        //μΊλ¦°λμ λΉκ³΅κ°μ -1λ‘ κ΅¬λΆ
        dayArr = new int[][]{
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1}
        };

        //μΌμκ° μ§μ μ μΌλ‘ μ°μ΄λ Label
        dayList = new JLabel[][]{
                {lbC1, lbC2, lbC3, lbC4, lbC5, lbC6, lbC7},
                {lbC8, lbC9, lbC10, lbC11, lbC12, lbC13, lbC14},
                {lbC15, lbC16, lbC17, lbC18, lbC19, lbC20, lbC21},
                {lbC22, lbC23, lbC24, lbC25, lbC26, lbC27, lbC28},
                {lbC29, lbC30, lbC31, lbC32, lbC33, lbC34, lbC35},
                {lbC36, lbC37, lbC38, lbC39, lbC40, lbC41, lbC42}
        };

        //μΊλ¦°λPanel μ 'μΌμ Panel'λ₯Ό κ΄λ¦¬νκΈ° νΈνλλ‘ λ€μ°¨μ λ°°μ΄μ λ΄λλ€
        dayPanel = new JPanel[][]{
                {plC1, plC2, plC3, plC4, plC5, plC6, plC7},
                {plC8, plC9, plC10, plC11, plC12, plC13, plC14},
                {plC15, plC16, plC17, plC18, plC19, plC20, plC21},
                {plC22, plC23, plC24, plC25, plC26, plC27, plC28},
                {plC29, plC30, plC31, plC32, plC33, plC34, plC35},
                {plC36, plC37, plC38, plC39, plC40, plC41, plC42}
        };
    }
    
    
    
    //λ§€κ°λ³μμ λ μ§κ°μ²΄λ₯Ό κΈ°μ€μΌλ‘ μΊλ¦°λλ₯Ό μλ°μ΄νΈνλ€.
    //μ²μ νλ©΄μ΄ Loadλ  λ
    //μΊλ¦°λ μλ¨ λ²νΌ ν΄λ¦­ μ
    private void dateRelease(Date date) {
        date.setDate(1);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        String month;
        String year;
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  //μμ λ§μ§λ§ λ 


        //μΊλ¦°λ μ΄κΈ°ν
        //μΊλ¦°λ μ΄κΈ°ν
        //μΊλ¦°λ μ΄κΈ°ν
        for (JLabel[] jLabels : dayList) for (JLabel jLabel : jLabels) jLabel.setText("");

        //μλ¨ λλ, μ μλ°μ΄νΈ
        //μλ¨ λλ, μ μλ°μ΄νΈ
        //μλ¨ λλ, μ μλ°μ΄νΈ
        year = String.valueOf(date.getYear()+1900);
        month = String.valueOf(date.getMonth()+1);

        if(month.length() == 1) month = "0" + month;

        lbYear.setText(year);
        lbMonth.setText(" " + month + " ");


        //μΊλ¦°λ μλ°μ΄νΈ
        //μΊλ¦°λ μλ°μ΄νΈ
        //μΊλ¦°λ μλ°μ΄νΈ
        int day = 1;

        for (int[] ints : dayArr) {
            Arrays.fill(ints, -1);
        }

        calendarLoop :
        for(int i = 0; i < dayList.length; i++) {
            for(int j = 0; j < dayList[i].length; j++) {
                if(i == 0 && date.getDay() > j) continue;

                dayList[i][j].setText(String.valueOf(day));
                dayArr[i][j] = day;
                day++;
                if(day > lastDay) break calendarLoop;
            }
        }
        for(int i = 0; i < dayList.length; i++) {
            for(int j = 0; j < dayList[i].length; j++) {
                System.out.print(dayArr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
```
<br>

- μΊλ¦°λ μλ¨ λ²νΌ ν΄λ¦­ μ μΊλ¦°λ λ³κ²½
```java
public class MainGUI2 extends javax.swing.JFrame {
    ...
    ...
    ...
    private void addEvent() {
        //μΊλ¦°λ λ²νΌ (ν΄λ¦­ μ λ―Έλ¦¬ μ μν DateReleaseHandlerλΌλ μ΄λ²€νΈνΈλ€λ¬ κ°μ²΄ μμ±)
        btnFirst.addActionListener(new DateReleaseHandler());
        btnPrev.addActionListener(new DateReleaseHandler());
        btnNext.addActionListener(new DateReleaseHandler());
        btnLast.addActionListener(new DateReleaseHandler());
    }
    
    class DateReleaseHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(currentDate); //λ²νΌμ μν΄ λ³κ²½λκΈ° μ΄μ μ μΊλ¦°λμ κ°μ κ°μ Έμ¨λ€.

            if(e.getSource() == btnFirst) {
                cal.add(Calendar.MONTH, -12);
            }else if(e.getSource() == btnPrev) {
                cal.add(Calendar.MONTH, -1);
            }else if(e.getSource() == btnNext) {
                cal.add(Calendar.MONTH, 1);
            }else if(e.getSource() == btnLast) {
                cal.add(Calendar.MONTH, 12);
            }

            currentDate = cal.getTime();  //λ²νΌμ μν΄ λ³νλ μΊλ¦°λμ κ°μ μΈμ€ν΄μ€ λ³μμ λ€μ λ΄μμ€λ€.
            dateRelease(currentDate); //ν΄λΉ λ©μλμ μν΄ λ§€κ°λ³μμ λ μ§μ λ³΄λ₯Ό κΈ°μ€μΌλ‘ μΊλ¦°λκ° μ΄κΈ°νκ° λλ€.
        }
    }
}
```
<br>

- μΊλ¦°λ μΌμ ν΄λ¦­ μ ν΄λΉ λ μ§ κ° κ°μ Έμ€κΈ°
```java
public class MainGUI2 extends javax.swing.JFrame {
    ...
    ...
    ...
    
    private void addEvent() {
        //μΊλ¦°λ μΌμ ν΄λ¦­ μ΄λ²€νΈ
        for(int i = 0; i < dayPanel.length; i++) {       
            for(int j = 0; j < dayPanel[i].length; j++) {
                int row = i;  //μΊλ¦°λμ ν : i
                int col = j;  //μΊλ¦°λμ μ΄ : j
                dayPanel[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                        int[] cool = {pointerInfo.getLocation().x, pointerInfo.getLocation().y};
                        if(e.getClickCount() > 1) {
                          calenderClick(row, col, cool);  //μ΄λ²€νΈ λ°μ μ, calenderClick() νΈμΆκ³Ό λ§€κ°λ³μλ‘ ν΄λ¦­ν ν, μ΄μ λ³΄λ΄μ€
                        }
                    }
                });
            }
        }
        
        ...
        ...
        ...
    }
    
    ...
    ...
    ...
    
    private void calenderClick(int i, int j, int[] loc) {
        int day = dayArr[i][j]; //ν΄λ¦­ν ν, μ΄μ κ°μ λ€μ°¨μ λ°°μ΄μμ μ°ΎμλΈλ€.
        System.out.println("ν΄λ¦­ν μΌμ : " + day);
        if(day == -1) return; //μ°ΎμλΈ λ°°μ΄μ κ°μ΄ '-1'μΌ κ²½μ° λΉκ³΅κ°μ΄λ―λ‘ return
        
        ...
        ...
        ...
    }
}
```
<br>

#### ν  μΌ
<div>
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167284346-ce5699d4-c939-4c69-bbae-28e76a60ab1d.png">
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167299346-cfd9dd7d-f5b4-4c61-9c5c-dca3f26941b9.png">
</div>
<img width="60%" src="https://user-images.githubusercontent.com/88151484/167300836-af0b4350-5946-495b-a360-0d90e403c73e.png">
<br>

- Fieldμ μλ ₯ ν `+` λ²νΌμ ν΄λ¦­νλ©΄ ν  μΌ λ¦¬μ€νΈμ μΆκ°
- μ€λμ ν  μΌ, λͺ¨λ  ν  μΌλ‘ κ΅¬λΆ
- ν  μΌ λ¦¬μ€νΈ ```λ€λ₯Έ μ¬μ©μ```μκ² κ³΅μ  κ°λ₯
