INSERT INTO workout_tracker.workout_plan (id, name) VALUES (1, 'Push Day');

INSERT INTO workout_tracker.exercise (id, name) VALUES (1, 'Dumbbell Bench Press');
INSERT INTO workout_tracker.exercise (id, name) VALUES (2, 'Seated Arnold Press');
INSERT INTO workout_tracker.exercise (id, name) VALUES (3, 'Cable Chest Fly');
INSERT INTO workout_tracker.exercise (id, name) VALUES (4, 'Tricep Rope Pull Down');
INSERT INTO workout_tracker.exercise (id, name) VALUES (5, 'Seated Dumbbell Lateral Raise');

INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (1, 1, 1, 1, 8, 10, 120);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (2, 2, 1, 1, 8, 10, 120);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (3, 3, 1, 1, 8, 10, 120);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (4, 4, 1, 2, 8, 10, 90);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (5, 5, 1, 2, 8, 10, 90);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (6, 6, 1, 2, 8, 10, 90);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (7, 7, 1, 3, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (8, 8, 1, 3, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (9, 9, 1, 3, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (10, 10, 1, 3, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (11, 11, 1, 4, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (12, 12, 1, 4, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (13, 13, 1, 4, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (14, 14, 1, 4, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (15, 15, 1, 5, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (16, 16, 1, 5, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (17, 17, 1, 5, 8, 10, 60);
INSERT INTO workout_tracker.workout_plan_item (id, index, workout_plan_id, exercise_id, rep_range_min, rep_range_max, seconds_to_rest) VALUES (18, 18, 1, 5, 8, 10, 60);

INSERT INTO workout_tracker.workout_session (id, workout_plan_id, time_completed) VALUES (1, 1, CURRENT_TIME);

INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (1, 1, 1, 10, 90);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (2, 1, 2, 10, 90);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (3, 1, 3, 10, 90);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (4, 1, 4, 10, 35);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (5, 1, 5, 10, 35);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (6, 1, 6, 10, 35);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (7, 1, 7, 10, 33);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (8, 1, 8, 10, 33);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (9, 1, 9, 10, 33);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (10, 1, 10, 10, 33);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (11, 1, 11, 10, 50);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (12, 1, 12, 10, 50);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (13, 1, 13, 10, 50);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (14, 1, 14, 10, 50);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (15, 1, 15, 10, 20);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (16, 1, 16, 10, 20);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (17, 1, 17, 10, 20);
INSERT INTO workout_tracker.workout_session_item (id, workout_session_id, workout_plan_item_id, reps_completed, weight_used) VALUES (18, 1, 18, 10, 20);