package bom;

public class Mark {
	
	Pupil pupil;
	
	Lesson lesson;
	
	Integer mark;

	public Pupil getPupil() {
		return pupil;
	}

	public void setPupil(Pupil pupil) {
		this.pupil = pupil;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString(){
		return pupil.getName()+" "+pupil.getSurname()+" "+lesson.getLessonDate()+" "+lesson.getTheme()+" "+mark;
	}

}
