package com.sms.service;

import com.sms.data.FileHandler;
import com.sms.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = FileHandler.loadStudents();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        saveChanges();
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                saveChanges();
                break;
            }
        }
    }

    public void deleteStudent(String studentId) {
        students.removeIf(s -> s.getId().equals(studentId));
        saveChanges();
    }

    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return students;
        }
        String lowerKeyword = keyword.toLowerCase();
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(lowerKeyword) ||
                             s.getId().toLowerCase().contains(lowerKeyword) ||
                             s.getCourse().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public Student getStudentById(String id) {
        Optional<Student> student = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        return student.orElse(null);
    }

    public int getTotalStudents() {
        return students.size();
    }

    public double getAverageMarks() {
        if (students.isEmpty()) return 0.0;
        double total = students.stream().mapToDouble(Student::getMarks).sum();
        return total / students.size();
    }

    public Student getHighestScorer() {
        if (students.isEmpty()) return null;
        Student highest = students.get(0);
        for (Student s : students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }
        return highest;
    }

    public Student getLowestScorer() {
        if (students.isEmpty()) return null;
        Student lowest = students.get(0);
        for (Student s : students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }
        return lowest;
    }

    private void saveChanges() {
        FileHandler.saveStudents(students);
    }
}
