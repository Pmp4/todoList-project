# 📆 TodoList-project 📆
처음하는 개발 프로젝트로, JDBC의 ```CRUD``` 활용에 익숙해지기 위해 진행한 프로젝트

### 사용된 기술
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
</div>
<img src="https://img.shields.io/badge/windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">

### 프로젝트 기간
전체 기간 : 2022-04-11 ~ 2022-04-29
|기간|진행|
|-|-|
|2022-04-11 ~ 2022-04-15|프로젝트 구상|
|2022-04-16 ~ 2022-04-20|메뉴구상 & DB설계|
|2022-04-21 ~ 2022-04-29|기능 구현|
<br>

## 📖 메뉴 구성
<img width="842" alt="스크린샷 2022-05-08 15 34 25" src="https://user-images.githubusercontent.com/88151484/167284861-2d2e927b-a297-4e81-a1d9-e9faf5bb6802.png">

- 로그인 페이지에서 ```회원가입```과 로그인 성공 시 페이지로 ```메인화면```으로 넘어갈 수 있게 구현
- 메인화면에서는 ```CardLayout```을 사용하여 ```캘린더``` ```오늘 할 일``` ```모든 할 일``` ```프로젝트``` 탭으로 총 4개의 탭 구현
- 생성한 할 일, 프로젝트는 다른 사용자에게 공유할 수 있도록 구현 (수정X, 할 일 완료 및 공유된 자신에게만 삭제할 수 있는 권한 부여)
<br>

## 💽 DB 설계
<div>
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167290357-b74ef54c-d6d9-4353-bc45-32e6d134cb3b.png">
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167290359-109771ad-6eb8-401f-9667-25bfe12e5e17.png">
</div>

테이블 부분은 <a href="https://docs.google.com/presentation/d/1W-vd92Hp-BsFgzraK4iwAI2g-tZcWvAVcTg9GBg3WU8/edit?usp=sharing" target="_blank">링크</a> 참조

## 🎨 프로그램 GUI
### 로그인/회원가입
<div>
<img width="400px" src="https://user-images.githubusercontent.com/88151484/167284322-4d061638-d17e-47a2-94a0-f86a65c79c89.png">
<img width="300px" src="https://user-images.githubusercontent.com/88151484/167284327-436e35c1-078b-4f3c-bbf3-fd692b22155b.png">
</div>
<br>

- 로그인 화면에서 ID, PWD 입력 후 ```Sign up``` 클릭 시, Field에 입력한 값들이 회원가입 탭에 전달되도록 구현
```java
//LoginGUI===========
    //Button 클릭 시 EventHandler
    private class ButtonEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == btnLogin) {
                    System.out.println("로그인 버튼 클릭");
                    login();
                } else if (e.getSource() == btnJoin) {  //이벤트 발생 소스가 btnJoin 객체일 경우
                    System.out.println("회원가입 버튼 클릭");
                    join(); //미리 정의한 join() 호출
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

        JoinGUI f = new JoinGUI(userId, pwd, this); //JoinGUI에게 생성자로 userId, pwd의 값과, 자신의 객체 주소를 넘긴다.
                                                    //사실 생각해보니깐 객체 주소만 넘겨도 됐을 거 같다.
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
        this(); //자신의 생성자 JoinGUI()을 호출

        tfUserId.setText(userId);
        tfPwd.setText(pwd);
    }

    public JoinGUI(String userId, String pwd, LoginGUI loginGUI) {
        this(userId, pwd);  //자신의 생성자 JoinGUI(String userId, String pwd)을 호출
        this.loginGUI = loginGUI; //넘겨받은 객체 주소를 저장
    }
    
    ...
    ...
  }
```
<br>

- 회원가입 완료 후, 회원가입 시 입력하였던 ID, PWD를 로그인화면에 담아준다.   
```java
//JoinGUI===========
  public class JoinGUI extends javax.swing.JFrame {
    //아이디 생성 메소드
    private void createUser() throws SQLException {
        ...
        ...
        ...
        
        
        if(cnt > 0) {
            JOptionPane.showMessageDialog(this, "계정등록 성공");
            loginGUI.tfUserId.setText(userId);  //생성자로 받아온 로그인화면의 객체정보에 등록한 아이디를 넘겨준다
            loginGUI.tfPwd.setText(pwd);
            
            this.dispose();
        }else {
            JOptionPane.showMessageDialog(this, "계정등록 실패");
        }
    }
  }
```
<br>

### 메인화면
#### 캘린더
<img width="600px" src="https://user-images.githubusercontent.com/88151484/167284320-4eb132d6-ecb8-43f0-b93d-539d160bcc2c.png">

