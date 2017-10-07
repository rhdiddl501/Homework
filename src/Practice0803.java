/*
 * Copyright 2017 jihye hwang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Created by jihye on 2017-10-04
 * Site       : https://www.acmicpc.net/problem/0803
 */

import java.util.*;
class Synk{
    private int balance; //총액 변수

    Synk(){ //생성자
        balance = 1000000; //총액 = 100만원
    }

    int GetBalance (){ //총액 반환 메소드
        return balance;
    }

    synchronized void withdraw(){ //임계영역인 총액 메소드

        if(GetBalance() < 9800) { //잔액이 9800원 미만일 때
            System.out.println("잔액 부족, 현재 잔액 : " + GetBalance() + ", 인출 금액 : 9800");
            System.out.println("총액은 : " + GetBalance());
            System.exit(0); //시스템 종료
        }

        balance -= 9800; //잔액에서 9800원이 인출됨.
        System.out.println("9800원을 인출, 남은 잔액 : " + GetBalance()); //알림문구 출력
    }

}

class thread extends Thread { //쓰레드 메소드
    Synk money; //객체생성

    thread(Synk st){
        this.money = st;
    }

    public void run() { //본격적인 출금 쓰레드 메소드
        while (true) {

            money.withdraw();
        }
    }
}

public class Practice0803 {
    public static void main(String[] args) {
        Synk st = new Synk();
        thread t1 = new thread(st); //첫 번째 스레드
        thread t2 = new thread(st); //두 번째 스레드
        thread t3 = new thread(st); //세 번째 스레드
        thread t4 = new thread(st); //네 번째 스레드

        t1.start();//스레드 시작
        t2.start();
        t3.start();
        t4.start();
    }
}