package com.kosmo.a08datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //앱 전체에서 사용하기 위해 멤버변수 생성
    Calendar calendar; //캘린더클래스(현재시간, 현재날짜 얻기 위해 생성)
    TextView date_tv, time_tv; //텍스트뷰 객체(시간, 날짜표시)
    int yearStr, monthStr, dayStr; //날짜
    int hourStr, minuteStr, secondStr; //시간

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //텍스트뷰 위젯 가져오기
        date_tv = findViewById(R.id.date_tv);
        time_tv = findViewById(R.id.time_tv);

        //캘린더 클래스를 통해 날짜와 시간을 구한다.
        calendar = Calendar.getInstance();
        //날짜
        yearStr = calendar.get(calendar.YEAR);
        monthStr = calendar.get(calendar.MONTH);
        dayStr = calendar.get(calendar.DATE);
        //시간
        hourStr = calendar.get(calendar.HOUR_OF_DAY);
        minuteStr = calendar.get(calendar.MINUTE);
        secondStr = calendar.get(calendar.SECOND);

        //현재날짜와 시간을 텍스트뷰에 설정
        date_tv.setText(yearStr+"년"+(monthStr+1)+"월"+dayStr+"일");
        time_tv.setText(hourStr+"시"+minuteStr+"분"+secondStr+"초");

        //날짜버튼을 눌렀을때 데이트피커를 띄워준다.
        Button btn_datepicker = findViewById(R.id.btn_datepicker);
        btn_datepicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //데이트 피커 대화창 객체 생성
                    /*
                    new DatePickerDialog(컨텍스트, 리스너, 년, 월, 일)
                    위 생성자를 통해 객체를 생성한 후 show()를 통해 화면에 출력한다.
                     */
                    DatePickerDialog dialog = new DatePickerDialog(
                            v.getContext(), listener, yearStr, monthStr, dayStr
                    );
                    //show()메서드를 통해 화면에 띄워준다.
                    dialog.show(); //없으면 데이트 피커 안뜸 (dialog는 무조건 show()써야함)

                }
            });

        //시간 버튼을 눌렀을때 타임피커를 띄워준다.
        Button btn_timepicker = findViewById(R.id.btn_timepicker);
        btn_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    /*
                    new TimePickerDialog(컨텍스트, 리스너, 시, 분, 24시간포맷);
                    ※ 24시간 포맷은 : true(24시간제로 표시), false(12시간제로 표시)
                     */
                new TimePickerDialog(
                        v.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //선택한 시간을 텍스트뷰에 표시한다.
                                time_tv.setText(
                                        String.format("설정된시간%n%d시 %d분", hourOfDay, minute));
                                //토스트로 띄워준다.
                                Toast.makeText(getApplicationContext(),
                                        String.format("설정된시간%n%d시 %d분", hourOfDay, minute),
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        hourStr,
                        minuteStr,
                        true
                ).show();
            }
        });
    }////end of onCreate();

    //데이트피커 리스너 선언(onCreate() 외부에 별도로 정의)
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //선택한 날짜를 텍스트뷰에 입력한다.
            date_tv.setText(
                String.format("설정된날짜%n%d년 %d월 %d일", year, (month+1), dayOfMonth));
                /*
                Calendar객체를 통해 얻어온 날짜중 월은 0~11까지를 반환하므로
                +1을 해줘야한다.
                 */
                //위의 내용을 토스트로 띄워준다.
                Toast.makeText(getApplicationContext(),
                        year+"년"+(month+1)+"월"+dayOfMonth+"일",
                        Toast.LENGTH_LONG).show();
        }
    };////end of listener
}////end of MainActivity