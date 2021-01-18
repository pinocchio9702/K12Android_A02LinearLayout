package com.kosmo.a01helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //전화번호 입력상자를 전역적으로 사용하기 위해 선언
    private EditText editText;


    /*
    Java에서의 출발점은 main()메소드였듯이, 안드로이드의 출발점은
    onCreate()메소드이다. 수명주기(Lifecycle) 메소드 중에서 첫번째로 실행된다.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        xml로 생성된 레이아웃을 엑티비티에 전개(Inflate)하는 메소드로
        해당 메소드가 실행되지 않으면 아무것도 보이지 않게된다.
         */
        setContentView(R.layout.activity_main);

        /*
        findViewById(res에 설정된 id)
            : xml에 설정된 id값을 통해 버튼 객체를 Java클래스로
            가져와서 사용한다.
         */
        Button btnNate = (Button) findViewById(R.id.btnNate);
        //리스너 부착방법1 : 버튼객체에 직접 리스너 인터페이스를 사용한다.
        btnNate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                버튼을 눌렀을때 토스트 메세지를 띄어준다. 토스트는 JS의
                alert()와 비슷하게 메세지를 일정시간 보여준다.
                형식 ] Toast.makeText(메세지를 띄울 컨텍스트(뷰),
                                    메세지 내용,
                                    시간(Long or Short)).show();
                 */
                Toast.makeText(v.getContext(),
                        "잠시후 네이트로 이동합니다.",
                        Toast.LENGTH_LONG).show();
                /*
                인텐트를 통해 새로운 액티비티를 띄어준다. 웹 URL이
                전달되므로 외장 웹브라우저를 통해 네이트를 접속하게 된다.
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://nate.com"));
                startActivity(intent);
            }
        });//////btnNate끝

        //버튼객체 가져온후 리스너 부착하기
        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        //리스너 부착방법2 : 외부에 선언된 리스너 객체를 사용한다.
        btnCall.setOnClickListener(listener);
        btnNext.setOnClickListener(listener);

        //전화번호 입력상자 가져오기
        editText = (EditText) findViewById(R.id.editText);


    }///onCreat() 끝

    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //버튼을 누룰때 전달되는 View객체를 통해 눌러진 버튼을 알아낼수 있다.
            if(v.getId() == R.id.btnCall){
                //editText(입력상자)에 입력된 내용을 가져와서 문자열로 변경
                String callNumber = editText.getText().toString();
                //인텐트를 통해 전달하면 전화걸기 앱이 실행된다. "tel:"으로 전화번호인걸 인식한다.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+callNumber));
                startActivity(intent);
            }else{
                //화면전환 버튼을 눌렀을때
                Intent intent = new Intent(v.getContext(),
                        SubActivity.class);
                //SubActivity를 띄어준다.
                startActivity(intent);
            }

        }
    };//////view.OnclickListenr 끝

    /*
    리스너 부착방법 4 : 버튼의 onclick속성을 이용한다. 이때 호출할
        메소드명을 기술하면 아래 함수를 즉시 호출할 수 잇다.
     */
    public void myBtnClick(View view){
        Toast.makeText(view.getContext(),
                "onClick속성을 이용한 버튼입니다. 저는 짧아요",
                Toast.LENGTH_SHORT).show();
    }//myBtnClick끝
}//mainActivity끝