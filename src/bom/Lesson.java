package bom;
import java.util.Date;

public class Lesson {
	
	private Integer id;
	
	private String theme;
	
	private Form form;
	
	private Date lessonDate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public Date getLessonDate() {
		return lessonDate;
	}

	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	
	@Override
	public String toString(){
		return form +" "+ lessonDate +" "+ theme;
	}
}
