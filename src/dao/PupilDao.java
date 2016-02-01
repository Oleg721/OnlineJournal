package dao;
import java.util.List;

import bom.Pupil;

public interface PupilDao {
	
	public List<Pupil> getAllPupils();

	public List<Pupil> getPupilByForm(Integer id);
	
	public void insertPupil(Pupil pupil);
	
	public void insertPupil(String name, String sername, Integer classID);

}