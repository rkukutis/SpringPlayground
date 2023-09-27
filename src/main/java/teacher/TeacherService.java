package teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TeacherService {
	private TeacherRepository teacherRepository;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

}
