package com.zachgoshen.workouttracker.data;

public interface DataConverter<Model, Data> {

	public Data convertFromModel(Model model);
	
	public Model convertFromData(Data data);
	
}
