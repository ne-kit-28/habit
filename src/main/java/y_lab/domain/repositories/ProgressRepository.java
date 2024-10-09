package y_lab.domain.repositories;

import y_lab.domain.entities.Progress;

import java.util.ArrayList;
import java.util.Optional;

public interface ProgressRepository {
    void save(Progress progress);
    void deleteAllByHabitId(Long habitId);
    void deleteAllByUserId(Long userId);
    Optional<Progress> findById(Long progressId);
    Optional<ArrayList<Progress>> findByHabitId(Long habitId);
}
