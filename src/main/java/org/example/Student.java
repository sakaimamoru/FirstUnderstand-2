package org.example;

public class Student {

  String name;
  int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;

  }

  @Override
  public String toString() {
    return name + ":" + score+"点";
  }
}