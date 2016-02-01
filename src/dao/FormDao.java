package dao;
import java.util.List;

import bom.Form;

public interface FormDao {

	public List<Form> getAllForms();

	Integer insertForms(String form);
	
	public void insertForms(Form form);
	
}
