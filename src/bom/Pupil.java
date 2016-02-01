package bom;

public class Pupil {
	
	Integer id;
	
	String name;
	
	String surname;
	
	Form form;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	
	public String toString() {
		return form.getName() + " " + getName() + " " + getSurname();
	}

}
