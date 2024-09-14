package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = new ArrayList<>();
    boolean running = true;
    //数値の選択
    while (running) {
      System.out.println("1.学生の追加");
      System.out.println("2.学生の削除");
      System.out.println("3.点数を更新");
      System.out.println("4.平均点を計算");
      System.out.println("5.全学生の情報を表示");
      System.out.println("6.終了");
      System.out.println("選択してください");
      String choice = scanner.next();
      scanner.nextLine();

      switch (choice) {
        case "1"://学生の追加
          System.out.println("学生の名前を入力してください");
          String nameToAdd = scanner.nextLine();
          System.out.println(nameToAdd + "の点数を入力してください");
          int scoreToAdd = scanner.nextInt();
          students.add(new Student(nameToAdd, scoreToAdd));
          System.out.println(nameToAdd + "が追加されました");
          break;

        case "2"://学生の削除
          System.out.println("削除する学生の名前を入力してください");
          String nameRemove = scanner.nextLine();
          boolean remove = false;
          for (Student student : students) {
            if (student.name.equals(nameRemove)) {
              students.remove(student);
              remove = true;
              System.out.println(nameRemove + "を削除しました");
            }
          }
          if (!remove) {
            System.out.println("該当する学生は居ません");
          }
          break;

        case "3"://点数の更新
          System.out.println("点数を更新する学生の名前を入力してください");
          String scoreUpdate = scanner.nextLine();

          //対象の学生を探す
          Student studentToUpdate = null;
          for (Student student : students) {
            if (student.name.equals(scoreUpdate)) {
              studentToUpdate = student;
              System.out.println("該当する学生が存在します");
              break;
            } else {
              System.out.println("該当する学生が存在しません");
              break;
            }
          }
          //該当学生が見つかった場合
          if (studentToUpdate != null) {
            System.out.println("更新する点数を入力してください");
            String preNewScore = scanner.next();
            boolean result = true;
            for (int i = 0; i < preNewScore.length(); i++) {
              if (!Character.isDigit(preNewScore.charAt(i))) {
                result = false;
                System.out.println("数字を入力してください");
                break;
              }
            }
            if (result) {
              int newScore = Integer.parseInt(preNewScore);
              studentToUpdate.score = newScore;
              System.out.println(studentToUpdate.name + "の点数が" + newScore + "に更新されました");
            } else {
              System.out.println("無効な入力です");
            }
          }
          break;

        case "4"://平均点を計算
          if (students.isEmpty()) {
            System.out.println("学生の情報がありません");
            break;
          } else {
            double average = students.stream()
                .mapToInt(student -> student.score)
                .average()
                .orElse(0.0);
            System.out.println("平均点:" + average + "点");
          }
          break;

        case "5"://全学生の情報を表示
          if (students.isEmpty()) {
            System.out.println("学生の情報がありません");
            break;
          } else {
            System.out.println("学生一覧:");
            students.forEach(System.out::println);
          }
          break;

        case "6"://終了
          running = false;
          System.out.println("プログラムを終了します");
          break;
        default:
          System.out.println("有効でない数値です");
          break;

      }
    }
  }
}
