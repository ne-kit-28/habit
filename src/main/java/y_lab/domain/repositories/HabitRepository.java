package y_lab.domain.repositories;

import y_lab.domain.entities.Habit;
import y_lab.domain.entities.User;

import java.util.ArrayList;
import java.util.Optional;

public interface HabitRepository {
    Optional<Habit> findById(Long id);
    Optional<Habit> findByName(String name);
    void save(Habit habit);
    void delete(Long id);
    void deleteAllByUserId(Long userId);
    Optional<ArrayList<Habit>> findHabitsByUserId(Long userId);
}
