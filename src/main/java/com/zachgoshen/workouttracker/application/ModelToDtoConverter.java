package com.zachgoshen.workouttracker.application;

public interface ModelToDtoConverter<Model, Dto> {

	public Dto convertFromModel(Model model);

}