- 캘린더 구현
```java
public class MainGUI2 extends javax.swing.JFrame {
    private int[][] dayArr;
    private JLabel[][] dayList;
    private JPanel[][] dayPanel;

    private Date currentDate; //캘린더 관련 날짜, 최초  및 캘린더 상단 버튼 클릭 시 초기화

    //캘린더 초기화 메서드
    private void calenderStart() {
        //캘린더의 빈공간은 -1로 구분
        dayArr = new int[][]{
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1}
        };

        //일자가 직접적으로 쓰이는 Label
        dayList = new JLabel[][]{
                {lbC1, lbC2, lbC3, lbC4, lbC5, lbC6, lbC7},
                {lbC8, lbC9, lbC10, lbC11, lbC12, lbC13, lbC14},
                {lbC15, lbC16, lbC17, lbC18, lbC19, lbC20, lbC21},
                {lbC22, lbC23, lbC24, lbC25, lbC26, lbC27, lbC28},
                {lbC29, lbC30, lbC31, lbC32, lbC33, lbC34, lbC35},
                {lbC36, lbC37, lbC38, lbC39, lbC40, lbC41, lbC42}
        };

        //캘린더Panel 속 '일자 Panel'를 관리하기 편하도록 다차원 배열에 담는다
        dayPanel = new JPanel[][]{
                {plC1, plC2, plC3, plC4, plC5, plC6, plC7},
                {plC8, plC9, plC10, plC11, plC12, plC13, plC14},
                {plC15, plC16, plC17, plC18, plC19, plC20, plC21},
                {plC22, plC23, plC24, plC25, plC26, plC27, plC28},
                {plC29, plC30, plC31, plC32, plC33, plC34, plC35},
                {plC36, plC37, plC38, plC39, plC40, plC41, plC42}
        };
    }
    
    
    
    //매개변수의 날짜객체를 기준으로 캘린더를 업데이트한다.
    //처음 화면이 Load될 때
    //캘린더 상단 버튼 클릭 시
    private void dateRelease(Date date) {
        date.setDate(1);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        String month;
        String year;
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  //월의 마지막 날


        //캘린더 초기화
        //캘린더 초기화
        //캘린더 초기화
        for (JLabel[] jLabels : dayList) for (JLabel jLabel : jLabels) jLabel.setText("");

        //상단 년도, 월 업데이트
        //상단 년도, 월 업데이트
        //상단 년도, 월 업데이트
        year = String.valueOf(date.getYear()+1900);
        month = String.valueOf(date.getMonth()+1);

        if(month.length() == 1) month = "0" + month;

        lbYear.setText(year);
        lbMonth.setText(" " + month + " ");


        //캘린더 업데이트
        //캘린더 업데이트
        //캘린더 업데이트
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

- 캘린더 상단 버튼 클릭 시 캘린더 변경
```java
public class MainGUI2 extends javax.swing.JFrame {
    ...
    ...
    ...
    private void addEvent() {
        //캘린더 버튼 (클릭 시 미리 정의한 DateReleaseHandler라는 이벤트핸들러 객체 생성)
        btnFirst.addActionListener(new DateReleaseHandler());
        btnPrev.addActionListener(new DateReleaseHandler());
        btnNext.addActionListener(new DateReleaseHandler());
        btnLast.addActionListener(new DateReleaseHandler());
    }
    
    class DateReleaseHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(currentDate); //버튼의 의해 변경되기 이전의 캘린더의 값을 가져온다.

            if(e.getSource() == btnFirst) {
                cal.add(Calendar.MONTH, -12);
            }else if(e.getSource() == btnPrev) {
                cal.add(Calendar.MONTH, -1);
            }else if(e.getSource() == btnNext) {
                cal.add(Calendar.MONTH, 1);
            }else if(e.getSource() == btnLast) {
                cal.add(Calendar.MONTH, 12);
            }

            currentDate = cal.getTime();  //버튼의 의해 변화된 캘린더의 값을 인스턴스 변수에 다시 담아준다.
            dateRelease(currentDate); //해당 메서드의 의해 매개변수의 날짜정보를 기준으로 캘린더가 초기화가 된다.
        }
    }
}
```
<br>

- 캘린더 일자 클릭 시 해당 날짜 값 가져오기
```java
public class MainGUI2 extends javax.swing.JFrame {
    ...
    ...
    ...
    
    private void addEvent() {
        //캘린더 일자 클릭 이벤트
        for(int i = 0; i < dayPanel.length; i++) {       
            for(int j = 0; j < dayPanel[i].length; j++) {
                int row = i;  //캘린더의 행 : i
                int col = j;  //캘린더의 열 : j
                dayPanel[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
                        int[] cool = {pointerInfo.getLocation().x, pointerInfo.getLocation().y};
                        if(e.getClickCount() > 1) {
                          calenderClick(row, col, cool);  //이벤트 발생 시, calenderClick() 호출과 매개변수로 클릭한 행, 열을 보내줌
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
        int day = dayArr[i][j]; //클릭한 행, 열의 값을 다차원 배열에서 찾아낸다.
        System.out.println("클릭한 일자 : " + day);
        if(day == -1) return; //찾아낸 배열의 값이 '-1'일 경우 빈공간이므로 return
        
        ...
        ...
        ...
    }
}
```
<br>

#### 할 일
<div>
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167284346-ce5699d4-c939-4c69-bbae-28e76a60ab1d.png">
  <img width="49%" src="https://user-images.githubusercontent.com/88151484/167299346-cfd9dd7d-f5b4-4c61-9c5c-dca3f26941b9.png">
</div>
<img width="60%" src="https://user-images.githubusercontent.com/88151484/167300836-af0b4350-5946-495b-a360-0d90e403c73e.png">
<br>

- Field에 입력 후 `+` 버튼을 클릭하면 할 일 리스트에 추가
- 오늘의 할 일, 모든 할 일로 구분
- 할 일 리스트 ```다른 사용자```에게 공유 가능
