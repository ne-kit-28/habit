package y_lab.out.repositories;

import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Progress;
import y_lab.domain.repositories.ProgressRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProgressRepositoryImpl implements ProgressRepository {
    HashMap<Long, Progress> progresses = new HashMap<>();
    Long idGenerated = 0L;

    /**
     * @param progress
     */
    @Override
    public void save(Progress progress) {
        progress.setId(idGenerated);
        progresses.put(idGenerated, progress);
        ++idGenerated;
    }

    /**
     * @param habitId
     */
    @Override
    public void deleteAllByHabitId(Long habitId) {
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getHabit().getId().equals(habitId))
                progresses.remove(entry.getKey());
        }
    }

    /**
     * @param userId
     */
    @Override
    public void deleteAllByUserId(Long userId) {
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getUser().getId().equals(userId))
                progresses.remove(entry.getKey());
        }
    }

    /**
     * @param progressId
     * @return
     */
    @Override
    public Optional<Progress> findById(Long progressId) {
        return Optional.of(progresses.get(progressId));
    }

    /**
     * @param habitId
     * @return
     */
    @Override
    public Optional<ArrayList<Progress>> findByHabitId(Long habitId) {
        ArrayList<Progress> arrayList = new ArrayList<>();
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getHabit().getId().equals(habitId))
                arrayList.add(entry.getValue());
        }
        return Optional.of(arrayList);
    }
}
