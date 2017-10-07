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
 * Site       : https://www.acmicpc.net/problem/970
 */

class Dollar{
    int balance; //잔액

    Dollar(){
        balance = 100000; //10만원으로 초기화
    }

    int GetBalance() { //잔액 반환
        return balance;
    }

    synchronized void WithDraw1(String name, int money) { //name = 쓰레드이름, money = 저출 금액

        balance -= money; //잔액에서 money 만큼 출금한다.
        System.out.println(name + ", 출금 = " + money + ", 통장 잔액 = " + balance); //알림
        notify();//대기상태인 쓰레드를 준비시킨다.
        try {
            wait();//현 쓰레드를 대기상태로 만든다.
        }catch(Exception e) {}

    }
}

class thread1 extends Thread{
    Dollar dl; //Dollar 객체 생성
    int money; //출금 금액 저장 변수
    thread1(String name, Dollar DL, int money){
        super(name);
        this.dl = DL;
        this.money = money;
    }
    public void run() {
        while(true){
            dl.WithDraw1(getName(), money);//쓰레드 이름, 출금 금액
            if(dl.GetBalance() == 50000) { //잔액이 5만원이면
                System.out.println(getName() + ", 출금 = " + money + ", 통장 잔액 = " + dl.GetBalance() + "  -> 출금을 하지 못함.");
                System.out.println("** 최종 통장 잔액 : " + dl.GetBalance() + " **");
                break; //쓰레드 종료
            }
        }
    }
}

public class addition{
    public static void main(String[] args) {
        Dollar DL = new Dollar(); //Dollar 객체 생성
        thread1 t1 = new thread1("쓰레드2",DL,5000); //첫 번째 쓰레드 객체 생성
        thread1 t2 = new thread1("쓰레드1",DL,10000); //두 번째 쓰레드 객체 생성

        System.out.println("[  쓰레드 입/출금 시스템    ]");
        t1.start(); //쓰레드 시작
        t2.start();
    }
}