package com.jt.sms.service;

import com.jt.sms.model.Teacher;

public interface ITeacherService {
    Teacher saveTeacher(Teacher newTeacher);
    Teacher updatedTeacherById(int id, Teacher toBeUpdatedTeacher);

}
